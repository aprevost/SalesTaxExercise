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
import com.teksystems.salestaxexercise.runners.TestHelpers.PricedGoods.CanadianPrices;
import com.teksystems.salestaxexercise.tax.canada.CanadianTaxJurisdiction;

/**
 * Unit tests for Canadian sales tax implementation
 * 
 * @author Andrew
 */
class SalesTaxTest {
		
	private SellableItem sellableItem;
	
	private SalesTax salesTax = CanadianTaxJurisdiction.getInstance().getSalesTax();

	private Money taxAmount;
	
	/**
	 * Test method for {@link com.teksystems.salestaxexercise.tax.SalesTax#getTaxAmountFor(com.teksystems.salestaxexercise.tax.Taxable)}.
	 * 
	 * Make sure books are exempted
	 */
	@Test
	void testGetTaxAmountForExemptBook() {
		sellableItem = CanadianPrices.BOOK;
		taxAmount = salesTax.getTaxAmountFor(sellableItem);
		assertNotNull(taxAmount, "tax amount");
		assertEquals(taxAmount.getCurrencyUnit(), CurrencyUnit.CAD, "currency unit");
		assertTrue(taxAmount.isZero(), "taxAmount.isZero");
	}
	
	/**
	 * Test method for {@link com.teksystems.salestaxexercise.tax.SalesTax#getTaxAmountFor(com.teksystems.salestaxexercise.tax.Taxable)}.
	 * 
	 * Make sure non-exempt goods have the correct amount of tax calculated
	 */
	@Test
	void testGetTaxAmountForNonExemptGood() {
		sellableItem = CanadianPrices.MUSIC_CD;
		taxAmount = salesTax.getTaxAmountFor(sellableItem);
		assertNotNull(taxAmount, "tax amount");
		assertEquals(taxAmount.getCurrencyUnit(), CurrencyUnit.CAD, "currency unit");
		assertEquals(taxAmount.getAmount(), new BigDecimal("1.50"), "tax amount");
	}
	
	/**
	 * Test method for {@link com.teksystems.salestaxexercise.tax.SalesTax#getTaxAmountFor(com.teksystems.salestaxexercise.tax.Taxable)}.
	 * 
	 * Make sure food items are exempted
	 */
	@Test
	void testGetTaxAmountForExemptFood() {
		sellableItem = CanadianPrices.CHOCOLATE_BAR;
		taxAmount = salesTax.getTaxAmountFor(sellableItem);
		assertNotNull(taxAmount, "tax amount");
		assertEquals(taxAmount.getCurrencyUnit(), CurrencyUnit.CAD, "currency unit");
		assertTrue(taxAmount.isZero(), "taxAmount.isZero");
	}

	/**
	 * Test method for {@link com.teksystems.salestaxexercise.tax.SalesTax#getTaxAmountFor(com.teksystems.salestaxexercise.tax.Taxable)}.
	 * 
	 * Make sure medical products are exempted
	 */
	@Test
	void testGetTaxAmountForExemptMedicalProduct() {
		sellableItem = CanadianPrices.PACKET_OF_HEADACHE_PILLS;
		taxAmount = salesTax.getTaxAmountFor(sellableItem);
		assertNotNull(taxAmount, "tax amount");
		assertEquals(taxAmount.getCurrencyUnit(), CurrencyUnit.CAD, "currency unit");
		assertTrue(taxAmount.isZero(), "taxAmount.isZero");
	}

}
