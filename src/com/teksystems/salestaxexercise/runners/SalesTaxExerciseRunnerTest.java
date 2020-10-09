/**
 * Contains integration tests for the exercise as a whole
 */
package com.teksystems.salestaxexercise.runners;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.teksystems.salestaxexercise.Receiptable;

/**
 * @author Andrew
 *
 */
class SalesTaxExerciseRunnerTest {

	/**
	 * Test method for {@link com.teksystems.salestaxexercise.runners.SalesTaxExerciseRunner#main(java.lang.String[])}.
	 */
	@Test
	void testMain() {
		SalesTaxExerciseRunner.main(null);
		List<Receiptable> outputReceipts = SalesTaxExerciseRunner.getOutputReceipts();
		assertNotNull(outputReceipts, "outputReceipts");
		assertEquals(outputReceipts.size(), 3, "outputReceipts.size");
		assertEquals(
				outputReceipts.get(0).toString(), 
				"1 book: 12.49" + System.lineSeparator()
					+ "1 music CD: 16.49" + System.lineSeparator()
					+ "1 chocolate bar: 0.85" + System.lineSeparator()
					+ "Sales Taxes: 1.50 Total: 29.83" + System.lineSeparator(),
				"outputReceipts[0]"
		);
		assertEquals(
				outputReceipts.get(1).toString(), 
				"1 imported box of chocolates: 10.50" + System.lineSeparator()
					+ "1 imported bottle of perfume: 54.65" + System.lineSeparator()
					+ "Sales Taxes: 7.65 Total: 65.15" + System.lineSeparator(),
				"outputReceipts[0]"
		);
		assertEquals(
				outputReceipts.get(2).toString(), 
				"1 imported bottle of perfume: 32.19" + System.lineSeparator()
					+ "1 bottle of perfume: 20.89" + System.lineSeparator()
					+ "1 packet of headache pills: 9.75" + System.lineSeparator()
					+ "1 imported box of chocolates: 11.85" + System.lineSeparator()
					+ "Sales Taxes: 6.70 Total: 74.68" + System.lineSeparator(),
				"outputReceipts[0]"
		);
	}

}
