/**
 * 
 */
package com.teksystems.salestaxexercise.goods;

import java.util.Locale;

/**
 * @author Andrew
 *
 */
public class Good {
	
	private String name;
	
	public String getName() {
		return name;
	}
	
	private Locale regionWhereProduced = Locale.CANADA;
	
	public Locale getRegionWhereProduced() {
		return regionWhereProduced;
	}
	
	@Override
	public String toString() {
		return name;
	}
	
	public Good(String name, Locale regionWhereProduced) {
		this.name = name;
		this.regionWhereProduced = regionWhereProduced;
	}

}
