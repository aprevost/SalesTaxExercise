package com.teksystems.salestaxexercise.tax;

import java.util.LinkedHashMap;
import java.util.LinkedHashSet;

import com.ibm.icu.util.Region;
import com.teksystems.salestaxexercise.SellableItem;

import org.joda.money.Money;

/**
 * Generic interface for any tax jurisdiction.
 * 
 * @author Andrew
 */
public interface TaxJurisdiction {
	
	/**
	 * Get the geographical region corresponding to this jurisdiction.
	 * 
	 * @return The geographical region corresponding to this jurisdiction.
	 */
	public Region getRegion();
	
	/**
	 * Get the tax category of a specific product or service in this jurisdiction.
	 * 
	 * TODO: SOLID design principles suggest maybe this shouldn't be here.
	 * There may be tax jurisdictions where all taxes are universally applied
	 * to all goods and services, meaning there is no need to keep track of
	 * categories. So this method should be split out in to a separate
	 * interface that extends this one, specifically for jurisdictions that
	 * require categories.
	 * 
	 * @param sellableItem the item to be sold
	 * @return The taxable category for this item in this jurisdiction.
	 */
	public TaxableCategory getTaxableCategoryFor(SellableItem sellableItem);
	
	/**
	 * Get the set of taxes which apply in this tax jurisdiction.
	 * 
	 * Must not contain any duplicate taxes and must be maintained in a
	 * particular order, as some taxes may be applied on top of other taxes prior to them on the list
	 * 
	 * e.g., Quebec sales taxes are applied on top of the Canadian federal GST.
	 * 
	 * The SOLID design principle that interfaces should wherever possible
	 * depend on other interfaces, not concrete classes,  would suggest this
	 * should return a Set or a List, not a LinkedHashSet.
	 * 
	 * If there was a generic Java collection interface that ensured both the
	 * uniqueness (like Set) AND order (like List) of is elements, I would
	 * definitely use it here. But as far as I know there is no such interface,
	 * only the class LinkedHashSet enforces both. So I decided in this case
	 * the benefits of depending on the concrete class outweighed the loss of
	 * flexibility.
	 * 
	 * @return The ordered set of all taxes for this jurisdiction.
	 */
	public LinkedHashSet<Tax> getAllTaxes();
		
	/**
	 * Get an ordered map of all the individual taxes and their amounts for
	 * the specified sellable item.
	 * 
	 * The SOLID design principle that interfaces should wherever possible
	 * depend on other interfaces, not concrete classes, would suggest this
	 * should return a Map, not a LinkedHashMap.
	 * 
	 * However, because some taxes may be assessed on top of other taxes,
	 * maintaining the order of taxes may be very important in many
	 * implementations. I decided the benefits of forcing every implementer to
	 * implement with a structure that maintains order outweighed the potential
	 * loss in flexibility.
	 * 
	 * @param sellableItem the item to be sold
	 * @return An ordered map of taxes and their respective amounts for the specified sellable item.
	 */
	public LinkedHashMap<Tax,Money> getTaxAmountsFor(SellableItem sellableItem);
		
}
