/**
 * 
 */
package com.teksystems.salestaxexercise;

import java.util.LinkedHashSet;

import org.joda.money.CurrencyUnit;

import com.teksystems.salestaxexercise.tax.TaxJurisdiction;

/**
 * Interface for a taxable collection of sellable items.
 * 
 * @author Andrew
 */
public interface TaxableBasket extends Taxable, Basket {
	
	/**
	 * Get the currency associated with this basket.
	 * <p>
	 * TODO: not sure this getter should be here, as currency is not really
	 * tax-specific. It should really apply to any sellable basket, regardless
	 * of taxes. Maybe this should be moved to the {@link Basket} interface
	 * instead?
	 * </p>
	 * @return The currency for this basket.
	 */
	public CurrencyUnit getCurrencyUnit();
	
	/**
	 * Get the tax jurisdiction associated with this basket.
	 * 
	 * @return The tax jurisdiction for this basket.
	 */
	public LinkedHashSet<TaxJurisdiction> getTaxJurisdictions();

}
