/**
 * 
 */
package com.teksystems.salestaxexercise.tax;

import org.joda.money.Money;

/**
 * @author Andrew
 *
 */
public interface TaxableItem extends Taxable {

	/**
	 * Taxable collections might not have a specific category assigned to them,
	 * but most taxable items should
	 * 
	 * TODO: SOLID design principles suggest this shouldn't be here.
	 * If we want to allow simple implementations not to have a category,
	 * this should be split out in to another interface again
	 * 
	 * @return the taxable category for this item
	 */
	public TaxableCategory getTaxableCategory();
	
	/**
	 * You should not be able to directly add a tax amount to a taxable
	 * collection, their tax amounts should be made up of the tax amounts
	 * of the taxable items inside them
	 * 
	 * @param tax the tax that is being applied to the item
	 * @param amount the amount of that tax for this item
	 */
	public void addTaxAmount(Tax tax, Money amount);
	
}
