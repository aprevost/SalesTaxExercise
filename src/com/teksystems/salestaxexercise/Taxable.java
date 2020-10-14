/**
 * 
 */
package com.teksystems.salestaxexercise;

import java.util.LinkedHashMap;

import org.joda.money.Money;

import com.teksystems.salestaxexercise.tax.Tax;

/**
 * Interface for anything that can have taxes associated with it.
 * 
 * Not just individual items or goods that are taxable, but also collections
 * of taxable goods.
 * 
 * @author Andrew
 *
 */
public interface Taxable {
	
	/**
	 * Get an ordered map of all the individual taxes and their amounts for
	 * this taxable object.
	 * <p>
	 * The SOLID design principle that interfaces should wherever possible
	 * depend on other interfaces, not concrete classes, would suggest this
	 * should return a Map, not a LinkedHashMap.
	 * </p><p>
	 * However, because some taxes may be assessed on top of other taxes
	 * (for example, Quebec sales tax is applied on top of the GST in Quebec),
	 * maintaining the order of taxes may be very important in many
	 * implementations. I decided the benefits of forcing every implementer to
	 * implement with a structure that maintains order outweighed the potential
	 * loss in flexibility.
	 * </p>
	 * 
	 * @return An ordered map of taxes and their respective amounts for this taxable object.
	 */
	public LinkedHashMap<Tax,Money> getTaxAmounts();
	
	/**
	 * Get the total amount of tax for this taxable object.
	 * 
	 * This method should always return the sum of all the individual tax
	 * amounts returned by {@link #getTaxAmounts()}
	 * 
	 * @return The total amount of taxes for this taxable object.
	 */
	public Money getTotalTax();
		
	/**
	 * Get the total price for this taxable object, including taxes.
	 * 
	 * @return The total price for this taxable object, including taxes.
	 */
	public Money getTotalPriceWithTax();

}
