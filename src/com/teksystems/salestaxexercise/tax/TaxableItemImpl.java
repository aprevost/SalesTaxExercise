/**
 * 
 */
package com.teksystems.salestaxexercise.tax;

import java.util.LinkedHashMap;
import java.util.Map;

import org.joda.money.Money;

import com.teksystems.salestaxexercise.SellableItem;

/**
 * @author Andrew
 *
 */
public class TaxableItemImpl implements TaxableItem {

	public TaxableItemImpl(SellableItem sellableItem, TaxJurisdiction taxJurisdiction, TaxableCategory taxableCategory) {
		this.sellableItem = sellableItem;
		this.taxJurisdiction = taxJurisdiction;
		this.taxableCategory = taxableCategory;
	}

	private TaxJurisdiction taxJurisdiction;
	@Override
	public TaxJurisdiction getTaxJurisdiction() {
		return taxJurisdiction;
	}

	private SellableItem sellableItem;
	@Override
	public SellableItem getSellableItem() {
		return sellableItem;
	}

	private TaxableCategory taxableCategory = TaxableCategory.OTHER;
	@Override
	public TaxableCategory getTaxableCategory() {
		return taxableCategory;
	}

	private LinkedHashMap<Tax,Money> taxAmounts = new LinkedHashMap<Tax,Money>(); 
	@Override
	public Map<Tax, Money> getTaxAmounts() {
		return taxAmounts;
	}

	@Override
	public void addTaxAmount(Tax tax, Money amount) {
		taxAmounts.put(tax,amount);
	}

	@Override
	public Money getTotalTax() {
		return Money.total(taxAmounts.values()); 
	}

	@Override
	public Money getTotalPriceWithTax() {
		return getSellableItem().getPrice().plus(getTotalTax());
	}

}
