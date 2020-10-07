/**
 * 
 */
package com.teksystems.salestaxexercise.tax;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashSet;

import org.joda.money.Money;

import com.ibm.icu.util.Region;
import com.teksystems.salestaxexercise.helpers.MoneyRoundingRule;
import com.teksystems.salestaxexercise.helpers.RegionHelper;

/**
 * @author Andrew
 *
 */
public class CanadianSalesTax implements Tax {
	
	private Region region;
	
	@Override
	public Region getRegion() {
		return region;
	}
	
	private BigDecimal percentageMultiplier = new BigDecimal("0.1");

	/**
	 * @return the percentage of the tax (e.g. 10.5 for 10.5%, NOT 0.105)
	 */
	public BigDecimal getPercentage() {
		return percentageMultiplier.multiply(new BigDecimal(10));
	}
	
	private RoundingMode roundingMode = RoundingMode.UP;
	
	public RoundingMode getRoundingMode() {
		return roundingMode;
	}
	
	private MoneyRoundingRule customRoundingRule = new MoneyRoundingRule(new BigDecimal("0.05"), roundingMode);
	
	public MoneyRoundingRule getCustomRoundingRule() {
		return customRoundingRule;
	}
		
	protected Money calculateSalesTax(Money preTaxPrice) {
		//First do the simple tax percentage multiplication, with default rounding mode
		Money unroundedTaxAmount = preTaxPrice.multipliedBy(percentageMultiplier, getRoundingMode());
		
		//Now check if there's a custom rounding rule that also needs to be applied
		MoneyRoundingRule customRoundingRule = getCustomRoundingRule();
		if (customRoundingRule == null) {
			return unroundedTaxAmount;
		}
		return customRoundingRule.round(unroundedTaxAmount);
	}
	
	private HashSet<TaxableCategory> exemptedCategories = new HashSet<TaxableCategory>();
	
	public HashSet<TaxableCategory> getExemptedCategories() {
		return exemptedCategories;
	}

	@Override
	public Money getTaxAmountFor(TaxableItem taxableItem) {
		
		if (taxableItem == null) {
			return null;
		}
		
		Money preTaxPrice = taxableItem.getPrice();
		if (preTaxPrice == null) {
			return null;
		}
		
		//Use preTaxPrice.withAmount rather than just creating a new Money
		//object form scratch so that currency of the pre-tax price is maintained
		//for the tax amount of 0 as well
		Money zeroTax = preTaxPrice.withAmount(BigDecimal.ZERO);
		
		if (preTaxPrice.isZero()) {
			return zeroTax;
		}
		
		Money taxAmount = calculateSalesTax(preTaxPrice);
		
		TaxableCategory category = taxableItem.getTaxableCategory();
		if (category == null || !getExemptedCategories().contains(category)) {
			return taxAmount;
		}
		
		return zeroTax;
	}
	
	public CanadianSalesTax(BigDecimal percentage) {
		this.region = RegionHelper.CANADA;
		//Multiply by 0.01 here, rather than dividing by 100, so that scale of multiplier is maintained correctly
		this.percentageMultiplier = percentage.multiply(new BigDecimal("0.01"));
		exemptedCategories.add(TaxableCategory.BOOK);
		exemptedCategories.add(TaxableCategory.FOOD);
		exemptedCategories.add(TaxableCategory.MEDICAL_PRODUCT);
	}


}
