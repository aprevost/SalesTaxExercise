/**
 * 
 */
package com.teksystems.salestaxexercise.tax;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashSet;

import org.joda.money.Money;

import com.teksystems.salestaxexercise.Priceable;
import com.teksystems.salestaxexercise.goods.Good;
import com.teksystems.salestaxexercise.goods.TaxableGood;

/**
 * @author Andrew
 *
 */
public class CanadianSalesTax implements Tax {
	
	private BigDecimal percentage = new BigDecimal("0.1");

	/**
	 * @return the percentage
	 */
	public BigDecimal getPercentage() {
		return percentage;
	}
	
	private BigDecimal roundTaxToMultipleOf = new BigDecimal("0.05");
	private RoundingMode taxRoundingMode = RoundingMode.UP;
	
	private static Money roundMoney(Money value, BigDecimal roundToMultipleOf, RoundingMode roundingMode) {
		if (roundToMultipleOf == null || roundToMultipleOf.signum() == 0) {
			// Don't allow division by 0
			return value;
		}
		else {
			//Convert the amount of money to a BigDecimal
			BigDecimal amount = value.getAmount();
			//First divide the amount by the unit we are rounding to
			//With scale set to 0 (meaning 0 decimal places, result must be a whole number)
			//And with the specified rounding mode
			BigDecimal divided = amount.divide(roundToMultipleOf, 0, roundingMode);
			
			//Now multiply again by the unit we are rounding to, 
			return value.withAmount(divided.multiply(roundToMultipleOf));
		}
	}
	
	private Money calculateSalesTax(Money shelfPrice) {
		Money unroundedTaxAmount = shelfPrice.multipliedBy(getPercentage(), taxRoundingMode);
		//Now round the tax using the specified rules
		return roundMoney(unroundedTaxAmount, roundTaxToMultipleOf, taxRoundingMode);
	}
	
	private HashSet<TaxableGoodCategory> exemptedCategories = new HashSet<TaxableGoodCategory>();

	@Override
	public Money getTaxAmountFor(Taxable price) {
		
		if (price == null) {
			return null;
		}
		
		Money shelfPrice = price.getShelfPrice();
		if (shelfPrice == null) {
			return null;
		}
		
		//Use shelfPrice.withAmount so that currency of the shelf price is maintained
		//for the tax amount of 0 as well
		Money zeroTax = shelfPrice.withAmount(BigDecimal.ZERO);
		
		if (shelfPrice.isZero()) {
			return zeroTax;
		}
		
		Money taxAmount = calculateSalesTax(shelfPrice);
		
		if (!(price instanceof TaxableGood)) {
			return taxAmount;
		}
		
		TaxableGood taxedGood = (TaxableGood) price;
		TaxableGoodCategory category = taxedGood.getTaxableGoodCategory();
		if (category == null || !exemptedCategories.contains(category)) {
			return taxAmount;
		}
		return zeroTax;
		
	}
	
	public CanadianSalesTax() {
		exemptedCategories.add(TaxableGoodCategory.BOOK);
		exemptedCategories.add(TaxableGoodCategory.FOOD);
		exemptedCategories.add(TaxableGoodCategory.MEDICAL_PRODUCT);
	}

}
