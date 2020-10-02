package com.teksystems.salestaxexercise.regions;

import java.util.Objects;

/**
 * Abstract class which implements all the basic functionality that should be
 * common to all objects which represent a specific geographical region.
 * 
 * This implementation assumes that all regions have an integer ID associated
 * with them (e.g., a primary key from a database)
 * 
 * @author Andrew
 *
 */
public abstract class AbstractRegion implements Region {
	
	private String name;

	/**
	 * This method can never return null.
	 * 
	 * The underlying field can only be set in the constructor
	 */
	@Override
	public String getName() {
		return name;
	}
	
	protected AbstractRegion (String name) {
		//Require name be non-null for sake of comparison
		Objects.requireNonNull(name, "Region name must not be null");
		//could/should we require the string be non-empty here as well?
		this.name = name;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == this) {
			return true;
		}
		if (!(obj instanceof AbstractRegion)) {
			return false;
		}
		AbstractRegion other = (AbstractRegion) obj;
		return java.util.Objects.equals(this.getName(), other.getName());
	}

}
