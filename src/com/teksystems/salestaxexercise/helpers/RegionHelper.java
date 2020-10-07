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
}
