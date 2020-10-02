package com.teksystems.salestaxexercise.regions;

/**
 * @author Andrew
 *
 * Generic interface for any class which has a country as one of its properties
 * 
 * For example, a sub-region of a country (like a province) could have its
 * country as one of its properties. 
 * 
 * This interface could also be implemented by a class which does not itself
 * represent a geographical region - for example, a person could have a country
 * associated with them (the one they live in), so could a company (the country
 * it's based in) or a product (the country it's produced in).
 * 
 * Extends the more general Region interface.
 */
public interface Country extends Region {
	
	/**
	 * The getter for the country associated with this object
	 */
	public Country getCountry();


}
