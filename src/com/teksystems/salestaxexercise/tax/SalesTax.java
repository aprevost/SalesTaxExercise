/**
 * 
 */
package com.teksystems.salestaxexercise.tax;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashSet;
import java.util.Set;

import org.joda.money.Money;

import com.teksystems.salestaxexercise.SellableItem;
import com.teksystems.salestaxexercise.helpers.MoneyRoundingRule;

/**
 * Generic sales tax class.
 * 
 * Should be able to be used for most simple sales taxes in most jurisdictions.
 * 
 * TODO: the most basic sales tax would have no exempted categories. Remove the
 * exemptions from this class, and make a separate abstract class (and separate
 * interface) that adds exemptions to it.
 * 
 * @author Andrew
 *
 */
public class SalesTax implements Tax {
	
	/**
	 * Constructor for a generic sales tax
	 * 
	 * TODO: should implement the Builder design pattern on this class to
	 * simplify construction
	 * 
	 * The percentage parameter should represent the actual percent number for
	 * this tax, NOT the multiplier corresponding to that percentage.
	 * 
	 * e.g. for a 10.5% sales tax, percentage should be 10.5, NOT the equivalent multiplier 0.105
	 * 
	 * @param taxJurisdiction the tax jurisdiction in which this tax applies, required
	 * @param name the name of of this tax, required and must be non-empty
	 * @param percentage the percentage for this sales tax (not divided by 100), required and must be non-zero
	 * @param exemptedCategories the set of taxable categories that are exempt from this tax, optional
	 * @param roundingMode the rounding mode for this tax, optional, defaults to {@link java.math.RoundingMode#HALF_UP}
	 * @param customRoundingRule any custom rounding rule that should be applied to this tax, optional
	 */
	public SalesTax(TaxJurisdiction taxJurisdiction, String name, BigDecimal percentage, Set<TaxableCategory> exemptedCategories, RoundingMode roundingMode, MoneyRoundingRule customRoundingRule) {

		//Ensure the required parameters are non-empty before setting them
		if (taxJurisdiction == null) {
			throw new IllegalArgumentException("taxJurisdiction must be specified for sales tax");
		}
		this.taxJurisdiction = taxJurisdiction;

		if (name == null || name.length() == 0) {
			throw new IllegalArgumentException("name cannot be empty for sales tax");
		}
		this.name = name;

		if (percentage == null || percentage.signum() == 0) {
			throw new IllegalArgumentException("percentage must be specified and non-zero for sales tax");
		}
		//Convert the whole number percentage in to the decimal multiplier for that percentage
		//Multiply by 0.01 here, rather than dividing by 100, so that scale of multiplier is maintained correctly
		percentageMultiplier = percentage.multiply(new BigDecimal("0.01"));

		if (exemptedCategories != null) {
			for (TaxableCategory category : exemptedCategories) {
				this.exemptedCategories.add(category);
			}
		}
		
		//We can never allow roundingMode to be set to null
		if (roundingMode != null) {
			this.roundingMode = roundingMode;
		}
		
		//customRoundingRule, on the other hand, can be null
		this.customRoundingRule = customRoundingRule;

	}

	private String name;
	
	@Override
	public String getName() {
		return name;
	}
	
	private TaxJurisdiction taxJurisdiction;
	
	@Override
	public TaxJurisdiction getTaxJurisdiction() {
		return taxJurisdiction;
	}
	
	private BigDecimal percentageMultiplier;

	/**
	 * @return the percentage of the tax (e.g. 10.5 for 10.5%, NOT the equivalent multiplier 0.105)
	 */
	public BigDecimal getPercentage() {
		return percentageMultiplier.multiply(new BigDecimal(10));
	}
	
	private RoundingMode roundingMode = RoundingMode.HALF_UP;
	
	public RoundingMode getRoundingMode() {
		return roundingMode;
	}
	
	private MoneyRoundingRule customRoundingRule;
	
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
	public Money getTaxAmountFor(SellableItem sellableItem) {
		
		if (sellableItem == null) {
			throw new IllegalArgumentException("sellableItem cannot be null");
		}
		
		Money preTaxPrice = sellableItem.getPrice();
		if (preTaxPrice == null) {
			throw new IllegalArgumentException("sellableItem price cannot be null");
		}
		
		//Use preTaxPrice.withAmount rather than just creating a new Money
		//object from scratch so that currency of the pre-tax price is
		//maintained for the tax amount of 0 as well
		Money zeroTax = preTaxPrice.withAmount(0);
		
		//No need to go through all the logic below if the price is zero
		if (preTaxPrice.isZero()) {
			return zeroTax;
		}
		
		//If there are no exempted categories for this sales tax, no need to
		//look up the taxable category of the item, just go ahead and calculate
		HashSet<TaxableCategory> exemptedCategories = getExemptedCategories();
		if (exemptedCategories.isEmpty()) {
			return calculateSalesTax(preTaxPrice);
		}
		
		//Check if this item is in an exempted category
		TaxableCategory taxableCategory = getTaxJurisdiction().getTaxableCategoryFor(sellableItem);
		//Assumption for null check below documented in project Readme 
		// - if no category can be retrieved for an item, or if the category retrieved
		//   is empty, we assume this item is not exempted
		if (taxableCategory == null || !getExemptedCategories().contains(taxableCategory)) {
			return calculateSalesTax(preTaxPrice);
		}
		
		return zeroTax;
	}
	
	@Override
	public String toString() {
		return name;
	}
	
	/**
	 * This is a hack, in the real world each tax would likely have a unique
	 * key defined in the data source
	 * 
	 * @return a string that uniquely identifies this tax
	 */
	public String getPrimaryKey() {
		return this.toString()
				+ taxJurisdiction.getRegion().toString();
	}
	
	@Override
	public boolean equals(Object obj) {
        if (this == obj) {
        	return true;
        }
        if (!(obj instanceof SalesTax)) {
        	return false;
        }

        SalesTax that = (SalesTax) obj;
        return this.getPrimaryKey().equals(that.getPrimaryKey());    
	}
	
    @Override
	public int hashCode() {
    	return getPrimaryKey().hashCode();
    }

	
}
