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

You will see processing output on your console and once you see `Application Started`, open a browser window and navigate to `http://localhost:8080/users/v1/user/dd7262ad-f713-4d36-bcb3-fe9b5e75a74d` to see the JSON output

## References
Below is a design reference web site
* Apigee: https://vimeo.com/31792500
* Twelve Factor App: https://12factor.net/

Below are some technical reference web sites
* Spring Boot: 
  * https://docs.spring.io/spring-boot/docs/current/reference/html/
  * https://docs.spring.io/spring-boot/docs/current/reference/html/common-application-properties.html
* Building REST API: https://spring.io/guides/gs/rest-service/
* Accessing MySQL: https://spring.io/guides/gs/accessing-data-mysql/
* Jackson Annotations: https://github.com/FasterXML/jackson-annotations/wiki
* JUnit: https://junit.org/junit4/
* JUnit with Mockito: 
  * http://www.vogella.com/tutorials/Mockito/article.html
  * https://www.tutorialspoint.com/mockito/mockito_junit_integration.htm