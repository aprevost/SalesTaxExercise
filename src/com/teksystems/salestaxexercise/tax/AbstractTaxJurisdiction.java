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
 * TODO: override equals and hashCode for this class, because these objects
 * will be stored in a HashSet in TaxableBasket implementations.
 * 
 * @author Andrew
 */
public abstract class AbstractTaxJurisdiction implements TaxJurisdiction {

	/**
	 * @param region the geographical region for this tax jurisdiction, required
	 */
	public AbstractTaxJurisdiction(Region region) {
		if (region == null) {
			throw new IllegalArgumentException("region cannot be null for a tax jurisdiction");
		}
		this.region = region;
	}

	private Region region;
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
	@Override
	public LinkedHashSet<Tax> getAllTaxes() {
		return taxes;
	}
	
	/**
	 * Intended to only be called from the constructors of extending classes
	 */
	protected void addTax(Tax tax) {
		taxes.add(tax);
	}

	@Override
	public LinkedHashMap<Tax, Money> getTaxAmountsFor(SellableItem sellableItem) {
		LinkedHashMap<Tax,Money> taxAmountMap = new LinkedHashMap<Tax,Money>();
		for (Tax tax : taxes) {
			taxAmountMap.put(tax, tax.getTaxAmountFor(sellableItem));
		}
		return taxAmountMap;
	}


}
