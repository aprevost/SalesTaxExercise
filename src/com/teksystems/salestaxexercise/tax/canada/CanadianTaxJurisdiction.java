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
	
	private static CanadianTaxJurisdiction instance = new CanadianTaxJurisdiction();
	
	public static CanadianTaxJurisdiction getInstance() {
		return instance;
	}
	
	private CanadianTaxJurisdiction() {
		super(RegionHelper.CANADA);
		addTax(SALES_TAX);
		addTax(IMPORT_DUTY);
	}

	/**
	 * Canadian taxes are always rounded up
	 */
	public static final RoundingMode ROUNDING_MODE = RoundingMode.UP;

	/**
	 * Canadian taxes are always rounded up to the nearest multiple of 5 cents
	 */
	public static final MoneyRoundingRule ROUNDING_RULE = new MoneyRoundingRule(
			new BigDecimal("0.05"),
			ROUNDING_MODE
	);
	
	/**
	 * Canada has a 10% sales tax, with 3 exempted categories
	 */
	public static final SalesTax SALES_TAX = new SalesTax(
			CanadianTaxJurisdiction.getInstance(),
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

	/**
	 * Canada has a 5% import duty on imported goods, with no exemptions,
	 * applied as a sales tax
	 */
	public static final ImportSalesTax IMPORT_DUTY = new ImportSalesTax(
			CanadianTaxJurisdiction.getInstance(),
			"Import Duty",
			new BigDecimal("5"),
			null,
			CanadianTaxJurisdiction.ROUNDING_MODE,
			CanadianTaxJurisdiction.ROUNDING_RULE
	);


	@Override
	public TaxableCategory getTaxableCategoryFor(SellableItem sellableItem) {
		//This is a complete hack for the purposes of this exercise
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
