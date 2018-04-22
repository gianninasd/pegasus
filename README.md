Pegasus
================
A Java micro-service experiment using Spring Boot, JPA and built with Gradle. Using MySQL 5.2 as the backing data store.

## Pre-requisites
* Install Git 2.11.x
* Install Java 8
* Install Gradle 4.6

## Getting started
Open a console and run the following commands to get going:
* `git clone https://github.com/gianninasd/pegasus.git`
* `cd pegasus`
* `gradlew build`
* `gradlew run`

You will see processing output on your console and once you see `initialization complete`, open a browser window and navigate to `http://localhost:8080/getUser` to see the JSON output