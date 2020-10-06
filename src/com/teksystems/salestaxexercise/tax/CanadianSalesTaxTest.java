/**
 * 
 */
package com.teksystems.salestaxexercise.tax;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.util.Locale;

import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.junit.jupiter.api.Test;

import com.teksystems.salestaxexercise.goods.GenericGood;
import com.teksystems.salestaxexercise.goods.GenericTaxableGood;
import com.teksystems.salestaxexercise.goods.TaxableGood;

/**
 * @author Andrew
 *
 */
class CanadianSalesTaxTest {
	
	private final CanadianSalesTax canadianSalesTax = new CanadianSalesTax();
	
	private Money taxAmount;
	
	private TaxableGood taxedGood;

	/**
	 * Test method for {@link com.teksystems.salestaxexercise.tax.CanadianSalesTax#getTaxAmountFor(com.teksystems.salestaxexercise.tax.Taxable)}.
	 */
	@Test
	void testGetTaxAmountForNonExemptGood() {
		taxedGood = new GenericTaxableGood(new GenericGood("music CD", Locale.CANADA), Locale.CANADA, Money.of(CurrencyUnit.CAD, new BigDecimal("14.99")), TaxableGoodCategory.OTHER);
		taxAmount = canadianSalesTax.getTaxAmountFor(taxedGood);
		assertNotNull(taxAmount, "tax amount is null");
		assertTrue(taxAmount.getCurrencyUnit().equals(CurrencyUnit.CAD), "currency unit is " + taxAmount.getCurrencyUnit());
		assertTrue(taxAmount.getAmount().equals(new BigDecimal("1.50")), "taxAmount is " + taxAmount.getAmount());
	}

}
