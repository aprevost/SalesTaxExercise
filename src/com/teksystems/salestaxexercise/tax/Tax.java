/**
 * 
 */
package com.teksystems.salestaxexercise.tax;

import org.joda.money.Money;

import com.teksystems.salestaxexercise.SellableItem;

/**
 * Generic interface for any tax, in any jurisdiction.
 * 
 * @author Andrew
 *
 */
public interface Tax {
	
	public String getName();
	
	public TaxJurisdiction getTaxJurisdiction();

	public Money getTaxAmountFor(SellableItem taxableItem);

}
