/**
 * Class that builds and runs the 3 test cases in the problem
 */
package com.teksystems.salestaxexercise.run;

import java.util.ArrayList;
import java.util.List;

import com.teksystems.salestaxexercise.Receipt;

/**
 * Contains the static "main" function used to run and test the entire project.
 * 
 * Prints the requested output to the console.
 * 
 * Also saves the Receipt object(s) generated by running the main function in
 * a member collection so they can be inspected by integration tests
 * 
 * @author Andrew
 *
 */
public class SalesTaxExerciseRunner {
	
	private static ArrayList<Receipt> outputReceipts = new ArrayList<Receipt>(); 

	/**
	 * @return the outputReceipts
	 */
	public static List<Receipt> getOutputReceipts() {
		return outputReceipts;
	}

	/**
	 * @param args ignored
	 */
	public static void main(String[] args) {
		System.out.println("Inside main");
	}

}
