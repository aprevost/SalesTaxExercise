/**
 * 
 */
package com.teksystems.salestaxexercise.tax;

import java.util.Map;

import org.joda.money.Money;

import com.teksystems.salestaxexercise.Priceable;

/**
 * Defines the interface for any object that has taxes associated with it
 * 
 * Not just individual items or goods that are taxable, but also collections
 * of taxable good
 * 
 * @author Andrew
 *
 */
public interface Taxable extends Priceable {
		
	public Map<Tax,Money> getTaxAmounts();
	
	public Money getTotalTax();
		
	public Money getTotalPriceWithTax();

}
