/**
 * 
 */
package com.teksystems.salestaxexercise;

import java.util.LinkedHashSet;

import com.teksystems.salestaxexercise.tax.TaxJurisdiction;
import com.teksystems.salestaxexercise.tax.Taxable;

/**
 * @author Andrew
 *
 */
public interface TaxableBasket extends Taxable, Basket {
	
	public LinkedHashSet<TaxJurisdiction> getTaxJurisdictions();

}
