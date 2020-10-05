/**
 * 
 */
package com.teksystems.salestaxexercise.goods;

import com.teksystems.salestaxexercise.tax.Taxable;
import com.teksystems.salestaxexercise.tax.TaxedGoodCategory;

/**
 * @author Andrew
 *
 */
public interface TaxedGood extends PricedGood, Taxable {
	
	public TaxedGoodCategory getTaxCategory();
	
	//TODO: implement overrides
	
}
