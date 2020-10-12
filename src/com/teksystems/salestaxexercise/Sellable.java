/**
 * 
 */
package com.teksystems.salestaxexercise;

import com.ibm.icu.util.Region;

import org.joda.money.Money;

/**
 * Generic interface for anything that can be sold for a given price.
 * 
 * This interface could apply to an individual item that can be sold, e.g. a
 * physical good, or a service. But it could also apply to a collection of
 * such items, like a basket of goods.
 * 
 * @author Andrew
 */
public interface Sellable {

	/**
	 * The same good or service can be sold for different prices in different
	 * countries or states or regions. Or might be available for sale in some
	 * regions, but not others.
	 * 
	 * So we require that all sellable items or collections of items have a
	 * geographical region attached to them
	 * 
	 * @return region in which the sale is taking place
	 */
	public Region getRegionOfSale();
	
	/**
	 * The price of the item or collection of items in this region
	 * 
	 * @return the price
	 */
	public Money getPrice();
	
}
