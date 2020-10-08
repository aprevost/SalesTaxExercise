/**
 * 
 */
package com.teksystems.salestaxexercise.tax;

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
	 * Taxable collections generally won't have categories assigned to them,
	 * but most taxable items should
	 * 
	 * TODO: SOLID design principles suggest maybe this shouldn't be here.
	 * If we want to allow simpler implementations not to have a category,
	 * this should be split out in to another interface again.
	 * 
	 * @return the taxable category for this item
	 */
	public TaxableCategory getTaxableCategory();
	
	/**
	 * You should not be able to directly add a tax amount to a taxable
	 * collection, their tax amounts should be made up of the tax amounts
	 * of the taxable items inside them
	 * 
	 * @param tax the tax that is being applied to the item
	 * @param amount the amount of that tax for this item
	 */
	public void addTaxAmount(Tax tax, Money amount);
	
}
