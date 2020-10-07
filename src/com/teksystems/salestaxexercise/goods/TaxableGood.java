/**
 * 
 */
package com.teksystems.salestaxexercise.goods;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.Map;

import com.ibm.icu.util.Region;

import org.joda.money.CurrencyUnit;
import org.joda.money.Money;

import com.teksystems.salestaxexercise.tax.Tax;
import com.teksystems.salestaxexercise.tax.TaxableCategory;
import com.teksystems.salestaxexercise.tax.TaxableItem;

/**
 * @author Andrew
 *
 */
public class TaxableGood implements PriceableGood, TaxableItem {
	
	private Good good;
	
	@Override
	public Good getGood() {
		return good;
	}

	private Region localeOfSale;
	
	@Override
	public Region getRegionOfSale() {
		return localeOfSale;
	}

	private Money shelfPrice = Money.of(CurrencyUnit.CAD, new BigDecimal("14.99"));
	
	@Override
	public Money getPrice() {
		return shelfPrice;
	}

	private TaxableCategory taxCategory;
	
	@Override
	public TaxableCategory getTaxableCategory() {
		return taxCategory;
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
		return getPrice().plus(getTotalTax());
	}
	
	public TaxableGood (Good good, Region localeOfSale, Money shelfPrice, TaxableCategory taxCategory) {
		this.good = good;
		this.localeOfSale = localeOfSale;
		this.shelfPrice = shelfPrice;
		this.taxCategory = taxCategory;
	}

}
