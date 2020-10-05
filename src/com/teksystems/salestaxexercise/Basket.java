/**
 * 
 */
package com.teksystems.salestaxexercise;

import java.util.LinkedHashMap;

import com.teksystems.salestaxexercise.goods.Good;

/**
 * @author Andrew
 *
 */
public interface Basket {
	
	public void addGood(Good good);
	
	public void removeGood(Good good);
	
	public LinkedHashMap<Good, Integer> getGoods();
	
}
