/**
 * 
 */
package com.teksystems.salestaxexercise.tax;

import java.util.LinkedHashMap;

import org.joda.money.Money;

import com.teksystems.salestaxexercise.Priceable;

/**
 * @author Andrew
 *
 */
public interface Taxable extends Priceable {
		
	public LinkedHashMap<Tax,Money> getItemizedTaxes();

	public Money getTotalTaxAmount();
	
	public Money getTotalPriceWithTax();

}
