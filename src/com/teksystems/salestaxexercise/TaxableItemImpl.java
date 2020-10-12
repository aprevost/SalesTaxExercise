/**
 * 
 */
package com.teksystems.salestaxexercise;

import java.util.LinkedHashMap;

import org.joda.money.Money;

import com.teksystems.salestaxexercise.tax.Tax;

/**
 * Generic implementation of the {@link TaxableItem} interface, for adding
 * taxes to a {@link SellableItem}.
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
		this.taxAmounts.putAll(taxAmounts);
	}

	@Override
	public Money getTotalTax() {
		if(taxAmounts.isEmpty()) {
			//Use getPrice().withAmount rather than just creating a new Money
			//object from scratch so that currency of the pre-tax price is
			//maintained for the tax amount of 0 as well
			return getSellableItem().getPrice().withAmount(0);
		}
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
