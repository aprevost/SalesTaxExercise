# SalesTaxExercise
Java coding exercise from TekSystems.

The full description of the exercise can be found here. TODO: insert link

Technical dependencies:

1) this project was implemented, tested, and run in Eclipse Version: 2020-06 (4.16.0).

- the Eclipse project files are included in the repository. So it should be possible to create the new Eclipse project on your local machine by simply running File -> Import... -> Git -> Projects from Git.

- the project files should be backwards-compatible with previous recent versions of Eclipse, but this has not been tested.

2) The JDK used for implementing, testing, and running this project was JDK 14.0.2.

- I did not knowingly make use of any recent additions to Java, so the code should be backwards compatible with any Java 8 or higher JDK, but this has not been tested.

3) The project depends on JUnit 5 for the unit and integration (end-to-end) tests.


Assumptions made:

As an architect, these are things I would have asked the customer/business folks in our initial discovery sessions before I finalized the design.

1) this is code that will live for a long time, as part of a system that is expected to grow and evolve.

- there is such a thing as over-engineering. Much of this design would be overkill and not necessary for the purposes of a throw-away demo or prototype.

2) the business side owners/users of this system most commonly use the word "goods" to describe the products they sell, in general.

- I would generally prefer to use a more generic word that doesn't have a double meaning, like "product", to refer to this concept in the code. But I aped the language used in the problem description instead (I similarly lifted the term "basket" directly from the problem description), because I've learned that when the terminology used in the code doesn't match the terminology used in business requirements docs, bug reports, and/or informal communication with people on the business and support side, it can cause confusion (especially when ramping up new team members).

3) the only country in which goods are currently being purchased is (a much-simplified version of) Canada

4) we are allowed to assume that any good whose country of origin is not available/not specified was produced in Canada (i.e, it is not subject to the import tax)
