/**
 * 
 */
package com.teksystems.salestaxexercise.tax;

import java.math.BigDecimal;

import org.joda.money.Money;

import com.ibm.icu.util.Region;
import com.teksystems.salestaxexercise.TraceableItem;

/**
 * @author Andrew
 *
 */
public class CanadianImportTax extends CanadianSalesTax {

	@Override
	public Money getTaxAmountFor(TaxableItem taxableItem) {
		
		if (taxableItem == null) {
			return null;
		}
		
		Money preTaxPrice = taxableItem.getPrice();
		if (preTaxPrice == null) {
			return null;
		}
		
		//Use preTaxPrice.withAmount rather than just creating a new Money
		//object form scratch so that currency of the pre-tax price is maintained
		//for the tax amount of 0 as well
		Money zeroTax = preTaxPrice.withAmount(BigDecimal.ZERO);
		
		if (preTaxPrice.isZero()) {
			return zeroTax;
		}
		
		//Assumption documented in project Readme - if no origin can be
		//retrieved for item, or if the origin retrieved is empty,
		//we assume this item is not subject to an import tax
		if (!(taxableItem instanceof TraceableItem)) {
			return zeroTax;
		}
		
		TraceableItem traceableItem = (TraceableItem) taxableItem;
		Region regionOfOrigin = traceableItem.getRegionOfOrigin();
		
		if (regionOfOrigin == null || regionOfOrigin.contains(this.getRegion())) {
			return zeroTax;
		}
		
		return calculateSalesTax(preTaxPrice);
		
	}
	/**
	 * @param percentage the percentage of the tax
	 */
	public CanadianImportTax(BigDecimal percentage) {
		super(percentage);
	}

}
