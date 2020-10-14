/**
 * 
 */
package com.teksystems.salestaxexercise.tax;

import java.util.LinkedHashMap;
import java.util.LinkedHashSet;

import org.joda.money.Money;

import com.ibm.icu.util.Region;
import com.teksystems.salestaxexercise.SellableItem;

/**
 * Abstract implementation of the logic that should be the same for every {@link TaxJurisdiction}.
 * 
 * @author Andrew
 */
public abstract class AbstractTaxJurisdiction implements TaxJurisdiction {

	/**
	 * Default constructor for any sub-class of AbstractTaxJurisdiction.
	 * 
	 * We would expect pretty much all sub-classes to implement their own
	 * constructor that wraps this one, so explicitly marking this method as
	 * protected (so it's not documented) rather than public.
	 * 
	 * @param region the geographical region for this tax jurisdiction, required
	 * @throws IllegalArgumentException if region is null.
	 */
	protected AbstractTaxJurisdiction(Region region) {
		if (region == null) {
			throw new IllegalArgumentException("region cannot be null for a tax jurisdiction");
		}
		this.region = region;
	}

	private Region region;
	/**
	 * {@inheritDoc}
	 * 
	 * This implementation will not allow this method to ever return null.
	 * 
	 * @return {@inheritDoc} Never null for this implementation.
	 */
	@Override
	public Region getRegion() {
		return region;
	}

	@Override
	public TaxableCategory getTaxableCategoryFor(SellableItem sellableItem) {
		//This one really always will be specific per tax jurisdiction
		
		//But some tax jurisdictions may not care about taxable categories
		//at all, so don't make this function abstract, just have it return
		//null by default, jurisdictions with categories can override
		return null;
	}
	
	private LinkedHashSet<Tax> taxes = new LinkedHashSet<Tax>();

	/**
	 * {@inheritDoc}
	 * 
	 * This implementation will not allow this method to ever return null.
	 * 
	 * @return {@inheritDoc} Never null for this implementation.
	 */
	@Override
	public LinkedHashSet<Tax> getAllTaxes() {
		return taxes;
	}
	
	/**
	 * Add a tax to this tax jurisdiction.
	 * 
	 * Intended to only be called from the constructors of extending classes.
	 * 
	 * @param tax The tax to add.
	 */
	protected void addTax(Tax tax) {
		taxes.add(tax);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * This implementation will not allow this method to ever return null.
	 * 
	 * @return {@inheritDoc} Never null for this implementation.
	 */
	@Override
	public LinkedHashMap<Tax, Money> getTaxAmountsFor(SellableItem sellableItem) {
		LinkedHashMap<Tax,Money> taxAmountMap = new LinkedHashMap<Tax,Money>();
		for (Tax tax : taxes) {
			taxAmountMap.put(tax, tax.getTaxAmountFor(sellableItem));
		}
		return taxAmountMap;
	}

	/**
	 * Get a printable string that identifies this this tax jurisdiction.
	 * 
	 * This implementation just calls the toString() method on the underlying
	 * {@link #getRegion()} object. Can never be null or empty.
	 * 
	 * @return A printable string that identifies this tax jurisdiction. Never null or empty.
	 */
	@Override
	public String toString() {
		return getRegion().toString();
	}
	
	/**
	 * Get the primary key identifying this tax jurisdiction.
	 * 
	 * This implementation just returns the {@link #toString()} value for this
	 * object. This is a hack, in the real world each jurisdiction would likely
	 * have a unique key defined in the data source.
	 * 
	 * @return A string that uniquely identifies this tax jurisdiction. Never null or empty.
	 */
	public String getPrimaryKey() {
		return this.toString();
	}
	
	/**
	 * Check if another object is equal to this one.
	 * 
	 * This implementation requires the other object to also extend
	 * AbstractTaxJurisdiction, and for its {@link #getPrimaryKey()} method to
	 * return a value that equals this object's getPrimaryKey().
	 * 
	 * @param obj the object to compare to this one
	 */
	@Override
	public boolean equals(Object obj) {
        if (this == obj) {
        	return true;
        }
        if (!(obj instanceof AbstractTaxJurisdiction)) {
        	return false;
        }

        AbstractTaxJurisdiction that = (AbstractTaxJurisdiction) obj;
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
