/**
 * 
 */
package com.teksystems.salestaxexercise.runners;

import java.util.Arrays;

import org.joda.money.CurrencyUnit;
import org.joda.money.Money;

import com.teksystems.salestaxexercise.TaxableBasketImpl;
import com.teksystems.salestaxexercise.goods.Good;
import com.teksystems.salestaxexercise.goods.PricedGood;
import com.teksystems.salestaxexercise.helpers.RegionHelper;
import com.teksystems.salestaxexercise.tax.canada.CanadianTaxJurisdiction;

/**
 * Constructs objects that simplify testing this module.
 * 
 * TODO: the constants in this class should be reorganized in to subclasses for
 * GOOD, PRICED_GOOD, BASKET
 * 
 * @author Andrew
 *
 */
public class TestHelpers {
	
	//Basic Good objects corresponding to the goods used in this exercise
	public static final Good CANADIAN_BOOK = new Good("book", RegionHelper.CANADA);
	public static final Good CANADIAN_MUSIC_CD = new Good("music CD", RegionHelper.CANADA);
	public static final Good CANADIAN_CHOCOLATE_BAR = new Good("chocolate bar", RegionHelper.CANADA);	
	public static final Good AMERICAN_BOX_OF_CHOCOLATES = new Good("box of chocolates", RegionHelper.USA);
	public static final Good FRENCH_BOTTLE_OF_PERFUME = new Good("bottle of perfume", RegionHelper.FRANCE);
	public static final Good AMERICAN_BOTTLE_OF_PERFUME = new Good("bottle of perfume", RegionHelper.USA);
	public static final Good CANADIAN_BOTTLE_OF_PERFUME = new Good("bottle of perfume", RegionHelper.CANADA);
	public static final Good CANADIAN_PACKET_OF_HEADACHE_PILLS = new Good("packet of headache pills", RegionHelper.CANADA);
	public static final Good FRENCH_BOX_OF_CHOCOLATES = new Good("box of chocolates", RegionHelper.FRANCE);

	//The prices for those goods, when sold in Canada
	public static final PricedGood CANADIAN_BOOK_PRICE = new PricedGood(
			CANADIAN_BOOK,
			RegionHelper.CANADA,
			Money.of(CurrencyUnit.CAD, 12.49)
	);
	public static final PricedGood CANADIAN_MUSIC_CD_PRICE = new PricedGood(
			CANADIAN_MUSIC_CD,
			RegionHelper.CANADA,
			Money.of(CurrencyUnit.CAD, 14.99)
	);
	public static final PricedGood CANADIAN_CHOCOLATE_BAR_PRICE = new PricedGood(
			CANADIAN_CHOCOLATE_BAR,
			RegionHelper.CANADA,
			Money.of(CurrencyUnit.CAD, 0.85)
	);
	public static final PricedGood AMERICAN_BOX_OF_CHOCOLATES_PRICE = new PricedGood(
			AMERICAN_BOX_OF_CHOCOLATES,
			RegionHelper.CANADA,
			Money.of(CurrencyUnit.CAD, 10.00)
	);
	public static final PricedGood FRENCH_BOTTLE_OF_PERFUME_PRICE = new PricedGood(
			FRENCH_BOTTLE_OF_PERFUME,
			RegionHelper.CANADA,
			Money.of(CurrencyUnit.CAD, 47.50)
	);
	public static final PricedGood AMERICAN_BOTTLE_OF_PERFUME_PRICE = new PricedGood(
			AMERICAN_BOTTLE_OF_PERFUME,
			RegionHelper.CANADA,
			Money.of(CurrencyUnit.CAD, 27.99)
	);
	public static final PricedGood CANADIAN_BOTTLE_OF_PERFUME_PRICE = new PricedGood(
			CANADIAN_BOTTLE_OF_PERFUME,
			RegionHelper.CANADA,
			Money.of(CurrencyUnit.CAD, 18.99)
	);
	public static final PricedGood CANADIAN_PACKET_OF_HEADACHE_PILLS_PRICE = new PricedGood(
			CANADIAN_PACKET_OF_HEADACHE_PILLS,
			RegionHelper.CANADA,
			Money.of(CurrencyUnit.CAD, 9.75)
	);
	public static final PricedGood FRENCH_BOX_OF_CHOCOLATES_PRICE = new PricedGood(
			FRENCH_BOX_OF_CHOCOLATES,
			RegionHelper.CANADA,
			Money.of(CurrencyUnit.CAD, 11.25)
	);
	
	public static final TaxableBasketImpl TEST_BASKET_1 = new TaxableBasketImpl(
			CurrencyUnit.CAD,
			RegionHelper.CANADA,
			Arrays.asList(CanadianTaxJurisdiction.getInstance()),
			Arrays.asList(
					CANADIAN_BOOK_PRICE,
					CANADIAN_MUSIC_CD_PRICE,
					CANADIAN_CHOCOLATE_BAR_PRICE
			)
	);

	public static final TaxableBasketImpl TEST_BASKET_2 = new TaxableBasketImpl(
			CurrencyUnit.CAD,
			RegionHelper.CANADA,
			Arrays.asList(CanadianTaxJurisdiction.getInstance()),
			Arrays.asList(
					AMERICAN_BOX_OF_CHOCOLATES_PRICE,
					FRENCH_BOTTLE_OF_PERFUME_PRICE
			)
	);

	public static final TaxableBasketImpl TEST_BASKET_3 = new TaxableBasketImpl(
			CurrencyUnit.CAD,
			RegionHelper.CANADA,
			Arrays.asList(CanadianTaxJurisdiction.getInstance()),
			Arrays.asList(
					AMERICAN_BOTTLE_OF_PERFUME_PRICE,
					CANADIAN_BOTTLE_OF_PERFUME_PRICE,
					CANADIAN_PACKET_OF_HEADACHE_PILLS_PRICE,
					FRENCH_BOX_OF_CHOCOLATES_PRICE
			)
	);

}
