/**
 * 
 */
package com.teksystems.salestaxexercise;

import java.util.LinkedHashMap;

import org.joda.money.Money;

import com.teksystems.salestaxexercise.tax.Tax;

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
	 * The SOLID design principle that interfaces should wherever possible
	 * depend on other interfaces, not concrete classes, would suggest this
	 * should return a Map, not a LinkedHashMap.
	 * 
	 * However, because some taxes may be assessed on top of other taxes,
	 * maintaining the order of taxes may be very important in many
	 * implementations. I decided the benefits of forcing every implementer to
	 * implement with a structure that maintains order outweighed the potential
	 * loss in flexibility.
	 * 
	 * @param taxAmounts the amounts for each tax
	 */
	public void addTaxAmounts(LinkedHashMap<Tax,Money> taxAmounts);
	
}
