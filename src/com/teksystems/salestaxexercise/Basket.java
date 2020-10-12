/**
 * 
 */
package com.teksystems.salestaxexercise;

import java.util.Collection;
import java.util.LinkedHashMap;

/**
 * Basic interface for any collection of items with prices attached to them.
 * 
 * TODO: should this interface extend Collection?
 * 
 * @author Andrew
 *
 */
public interface Basket extends Sellable {
	
	/**
	 * Add an item to the basket
	 * 
	 * @param sellableItem the priced item to add
	 */
	public void add(SellableItem sellableItem);
	
	/**
	 * Remove an item from the basket
	 * 
	 * @param sellableItem the priced item to remove
	 */
	public void remove(SellableItem sellableItem);
	
	/**
	 * Add a collection of items to the basket
	 * 
	 * @param sellableItems the priced items to add
	 */
	public void addAll(Collection<SellableItem> sellableItems);
	
	/**
	 * Remove a collection of items to the basket
	 * 
	 * @param sellableItems the priced items to remove
	 */
	public void removeAll(Collection<SellableItem> sellableItems);
	
	/**
	 * Remove all items from the basket
	 */
	public void clear();
	
	/**
	 * Get an ordered map of all the items in the basket, with a count
	 * for each item
	 * 
	 * @return a collection priced items
	 */
	public LinkedHashMap<SellableItem,Integer> getAllItemsWithCount();

}
