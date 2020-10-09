package com.teksystems.salestaxexercise.tax;

import java.util.LinkedHashMap;
import java.util.LinkedHashSet;

import com.ibm.icu.util.Region;
import com.teksystems.salestaxexercise.SellableItem;

import org.joda.money.Money;

/**
 * Interface for any tax jurisdiction
 * 
 * @author Andrew
 */
public interface TaxJurisdiction {
	
	/**
	 * Every tax jurisdiction must correspond to a specific geographical region
	 * 
	 * @return the geographical region corresponding to this jurisdiction
	 */
	public Region getRegion();
	
	/**
	 * TODO: SOLID design principles suggest maybe this shouldn't be here.
	 * If we want to allow simple implementations not to have a category,
	 * this should be split out in to another interface again.
	 * 
	 * @param sellableItem the item to be sold
	 * @return the taxable category for this item in this jurisdiction, null if no category found
	 */
	public TaxableCategory getTaxableCategoryFor(SellableItem sellableItem);
	
	/**
	 * The list of taxes for a given jurisdiction must not contain any duplicate
	 * taxes and must be maintained in a particular order, as some taxes
	 * may be applied on top of other taxes prior to them on the list
	 * 
	 * e.g., Quebec sales taxes are applied on top of the federal sales tax
	 * 
	 * @return the ordered set of all taxes for this jurisdiction
	 */
	public LinkedHashSet<Tax> getAllTaxes();
		
	/**
	 * 
	 * @param sellableItem the item to be sold
	 * @return a map from each tax for 
	 */
	public LinkedHashMap<Tax,Money> getTaxAmountsFor(SellableItem sellableItem);
		
}
