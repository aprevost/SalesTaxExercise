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
import com.teksystems.salestaxexercise.runners.TestHelpers;
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
		sellableItem = TestHelpers.CANADIAN_BOOK_PRICE;
		taxableCategory = taxJurisdiction.getTaxableCategoryFor(sellableItem);
		assertNotNull(taxableCategory, "taxableCategory");
		assertEquals(taxableCategory, TaxableCategory.BOOK, "taxableCategory");
	}

	/**
	 * Test method for {@link com.teksystems.salestaxexercise.tax.canada.CanadianTaxJurisdiction#getTaxableCategoryFor(com.teksystems.salestaxexercise.SellableItem)}.
	 */
	@Test
	void testGetTaxableCategoryForChocolateBar() {
		sellableItem = TestHelpers.CANADIAN_CHOCOLATE_BAR_PRICE;
		taxableCategory = taxJurisdiction.getTaxableCategoryFor(sellableItem);
		assertNotNull(taxableCategory, "taxableCategory");
		assertEquals(taxableCategory, TaxableCategory.FOOD, "taxableCategory");
	}

	/**
	 * Test method for {@link com.teksystems.salestaxexercise.tax.canada.CanadianTaxJurisdiction#getTaxableCategoryFor(com.teksystems.salestaxexercise.SellableItem)}.
	 */
	@Test
	void testGetTaxableCategoryForBoxOfChocolates() {
		sellableItem = TestHelpers.AMERICAN_BOX_OF_CHOCOLATES_PRICE;
		taxableCategory = taxJurisdiction.getTaxableCategoryFor(sellableItem);
		assertNotNull(taxableCategory, "taxableCategory");
		assertEquals(taxableCategory, TaxableCategory.FOOD, "taxableCategory");
	}

	/**
	 * Test method for {@link com.teksystems.salestaxexercise.tax.canada.CanadianTaxJurisdiction#getTaxableCategoryFor(com.teksystems.salestaxexercise.SellableItem)}.
	 */
	@Test
	void testGetTaxableCategoryForHeadachePills() {
		sellableItem = TestHelpers.CANADIAN_PACKET_OF_HEADACHE_PILLS_PRICE;
		taxableCategory = taxJurisdiction.getTaxableCategoryFor(sellableItem);
		assertNotNull(taxableCategory, "taxableCategory");
		assertEquals(taxableCategory, TaxableCategory.MEDICAL_PRODUCT, "taxableCategory");
	}

	/**
	 * Test method for {@link com.teksystems.salestaxexercise.tax.canada.CanadianTaxJurisdiction#getTaxableCategoryFor(com.teksystems.salestaxexercise.SellableItem)}.
	 */
	@Test
	void testGetTaxableCategoryForMusicCD() {
		sellableItem = TestHelpers.CANADIAN_MUSIC_CD_PRICE;
		taxableCategory = taxJurisdiction.getTaxableCategoryFor(sellableItem);
		assertNotNull(taxableCategory, "taxableCategory");
		assertEquals(taxableCategory, TaxableCategory.OTHER, "taxableCategory");
	}

	/**
	 * Test method for {@link com.teksystems.salestaxexercise.tax.canada.CanadianTaxJurisdiction#getTaxableCategoryFor(com.teksystems.salestaxexercise.SellableItem)}.
	 */
	@Test
	void testGetTaxableCategoryForBottleOfPerfume() {
		sellableItem = TestHelpers.FRENCH_BOTTLE_OF_PERFUME_PRICE;
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
		sellableItem = TestHelpers.CANADIAN_BOOK_PRICE;
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
		sellableItem = TestHelpers.CANADIAN_MUSIC_CD_PRICE;
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
		sellableItem = TestHelpers.AMERICAN_BOX_OF_CHOCOLATES_PRICE;
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
		sellableItem = TestHelpers.FRENCH_BOTTLE_OF_PERFUME_PRICE;
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
