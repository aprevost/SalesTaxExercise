/**
 * 
 */
package com.teksystems.salestaxexercise.tax.canada;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.HashSet;

import com.teksystems.salestaxexercise.SellableItem;
import com.teksystems.salestaxexercise.helpers.MoneyRoundingRule;
import com.teksystems.salestaxexercise.helpers.RegionHelper;
import com.teksystems.salestaxexercise.tax.AbstractTaxJurisdiction;
import com.teksystems.salestaxexercise.tax.ImportSalesTax;
import com.teksystems.salestaxexercise.tax.SalesTax;
import com.teksystems.salestaxexercise.tax.TaxableCategory;

/**
 * Singleton class that represents the Canadian federal tax jurisdiction
 * 
 * NOTE: in a real system, there likely wouldn't be a need for this class, or
 * at least for all of the constants inside it to be constants. All of this
 * country-specific info could be loaded from a data source and used to create
 * a country-specific instance of a more generic concrete class
 * 
 * @author Andrew
 */
public class CanadianTaxJurisdiction extends AbstractTaxJurisdiction {
	
	/**
	 * Canadian taxes are always rounded up
	 */
	public static final RoundingMode ROUNDING_MODE = RoundingMode.UP;

	/**
	 * Canadian taxes are always rounded up to the nearest multiple of 5 cents
	 */
	public static final MoneyRoundingRule ROUNDING_RULE = new MoneyRoundingRule(
			new BigDecimal("0.05"),
			CanadianTaxJurisdiction.ROUNDING_MODE
	);
	
	//Make sure the call to the constructor occurs below the constant
	//declarations above, because those constants are used in the constructor
	private static CanadianTaxJurisdiction instance = new CanadianTaxJurisdiction();
	
	public static CanadianTaxJurisdiction getInstance() {
		return instance;
	}
	
	private CanadianTaxJurisdiction() {
		super(RegionHelper.CANADA);
		
		//Canada has a 5% import duty on imported goods, with no exemptions,
		//applied as a sales tax
		importSalesTax = new ImportSalesTax(
				this,
				"Import Duty",
				new BigDecimal("5"),
				null,
				CanadianTaxJurisdiction.ROUNDING_MODE,
				CanadianTaxJurisdiction.ROUNDING_RULE
		);
		addTax(importSalesTax);
		//Canada has a 10% sales tax, with 3 exempted categories
		salesTax = new SalesTax(
				this,
				"Sales Tax",
				new BigDecimal("10"),
				new HashSet<TaxableCategory>(Arrays.asList(
						TaxableCategory.BOOK,
						TaxableCategory.FOOD,
						TaxableCategory.MEDICAL_PRODUCT
				)),
				CanadianTaxJurisdiction.ROUNDING_MODE,
				CanadianTaxJurisdiction.ROUNDING_RULE
		);
		addTax(salesTax);
	}

	private SalesTax salesTax;
	/**
	 * Getter here exclusively so this tax object can be used for unit testing
	 * 
	 * @return the Canadian sales tax
	 */
	public SalesTax getSalesTax() {
		return salesTax;
	}

	private ImportSalesTax importSalesTax;
	/**
	 * Getter here exclusively so this tax object can be used for unit testing
	 * 
	 * @return the Canadian import duty
	 */
	public ImportSalesTax getImportSalesTax() {
		return importSalesTax;
	}

	@Override
	public TaxableCategory getTaxableCategoryFor(SellableItem sellableItem) {
		//This is a complete hack for the purposes of this exercise
		//In the real world the taxable category mappings would be retrieved
		//from a data source
		switch (sellableItem.getName()) {
			case "book":
				return TaxableCategory.BOOK;
			case "packet of headache pills":
				return TaxableCategory.MEDICAL_PRODUCT;
			case "box of chocolates":
			case "chocolate bar":
				return TaxableCategory.FOOD;
			default:
				return TaxableCategory.OTHER;
		}
	}
}
