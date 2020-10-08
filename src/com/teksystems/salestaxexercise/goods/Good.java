/**
 * 
 */
package com.teksystems.salestaxexercise.goods;

import com.ibm.icu.util.Region;

/**
 * @author Andrew
 *
 */
public class Good {
	
	private String name;
	
	public String getName() {
		return name;
	}
	
	private Region regionOfOrigin;
	
	public Region getRegionOfOrigin() {
		return regionOfOrigin;
	}
	
	@Override
	public String toString() {
		return name;
	}
	
	public Good(String name, Region regionWhereProduced) {
		this.name = name;
		this.regionOfOrigin = regionWhereProduced;
	}

}
