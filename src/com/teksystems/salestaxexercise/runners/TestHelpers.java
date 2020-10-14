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
 * @author Andrew
 */
public class TestHelpers {
	
	/**
	 * Make this constructor private so it doesn't appear in the Javadoc.
	 */
	private TestHelpers() {
	}
	
	/**
	 * Basic {@link Good} objects corresponding to the goods used in this
	 * exercise.
	 */
	public static class Goods {
		
		/**
		 * Make this constructor private so it doesn't appear in the Javadoc.
		 */
		private Goods() {
		}
		
		/**
		 * {@link Good} objects with Canada as their region of origin.
		 */
		public static class CanadianGoods {

			/**
			 * Make this constructor private so it doesn't appear in the Javadoc.
			 */
			private CanadianGoods() {
			}
			
			/**
			 * A Canadian book.
			 */
			public static final Good BOOK = new Good("book", RegionHelper.CANADA);
			/**
			 * A Canadian music CD.
			 */
			public static final Good MUSIC_CD = new Good("music CD", RegionHelper.CANADA);
			/**
			 * A Canadian chocolate bar.
			 */
			public static final Good CHOCOLATE_BAR = new Good("chocolate bar", RegionHelper.CANADA);
			/**
			 * A Canadian bottle of perfume.
			 */
			public static final Good BOTTLE_OF_PERFUME = new Good("bottle of perfume", RegionHelper.CANADA);
			/**
			 * A Canadian packet of headache pills.
			 */
			public static final Good PACKET_OF_HEADACHE_PILLS = new Good("packet of headache pills", RegionHelper.CANADA);
			
		}
		
		/**
		 * {@link Good} objects with the USA as their region of origin.
		 */
		public static class AmericanGoods {

			/**
			 * Make this constructor private so it doesn't appear in the Javadoc.
			 */
			private AmericanGoods() {
			}
			
			/**
			 * An American box of chocolates.
			 */
			public static final Good BOX_OF_CHOCOLATES = new Good("box of chocolates", RegionHelper.USA);
			/**
			 * An American bottle of perfume.
			 */
			public static final Good BOTTLE_OF_PERFUME = new Good("bottle of perfume", RegionHelper.USA);
			
		}
		
		/**
		 * {@link Good} objects with France as their region of origin.
		 */		
		public static class FrenchGoods {

			/**
			 * Make this constructor private so it doesn't appear in the Javadoc.
			 */
			private FrenchGoods() {
			}
			
			/**
			 * A French bottle of perfume.
			 */
			public static final Good BOTTLE_OF_PERFUME = new Good("bottle of perfume", RegionHelper.FRANCE);
			/**
			 * An French box of chocolates.
			 */
			public static final Good BOX_OF_CHOCOLATES = new Good("box of chocolates", RegionHelper.FRANCE);
			
		}
		
	} //end Goods
	
	/**
	 * {@link PricedGood} objects corresponding to the prices for the goods used in this exercise.
	 */
	public static class PricedGoods {
		
		/**
		 * Make this constructor private so it doesn't appear in the Javadoc.
		 */
		private PricedGoods() {
		}
		
		/**
		 * The {@link PricedGood} list for Canada.
		 */
		public static class CanadianPrices {
			
			/**
			 * Make this constructor private so it doesn't appear in the Javadoc.
			 */
			private CanadianPrices() {
			}
			
			/**
			 * The {@link Goods.CanadianGoods#BOOK} costs $12.49 CAD before taxes in Canada.
			 */
			public static final PricedGood BOOK = new PricedGood(
					Goods.CanadianGoods.BOOK,
					RegionHelper.CANADA,
					Money.of(CurrencyUnit.CAD, 12.49)
			);
			/**
			 * The {@link Goods.CanadianGoods#MUSIC_CD} costs $14.99 CAD before taxes in Canada.
			 */
			public static final PricedGood MUSIC_CD = new PricedGood(
					Goods.CanadianGoods.MUSIC_CD,
					RegionHelper.CANADA,
					Money.of(CurrencyUnit.CAD, 14.99)
			);
			/**
			 * The {@link Goods.CanadianGoods#CHOCOLATE_BAR} costs $0.85 CAD before taxes in Canada.
			 */
			public static final PricedGood CHOCOLATE_BAR = new PricedGood(
					Goods.CanadianGoods.CHOCOLATE_BAR,
					RegionHelper.CANADA,
					Money.of(CurrencyUnit.CAD, 0.85)
			);
			/**
			 * The {@link Goods.CanadianGoods#BOTTLE_OF_PERFUME} costs $18.99 CAD before taxes in Canada.
			 */
			public static final PricedGood BOTTLE_OF_PERFUME = new PricedGood(
					Goods.CanadianGoods.BOTTLE_OF_PERFUME,
					RegionHelper.CANADA,
					Money.of(CurrencyUnit.CAD, 18.99)
			);
			/**
			 * The {@link Goods.CanadianGoods#PACKET_OF_HEADACHE_PILLS} costs $9.75 CAD before taxes in Canada.
			 */
			public static final PricedGood PACKET_OF_HEADACHE_PILLS = new PricedGood(
					Goods.CanadianGoods.PACKET_OF_HEADACHE_PILLS,
					RegionHelper.CANADA,
					Money.of(CurrencyUnit.CAD, 9.75)
			);
			
			/**
			 * The {@link PricedGood}s for Canada that are imports.
			 */
			public static class CanadianImportPrices {

				/**
				 * Make this constructor private so it doesn't appear in the Javadoc.
				 */
				private CanadianImportPrices() {
				}
				
				/**
				 * The {@link Goods.AmericanGoods#BOX_OF_CHOCOLATES} costs $10.00 CAD before taxes in Canada.
				 */
				public static final PricedGood AMERICAN_BOX_OF_CHOCOLATES = new PricedGood(
						Goods.AmericanGoods.BOX_OF_CHOCOLATES,
						RegionHelper.CANADA,
						Money.of(CurrencyUnit.CAD, 10.00)
				);
				/**
				 * The {@link Goods.FrenchGoods#BOTTLE_OF_PERFUME} costs $47.50 CAD before taxes in Canada.
				 */
				public static final PricedGood FRENCH_BOTTLE_OF_PERFUME = new PricedGood(
						Goods.FrenchGoods.BOTTLE_OF_PERFUME,
						RegionHelper.CANADA,
						Money.of(CurrencyUnit.CAD, 47.50)
				);
				/**
				 * The {@link Goods.AmericanGoods#BOTTLE_OF_PERFUME} costs $27.99 CAD before taxes in Canada.
				 */
				public static final PricedGood AMERICAN_BOTTLE_OF_PERFUME = new PricedGood(
						Goods.AmericanGoods.BOTTLE_OF_PERFUME,
						RegionHelper.CANADA,
						Money.of(CurrencyUnit.CAD, 27.99)
				);
				/**
				 * The {@link Goods.FrenchGoods#BOX_OF_CHOCOLATES} costs $11.25 CAD before taxes in Canada.
				 */
				public static final PricedGood FRENCH_BOX_OF_CHOCOLATES = new PricedGood(
						Goods.FrenchGoods.BOX_OF_CHOCOLATES,
						RegionHelper.CANADA,
						Money.of(CurrencyUnit.CAD, 11.25)
				);
				
			} //end CanadianImportPrices

		} //end CanadianPrices
		
	} //end PricedGoods
	
	/**
	 * Pre-built test {@link com.teksystems.salestaxexercise.Basket}s (all Canadian right now).
	 */
	public static class Baskets {

		/**
		 * Make this constructor private so it doesn't appear in the Javadoc.
		 */
		private Baskets() {
		}
		
		/**
		 * The first Canadian test {@link com.teksystems.salestaxexercise.Basket} for this exercise.
		 * 
		 * Contains only domestically produced goods:
		 * <p>
		 * {@link PricedGoods.CanadianPrices#BOOK}<br>
		 * {@link PricedGoods.CanadianPrices#MUSIC_CD}<br>
		 * {@link PricedGoods.CanadianPrices#CHOCOLATE_BAR}
		 * </p>
		 */
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
		
		/**
		 * The second Canadian test {@link com.teksystems.salestaxexercise.Basket} for this exercise.
		 * 
		 * Contains only goods imported in to Canada:
		 * <p>
		 * {@link PricedGoods.CanadianPrices.CanadianImportPrices#AMERICAN_BOX_OF_CHOCOLATES}<br>
		 * {@link PricedGoods.CanadianPrices.CanadianImportPrices#FRENCH_BOTTLE_OF_PERFUME}
		 * </p>
		 */
		public static final TaxableBasketImpl TEST_BASKET_2 = new TaxableBasketImpl(
				CurrencyUnit.CAD,
				RegionHelper.CANADA,
				Arrays.asList(CanadianTaxJurisdiction.getInstance()),
				Arrays.asList(
						PricedGoods.CanadianPrices.CanadianImportPrices.AMERICAN_BOX_OF_CHOCOLATES,
						PricedGoods.CanadianPrices.CanadianImportPrices.FRENCH_BOTTLE_OF_PERFUME
				)
		);
		
		/**
		 * The third Canadian test {@link com.teksystems.salestaxexercise.Basket} for this exercise.
		 * 
		 * Contains a mix of domestically produced and imported goods:
		 * <p>
		 * {@link PricedGoods.CanadianPrices.CanadianImportPrices#AMERICAN_BOTTLE_OF_PERFUME}<br>
		 * {@link PricedGoods.CanadianPrices#BOTTLE_OF_PERFUME}<br>
		 * {@link PricedGoods.CanadianPrices#PACKET_OF_HEADACHE_PILLS}<br>
		 * {@link PricedGoods.CanadianPrices.CanadianImportPrices#FRENCH_BOX_OF_CHOCOLATES}
		 * </p>
		 */
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
