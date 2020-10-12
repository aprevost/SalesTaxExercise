/**
 * 
 */
package com.teksystems.salestaxexercise.goods;

import com.ibm.icu.util.Region;

/**
 * A generic product/good.
 * 
 * Contains only the properties of a good that are universal, no price or tax
 * information that could vary depending on which region the good was sold in.
 * 
 * @author Andrew
 */
public class Good {
	
	public Good(String name, Region regionWhereProduced) {
		if (name == null) {
			throw new IllegalArgumentException("name must not be null");
		}
		if (name.isBlank()) {
			throw new IllegalArgumentException("name must not be blank");
		}
		this.name = name;
		this.regionOfOrigin = regionWhereProduced;
	}

	private String name;
	
	/**
	 * @return the good's name, guaranteed non-null and non-empty
	 */
	public String getName() {
		return name;
	}
	
	private Region regionOfOrigin;
	
	/**
	 * @return the good's region of origin, null if unknown
	 */
	public Region getRegionOfOrigin() {
		return regionOfOrigin;
	}
	
	@Override
	public String toString() {
		return name;
	}
	
	/**
	 * This is a hack, in the real world each good would likely have a unique
	 * key defined in the data source
	 * 
	 * @return a string that uniquely identifies this good
	 */
	public String getPrimaryKey() {
		String primaryKeyString = this.toString();
		Region region = getRegionOfOrigin();
		if (region != null) {
			primaryKeyString += region.toString();
		}
		return primaryKeyString;
	}
	
	@Override
	public boolean equals(Object obj) {
        if (this == obj) {
        	return true;
        }
        if (!(obj instanceof Good)) {
        	return false;
        }

        Good that = (Good) obj;
        return this.getPrimaryKey().equals(that.getPrimaryKey());    
	}
	
    @Override
	public int hashCode() {
    	return getPrimaryKey().hashCode();
    }
	
}
