/**
 * 
 */
package com.teksystems.salestaxexercise.goods;

import com.ibm.icu.util.Region;

import org.joda.money.Money;

import com.teksystems.salestaxexercise.SellableItem;

/**
 * @author Andrew
 *
 */
public class PricedGood implements SellableItem {
	
	private Good good;
	
	public Good getGood() {
		return good;
	}

	@Override
	public String getName() {
		return getGood().getName();
	}
	
	@Override
	public Region getRegionOfOrigin() {
		return getGood().getRegionOfOrigin();
	}

	private Region regionOfSale;
	
	@Override
	public Region getRegionOfSale() {
		return regionOfSale;
	}

	private Money shelfPrice;
	
	@Override
	public Money getPrice() {
		return shelfPrice;
	}
	
	@Override
	public String toString() {
		return getGood().toString();
	}	

	public PricedGood (Good good, Region regionOfSale, Money shelfPrice) {
		this.good = good;
		this.regionOfSale = regionOfSale;
		this.shelfPrice = shelfPrice;
	}

}
