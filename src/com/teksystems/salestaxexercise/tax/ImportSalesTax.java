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

/**
 * Generic class for implementing an import duty that is assessed like a sales tax
 * 
 * Identical in almost every way to {@link SalesTax}, just adds a check in
 * {@link #getTaxAmountFor(SellableItem)} to return an amount of 0 if the item
 * is not imported
 * 
 * @author Andrew
 *
 */
public class ImportSalesTax extends SalesTax {

	public ImportSalesTax(TaxJurisdiction taxJurisdiction, String name, BigDecimal percentage, Set<TaxableCategory> exemptedCategories, RoundingMode roundingMode, MoneyRoundingRule customRoundingRule) {
		super(taxJurisdiction, name, percentage, exemptedCategories, roundingMode, customRoundingRule);
	}
	
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
		Money zeroTax = preTaxPrice.withAmount(BigDecimal.ZERO);
		
		if (preTaxPrice.isZero()) {
			return zeroTax;
		}
		
		Region itemRegionOfOrigin = sellableItem.getRegionOfOrigin();
		Region taxRegion = getTaxJurisdiction().getRegion();
		
		//Assumption for null check below documented in project Readme 
		// - if no origin can be retrieved for item, or if the origin retrieved
		//   is empty, we assume this item is not subject to an import tax
		
		//The .contains check below requires and understanding of how the
		//Region class works (the Region class was chosen for this purpose
		//precisely because of this feature)
		// - if this item was produced in Quebec, and this sales tax is a Canadian
		//     national tax, the itemRegionOfOrigin (Quebec) does not equal the 
		//     tax region (Canada), so the equals check will return false
		//  - however, the contains check "Returns true if this region contains
		//    the supplied other region anywhere in the region hierarchy."
		//    (as per the Javadoc)
		//  - since Quebec is a subregion of Canada, Quebec is in the region
		//    hierarchy of Canada, so the contains check will return true,
		//    this is not an import
		//  - NOTE: the Region class does not support sub-regions within
		//    countries, like provinces, off the shelf, would have to be
		//    extended
		//TODO: long-term this logic would have to be a lot more sophisticated
		if (itemRegionOfOrigin == null
				|| taxRegion.equals(itemRegionOfOrigin)
				|| taxRegion.contains(itemRegionOfOrigin)
		) {
			//The item is not subject to the import tax
			return zeroTax;
		}
		
		return calculateSalesTax(preTaxPrice);
		
	}
	

}
