/**
 * 
 */
package com.teksystems.salestaxexercise.goods;

import java.util.Locale;

/**
 * @author Andrew
 *
 */
public class GenericGood implements Good {
	
	private String name;
	
	@Override
	public String getName() {
		return name;
	}
	
	private Locale regionWhereProduced = Locale.CANADA;
	
	@Override
	public Locale getRegionWhereProduced() {
		return regionWhereProduced;
	}
	
	@Override
	public String toString() {
		return name;
	}
	
	public GenericGood(String name, Locale regionWhereProduced) {
		this.name = name;
		this.regionWhereProduced = regionWhereProduced;
	}

}
