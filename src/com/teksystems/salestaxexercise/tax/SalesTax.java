/**
 * 
 */
package com.teksystems.salestaxexercise.tax;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.joda.money.Money;

import com.teksystems.salestaxexercise.Priceable;
import com.teksystems.salestaxexercise.goods.Good;
import com.teksystems.salestaxexercise.goods.TaxedGood;

/**
 * @author Andrew
 *
 */
public class SalesTax implements Tax {
	
	private BigDecimal percentage = new BigDecimal("0.1");

	/**
	 * @return the percentage
	 */
	public BigDecimal getPercentage() {
		return percentage;
	}
	
	//TODO: implement this function properly!
	private Money roundTaxAmount(Money tax) {
		return tax;
	}
	
	private Money calculateSalesTax(Money shelfPrice) {
		return Money.parse("0.05");
	}

	@Override
	public Money getTaxAmountFor(Taxable price) {
		
		Money taxAmount = Money.parse("0.00");
		if (price == null) {
			return taxAmount;
		}
		
		Money shelfPrice = price.getShelfPrice();
		if (shelfPrice == null || shelfPrice.isZero()) {
			return taxAmount;
		}
		
		
		if (!(price instanceof TaxedGood)) {
			return taxAmount;
		}
		
		TaxedGood pricedGood = (TaxedGood) price;
		TaxedGoodCategory category = pricedGood.getCategory();
		if (category == null) {
			return this.calculateSalesTax(shelfPrice);
		}
		
		//Apply exemptions
		switch (category) {
			case BOOK:
			case FOOD:
			case MEDICAL_PRODUCT:
				break;
		default:
			taxAmount = roundTaxAmount(shelfPrice.multipliedBy(this.getPercentage(),RoundingMode.UP));
		}
		return taxAmount;
	}

}
