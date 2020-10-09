/**
 * 
 */
package com.teksystems.salestaxexercise;

import java.util.LinkedHashMap;

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
	public void add(SellableItem sellableItem);
	
	/**
	 * Remove an item from the basket
	 * 
	 * @param item the priced item to remove
	 */
	public void remove(SellableItem sellableItem);
	
	
	/**
	 * Get an ordered map of all the items in the basket, with a count
	 * for each item
	 * 
	 * @return a collection priced items
	 */
	public LinkedHashMap<SellableItem,Integer> getAllItemsWithCount();

}
