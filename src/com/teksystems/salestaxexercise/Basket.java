/**
 * 
 */
package com.teksystems.salestaxexercise;

import java.util.Collection;

/**
 * Basic interface for a collection of items with prices attached to them
 * 
 * @author Andrew
 *
 */
public interface Basket extends Sellable {
	
	/**
	 * Add an item to the basket
	 * 
	 * @param item the priced item to add
	 */
	public void add(Sellable item);
	
	/**
	 * Remove an item from the basket
	 * 
	 * @param item the priced item to remove
	 */
	public void remove(Sellable item);
	
	
	/**
	 * Get a collection of all the priced items in the basket
	 * 
	 * @return a collection priced items
	 */
	public Collection<Sellable> getAllItems();

}
