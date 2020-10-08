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
class SalesTaxTest {
		
	private SellableItem sellableItem;

	private Money taxAmount;
	
	/**
	 * Test method for {@link com.teksystems.salestaxexercise.tax.SalesTax#getTaxAmountFor(com.teksystems.salestaxexercise.tax.Taxable)}.
	 * 
	 * Make sure books are exempted
	 */
	@Test
	void testGetTaxAmountForExemptBook() {
		sellableItem = TestHelpers.CANADIAN_BOOK_PRICE;
		taxAmount = CanadianTaxJurisdiction.SALES_TAX.getTaxAmountFor(sellableItem);
		assertNotNull(taxAmount, "tax amount is null");
		assertTrue(taxAmount.getCurrencyUnit().equals(CurrencyUnit.CAD), "currency unit is " + taxAmount.getCurrencyUnit());
		assertTrue(taxAmount.isZero(), "taxAmount is " + taxAmount.getAmount());
	}
	
	/**
	 * Test method for {@link com.teksystems.salestaxexercise.tax.SalesTax#getTaxAmountFor(com.teksystems.salestaxexercise.tax.Taxable)}.
	 * 
	 * Make sure non-exempt goods have the correct amount of tax calculated
	 */
	@Test
	void testGetTaxAmountForNonExemptGood() {
		sellableItem = TestHelpers.CANADIAN_MUSIC_CD_PRICE;
		taxAmount = CanadianTaxJurisdiction.SALES_TAX.getTaxAmountFor(sellableItem);
		assertNotNull(taxAmount, "tax amount is null");
		assertTrue(taxAmount.getCurrencyUnit().equals(CurrencyUnit.CAD), "currency unit is " + taxAmount.getCurrencyUnit());
		assertTrue(taxAmount.getAmount().equals(new BigDecimal("1.50")), "taxAmount is " + taxAmount.getAmount());
	}
	
	/**
	 * Test method for {@link com.teksystems.salestaxexercise.tax.SalesTax#getTaxAmountFor(com.teksystems.salestaxexercise.tax.Taxable)}.
	 * 
	 * Make sure food items are exempted
	 */
	@Test
	void testGetTaxAmountForExemptFood() {
		sellableItem = TestHelpers.CANADIAN_CHOCOLATE_BAR_PRICE;
		taxAmount = CanadianTaxJurisdiction.SALES_TAX.getTaxAmountFor(sellableItem);
		assertNotNull(taxAmount, "tax amount is null");
		assertTrue(taxAmount.getCurrencyUnit().equals(CurrencyUnit.CAD), "currency unit is " + taxAmount.getCurrencyUnit());
		assertTrue(taxAmount.isZero(), "taxAmount is " + taxAmount.getAmount());
	}

	/**
	 * Test method for {@link com.teksystems.salestaxexercise.tax.SalesTax#getTaxAmountFor(com.teksystems.salestaxexercise.tax.Taxable)}.
	 * 
	 * Make sure medical products are exempted
	 */
	@Test
	void testGetTaxAmountForExemptMedicalProduct() {
		sellableItem = TestHelpers.CANADIAN_PACKET_OF_HEADACHE_PILLS_PRICE;
		taxAmount = CanadianTaxJurisdiction.SALES_TAX.getTaxAmountFor(sellableItem);
		assertNotNull(taxAmount, "tax amount is null");
		assertTrue(taxAmount.getCurrencyUnit().equals(CurrencyUnit.CAD), "currency unit is " + taxAmount.getCurrencyUnit());
		assertTrue(taxAmount.isZero(), "taxAmount is " + taxAmount.getAmount());
	}

}
