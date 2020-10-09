/**
 * 
 */
package com.teksystems.salestaxexercise.tax;

import java.util.LinkedHashMap;

import org.joda.money.Money;

import com.teksystems.salestaxexercise.SellableItem;

/**
 * Generic Implementation of the TaxableItem interface, for adding
 * taxes to a sellable item
 * 
 * @author Andrew
 *
 */
public class TaxableItemImpl implements TaxableItem {

	public TaxableItemImpl(SellableItem sellableItem) {
		if (sellableItem == null) {
			throw new IllegalArgumentException("sellableItem must not be null");
		}
		this.sellableItem = sellableItem;
	}

	private SellableItem sellableItem;

	/**
	 * @return the sellable item (without taxes), cannot be null
	 */
	@Override
	public SellableItem getSellableItem() {
		return sellableItem;
	}

	private LinkedHashMap<Tax,Money> taxAmounts = new LinkedHashMap<Tax,Money>(); 

	/**
	 * @return the ordered set of taxes, with their amounts, for this item, cannot be null but can be empty
	 */
	@Override
	public LinkedHashMap<Tax, Money> getTaxAmounts() {
		return taxAmounts;
	}

	@Override
	public void addTaxAmount(Tax tax, Money amount) {
		taxAmounts.put(tax,amount);
	}

	@Override
	public void addTaxAmounts(LinkedHashMap<Tax,Money> taxAmounts) {
		taxAmounts.putAll(taxAmounts);
	}

	@Override
	public Money getTotalTax() {
		return Money.total(taxAmounts.values()); 
	}

	@Override
	public Money getTotalPriceWithTax() {
		return getSellableItem().getPrice().plus(getTotalTax());
	}

	@Override
	public String toString() {
		return getSellableItem().toString();
	}	

	@Override
	public boolean equals(Object obj) {
        if (this == obj) {
        	return true;
        }
        if (!(obj instanceof TaxableItemImpl)) {
        	return false;
        }

        TaxableItemImpl that = (TaxableItemImpl) obj;
        return this.getSellableItem().equals(that.getSellableItem());    
	}
	
    @Override
	public int hashCode() {
    	return this.getSellableItem().hashCode();
    }
}
