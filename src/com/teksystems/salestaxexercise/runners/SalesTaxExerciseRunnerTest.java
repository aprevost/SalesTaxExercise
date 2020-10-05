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
		assertNotNull(outputReceipts, "outputReceipts is null");
		assertEquals(outputReceipts.size(), 3, "outputReceipts size is not 3");
		assertEquals(
				outputReceipts.get(0).toString(), 
					"1 book: 12.49\r\n" + 
					"1 music CD: 16.49\r\n" + 
					"1 chocolate bar: 0.85\r\n" + 
					"Sales Taxes: 1.50 Total: 29.83\r\n",
				"outputReceipts[0] does not match expected string"
		);
	}

}
