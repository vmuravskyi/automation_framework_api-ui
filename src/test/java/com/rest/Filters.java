package com.rest;

import com.github.javafaker.DateAndTime;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Date;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.joda.time.LocalDateTime;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Filters {

    RequestSpecification requestSpecification;
    ResponseSpecification responseSpecification;

    @BeforeClass
    public void setUp() throws FileNotFoundException {
        PrintStream outputStream = new PrintStream(new File("restAssured.log"));
        requestSpecification = new RequestSpecBuilder()
            .addFilter(new RequestLoggingFilter(outputStream))
            .addFilter(new ResponseLoggingFilter(outputStream))
            .build();
        responseSpecification = new ResponseSpecBuilder().build();
    }

    @Test(description = "Log RestAssured logs into a file.log")
    public void loggingFilter() {

        RestAssured.given(requestSpecification)
            .baseUri("https://postman-echo.com")
            .when()
            .get("/get")
            .then()
            .spec(responseSpecification)
            .assertThat()
            .statusCode(200);
    }

    @Test
    public void loggerTest() {
        Logger LOGGER = LogManager.getLogger();
        LOGGER.log(Level.ERROR, "This is an error");
    }

}
