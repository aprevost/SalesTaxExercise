/**
 * 
 */
package com.teksystems.salestaxexercise.runners;

import java.math.BigDecimal;

import org.joda.money.CurrencyUnit;
import org.joda.money.Money;

import com.teksystems.salestaxexercise.goods.Good;
import com.teksystems.salestaxexercise.goods.TaxableGood;
import com.teksystems.salestaxexercise.helpers.RegionHelper;
import com.teksystems.salestaxexercise.tax.CanadianImportTax;
import com.teksystems.salestaxexercise.tax.CanadianSalesTax;
import com.teksystems.salestaxexercise.tax.TaxableCategory;

/**
 * Constructs the objects needed to run the test exercise
 * 
 * Also used in other unit tests throughout the module
 * 
 * @author Andrew
 *
 */
public class TestHelpers {
	public static final CanadianSalesTax SALES_TAX_CANADIAN = new CanadianSalesTax(new BigDecimal(10));
	public static final CanadianSalesTax IMPORT_TAX_CANADIAN = new CanadianImportTax(new BigDecimal(5));
	
	
	public static final TaxableGood BOOK_CANADIAN = new TaxableGood(
			new Good("book", RegionHelper.CANADA),
			RegionHelper.CANADA,
			Money.of(CurrencyUnit.CAD, new BigDecimal("12.49")),
			TaxableCategory.BOOK
	);
	
	public static final TaxableGood MUSIC_CD_CANADIAN = new TaxableGood(
			new Good("music CD", RegionHelper.CANADA),
			RegionHelper.CANADA,
			Money.of(CurrencyUnit.CAD, new BigDecimal("14.99")),
			TaxableCategory.OTHER
	);
	
	public static final TaxableGood CHOCOLATE_BAR_CANADIAN = new TaxableGood(
			new Good("chocolate bar", RegionHelper.CANADA),
			RegionHelper.CANADA,
			Money.of(CurrencyUnit.CAD, new BigDecimal("0.85")),
			TaxableCategory.FOOD
	);
	
	public static final TaxableGood BOX_OF_CHOCOLATES_AMERICAN = new TaxableGood(
			new Good("box of chocolates", RegionHelper.USA),
			RegionHelper.CANADA,
			Money.of(CurrencyUnit.CAD, new BigDecimal("10.00")),
			TaxableCategory.FOOD
	);
	
	public static final TaxableGood BOTTLE_OF_PERFUME_FRENCH = new TaxableGood(
			new Good("bottle of perfume", RegionHelper.FRANCE),
			RegionHelper.CANADA,
			Money.of(CurrencyUnit.CAD, new BigDecimal("47.50")),
			TaxableCategory.OTHER
	);
	
	public static final TaxableGood BOTTLE_OF_PERFUME_AMERICAN = new TaxableGood(
			new Good("bottle of perfume", RegionHelper.USA),
			RegionHelper.CANADA,
			Money.of(CurrencyUnit.CAD, new BigDecimal("27.99")),
			TaxableCategory.OTHER
	);
	
	public static final TaxableGood BOTTLE_OF_PERFUME_CANADIAN = new TaxableGood(
			new Good("bottle of perfume", RegionHelper.CANADA),
			RegionHelper.CANADA,
			Money.of(CurrencyUnit.CAD, new BigDecimal("18.99")),
			TaxableCategory.OTHER
	);
	
	public static final TaxableGood PACKET_OF_HEADACHE_PILLS_CANADIAN = new TaxableGood(
			new Good("packet of headache pills", RegionHelper.CANADA),
			RegionHelper.CANADA,
			Money.of(CurrencyUnit.CAD, new BigDecimal("9.75")),
			TaxableCategory.MEDICAL_PRODUCT
	);
	
	public static final TaxableGood BOX_OF_CHOCOLATES_FRENCH = new TaxableGood(
			new Good("box of chocolates", RegionHelper.FRANCE),
			RegionHelper.CANADA,
			Money.of(CurrencyUnit.CAD, new BigDecimal("11.25")),
			TaxableCategory.FOOD
	);

}
