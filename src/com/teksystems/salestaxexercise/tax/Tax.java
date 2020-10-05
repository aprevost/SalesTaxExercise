/**
 * 
 */
package com.teksystems.salestaxexercise.tax;

import org.joda.money.Money;

/**
 * @author Andrew
 *
 */
public interface Tax {

	public Money getTaxAmountFor(Taxable price);

}
