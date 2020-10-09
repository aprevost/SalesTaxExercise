/**
 * 
 */
package com.teksystems.salestaxexercise;

import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;

import org.joda.money.CurrencyUnit;
import org.joda.money.Money;

import com.ibm.icu.util.Region;
import com.teksystems.salestaxexercise.helpers.RegionHelper;
import com.teksystems.salestaxexercise.tax.Tax;
import com.teksystems.salestaxexercise.tax.TaxJurisdiction;
import com.teksystems.salestaxexercise.tax.TaxableItem;
import com.teksystems.salestaxexercise.tax.TaxableItemImpl;

/**
 * Implementation of a basket of good with taxes
 * 
 * TODO: this class is still a work in progress, only the functionality vital to
 * producing the sample outputs for the exercise has been implemented
 * 
 * @author Andrew
 *
 */
public class TaxableBasketImpl implements TaxableBasket {

	/**
	 * 
	 */
	public TaxableBasketImpl(Region regionOfSale, LinkedHashSet<TaxJurisdiction> taxJurisdictions) {
		if (regionOfSale == null) {
			throw new IllegalArgumentException("region cannot be null");
		}
		this.regionOfSale = regionOfSale;
		if (taxJurisdictions == null) {
			throw new IllegalArgumentException("taxJurisdictions cannot be null");
		}
		this.taxJurisdictions.addAll(taxJurisdictions);
	}

	private Region regionOfSale;

	@Override
	public Region getRegionOfSale() {
		return regionOfSale;
	}

	private LinkedHashSet<TaxJurisdiction> taxJurisdictions = new LinkedHashSet<TaxJurisdiction>();

	/**
	 * @return the list of tax jurisdictions for this basket, cannot be null or
	 *         empty
	 */
	@Override
	public LinkedHashSet<TaxJurisdiction> getTaxJurisdictions() {
		return taxJurisdictions;
	}

	@Override
	public LinkedHashMap<Tax, Money> getTaxAmounts() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Money getTotalTax() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Money getTotalPriceWithTax() {
		// TODO Auto-generated method stub
		return null;
	}

	private LinkedHashMap<TaxableItem, Integer> taxableItemCountMap = new LinkedHashMap<TaxableItem, Integer>();

	@Override
	public void add(SellableItem sellableItem) {
		if (sellableItem == null) {
			throw new IllegalArgumentException("sellableItem cannot be null");
		}

		// Make sure this item/price pair is valid for this basket
		Region sellableItemRegion = sellableItem.getRegionOfSale();
		if (!RegionHelper.regionIsOrContainsRegion(getRegionOfSale(), sellableItemRegion)) {
			throw new IllegalArgumentException("sellableItem price region not compatible with basket region of sale");
		}

		// We actually store TaxableItems, not SellableItems, so create one
		TaxableItemImpl taxableItem = new TaxableItemImpl(sellableItem);

		// Check if we already have this item in the basket
		if (taxableItemCountMap.containsKey(taxableItem)) {
			// If so, just increment its count
			int previousCount = taxableItemCountMap.get(taxableItem);
			taxableItemCountMap.put(taxableItem, previousCount + 1);
			return;
		}

		// Get the tax amounts for this item in this tax jurisdiction,
		// and add them to the taxable item
		for (TaxJurisdiction taxJurisdiction : getTaxJurisdictions()) {
			taxableItem.addTaxAmounts(taxJurisdiction.getTaxAmountsFor(sellableItem));
		}

		taxableItemCountMap.put(taxableItem, 1);
	}

	@Override
	public void remove(SellableItem sellableItem) {
		// TODO Auto-generated method stub

	}

	@Override
	public LinkedHashMap<SellableItem, Integer> getAllItemsWithCount() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Money getPrice() {
		Money total = null; //try to avoid initializing/knowing currency
		for (Map.Entry<TaxableItem,Integer> entry : taxableItemCountMap.entrySet()) {
			
			Money price = entry.getKey().getSellableItem().getPrice();
			Integer count = entry.getValue();
			total = price.multipliedBy(count).plus(total);
		}
		return total;
	}

}
