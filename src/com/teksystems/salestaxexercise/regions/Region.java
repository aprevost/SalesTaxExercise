/**
 * Package containing all interfaces and classes related to the handling of
 * geographic regions
 */
package com.teksystems.salestaxexercise.regions;

/**
 * @author Andrew
 *
 * Generic interface for any geographical region in the world
 * 
 * Could be a continent, a trade bloc (like the EU), a country, a province, a 
 * state, a territory, a city, a borough, a park, etc.
 * 
 */
public interface Region {

	/**
	 * Getter for the human-readable name of the region
	 * 
	 * Note: there is no corresponding setter for the name in this interface,
	 * because many implementations of Region may not want the name to be
	 * modifiable after initialization. A separate interface can be created
	 * later for Regions with settable names, if/when that requirement arises.
	 */
	public String getName();
	
}
