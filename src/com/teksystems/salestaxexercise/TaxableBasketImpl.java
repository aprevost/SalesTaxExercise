/**
 * 
 */
package com.teksystems.salestaxexercise;

import java.util.Collection;
import java.util.Map;

import org.joda.money.Money;

import com.ibm.icu.util.Region;
import com.teksystems.salestaxexercise.tax.Tax;
import com.teksystems.salestaxexercise.tax.TaxJurisdiction;

/**
 * @author Andrew
 *
 */
public class TaxableBasketImpl implements TaxableBasket {

	/**
	 * 
	 */
	public TaxableBasketImpl() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public TaxJurisdiction getTaxJurisdiction() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<Tax, Money> getTaxAmounts() {
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

	@Override
	public void add(Sellable item) {
		// TODO Auto-generated method stub

	}

	@Override
	public void remove(Sellable item) {
		// TODO Auto-generated method stub

	}

	@Override
	public Collection<Sellable> getAllItems() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Region getRegionOfSale() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Money getPrice() {
		// TODO Auto-generated method stub
		return null;
	}

}
