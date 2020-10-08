/**
 * 
 */
package com.teksystems.salestaxexercise.tax;

/**
 * The list of different categories of goods, for tax purposes
 * 
 * TODO: in a real system, the data structure for handling this would need to
 * be more complex than an enum, should be a class with a TaxJurisdiction 
 * as one of its properties, as the list of categories would be completely
 * different for different jurisdictions
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
