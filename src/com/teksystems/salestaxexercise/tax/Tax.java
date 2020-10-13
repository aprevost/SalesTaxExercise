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
	
	/**
	 * Get the name for this tax.
	 * 
	 * @return The name of this tax.
	 */
	public String getName();
	
	/**
	 * Get the tax jurisdiction in which this tax is applied.
	 * 
	 * @return The tax jurisdiction in which this tax is applied.
	 */
	public TaxJurisdiction getTaxJurisdiction();

	/**
	 * Get the amount of this tax for the specified sellable item.
	 * 
	 * TODO: does this method really need to take a sellable item as its
	 * parameter, could possibly take the more generic {@link com.teksystems.salestaxexercise.Sellable}
	 * interface instead? Some taxes might exist which apply to a collection of
	 * goods (or services) rather than to each specific item in that collection
	 * separately?
	 * 
	 * The only reason I really need a SellableItem here, for the limited
	 * purposes of this specific exercise, is because I need the {@link SellableItem#getRegionOfOrigin()}
	 * function for the import duty... and I'm not even sure that method
	 * belongs in that interface anyway.
	 * 
	 * @param sellableItem the item to which this tax will be applied
	 * @return The amount of this tax for the specified item.
	 */
	public Money getTaxAmountFor(SellableItem sellableItem);

}
