/**
 * Contains integration tests for the exercise as a whole
 */
package com.teksystems.salestaxexercise.runners;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

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
		assertNotNull(SalesTaxExerciseRunner.getOutputReceipts());
	}

}
