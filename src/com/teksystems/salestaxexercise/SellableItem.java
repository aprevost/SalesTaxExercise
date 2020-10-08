/**
 * 
 */
package com.teksystems.salestaxexercise;

import com.ibm.icu.util.Region;

/**
 * @author Andrew
 *
 */
public interface SellableItem extends Sellable {
	
	public String getName();
	
	public Region getRegionOfOrigin();
	
}
