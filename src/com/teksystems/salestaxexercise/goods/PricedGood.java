/**
 * 
 */
package com.teksystems.salestaxexercise.goods;

import com.ibm.icu.util.Region;

import org.joda.money.Money;

import com.teksystems.salestaxexercise.SellableItem;
import com.teksystems.salestaxexercise.helpers.RegionHelper;

/**
 * A good with its price for a specific region
 * 
 * @author Andrew
 *
 */
public class PricedGood implements SellableItem {
	
	public PricedGood (Good good, Region regionOfSale, Money shelfPrice) {
		if (good == null) {
			throw new IllegalArgumentException("good cannot be null");
		}
		if (shelfPrice == null) {
			throw new IllegalArgumentException("shelf price cannot be null");
		}
		this.good = good;
		this.regionOfSale = regionOfSale;
		this.shelfPrice = shelfPrice;
	}
	
	private Good good;

	/**
	 * @return the good object (without price), cannot be null
	 */
	public Good getGood() {
		return good;
	}

	@Override
	public String getName() {
		return getGood().getName();
	}
	
	/**
	 * @return the region of origin of the good, could be null
	 */
	@Override
	public Region getRegionOfOrigin() {
		return getGood().getRegionOfOrigin();
	}

	private Region regionOfSale;
	
	/**
	 * @return the region where the good can be sold at this price, cannot be null
	 */
	@Override
	public Region getRegionOfSale() {
		return regionOfSale;
	}

	private Money shelfPrice;
	
	/**
	 * @return the shelf price of the good in this region, cannot be null (but could be 0)
	 */
	@Override
	public Money getPrice() {
		return shelfPrice;
	}
	
	/**
	 * Prefixes the Good's label with "imported" if the Good's region of origin
	 * is outside the region of sale for this price
	 * 
	 * @return the display string for this priced good
	 */
	@Override
	public String toString() {
		String output = "";
		Region regionOfSale = getRegionOfSale();
		Region regionOfOrigin = getRegionOfOrigin();
		if (regionOfOrigin != null
				&& !RegionHelper.regionIsOrContainsRegion(regionOfSale, regionOfOrigin)
		) {
			output = "imported ";
		}
		output += getGood().toString();
		return output;
	}	

	/**
	 * This is a hack, in the real world each price would likely have a unique
	 * key defined in the data source
	 * 
	 * @return a string that uniquely identifies this Good/Region pairing
	 */
	public String getPrimaryKey() {
		return getGood().getPrimaryKey() + getRegionOfSale().toString();
	}
	
	@Override
	public boolean equals(Object obj) {
        if (this == obj) {
        	return true;
        }
        if (!(obj instanceof PricedGood)) {
        	return false;
        }

        PricedGood that = (PricedGood) obj;
        return this.getPrimaryKey().equals(that.getPrimaryKey());    
	}
	
    @Override
	public int hashCode() {
    	return getPrimaryKey().hashCode();
    }
	
}
