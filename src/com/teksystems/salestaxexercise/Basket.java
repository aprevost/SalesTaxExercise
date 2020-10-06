/**
 * 
 */
package com.teksystems.salestaxexercise;

import com.teksystems.salestaxexercise.goods.TaxableGood;
import com.teksystems.salestaxexercise.tax.Taxable;

/**
 * @author Andrew
 *
 */
public interface Basket extends Taxable {
	
	public void addGood(TaxableGood good);
	
	public void removeGood(TaxableGood good);
}
