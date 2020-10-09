/**
 * 
 */
package com.teksystems.salestaxexercise;

import java.util.LinkedHashSet;

import org.joda.money.CurrencyUnit;

import com.teksystems.salestaxexercise.tax.TaxJurisdiction;

/**
 * @author Andrew
 *
 */
public interface TaxableBasket extends Taxable, Basket {
	
	public CurrencyUnit getCurrencyUnit();
	
	public LinkedHashSet<TaxJurisdiction> getTaxJurisdictions();

}
