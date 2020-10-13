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
import com.teksystems.salestaxexercise.runners.TestHelpers.PricedGoods.CanadianPrices.CanadianImportPrices;
import com.teksystems.salestaxexercise.tax.canada.CanadianTaxJurisdiction;

/**
 * Unit tests for Canadian sales tax implementation
 * 
 * @author Andrew
 */
class ImportSalesTaxTest {

	private SellableItem sellableItem;

	private ImportSalesTax importSalesTax = CanadianTaxJurisdiction.getInstance().getImportSalesTax();

	private Money taxAmount;
	
	/**
	 * Test method for {@link com.teksystems.salestaxexercise.tax.ImportSalesTax#getTaxAmountFor(com.teksystems.salestaxexercise.tax.Taxable)}.
	 * 
	 * Make sure import duty is not assessed on a domestically produced item
	 */
	@Test
	void testGetTaxAmountForExemptDomesticGood() {
		sellableItem = CanadianPrices.BOOK;
		taxAmount = importSalesTax.getTaxAmountFor(sellableItem);
		assertNotNull(taxAmount, "tax amount");
		assertEquals(taxAmount.getCurrencyUnit(), CurrencyUnit.CAD, "currency unit");
		assertTrue(taxAmount.isZero(), "taxAmount.isZero");
	}
	
	/**
	 * Test method for {@link com.teksystems.salestaxexercise.tax.ImportSalesTax#getTaxAmountFor(com.teksystems.salestaxexercise.tax.Taxable)}.
	 * 
	 * Make sure amount of import duty is correct for foreign product
	 */
	@Test
	void testGetTaxAmountForImportedGood() {
		sellableItem = CanadianImportPrices.AMERICAN_BOX_OF_CHOCOLATES;
		taxAmount = importSalesTax.getTaxAmountFor(sellableItem);
		assertNotNull(taxAmount, "tax amount");
		assertEquals(taxAmount.getCurrencyUnit(), CurrencyUnit.CAD, "currency unit");
		assertEquals(taxAmount.getAmount(), new BigDecimal("0.50"), "taxAmount");
	}

}
