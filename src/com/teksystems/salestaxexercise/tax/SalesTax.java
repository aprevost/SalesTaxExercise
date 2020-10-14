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
 * Generic implementation of a simple sales tax.
 * 
 * Should be able to be used for most simple sales taxes in most jurisdictions.
 * <p>
 * TODO: the most basic sales tax would have no exempted categories. Remove the
 * exemptions from this class, and make a separate abstract class (and separate
 * interface) that adds exemptions to it.
 * </p>
 * @author Andrew
 *
 */
public class SalesTax implements Tax {
	
	/**
	 * Constructor for a generic sales tax.
	 * <p>
	 * TODO: should implement the Builder design pattern on this class to
	 * simplify construction
	 * </p><p>
	 * The percentage parameter should represent the actual percent number for
	 * this tax, NOT the multiplier corresponding to that percentage.
	 * </p><p>
	 * e.g. for a 10.5% sales tax, percentage should be 10.5, NOT the equivalent multiplier 0.105
	 * </p>
	 * @param taxJurisdiction the tax jurisdiction in which this tax applies, required
	 * @param name the name of of this tax, required and must be non-empty
	 * @param percentage the percentage for this sales tax (not divided by 100), required and must be non-zero
	 * @param exemptedCategories the set of taxable categories that are exempt from this tax, optional
	 * @param roundingMode the rounding mode for this tax, optional, defaults to {@link RoundingMode#HALF_UP}
	 * @param customRoundingRule any custom rounding rule that should be applied to this tax, optional
	 * 
	 * @throws IllegalArgumentException if any of the required parameters are null or empty/zero.
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
	
	/**
	 * {@inheritDoc}
	 * 
	 * This implementation will not allow this method to ever return null.
	 * 
	 * @return {@inheritDoc} Never null for this implementation.
	 */
	@Override
	public String getName() {
		return name;
	}
	
	private TaxJurisdiction taxJurisdiction;
	
	/**
	 * {@inheritDoc}
	 * 
	 * This implementation will not allow this method to ever return null.
	 * 
	 * @return {@inheritDoc} Never null for this implementation.
	 */
	@Override
	public TaxJurisdiction getTaxJurisdiction() {
		return taxJurisdiction;
	}
	
	private BigDecimal percentageMultiplier;

	/**
	 * Get the percentage of this sales tax.
	 * 
	 * This will return the actual percent number for this tax, NOT the
	 * multiplier corresponding to that percentage. (e.g. 10.5 for 10.5%, NOT
	 * the equivalent multiplier 0.105)
	 * 
	 * @return The percentage of the tax.
	 */
	public BigDecimal getPercentage() {
		return percentageMultiplier.multiply(new BigDecimal(10));
	}
	
	private RoundingMode roundingMode = RoundingMode.HALF_UP;
	
	/**
	 * Get the rounding mode for this sales tax.
	 * 
	 * This implementation will not allow this method to ever return null,
	 * it will default to {@link RoundingMode#HALF_UP} if no rounding mode was
	 * specified when this object was constructed.
	 * 
	 * @return The rounding mode for this sales tax. Never null.
	 */
	public RoundingMode getRoundingMode() {
		return roundingMode;
	}
	
	private MoneyRoundingRule customRoundingRule;
	
	/**
	 * Get the custom rounding rule for this sales tax, if there is one.
	 * 
	 * Will return null if there is no custom rounding rule for this tax.
	 * 
	 * @return The custom rounding rule for this sales tax, null if there isn't one.
	 */
	public MoneyRoundingRule getCustomRoundingRule() {
		return customRoundingRule;
	}
		
	/**
	 * Returns the amount of this tax for the specified price.
	 * 
	 * Multiplies by the tax percentage, applying this tax's
	 * {@link #getRoundingMode()}, and then also applies its
	 * {@link #getCustomRoundingRule()} if there is one.
	 * <p>
	 * TODO: when exemptions are moved out in to a separate class that extends
	 * this one, it should be possible to make this method private. Extending
	 * classes can just call the public {@link #getTaxAmountFor(SellableItem)}
	 * method instead, will never need to call this method directly.
	 * </p>
	 * @param preTaxPrice the price before this tax is applied
	 * @return The amount of tax for the input amount. Never null.
	 */
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
	
	/**
	 * Get the categories of taxable item that are exempt from this tax.
	 * <p>
	 * TODO: this should be moved out to a separate class that extends
	 * this one.
	 * </p>
	 * @return The categories of taxable item that are exempt from this tax.
	 */
	public HashSet<TaxableCategory> getExemptedCategories() {
		return exemptedCategories;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * This implementation will not allow this method to ever return null.
	 * <p>
	 * TODO: the exempted categories logic here should be moved out to a
	 * separate class that extends this one.
	 * </p>
	 * @return {@inheritDoc} Never null for this implementation.
	 * 
	 * @throws IllegalArgumentException if sellableItem or its price are null.
	 */
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
	
	/**
	 * Get a printable string that identifies this this tax.
	 * 
	 * This implementation just returns the {@link #getName()} value for this object.
	 * 
	 * @return A printable string that identifies this tax.
	 */
	@Override
	public String toString() {
		return getName();
	}
	
	/**
	 * Get the primary key identifying this tax.
	 * 
	 * This implementation returns the {@link #toString()} value for this
	 * object appended to the toString() value of the underlying
	 * {@link TaxJurisdiction#getRegion()}. This is a hack, in the real world
	 * each tax would likely have a unique key defined in the data source.
	 * 
	 * @return A string that uniquely identifies this tax.
	 */
	public String getPrimaryKey() {
		return this.toString()
				+ taxJurisdiction.getRegion().toString();
	}
	
	/**
	 * Check if another object is equal to this one.
	 * 
	 * This implementation requires the other object to also be an instance of
	 * SalesTax, and for its {@link #getPrimaryKey()} method to
	 * return a value that equals this object's getPrimaryKey().
	 * 
	 * @param obj the object to compare to this one
	 */
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
	
	/**
	 * Get a uniquely identifying hash code for this object.
	 * 
	 * This implementation simply returns the hashCode of its
	 * {@link #getPrimaryKey()} string.
	 * 
	 * @return A uniquely identifying hash code for this object.
	 */
    @Override
	public int hashCode() {
    	return getPrimaryKey().hashCode();
    }

	
}
