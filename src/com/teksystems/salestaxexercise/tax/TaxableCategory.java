/**
 * 
 */
package com.teksystems.salestaxexercise.tax;

/**
 * Enum of all possible categories of goods, for tax purposes.
 * <p>
 * TODO: in a real system, the data structure for handling this would need to
 * be more complex than an enum, should be a {@link TaxJurisdiction}-specific
 * class with that jurisdction as one of its properties, as the list of
 * possible categories would be completely different for different
 * jurisdictions.
 * </p>
 * @author Andrew
 */
public enum TaxableCategory {
	/**
	 * A book.
	 */
	BOOK,
	/**
	 * A food item.
	 */
	FOOD,
	/**
	 * A medical product.
	 */
	MEDICAL_PRODUCT,
	/**
	 * Default/catch-all category for anything that is not a book, a food item, or a medical product.
	 */
	OTHER
}
