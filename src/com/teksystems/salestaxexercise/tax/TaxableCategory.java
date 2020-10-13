/**
 * 
 */
package com.teksystems.salestaxexercise.tax;

/**
 * Enum of all possible categories of goods, for tax purposes.
 * 
 * TODO: in a real system, the data structure for handling this would need to
 * be more complex than an enum, should be a {@link TaxJurisdiction}-specific
 * class with that jurisdction as one of its properties, as the list of
 * possible categories would be completely different for different
 * jurisdictions.
 * 
 * @author Andrew
 *
 */
public enum TaxableCategory {
	BOOK,
	FOOD,
	MEDICAL_PRODUCT,
	OTHER
}
