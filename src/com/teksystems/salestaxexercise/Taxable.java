/**
 * 
 */
package com.teksystems.salestaxexercise;

import java.util.LinkedHashMap;

import org.joda.money.Money;

import com.teksystems.salestaxexercise.tax.Tax;

/**
 * Defines the interface for any object that has taxes associated with it
 * 
 * Not just individual items or goods that are taxable, but also collections
 * of taxable goods
 * 
 * @author Andrew
 *
 */
public interface Taxable {
	
	/**
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
	 * @return the list of taxes and amounts for this taxable object
	 */
	public LinkedHashMap<Tax,Money> getTaxAmounts();
	
	public Money getTotalTax();
		
	public Money getTotalPriceWithTax();

}
