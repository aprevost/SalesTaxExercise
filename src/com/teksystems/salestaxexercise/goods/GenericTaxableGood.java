/**
 * 
 */
package com.teksystems.salestaxexercise.goods;

import java.math.BigDecimal;
import java.util.Locale;

import org.joda.money.CurrencyUnit;
import org.joda.money.Money;

import com.teksystems.salestaxexercise.tax.TaxableGoodCategory;

/**
 * @author Andrew
 *
 */
public class GenericTaxableGood implements TaxableGood {
	
	private GenericGood good;
	
	@Override
	public Good getGood() {
		return good;
	}

	private Locale localeOfSale;
	
	@Override
	public Locale getLocaleOfSale() {
		return localeOfSale;
	}

	private Money shelfPrice = Money.of(CurrencyUnit.CAD, new BigDecimal("14.99"));
	
	@Override
	public Money getShelfPrice() {
		return shelfPrice;
	}

	private TaxableGoodCategory taxCategory;
	
	@Override
	public TaxableGoodCategory getTaxableGoodCategory() {
		return taxCategory;
	}

	private Money totalTax = null;

	@Override
	public Money getTotalTax() {
		return totalTax; 
	}

	@Override
	public void setTotalTax(Money totalTax) {
		this.totalTax = totalTax;
	}

	@Override
	public Money getTotalPriceWithTax() {
		if (totalTax == null) {
			return getShelfPrice();
		}
		return getShelfPrice().plus(getTotalTax());
	}
	
	public GenericTaxableGood (GenericGood good, Locale localeOfSale, Money shelfPrice, TaxableGoodCategory taxCategory) {
		this.good = good;
		this.localeOfSale = localeOfSale;
		this.shelfPrice = shelfPrice;
		this.taxCategory = taxCategory;
	}

}
