/**
 * 
 */
package com.teksystems.salestaxexercise;

import java.util.LinkedHashMap;

import org.joda.money.Money;

import com.teksystems.salestaxexercise.tax.Tax;

/**
 * Interface for a specific product or service which is taxable.
 * 
 * As opposed to the more generic {@link Taxable} interface which can apply
 * to collections of taxable items as well.
 * 
 * @author Andrew
 *
 */
public interface TaxableItem extends Taxable {

	/**
	 * Get the sellable item associated with this taxable item.
	 * 
	 * This interface does not extend {@link SellableItem}, but instead include
	 * this getter for an underlying {@link SellableItem}.
	 * 
	 * Chose to implement this using composition, rather than inheritance, as
	 * the Liskov substitution principle could not necessarily be maintained
	 * in all instances if inheritance was used. Something that's true about a
	 * priced item in general is not guaranteed to always be true once the
	 * taxes for a specific tax jurisdiction are added to that item.
	 * 
	 * @return The {@link SellableItem} object to which this taxable item adds taxes.
	 */
	public SellableItem getSellableItem();
	
	/**
	 * Add a specific tax and the amount of that tax for this specific item.
	 * 
	 * This is here, and not in the more generic {@link Taxable} interface like
	 * the corresponding getter {@link Taxable#getTaxAmounts()}, because you
	 * should not be able to directly add a tax amount to a taxable collection
	 * - the tax amounts for a collection of taxable items should be the sum of
	 * the tax amounts for the taxable items inside that collection.
	 * 
	 * @param tax the tax that is being applied to the item
	 * @param amount the amount of that tax for this item
	 */
	public void addTaxAmount(Tax tax, Money amount);
	
	/**
	 * Add an ordered set of taxes and amounts.
	 * 
	 * The SOLID design principle that interfaces should wherever possible
	 * depend on other interfaces, not concrete classes, would suggest this
	 * method should take a Map as its parameter, not a LinkedHashMap.
	 * 
	 * However, because some taxes may be assessed on top of other taxes,
	 * maintaining the order of taxes may be very important in many
	 * implementations. I decided the benefits of forcing every implementer to
	 * implement with a structure that maintains order outweighed the potential
	 * loss in flexibility.
	 * 
	 * @param taxAmounts an ordered map of taxes and their respective amounts for this taxable item
	 */
	public void addTaxAmounts(LinkedHashMap<Tax,Money> taxAmounts);
	
}
