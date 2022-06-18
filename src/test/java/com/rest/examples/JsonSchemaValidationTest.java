package com.rest.examples;

import io.restassured.RestAssured;
import io.restassured.module.jsv.JsonSchemaValidator;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;

public class JsonSchemaValidationTest {

    @Test
    public void validateJsonSchema() {
        RestAssured.given()
                .baseUri("https://postman-echo.com")
                .log().all()
                .when()
                .get("/get")
                .then()
                .log().all()
                .assertThat()
                .statusCode(HttpStatus.SC_OK)
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("jsonSchema/echoGetJsonSchema.json"));
    }

}
