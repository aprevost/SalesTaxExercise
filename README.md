# SalesTaxExercise
Java coding exercise from TekSystems.

The full description of the exercise can be found here. TODO: insert link

## Technical dependencies:

1) this project was implemented, tested, and run in Eclipse Version: 2020-06 (4.16.0).

- the Eclipse project files are included in the repository. So it should be possible to create the new Eclipse project on your local machine by simply running File -> Import... -> Git -> Projects from Git.

- the project files should be backwards-compatible with previous recent versions of Eclipse, but this has not been tested.

2) The JDK used for implementing, testing, and running this project was JDK 14.0.2.

- I did not knowingly make use of any recent additions to Java, so the code should be backwards compatible with any Java 8 or higher JDK, but this has not been tested.

3) The project has one 3rd-party Maven dependency, Joda-Money (https://www.joda.org/joda-money/), used to avoid having to build a custom package to support multiple currencies.

4) The project depends on JUnit 5 for the unit and integration (end-to-end) tests.


## Business-level assumptions made:

As an architect, these are things I would have asked the customer/business folks to confirm for me before I finalized the design:

1) this is code that will live for a long time, as part of a system that is expected to grow and evolve.

- there is such a thing as over-engineering. Much of this design would be overkill (in particular the parts of the design that anticipate the possibility of internationalization in the future) and not necessary for the purposes of a throw-away demo or prototype.

2) the business side owners/users of this system most commonly use the word "goods" to describe the products they sell, in general.

- I would generally prefer to use a more generic word that doesn't have a double meaning, like "product", to refer to this concept in the code. But I aped the language used in the problem description instead.

- I similarly lifted the terms "basket" and "shelf price" directly from the problem description in to the code, I probably wouldn't have used those terms otherwise

- I've learned from experience that when the terminology used in the code doesn't match the terminology used in business requirements docs, bug reports, and/or informal communication with people on the business and support side, it can cause confusion (especially when ramping up new team members).

3) the only country in which goods are currently being purchased is (a fictional version of) Canada

4) we are allowed to assume that any good whose country of origin is not available/not specified was produced in Canada (i.e, it is not subject to the import tax)

5) the rounding rule for sales taxes applies to the import duty as well

- the problem does state that the import duty is an "additional sales tax", which implies to me that it does indeed fall in to the category of a "sales tax" for the purposes of this exercise, meaning the stated "rounding rules for sales tax" would apply to this duty as well

- the sample outputs provided do confirm this assumption. But as this is not explicitly stated in the requirements, it's something I'd normally confirm with the client to be 100% sure, so that the documentation could be updated to make this crystal clear.

6) the rounding rule applies separately to each individual good in the basket

- e.g., if you have two non-imported, non-exempt goods that cost 20 cents each, meaning the 10% sales tax would be 2 cents on each good before rounding, the 2 cents would be rounded up to 5 cents for EACH good, making the total sales tax for the 2 goods 10 cents (rather than 5 cents, as it would be if the two unrounded taxes were added together first before rounding)

- again, the sample outputs provided do confirm this assumption. But as this is not explicitly stated in the requirements, it's something I'd more formally confirm and document.

7) the rounding rule applies separately to the basic sales tax and the import duty

- e.g., if there's an imported, non-exempt good that costs 20 cents, meaning the 10% sales tax would be 2 cents before rounding and the 5% import duty would be 1 cent, the sales tax AND the import duty are BOTH rounded up to 5 cents, making the total tax on the good 10 cents (rather than 5 cents, as it would be if the two unrounded taxes were added together first before rounding)

- once again, the sample outputs provided do confirm this assumption. But as this is not explicitly stated in the requirements, it's something I'd more formally confirm and document.

8) if the basket contains more than 1 of a specific good, the rounding rule applies separately to each individual item

- e.g., if the basket contains 2 non-imported paper clips (a non-exempt good) that cost 20 cents each, meaning the 10% sales tax would be 2 cents on each paper clip before rounding, the 2 cents would be rounded up to 5 cents for EACH paper clip, making the total sales tax for the 2 paper clips 10 cents (rather than 5 cents, as it would be if the two unrounded taxes were added together first before rounding)

- this one cannot be confirmed by the sample outputs, as none of the sample inputs has more than 1 of the same good. So this one would have to be confirmed by asking the client, and/or requesting a sample input with 2 of the same good with its corresponding output, and/or checking the website of the relevant tax authority.



## Technical next steps

Things I haven't done yet, but would have gotten around to if my time to complete this exercise was unlimited:

1)