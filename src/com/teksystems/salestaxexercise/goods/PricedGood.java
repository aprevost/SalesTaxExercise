/**
 * 
 */
package com.teksystems.salestaxexercise.goods;

import com.teksystems.salestaxexercise.Priceable;

/**
 * @author Andrew
 *
 */
public interface PricedGood extends Priceable {
	
	public Good getGood();
	
}
