/**
 * 
 */
package com.teksystems.salestaxexercise;

import com.ibm.icu.util.Region;

import org.joda.money.Money;

/**
 * A generic interface for anything that can have a price attached to it
 * 
 * This interface could apply to an individual item that can be sold, e.g. a
 * physical good, or a service. But it could also apply to a collection of
 * such items, like a basket of goods.
 * 
 * @author Andrew
 */
public interface Priceable {

	/**
	 * The same good or service can be sold for different prices in different
	 * countries or states or regions
	 * 
	 * So we require that all prices have a geographical region attached to them
	 * 
	 * @return region in which the sale is taking place
	 */
	public Region getRegionOfSale();
	
	/**
	 * The price of the item in this locale
	 * 
	 * @return the price
	 */
	public Money getPrice();
	
}
