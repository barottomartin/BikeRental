# BikeRental coding exercise

## How to test it locally

Prerequisites
* Java 8
* Maven 3.5

Clone and go to the repository root folder and then execute

`
mvn clean && mvn compile && mvn test
`

## Code coverage report
Execute `mvn cobertura:cobertura` and the coverage report will be created
as a web page in `${project}/target/site/cobertura/index.html`

## Or check it out in Travis and Codecov
[![Build Status](https://travis-ci.org/barottomartin/BikeRental.svg?branch=master)](https://travis-ci.org/barottomartin/BikeRental)
[![codecov](https://codecov.io/gh/barottomartin/BikeRental/branch/master/graph/badge.svg)](https://codecov.io/gh/barottomartin/BikeRental)

## Design

The main goal of the design is the ability to calculate the price of
different kind of rentals. For that purpose the interface Rental was
created along its getPrice method.

The system allows two kind of rentals. Single and Family, both implement
Rental interface.

A single rental price is calculated matching its duration with a corresponding
tariff. The enum RentalPricing was created for that, it encapsulates all
time related pricing logic.

A family rental is a promotion so its price is based on the rentals
included adjusted by promotion logic.