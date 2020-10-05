/**
 * 
 */
package com.teksystems.salestaxexercise;

import java.util.Locale;

import org.joda.money.Money;

/**
 * @author Andrew
 *
 */
public interface Priceable {

	public Locale getLocale();
	
	public Money getShelfPrice();
	
}
