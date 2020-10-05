/**
 * 
 */
package com.teksystems.salestaxexercise.goods;

import java.util.Locale;

import com.teksystems.salestaxexercise.tax.TaxedGoodCategory;

/**
 * @author Andrew
 *
 */
public interface Good {
	
	/**
	 * 
	 */
	public String getName();
	
	/**
	 * 
	 */
	public TaxedGoodCategory getCategory();	
	
	/**
	 * 
	 */
	public Locale getRegionWhereProduced();
	
}
