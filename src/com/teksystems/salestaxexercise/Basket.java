/**
 * 
 */
package com.teksystems.salestaxexercise;

import java.util.Collection;
import java.util.LinkedHashMap;

/**
 * Basic interface for any collection of items with prices attached to them.
 * <p>
 * TODO: should this interface extend Collection?
 * </p>
 * @author Andrew
 *
 */
public interface Basket extends Sellable {
	
	/**
	 * Add an item to the basket.
	 * 
	 * @param sellableItem the priced item to add
	 */
	public void add(SellableItem sellableItem);
	
	/**
	 * Remove an item from the basket.
	 * 
	 * @param sellableItem the priced item to remove
	 */
	public void remove(SellableItem sellableItem);
	
	/**
	 * Add a collection of items to the basket.
	 * 
	 * @param sellableItems the priced items to add
	 */
	public void addAll(Collection<SellableItem> sellableItems);
	
	/**
	 * Remove a collection of items to the basket.
	 * 
	 * @param sellableItems the priced items to remove
	 */
	public void removeAll(Collection<SellableItem> sellableItems);
	
	/**
	 * Remove all items from the basket.
	 */
	public void clear();
	
	/**
	 * Get an ordered map of all the items in the basket, with a count
	 * for each item.
	 * 
	 * The counts represent the number of units of each item that have been
	 * added to the basket, e.g. 5 chocolate bars, 2 bottles of perfume, etc.
	 * 
	 * Technically, SOLID design principles would suggest this method should
	 * return just a Map, as wherever possible we should depend on interfaces,
	 * not concrete classes. However, as I anticipate that maintaining the
	 * order of items in a basket would be important for most implementations,
	 * I made a decision that the advantage of making sure all implementers
	 * are forced to maintain order outweighs the loss of flexibility.
	 * 
	 * @return An ordered map of priced items to the count of that item.
	 */
	public LinkedHashMap<SellableItem,Integer> getAllItemsWithCount();

}
