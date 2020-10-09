/**
 * 
 */
package com.teksystems.salestaxexercise.tax;

import java.util.LinkedHashMap;

import org.joda.money.Money;

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
	
	public LinkedHashMap<Tax,Money> getTaxAmounts();
	
	public Money getTotalTax();
		
	public Money getTotalPriceWithTax();

}
