/**
 * 
 */
package com.teksystems.salestaxexercise;

import com.ibm.icu.util.Region;

/**
 * Interface for a specific product or service which is sellable.
 * 
 * As opposed to the more generic {@link Sellable} interface which can apply
 * to collections of sellable items as well.
 * 
 * @author Andrew
 *
 */
public interface SellableItem extends Sellable {
	
	/**
	 * Get the name of the product or service.
	 * 
	 * A collection of sellable items doesn't require a label, but a specific
	 * item does.
	 * 
	 * @return The name of the product or service.
	 */
	public String getName();
	
	/**
	 * Get the region of origin for this item.
	 * 
	 * TODO: SOLID design principles would suggest this should probably be
	 * split out in to a separate interface. I can foresee many future
	 * implementations of sellable items for which the region of origin is not
	 * needed.
	 * 
	 * @return The region in which this item was produced (or the service was performed).
	 */
	public Region getRegionOfOrigin();
	
}
