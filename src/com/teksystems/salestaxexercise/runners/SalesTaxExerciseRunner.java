/**
 * 
 */
package com.teksystems.salestaxexercise.runners;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.teksystems.salestaxexercise.Receiptable;
import com.teksystems.salestaxexercise.runners.TestHelpers.Baskets;

/**
 * Contains the static {@link #main(String[])} function used to run and test
 * the entire project.
 * 
 * Builds and runs the 3 test cases in the problem, then prints the requested
 * output to the console.
 * 
 * Also saves the {@link Receiptable} object(s) generated by running the main
 * function in a member collection so they can be inspected by an automated
 * end-to-end test.
 * 
 * @author Andrew
 *
 */
public class SalesTaxExerciseRunner {
	
	/**
	 * Make this constructor private so it doesn't appear in the Javadoc.
	 */
	private SalesTaxExerciseRunner() {
	}
	
	private static ArrayList<Receiptable> outputReceipts = new ArrayList<Receiptable>(); 

	/**
	 * Returns the output receipt objects.
	 * 
	 * Never null, but always empty if the main function hasn't been run yet.
	 * 
	 * Getter included only to simplify end-to-end tests.
	 * 
	 * @return The output receipt objects. Never null.
	 */
	public static List<Receiptable> getOutputReceipts() {
		return outputReceipts;
	}

	/**
	 * The main function used to run and test the entire project.
	 * 
	 * Prints the desired output to the console.
	 * 
	 * @param args ignored
	 */
	public static void main(String[] args) {
		outputReceipts.addAll(Arrays.asList(
				Baskets.TEST_BASKET_1,
				Baskets.TEST_BASKET_2,
				Baskets.TEST_BASKET_3
		));
		int count = 1;
		for (Receiptable receipt : outputReceipts) {
			System.out.println("Output " + count + ":");
			receipt.printReceipt();
			System.out.println();
			count++;
		}
	}

}
