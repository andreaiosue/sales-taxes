[![CircleCI](https://circleci.com/gh/andreaiosue/sales-taxes.svg?style=svg)](https://circleci.com/gh/andreaiosue/sales-taxes)

# Sales Taxes

Demo Sales Taxes application

## Getting Started

The application calculates sales taxes for given input products.

Input is fed each time within a distinct JUnit test.

After each unit test execution completes, calculation output gets printed to console.

### Prerequisites

This project is built using Maven 3 and Java 1.8.

### Installing

Clone this repository on your local machine.

```
git clone https://github.com/andreaiosue/sales-taxes.git
```

## Running the tests

All the test cases are contained in a single test suite under the `/src/test/` java directory.

### Print the application output

To run all the provided unit tests and to see the generated console output, just launch the Maven test phase.

```
mvn test
```
