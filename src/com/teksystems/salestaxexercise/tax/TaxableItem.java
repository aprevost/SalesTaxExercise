/**
 * 
 */
package com.teksystems.salestaxexercise.tax;

import java.util.LinkedHashMap;

import org.joda.money.Money;

import com.teksystems.salestaxexercise.SellableItem;

/**
 * Interface specific to a single, unitary item that is taxable.
 * 
 * Contains methods which will not generally apply to collections of taxable items in the
 * same way that the more generic methods in {@link Taxable} do.
 * 
 * @author Andrew
 *
 */
public interface TaxableItem extends Taxable {

	/**
	 * @return the SellableItem object associated with this TaxableItem object
	 */
	public SellableItem getSellableItem();
	
	/**
	 * You should not be able to directly add a tax amount to a taxable
	 * collection, their tax amounts should be made up of the tax amounts
	 * of the taxable items inside them
	 * 
	 * @param tax the tax that is being applied to the item
	 * @param amount the amount of that tax for this item
	 */
	public void addTaxAmount(Tax tax, Money amount);
	
	/**
	 * Add an ordered set of taxes and amounts
	 * 
	 * @param taxAmounts the amounts for each tax
	 */
	public void addTaxAmounts(LinkedHashMap<Tax,Money> taxAmounts);
	
}
