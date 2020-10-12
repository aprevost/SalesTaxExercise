/**
 * 
 */
package com.teksystems.salestaxexercise;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;

import org.joda.money.CurrencyUnit;
import org.joda.money.Money;

import com.ibm.icu.util.Region;
import com.teksystems.salestaxexercise.helpers.RegionHelper;
import com.teksystems.salestaxexercise.tax.Tax;
import com.teksystems.salestaxexercise.tax.TaxJurisdiction;

/**
 * Generic implementation of a basket of sellable items with taxes.
 * 
 * TODO: this class shouldn't really implement {@link Receiptable} itself, there should
 * be a separate View class for that.
 * 
 * TODO: this class is still a work in progress, only the functionality vital to
 * producing the sample outputs for the exercise has been implemented.
 * 
 * @author Andrew
 *
 */
public class TaxableBasketImpl implements TaxableBasket, Receiptable {

	public TaxableBasketImpl(CurrencyUnit currencyUnit, Region regionOfSale, Collection<TaxJurisdiction> taxJurisdictions, Collection<SellableItem> sellableItems) {
		if (currencyUnit == null) {
			throw new IllegalArgumentException("currency cannot be null");
		}
		this.currency = currencyUnit;

		if (regionOfSale == null) {
			throw new IllegalArgumentException("region cannot be null");
		}
		this.regionOfSale = regionOfSale;

		if (taxJurisdictions == null) {
			throw new IllegalArgumentException("taxJurisdictions cannot be null");
		}
		if (taxJurisdictions.isEmpty()) {
			throw new IllegalArgumentException("taxJurisdictions cannot be empty");			
		}
		this.taxJurisdictions.addAll(taxJurisdictions);
		
		addAll(sellableItems);
	}

	private CurrencyUnit currency;

	@Override
	public CurrencyUnit getCurrencyUnit() {
		return currency;
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

	private LinkedHashMap<TaxableItem, Integer> taxableItemCountMap = new LinkedHashMap<TaxableItem, Integer>();

	@Override
	public void add(SellableItem sellableItem) {
		
		if (sellableItem == null) {
			throw new IllegalArgumentException("sellableItem cannot be null");
		}
		
		//Make sure the price is in the correct currency for this basket
		if (! sellableItem.getPrice().getCurrencyUnit().equals(getCurrencyUnit())) {
			throw new IllegalArgumentException("CurrencyUnit for sellableItem does not match basket CurrencyUnit");
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
	public void addAll(Collection<SellableItem> sellableItems) {
		for (SellableItem sellableItem : sellableItems) {
			add(sellableItem);
		}
	}

	@Override
	public void remove(SellableItem sellableItem) {
		// TODO Auto-generated method stub

	}

	@Override
	public void removeAll(Collection<SellableItem> sellableItems) {
		// TODO Auto-generated method stub
	}
	
	@Override
	public void clear() {
		taxableItemCountMap.clear();
	}
	
	@Override
	public LinkedHashMap<SellableItem, Integer> getAllItemsWithCount() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Money getPrice() {
		//TODO: as this and the next 2 methods all do almost the exact same
		//thing, just retrieving a different amount from the key object, I know
		//there is likely a way of genericizing the pattern in a reusable way
		//rather than just repeating the same pattern witn 1 variation 3 times
		Money total = Money.of(getCurrencyUnit(), 0);
		for (Map.Entry<TaxableItem,Integer> entry : taxableItemCountMap.entrySet()) {
			
			Money price = entry.getKey().getSellableItem().getPrice();
			Integer count = entry.getValue();
			total = price.multipliedBy(count).plus(total);
		}
		return total;
	}
	
	@Override
	public Money getTotalTax() {
		Money total = Money.of(getCurrencyUnit(), 0);
		for (Map.Entry<TaxableItem,Integer> entry : taxableItemCountMap.entrySet()) {
			
			Money totalTaxForItem = entry.getKey().getTotalTax();
			Integer count = entry.getValue();
			total = totalTaxForItem.multipliedBy(count).plus(total);
		}
		return total;
	}

	@Override
	public Money getTotalPriceWithTax() {
		Money total = Money.of(getCurrencyUnit(), 0);
		for (Map.Entry<TaxableItem,Integer> entry : taxableItemCountMap.entrySet()) {
			
			Money totalPriceWithTaxForItem = entry.getKey().getTotalPriceWithTax();
			Integer count = entry.getValue();
			total = totalPriceWithTaxForItem.multipliedBy(count).plus(total);
		}
		return total;
	}
	
	@Override
	public String toString() {
		String output = "";
		for (Map.Entry<TaxableItem,Integer> entry : taxableItemCountMap.entrySet()) {
			TaxableItem taxableItem = entry.getKey();
			Integer count = entry.getValue();
			output += count + " "
					+ taxableItem + ": "
					+ taxableItem.getTotalPriceWithTax().multipliedBy(count).getAmount()
					+ System.lineSeparator();
		}
		//This is not as efficient as it could be, as it means we iterate
		//through all entries 3 times
		output += "Sales Taxes: " + getTotalTax().getAmount()
				+ " " + "Total: " + getTotalPriceWithTax().getAmount()
				+ System.lineSeparator();
		return output;
	}

	/**
	 * This class shouldn't really implement this method itself,
	 * there should be a separate class that implements Receiptable
	 */
	@Override
	public void printReceipt() {
		System.out.print(this.toString());
	}


}
