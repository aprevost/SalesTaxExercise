/**
 * 
 */
package com.teksystems.salestaxexercise.tax;

import org.joda.money.Money;

import com.teksystems.salestaxexercise.Priceable;

/**
 * @author Andrew
 *
 */
public interface Taxable extends Priceable {
		
	public Money getTotalTax();
	
	public void  setTotalTax(Money totalTax);
	
	public Money getTotalPriceWithTax();

}
