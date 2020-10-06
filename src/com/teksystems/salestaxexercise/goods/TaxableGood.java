/**
 * 
 */
package com.teksystems.salestaxexercise.goods;

import com.teksystems.salestaxexercise.tax.Taxable;
import com.teksystems.salestaxexercise.tax.TaxableGoodCategory;

/**
 * @author Andrew
 *
 */
public interface TaxableGood extends PricedGood, Taxable {
	
	public TaxableGoodCategory getTaxableGoodCategory();
		
}
