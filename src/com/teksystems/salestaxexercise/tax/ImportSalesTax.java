/**
 * 
 */
package com.teksystems.salestaxexercise.tax;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Set;

import org.joda.money.Money;

import com.ibm.icu.util.Region;
import com.teksystems.salestaxexercise.SellableItem;
import com.teksystems.salestaxexercise.helpers.MoneyRoundingRule;
import com.teksystems.salestaxexercise.helpers.RegionHelper;

/**
 * Generic implementation of an import duty that is assessed like a sales tax.
 * 
 * Identical in almost every way to {@link SalesTax}, just adds a check in
 * {@link #getTaxAmountFor(SellableItem)} to return an amount of 0 if the item
 * is not imported.
 * 
 * @author Andrew
 *
 */
public class ImportSalesTax extends SalesTax {

	/**
	 * Merely wraps the super class constructor {@link SalesTax#SalesTax(TaxJurisdiction, String, BigDecimal, Set, RoundingMode, MoneyRoundingRule)}.
	 * <p>
	 * TODO: implementing and documenting this would be easier if the Builder
	 * design pattern was used here and in {@link SalesTax}.
	 * </p>
	 * @see SalesTax#SalesTax(TaxJurisdiction, String, BigDecimal, Set, RoundingMode, MoneyRoundingRule)
	 * 
	 * @param taxJurisdiction see super class constructor
	 * @param name see super class constructor
	 * @param percentage see super class constructor
	 * @param exemptedCategories see super class constructor
	 * @param roundingMode see super class constructor
	 * @param customRoundingRule see super class constructor
	 */
	public ImportSalesTax(TaxJurisdiction taxJurisdiction, String name, BigDecimal percentage, Set<TaxableCategory> exemptedCategories, RoundingMode roundingMode, MoneyRoundingRule customRoundingRule) {
		super(taxJurisdiction, name, percentage, exemptedCategories, roundingMode, customRoundingRule);
	}
	
	/**
	 * {@inheritDoc}
	 * 
	 * Wraps {@link SalesTax#getTaxAmountFor(SellableItem)} and adds check of
	 * the {@link SellableItem#getRegionOfOrigin()} vs. this object's
	 * {@link TaxJurisdiction#getRegion()}.
	 * 
	 * If the tax jurisdiction's
	 * {@link Region} contains this item's region of origin, or if either
	 * region is null, then will just return an amount of 0.
	 * 
	 * @throws IllegalArgumentException {@inheritDoc}
	 */
	@Override
	public Money getTaxAmountFor(SellableItem sellableItem) {
		
		if (sellableItem == null) {
			throw new IllegalArgumentException("sellableItem cannot be null");
		}
		
		Money preTaxPrice = sellableItem.getPrice();
		if (preTaxPrice == null) {
			throw new IllegalArgumentException("sellableItem price cannot be null");
		}
		
		//Use preTaxPrice.withAmount rather than just creating a new Money
		//object form scratch so that currency of the pre-tax price is maintained
		//for the tax amount of 0 as well
		Money zeroTax = preTaxPrice.withAmount(0);
		
		if (preTaxPrice.isZero()) {
			return zeroTax;
		}
		
		Region itemRegionOfOrigin = sellableItem.getRegionOfOrigin();
		Region taxRegion = getTaxJurisdiction().getRegion();
		
		//Assumption for null check below documented in project Readme 
		// - if no origin can be retrieved for item, or if the origin retrieved
		//   is empty, we assume this item is not subject to an import tax
		if (itemRegionOfOrigin == null
				|| RegionHelper.regionIsOrContainsRegion(taxRegion, itemRegionOfOrigin)
		) {
			//The item is not subject to the import tax
			return zeroTax;
		}
		
		return calculateSalesTax(preTaxPrice);
		
	}
	

}
