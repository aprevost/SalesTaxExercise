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
 */
public class TestHelpers {
	
	/**
	 * Basic {@link Good} objects corresponding to the goods used in this exercise
	 */
	public static class Goods {
		
		/**
		 * {@link Good} objects with Canada as their region of origin
		 */
		public static class CanadianGoods {

			public static final Good BOOK = new Good("book", RegionHelper.CANADA);
			public static final Good MUSIC_CD = new Good("music CD", RegionHelper.CANADA);
			public static final Good CHOCOLATE_BAR = new Good("chocolate bar", RegionHelper.CANADA);
			public static final Good BOTTLE_OF_PERFUME = new Good("bottle of perfume", RegionHelper.CANADA);
			public static final Good PACKET_OF_HEADACHE_PILLS = new Good("packet of headache pills", RegionHelper.CANADA);
			
		}
		
		/**
		 * {@link Good} objects with the USA as their region of origin
		 */
		public static class AmericanGoods {

			public static final Good BOX_OF_CHOCOLATES = new Good("box of chocolates", RegionHelper.USA);
			public static final Good BOTTLE_OF_PERFUME = new Good("bottle of perfume", RegionHelper.USA);
			
		}
		
		/**
		 * {@link Good} objects with France as their region of origin
		 */		
		public static class FrenchGoods {

			public static final Good BOTTLE_OF_PERFUME = new Good("bottle of perfume", RegionHelper.FRANCE);
			public static final Good BOX_OF_CHOCOLATES = new Good("box of chocolates", RegionHelper.FRANCE);
			
		}
		
	} //end Goods
	
	/**
	 * {@link PricedGood} objects corresponding to the prices for the goods used in this exercise
	 */
	public static class PricedGoods {
		
		/**
		 * The {@link PricedGood} list for Canada
		 */
		public static class CanadianPrices {
			
			public static final PricedGood BOOK = new PricedGood(
					Goods.CanadianGoods.BOOK,
					RegionHelper.CANADA,
					Money.of(CurrencyUnit.CAD, 12.49)
			);
			public static final PricedGood MUSIC_CD = new PricedGood(
					Goods.CanadianGoods.MUSIC_CD,
					RegionHelper.CANADA,
					Money.of(CurrencyUnit.CAD, 14.99)
			);
			public static final PricedGood CHOCOLATE_BAR = new PricedGood(
					Goods.CanadianGoods.CHOCOLATE_BAR,
					RegionHelper.CANADA,
					Money.of(CurrencyUnit.CAD, 0.85)
			);
			public static final PricedGood BOTTLE_OF_PERFUME = new PricedGood(
					Goods.CanadianGoods.BOTTLE_OF_PERFUME,
					RegionHelper.CANADA,
					Money.of(CurrencyUnit.CAD, 18.99)
			);
			public static final PricedGood PACKET_OF_HEADACHE_PILLS = new PricedGood(
					Goods.CanadianGoods.PACKET_OF_HEADACHE_PILLS,
					RegionHelper.CANADA,
					Money.of(CurrencyUnit.CAD, 9.75)
			);
			
			/**
			 * The {@link PricedGood}s for Canada that are imports
			 */
			public static class CanadianImportPrices {

				public static final PricedGood AMERICAN_BOX_OF_CHOCOLATES = new PricedGood(
						Goods.AmericanGoods.BOX_OF_CHOCOLATES,
						RegionHelper.CANADA,
						Money.of(CurrencyUnit.CAD, 10.00)
				);
				public static final PricedGood FRENCH_BOTTLE_OF_PERFUME = new PricedGood(
						Goods.FrenchGoods.BOTTLE_OF_PERFUME,
						RegionHelper.CANADA,
						Money.of(CurrencyUnit.CAD, 47.50)
				);
				public static final PricedGood AMERICAN_BOTTLE_OF_PERFUME = new PricedGood(
						Goods.AmericanGoods.BOTTLE_OF_PERFUME,
						RegionHelper.CANADA,
						Money.of(CurrencyUnit.CAD, 27.99)
				);
				public static final PricedGood FRENCH_BOX_OF_CHOCOLATES = new PricedGood(
						Goods.FrenchGoods.BOX_OF_CHOCOLATES,
						RegionHelper.CANADA,
						Money.of(CurrencyUnit.CAD, 11.25)
				);
				
			} //end Imports

		} //end Canada
		
	} //end PricedGoods
	
	/**
	 * Pre-built test baskets (all Canadian right now)
	 */
	public static class Baskets {

		public static final TaxableBasketImpl TEST_BASKET_1 = new TaxableBasketImpl(
				CurrencyUnit.CAD,
				RegionHelper.CANADA,
				Arrays.asList(CanadianTaxJurisdiction.getInstance()),
				Arrays.asList(
						PricedGoods.CanadianPrices.BOOK,
						PricedGoods.CanadianPrices.MUSIC_CD,
						PricedGoods.CanadianPrices.CHOCOLATE_BAR
				)
		);
		
		public static final TaxableBasketImpl TEST_BASKET_2 = new TaxableBasketImpl(
				CurrencyUnit.CAD,
				RegionHelper.CANADA,
				Arrays.asList(CanadianTaxJurisdiction.getInstance()),
				Arrays.asList(
						PricedGoods.CanadianPrices.CanadianImportPrices.AMERICAN_BOX_OF_CHOCOLATES,
						PricedGoods.CanadianPrices.CanadianImportPrices.FRENCH_BOTTLE_OF_PERFUME
				)
		);
		
		public static final TaxableBasketImpl TEST_BASKET_3 = new TaxableBasketImpl(
				CurrencyUnit.CAD,
				RegionHelper.CANADA,
				Arrays.asList(CanadianTaxJurisdiction.getInstance()),
				Arrays.asList(
						PricedGoods.CanadianPrices.CanadianImportPrices.AMERICAN_BOTTLE_OF_PERFUME,
						PricedGoods.CanadianPrices.BOTTLE_OF_PERFUME,
						PricedGoods.CanadianPrices.PACKET_OF_HEADACHE_PILLS,
						PricedGoods.CanadianPrices.CanadianImportPrices.FRENCH_BOX_OF_CHOCOLATES
				)
		);
		
	}

}
