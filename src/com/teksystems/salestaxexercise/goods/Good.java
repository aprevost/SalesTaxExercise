/**
 * 
 */
package com.teksystems.salestaxexercise.goods;

import com.ibm.icu.util.Region;
import com.teksystems.salestaxexercise.TraceableItem;

/**
 * @author Andrew
 *
 */
public class Good implements TraceableItem {
	
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
