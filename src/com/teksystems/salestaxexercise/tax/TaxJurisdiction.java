package com.teksystems.salestaxexercise.tax;

import java.util.LinkedHashMap;
import java.util.LinkedHashSet;

import com.ibm.icu.util.Region;

import org.joda.money.Money;

/**
 * @author Andrew
 *
 */
public interface TaxJurisdiction {
	
	public Region getRegion();
	
	public LinkedHashSet<Tax> getAllTaxes();
	
	public LinkedHashSet<Tax> getAllTaxesFor(Taxable price);
	
	public LinkedHashMap<Tax,Money> getItemizedTaxesFor(Taxable price);
		
	public Money getTotalTaxAmountFor(Taxable price);
	
	public Money getTotalPriceWithTaxFor(Taxable price);

}
