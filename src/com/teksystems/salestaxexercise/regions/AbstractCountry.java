package com.teksystems.salestaxexercise.regions;

/**
 * @author Andrew
 * 
 * Abstract class which implements all the basic functionality that should be
 * common to most objects which represent a specific country.
 */
public abstract class AbstractCountry extends AbstractRegion implements Country {

	/**
	 * @param name
	 */
	public AbstractCountry(String name) {
		super(name);
	}

	/**
	 * For an object that is itself a country (doesn't merely have a country as
	 * one of its properties), the getCountry() function should always just
	 * return the object itself.
	 */
	@Override
	public Country getCountry() {
		return this;
	}

}
