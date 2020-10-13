/**
 * 
 */
package com.teksystems.salestaxexercise.tax.canada;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;

import org.joda.money.Money;
import org.junit.jupiter.api.Test;

import com.teksystems.salestaxexercise.SellableItem;
import com.teksystems.salestaxexercise.runners.TestHelpers.PricedGoods.CanadianPrices;
import com.teksystems.salestaxexercise.runners.TestHelpers.PricedGoods.CanadianPrices.CanadianImportPrices;
import com.teksystems.salestaxexercise.tax.Tax;
import com.teksystems.salestaxexercise.tax.TaxableCategory;

/**
 * @author Andrew
 *
 */
class CanadianTaxJurisdictionTest {
	
	private SellableItem sellableItem;
	private TaxableCategory taxableCategory;
	private CanadianTaxJurisdiction taxJurisdiction = CanadianTaxJurisdiction.getInstance();

	/**
	 * Test method for {@link com.teksystems.salestaxexercise.tax.canada.CanadianTaxJurisdiction#getTaxableCategoryFor(com.teksystems.salestaxexercise.SellableItem)}.
	 */
	@Test
	void testGetTaxableCategoryForBook() {
		sellableItem = CanadianPrices.BOOK;
		taxableCategory = taxJurisdiction.getTaxableCategoryFor(sellableItem);
		assertNotNull(taxableCategory, "taxableCategory");
		assertEquals(taxableCategory, TaxableCategory.BOOK, "taxableCategory");
	}

	/**
	 * Test method for {@link com.teksystems.salestaxexercise.tax.canada.CanadianTaxJurisdiction#getTaxableCategoryFor(com.teksystems.salestaxexercise.SellableItem)}.
	 */
	@Test
	void testGetTaxableCategoryForChocolateBar() {
		sellableItem = CanadianPrices.CHOCOLATE_BAR;
		taxableCategory = taxJurisdiction.getTaxableCategoryFor(sellableItem);
		assertNotNull(taxableCategory, "taxableCategory");
		assertEquals(taxableCategory, TaxableCategory.FOOD, "taxableCategory");
	}

	/**
	 * Test method for {@link com.teksystems.salestaxexercise.tax.canada.CanadianTaxJurisdiction#getTaxableCategoryFor(com.teksystems.salestaxexercise.SellableItem)}.
	 */
	@Test
	void testGetTaxableCategoryForBoxOfChocolates() {
		sellableItem = CanadianImportPrices.AMERICAN_BOX_OF_CHOCOLATES;
		taxableCategory = taxJurisdiction.getTaxableCategoryFor(sellableItem);
		assertNotNull(taxableCategory, "taxableCategory");
		assertEquals(taxableCategory, TaxableCategory.FOOD, "taxableCategory");
	}

	/**
	 * Test method for {@link com.teksystems.salestaxexercise.tax.canada.CanadianTaxJurisdiction#getTaxableCategoryFor(com.teksystems.salestaxexercise.SellableItem)}.
	 */
	@Test
	void testGetTaxableCategoryForHeadachePills() {
		sellableItem = CanadianPrices.PACKET_OF_HEADACHE_PILLS;
		taxableCategory = taxJurisdiction.getTaxableCategoryFor(sellableItem);
		assertNotNull(taxableCategory, "taxableCategory");
		assertEquals(taxableCategory, TaxableCategory.MEDICAL_PRODUCT, "taxableCategory");
	}

	/**
	 * Test method for {@link com.teksystems.salestaxexercise.tax.canada.CanadianTaxJurisdiction#getTaxableCategoryFor(com.teksystems.salestaxexercise.SellableItem)}.
	 */
	@Test
	void testGetTaxableCategoryForMusicCD() {
		sellableItem = CanadianPrices.MUSIC_CD;
		taxableCategory = taxJurisdiction.getTaxableCategoryFor(sellableItem);
		assertNotNull(taxableCategory, "taxableCategory");
		assertEquals(taxableCategory, TaxableCategory.OTHER, "taxableCategory");
	}

	/**
	 * Test method for {@link com.teksystems.salestaxexercise.tax.canada.CanadianTaxJurisdiction#getTaxableCategoryFor(com.teksystems.salestaxexercise.SellableItem)}.
	 */
	@Test
	void testGetTaxableCategoryForBottleOfPerfume() {
		sellableItem = CanadianImportPrices.FRENCH_BOTTLE_OF_PERFUME;
		taxableCategory = taxJurisdiction.getTaxableCategoryFor(sellableItem);
		assertNotNull(taxableCategory, "taxableCategory");
		assertEquals(taxableCategory, TaxableCategory.OTHER, "taxableCategory");
	}

	/**
	 * Test method for {@link com.teksystems.salestaxexercise.tax.AbstractTaxJurisdiction#getAllTaxes()}.
	 */
	@Test
	void testGetAllTaxes() {
		LinkedHashSet<Tax> taxes = taxJurisdiction.getAllTaxes();
		assertNotNull(taxes, "taxes");
		assertEquals(taxes.size(), 2, "all taxes size");
		assertIterableEquals(taxes,
				Arrays.asList(
						taxJurisdiction.getImportSalesTax(),
						taxJurisdiction.getSalesTax()
				)
		);
	}

	private LinkedHashMap<Tax,Money> taxAmounts;
	
	/**
	 * Test method for {@link com.teksystems.salestaxexercise.tax.AbstractTaxJurisdiction#getTaxAmountsFor(com.teksystems.salestaxexercise.SellableItem)}.
	 */
	@Test
	void testGetTaxAmountsForExemptDomesticItem() {
		sellableItem = CanadianPrices.BOOK;
		taxAmounts = taxJurisdiction.getTaxAmountsFor(sellableItem);
		assertNotNull(taxAmounts, "taxAmounts");
		assertEquals(taxAmounts.size(), 2, "taxAmounts size");
		int i = 1;
		for (Map.Entry<Tax,Money> entry : taxAmounts.entrySet()) {
			Tax tax = entry.getKey();
			Money amount = entry.getValue();
			if (i == 1) {
				assertEquals(tax, taxJurisdiction.getImportSalesTax(), "tax " + i);
				assertTrue(amount.isZero(), "amount " + i + " isZero");
			}
			if (i == 2) {
				assertEquals(tax, taxJurisdiction.getSalesTax(), "tax " + i);
				assertTrue(amount.isZero(), "amount " + i + " isZero");
			}
			i++;
		}
	}
	
	/**
	 * Test method for {@link com.teksystems.salestaxexercise.tax.AbstractTaxJurisdiction#getTaxAmountsFor(com.teksystems.salestaxexercise.SellableItem)}.
	 */
	@Test
	void testGetTaxAmountsForNonExemptDomesticItem() {
		sellableItem = CanadianPrices.MUSIC_CD;
		taxAmounts = taxJurisdiction.getTaxAmountsFor(sellableItem);
		assertNotNull(taxAmounts, "taxAmounts");
		assertEquals(taxAmounts.size(), 2, "taxAmounts size");
		int i = 1;
		for (Map.Entry<Tax,Money> entry : taxAmounts.entrySet()) {
			Tax tax = entry.getKey();
			Money amount = entry.getValue();
			if (i == 1) {
				assertEquals(tax, taxJurisdiction.getImportSalesTax(), "tax " + i);
				assertTrue(amount.isZero(), "amount " + i + " isZero");
			}
			if (i == 2) {
				assertEquals(tax, taxJurisdiction.getSalesTax(), "tax " + i);
				assertEquals(amount.getAmount(), new BigDecimal("1.50"), "amount " + i);
			}
			i++;
		}
	}

	/**
	 * Test method for {@link com.teksystems.salestaxexercise.tax.AbstractTaxJurisdiction#getTaxAmountsFor(com.teksystems.salestaxexercise.SellableItem)}.
	 */
	@Test
	void testGetTaxAmountsForExemptImportedItem() {
		sellableItem = CanadianImportPrices.AMERICAN_BOX_OF_CHOCOLATES;
		taxAmounts = taxJurisdiction.getTaxAmountsFor(sellableItem);
		assertNotNull(taxAmounts, "taxAmounts");
		assertEquals(taxAmounts.size(), 2, "taxAmounts size");
		int i = 1;
		for (Map.Entry<Tax,Money> entry : taxAmounts.entrySet()) {
			Tax tax = entry.getKey();
			Money amount = entry.getValue();
			if (i == 1) {
				assertEquals(tax, taxJurisdiction.getImportSalesTax(), "tax " + i);
				assertEquals(amount.getAmount(), new BigDecimal("0.50"), "amount " + i);
			}
			if (i == 2) {
				assertEquals(tax, taxJurisdiction.getSalesTax(), "tax " + i);
				assertTrue(amount.isZero(), "amount " + i + " isZero");
			}
			i++;
		}
	}

	/**
	 * Test method for {@link com.teksystems.salestaxexercise.tax.AbstractTaxJurisdiction#getTaxAmountsFor(com.teksystems.salestaxexercise.SellableItem)}.
	 */
	@Test
	void testGetTaxAmountsForNonExemptImportedItem() {
		sellableItem = CanadianImportPrices.FRENCH_BOTTLE_OF_PERFUME;
		taxAmounts = taxJurisdiction.getTaxAmountsFor(sellableItem);
		assertNotNull(taxAmounts, "taxAmounts");
		assertEquals(taxAmounts.size(), 2, "taxAmounts size");
		int i = 1;
		for (Map.Entry<Tax,Money> entry : taxAmounts.entrySet()) {
			Tax tax = entry.getKey();
			Money amount = entry.getValue();
			if (i == 1) {
				assertEquals(tax, taxJurisdiction.getImportSalesTax(), "tax " + i);
				assertEquals(amount.getAmount(), new BigDecimal("2.40"), "amount " + i);
			}
			if (i == 2) {
				assertEquals(tax, taxJurisdiction.getSalesTax(), "tax " + i);
				assertEquals(amount.getAmount(), new BigDecimal("4.75"), "amount " + i);
			}
			i++;
		}
	}

}
