/**
 * 
 */
package com.teksystems.salestaxexercise.helpers;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.joda.money.Money;

/**
 * Helper class that can be used to round any {@link org.joda.money.Money} object to a multiple of any
 * arbitrary amount.
 * 
 * (e.g. a multiple of 5 cents, a multiple of 2 dollars, etc.)
 * 
 * @author Andrew
 *
 */
public class MoneyRoundingRule {
	
	/**
	 * The default value to round to, if none is specified - 0.05
	 */
	public static final BigDecimal ROUND_TO_MULTIPLE_OF_DEFAULT = new BigDecimal("0.05");
	
	private BigDecimal roundToMultipleOf = ROUND_TO_MULTIPLE_OF_DEFAULT;
	
	/**
	 * This rule will round input amounts to a multiple of this amount, never null
	 * 
	 * @return the interval amount to round to, never null
	 */
	public BigDecimal getRoundToMultipleOf() {
		return roundToMultipleOf;
	}
	
	/**
	 * The default rounding mode used in this class, if none is specified, is {@link java.math.RoundingMode#UP}
	 */
	public static final RoundingMode ROUNDING_MODE_DEFAULT = RoundingMode.UP;
	
	private RoundingMode roundingMode = ROUNDING_MODE_DEFAULT;
	
	/**
	 * See {@link java.math.RoundingMode} for possible values
	 * 
	 * @return the RoundingMode used by this rule, never null
	 */
	public RoundingMode getRoundingMode() {
		return roundingMode;
	}
	
	/**
	 * Static function that can be used to round a Money object to a multiple
	 * of any arbitrary amount
	 * (e.g. a multiple of 5 cents, a multiple of 2 dollars, etc.)
	 * 
	 * @param value the Money object to be rounded (if null, will return null)
	 * @param roundToMultipleOf the amount to which the input value should be rounded (if null or 0, value will be returned unmodified)
	 * @param roundingMode to use (if null, defaults to {@link MoneyRoundingRule#ROUNDING_MODE_DEFAULT})
	 * 
	 * @return a new Money object with a rounded amount, but the same currency as the input value
	 */
	public static Money roundMoney(Money value, BigDecimal roundToMultipleOf, RoundingMode roundingMode) {
		//The 2nd and 3rd checks aren't necessary if called by an instance of this class
		//as the constructor does it for you
		if (value == null || roundToMultipleOf == null || roundToMultipleOf.signum() == 0) {
			//TODO: if I had a logging class implemented, I would log this as a low-level warning, shouldn't really ever happen
			return value;
		}
		
		if (roundingMode == null) {
			roundingMode = ROUNDING_MODE_DEFAULT;
		}
		
		//First convert the amount of money to a BigDecimal
		BigDecimal amount = value.getAmount();
		
		//Divide the amount by the unit we are rounding to
		//With scale set to 0 (meaning 0 decimal places, result must be a whole number)
		//And with the specified rounding mode
		BigDecimal divided = amount.divide(roundToMultipleOf, 0, roundingMode);
		
		//Now to get the rounded amount, multiply again by the unit we are rounding to
		//And convert back to a Money object with the same currency as the input value
		return value.withAmount(divided.multiply(roundToMultipleOf));
	}
	
	/**
	 * Rounds the input money object
	 * 
	 * Just wraps the static {@link MoneyRoundingRule#roundMoney(Money, BigDecimal, RoundingMode)}
	 * function, passing in the instantiated object's stored rounding settings
	 * 
	 * @param value the Money object to be rounded (if null, null will be returned)
	 * 
	 * @return a new Money object with a rounded amount, but the same currency as the input value
	 */
	public Money round(Money value) {
		return roundMoney(value, roundToMultipleOf, roundingMode);
	}
	
	/**
	 * Instantiated objects of this class are basically just wrappers around
	 * around the static {@link MoneyRoundingRule#roundMoney(Money, BigDecimal, RoundingMode)}
	 * function to allow you to store and reuse the same rounding rules over
	 * and over again with calls to {@link MoneyRoundingRule#round(Money)}.
	 * 
	 * @param roundToMultipleOf the amount to which the input value should be rounded (if null, defaults to {@link MoneyRoundingRule#ROUND_TO_MULTIPLE_OF_DEFAULT})
	 * @param roundingMode the rounding mode to use (if null, defaults to {@link MoneyRoundingRule#ROUNDING_MODE_DEFAULT})
	 * 
	 * @throws IllegalArgumentException if the specified amount we're supposed to round to is zero
	 */
	public MoneyRoundingRule(BigDecimal roundToMultipleOf, RoundingMode roundingMode) {
		if (roundToMultipleOf != null) {
			if (roundToMultipleOf.signum() == 0) {
				throw new IllegalArgumentException("roundToMultipleOf cannot be zero");
			}
			this.roundToMultipleOf = roundToMultipleOf;			
		}
		if (roundingMode != null) {
			this.roundingMode = roundingMode;
		}
	}

}
