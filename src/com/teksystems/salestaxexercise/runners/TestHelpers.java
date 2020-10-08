/**
 * 
 */
package com.teksystems.salestaxexercise.runners;

import java.math.BigDecimal;

import org.joda.money.CurrencyUnit;
import org.joda.money.Money;

import com.teksystems.salestaxexercise.goods.Good;
import com.teksystems.salestaxexercise.goods.PricedGood;
import com.teksystems.salestaxexercise.helpers.RegionHelper;

/**
 * Constructs the objects needed to run the test exercise
 * 
 * Also used in other unit tests throughout the module
 * 
 * In a real system these objects would all be built dynamically based on
 * data retrieved from a data source
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
			Money.of(CurrencyUnit.CAD, new BigDecimal("12.49"))
	);
	public static final PricedGood CANADIAN_MUSIC_CD_PRICE = new PricedGood(
			CANADIAN_MUSIC_CD,
			RegionHelper.CANADA,
			Money.of(CurrencyUnit.CAD, new BigDecimal("14.99"))
	);
	public static final PricedGood CANADIAN_CHOCOLATE_BAR_PRICE = new PricedGood(
			CANADIAN_CHOCOLATE_BAR,
			RegionHelper.CANADA,
			Money.of(CurrencyUnit.CAD, new BigDecimal("0.85"))
	);
	public static final PricedGood AMERICAN_BOX_OF_CHOCOLATES_PRICE = new PricedGood(
			AMERICAN_BOX_OF_CHOCOLATES,
			RegionHelper.CANADA,
			Money.of(CurrencyUnit.CAD, new BigDecimal("10.00"))
	);
	public static final PricedGood FRENCH_BOTTLE_OF_PERFUME_PRICE = new PricedGood(
			FRENCH_BOTTLE_OF_PERFUME,
			RegionHelper.CANADA,
			Money.of(CurrencyUnit.CAD, new BigDecimal("47.50"))
	);
	public static final PricedGood AMERICAN_BOTTLE_OF_PERFUME_PRICE = new PricedGood(
			AMERICAN_BOTTLE_OF_PERFUME,
			RegionHelper.CANADA,
			Money.of(CurrencyUnit.CAD, new BigDecimal("27.99"))
	);
	public static final PricedGood CANADIAN_BOTTLE_OF_PERFUME_PRICE = new PricedGood(
			CANADIAN_BOTTLE_OF_PERFUME,
			RegionHelper.CANADA,
			Money.of(CurrencyUnit.CAD, new BigDecimal("18.99"))
	);
	public static final PricedGood CANADIAN_PACKET_OF_HEADACHE_PILLS_PRICE = new PricedGood(
			CANADIAN_PACKET_OF_HEADACHE_PILLS,
			RegionHelper.CANADA,
			Money.of(CurrencyUnit.CAD, new BigDecimal("9.75"))
	);
	public static final PricedGood FRENCH_BOX_OF_CHOCOLATES_PRICE = new PricedGood(
			FRENCH_BOX_OF_CHOCOLATES,
			RegionHelper.CANADA,
			Money.of(CurrencyUnit.CAD, new BigDecimal("11.25"))
	);

}
