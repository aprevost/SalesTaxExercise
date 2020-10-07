/**
 * 
 */
package com.teksystems.salestaxexercise.tax;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;

import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.junit.jupiter.api.Test;

import com.teksystems.salestaxexercise.goods.TaxableGood;
import com.teksystems.salestaxexercise.runners.TestHelpers;

/**
 * @author Andrew
 *
 */
class CanadianSalesTaxTest {
		
	private TaxableGood taxedGood;

	private Money taxAmount;
	
	/**
	 * Test method for {@link com.teksystems.salestaxexercise.tax.CanadianSalesTax#getTaxAmountFor(com.teksystems.salestaxexercise.tax.Taxable)}.
	 */
	@Test
	void testGetTaxAmountForExemptBook() {
		taxedGood = TestHelpers.BOOK_CANADIAN;
		taxAmount = TestHelpers.SALES_TAX_CANADIAN.getTaxAmountFor(taxedGood);
		assertNotNull(taxAmount, "tax amount is null");
		assertTrue(taxAmount.getCurrencyUnit().equals(CurrencyUnit.CAD), "currency unit is " + taxAmount.getCurrencyUnit());
		assertTrue(taxAmount.isZero(), "taxAmount is " + taxAmount.getAmount());
	}
	
	/**
	 * Test method for {@link com.teksystems.salestaxexercise.tax.CanadianSalesTax#getTaxAmountFor(com.teksystems.salestaxexercise.tax.Taxable)}.
	 */
	@Test
	void testGetTaxAmountForNonExemptGood() {
		taxedGood = TestHelpers.MUSIC_CD_CANADIAN;
		taxAmount = TestHelpers.SALES_TAX_CANADIAN.getTaxAmountFor(taxedGood);
		assertNotNull(taxAmount, "tax amount is null");
		assertTrue(taxAmount.getCurrencyUnit().equals(CurrencyUnit.CAD), "currency unit is " + taxAmount.getCurrencyUnit());
		assertTrue(taxAmount.getAmount().equals(new BigDecimal("1.50")), "taxAmount is " + taxAmount.getAmount());
	}
	
	/**
	 * Test method for {@link com.teksystems.salestaxexercise.tax.CanadianSalesTax#getTaxAmountFor(com.teksystems.salestaxexercise.tax.Taxable)}.
	 */
	@Test
	void testGetTaxAmountForExemptFood() {
		taxedGood = TestHelpers.CHOCOLATE_BAR_CANADIAN;
		taxAmount = TestHelpers.SALES_TAX_CANADIAN.getTaxAmountFor(taxedGood);
		assertNotNull(taxAmount, "tax amount is null");
		assertTrue(taxAmount.getCurrencyUnit().equals(CurrencyUnit.CAD), "currency unit is " + taxAmount.getCurrencyUnit());
		assertTrue(taxAmount.isZero(), "taxAmount is " + taxAmount.getAmount());
	}

	/**
	 * Test method for {@link com.teksystems.salestaxexercise.tax.CanadianSalesTax#getTaxAmountFor(com.teksystems.salestaxexercise.tax.Taxable)}.
	 */
	@Test
	void testGetTaxAmountForExemptMedicalProduct() {
		taxedGood = TestHelpers.PACKET_OF_HEADACHE_PILLS_CANADIAN;
		taxAmount = TestHelpers.SALES_TAX_CANADIAN.getTaxAmountFor(taxedGood);
		assertNotNull(taxAmount, "tax amount is null");
		assertTrue(taxAmount.getCurrencyUnit().equals(CurrencyUnit.CAD), "currency unit is " + taxAmount.getCurrencyUnit());
		assertTrue(taxAmount.isZero(), "taxAmount is " + taxAmount.getAmount());
	}

}
