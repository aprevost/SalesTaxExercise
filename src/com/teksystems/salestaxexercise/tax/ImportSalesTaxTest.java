/**
 * 
 */
package com.teksystems.salestaxexercise.tax;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;

import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.junit.jupiter.api.Test;

import com.teksystems.salestaxexercise.SellableItem;
import com.teksystems.salestaxexercise.runners.TestHelpers;
import com.teksystems.salestaxexercise.tax.canada.CanadianTaxJurisdiction;

/**
 * Unit tests for Canadian sales tax implementation
 * 
 * @author Andrew
 */
class ImportSalesTaxTest {

	private SellableItem sellableItem;

	private Money taxAmount;
	
	/**
	 * Test method for {@link com.teksystems.salestaxexercise.tax.ImportSalesTax#getTaxAmountFor(com.teksystems.salestaxexercise.tax.Taxable)}.
	 * 
	 * Make sure import duty is not assessed on a domestically produced item
	 */
	@Test
	void testGetTaxAmountForExemptDomesticGood() {
		sellableItem = TestHelpers.CANADIAN_BOOK_PRICE;
		taxAmount = CanadianTaxJurisdiction.IMPORT_DUTY.getTaxAmountFor(sellableItem);
		assertNotNull(taxAmount, "tax amount is null");
		assertTrue(taxAmount.getCurrencyUnit().equals(CurrencyUnit.CAD), "currency unit should be CAD, but is " + taxAmount.getCurrencyUnit());
		assertTrue(taxAmount.isZero(), "taxAmount should be 0, but is " + taxAmount.getAmount());
	}
	
	/**
	 * Test method for {@link com.teksystems.salestaxexercise.tax.ImportSalesTax#getTaxAmountFor(com.teksystems.salestaxexercise.tax.Taxable)}.
	 * 
	 * Make sure amount of import duty is correct for foreign product
	 */
	@Test
	void testGetTaxAmountForImportedGood() {
		sellableItem = TestHelpers.AMERICAN_BOX_OF_CHOCOLATES_PRICE;
		taxAmount = CanadianTaxJurisdiction.IMPORT_DUTY.getTaxAmountFor(sellableItem);
		assertNotNull(taxAmount, "tax amount is null");
		assertTrue(taxAmount.getCurrencyUnit().equals(CurrencyUnit.CAD), "currency unit should be CAD, but is " + taxAmount.getCurrencyUnit());
		assertTrue(taxAmount.getAmount().equals(new BigDecimal("0.50")), "taxAmount should be 0.50, but is " + taxAmount.getAmount());
	}

}
