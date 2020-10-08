# SalesTaxExercise

My solution to a Java coding exercise from TekSystems.

The Word doc with the full description of the exercise can be downloaded here: [doc/SalesTax Coding Exercise-.NET and Java.docx](doc/SalesTax%20Coding%20Exercise-.NET%20and%20Java.docx)

If you just call the main() function in [com.teksystems.salestaxexercise.runners.SalesTaxExerciseRunner](src/com/teksystems/salestaxexercise/runners/SalesTaxExerciseRunner.java) with no arguments or inputs (the 3 sample inputs are hardcoded), it should print to the console the 3 sample outputs, namely:

```
Output 1: 
1 book: 12.49 
1 music CD: 16.49 
1 chocolate bar: 0.85 
Sales Taxes: 1.50 Total: 29.83

Output 2: 
1 imported box of chocolates: 10.50 
1 imported bottle of perfume: 54.65 
Sales Taxes: 7.65 Total: 65.15

Output 3: 
1 imported bottle of perfume: 32.19 
1 bottle of perfume: 20.89 
1 packet of headache pills: 9.75 
1 imported box of chocolates: 11.85 
Sales Taxes: 6.70 Total: 74.68
```

The full Javadoc can be found here (note Javadoc can't be viewed on GitHub): [doc/javadoc/index.html](doc/javadoc/index.html)



## Technical dependencies:

1) this project was implemented, tested, and run in Eclipse Version: 2020-06 (4.16.0).

- the Eclipse project files are included in the repository. So it should be possible to create the new Eclipse project on your local machine by simply running File -> Import... -> Git -> Projects from Git.

- the project files should be backwards-compatible with previous recent versions of Eclipse, but this has not been tested.

- in Eclipse, to produce the output above in the Console window you should be able to just right-click on [src/com/teksystems/salestaxexercise/runners/SalesTaxExerciseRunner.java](src/com/teksystems/salestaxexercise/runners/SalesTaxExerciseRunner.java) in the Project Explorer or Package Explorer view to Run As -> Java Application


2) The JDK used for implementing, testing, and running this project was JDK 14.0.2.

- I did not knowingly make use of any recent additions to Java, so the code should be backwards compatible with any Java 8 or higher JDK, but this has not been tested.


3) The project has two 3rd-party Maven dependencies:

- [Joda-Money](https://www.joda.org/joda-money/), used to avoid having to build a custom package to support multiple currencies

- [icu4j](https://unicode-org.github.io/icu-docs/apidoc/released/icu4j/), used to avoid having to build region-handling logic from scratch


4) The project depends on JUnit 5 for the unit and integration (end-to-end) tests.



## Business-level assumptions made:

As an architect, these are things I would have asked the customer/business folks to confirm for me before I finalized the design:

1) this is code that will live for a long time, as part of a system that is expected to grow and evolve.

- there is such a thing as over-engineering and over-documenting. Much of this design would be overkill (in particular the parts of the design that anticipate the possibility of internationalization in the future) if this was a throw-away demo or prototype.


2) the business side owners/users of this system most commonly use the word "good" to describe the products they sell, in general.

- I would generally prefer to use a more generic word that doesn't have a double meaning, like "product", to refer to this concept in the code. But I aped the language used in the problem description instead.

- I similarly lifted the terms "basket" and "shelf price" directly from the problem description in to the code, I probably wouldn't have used those terms otherwise

- I've learned from experience that when the terminology used in the code doesn't match the terminology used in business requirements docs, bug reports, and/or informal communication with people on the business and support side, it can cause confusion (especially when ramping up new team members).


3) the only country in which goods are currently being purchased is (a fictional version of) Canada


4) we are allowed to assume that if the country of origin is not available/not specified in whatever data source we're using to retrieve our list of goods and prices for a given region, that means that good is not subject to any import taxes in that region


5) we are similarly allowed to assume that if the specific tax category of a particular good for a specific tax jurisdiction is not available/not specified in whatever data source we're using, the good should not be considered exempt from any sales tax with a list of exempted categories

- but it would be considered exempt from any (future) sales tax which has a list of included categories rather than a list of exempted categories 


6) the rounding rule for sales taxes applies to the import duty as well

- the problem does state that the import duty is an "additional sales tax", which implies to me that it does indeed fall in to the category of a "sales tax" for the purposes of this exercise, meaning the stated "rounding rules for sales tax" would apply to this duty as well

- the sample outputs provided do confirm this assumption as well. But as this is not explicitly stated in the requirements, it's something I'd normally confirm with the client to be 100% sure, so that the documentation could be updated to make this crystal clear.


7) the rounding rule applies separately to each individual good in the basket

- e.g., if you have two non-imported, non-exempt goods that cost 20 cents each, meaning the 10% sales tax would be 2 cents on each good before rounding, the 2 cents would be rounded up to 5 cents for EACH good, making the total sales tax for the 2 goods 10 cents (rather than 5 cents, as it would be if the two unrounded taxes were added together first before rounding)

- again, the sample outputs provided do confirm this assumption. But as this is not explicitly stated in the requirements, it's something I'd more formally confirm and document.


8) the rounding rule applies separately to the basic sales tax and the import duty

- e.g., if there's an imported, non-exempt good that costs 20 cents, meaning the 10% sales tax would be 2 cents before rounding and the 5% import duty would be 1 cent, the sales tax AND the import duty are BOTH rounded up to 5 cents, making the total tax on the good 10 cents (rather than 5 cents, as it would be if the two unrounded taxes were added together first before rounding)

- once again, the sample outputs provided do confirm this assumption. But as this is not explicitly stated in the requirements, it's something I'd more formally confirm and document.


9) if the basket contains more than 1 of a specific good, the rounding rule applies separately to each individual item

- e.g., if the basket contains 2 non-imported paper clips (a non-exempt good) that cost 20 cents each, meaning the 10% sales tax would be 2 cents on each paper clip before rounding, the 2 cents would be rounded up to 5 cents for EACH paper clip, making the total sales tax for the 2 paper clips 10 cents (rather than 5 cents, as it would be if the two unrounded taxes were added together first before rounding)

- this one cannot be confirmed by the sample outputs, as none of the sample inputs has more than 1 of the same good. So this one would have to be confirmed by asking the client, and/or requesting a sample input with 2 of the same good with its corresponding output, and/or checking the website of the relevant tax authority.



## Technical next steps/known design issues

Things I haven't done yet, but would have gotten around to if my time to complete this exercise was unlimited:

1) as an architect I focused most of my time and effort in the creation and documentation of an interface design that embodies SOLID design principles - the actual concrete implementations of those interfaces are a bit sloppy

- the constructor calls for many of my objects are complex and hard to read, implementing the Builder design pattern would simplify them immensely

- many of my implementation classes don't extend any other class, a lot of of the generic/reusable logic in them should be moved down in to abstract classes which they could then extend

- I didn't include as many explanatory comments inside function implementations as I usually would, to make what's actually happening inside the logic easier to understand for other developers tasked with maintaining my code in the future


2) not all the Javadoc is complete (documentation is a must for architects!)

- it's mostly there for interfaces, but still missing from many of the implementation classes


3) many more unit tests are required

- I do believe in the principles of Test-Driven Development, so the test cases required to ensure the 3 sample outputs above would be generated correctly were largely written before the code required to make those tests pass

- but no tests were written for any other possible cases, even the ones I explicitly designed my code to handle (for example, 2 units of the same good in the same basket)


4) I almost completely ignored the view aspect of a complete design

- using toString() on all of my classes to generate the receipt output is obviously not the right design for the long term

- in anticipation of the need to support multiple output languages in the future, I would implement a more generic view design based on language-specific ResourceBundles



5) the Region class from the Maven icu4j package, which I used for geographical locations in my design, would prove insufficient over time

- it does not explicitly support sub-regions within a country like province or states, for example, which would be important for real-world sales tax calculations

- the hope is that when the need arose, this class could be extended to create one that supports all the needed functionality

- if not, we would have to swap in a custom Region class of our own creation which reimplemented all the methods from this one that our code actually uses, plus the additional ones required







