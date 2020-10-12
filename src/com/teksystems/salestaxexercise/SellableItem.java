/**
 * 
 */
package com.teksystems.salestaxexercise;

import com.ibm.icu.util.Region;

/**
 * Interface for a unitary item which is sellable.
 * 
 * As opposed to the more generic {@link Sellable} interface which can apply
 * to collections of sellable items as well.
 * 
 * @author Andrew
 *
 */
public interface SellableItem extends Sellable {
	
	public String getName();
	
	public Region getRegionOfOrigin();
	
}
