/**
 * 
 */
package com.teksystems.salestaxexercise.tax;

import org.joda.money.Money;

import com.ibm.icu.util.Region;

/**
 * @author Andrew
 *
 */
public interface Tax {
	
	public Region getRegion();

	public Money getTaxAmountFor(TaxableItem taxableItem);

}
