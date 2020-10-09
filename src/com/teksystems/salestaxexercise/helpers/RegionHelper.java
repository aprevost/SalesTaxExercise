/**
 * 
 */
package com.teksystems.salestaxexercise.helpers;

import com.ibm.icu.util.Region;

/**
 * Contains helpers related to region
 * 
 * @author Andrew
 *
 */
public class RegionHelper {
	/**
	 * The {@link com.ibm.icu.util.Region} object for Canada
	 */
	public static final Region CANADA = Region.getInstance("CA");
	/**
	 * The {@link com.ibm.icu.util.Region} object for the US
	 */
	public static final Region USA = Region.getInstance("US");
	/**
	 * The {@link com.ibm.icu.util.Region} object for the US
	 */
	public static final Region FRANCE = Region.getInstance("FR");
	
	/**
	 * Helper function to determine if two regions are equal OR if the second
	 * region is contained inside the first
	 * 
	 * The {@link com.ibm.icu.util.Region} class was chosen precisely because
	 * it implements the {@link com.ibm.icu.util.Region#contains(Region)}
	 * function.
	 * 
	 * An example would be that Quebec and Canada are two different Regions,
	 * but Canada contains Quebec (and vice-versa).
	 * 
	 * Note that the Region class does not support sub-regions within countries,
	 * like Canadian provinces, off the shelf, would have to be extended for
	 * that.
	 * 
	 * @param fixedRegion the region inside which the other region could be contained
	 * @param possiblyContainedRegion the region which might be contained within the other region
	 * @return whether the first region is or contains the second region
	 */
	public static final boolean regionIsOrContainsRegion(Region fixedRegion, Region possiblyContainedRegion) {
		if (fixedRegion == null || possiblyContainedRegion == null) {
			return false;
		}
		return fixedRegion.equals(possiblyContainedRegion) || fixedRegion.contains(possiblyContainedRegion);
	}
}
