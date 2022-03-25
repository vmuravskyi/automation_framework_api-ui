package com.rest;

import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import org.apache.hc.core5.http.HttpStatus;
import org.testng.annotations.Test;

public class RequestSpecificationExample {

    @Test
    public void validateStatusCode() {
        RequestSpecification requestSpecification = RestAssured.given()
            .baseUri("https://api.postman.com")
            .header("X-Api-Key", "PMAK-623731f9ae216434f3a7f279-a745745a851dd7107adc1360789eee7305");

        RestAssured.given(requestSpecification)
            .when()
            .get("/workspaces")
            .then()
            .log().all()
            .assertThat()
            .statusCode(HttpStatus.SC_OK);
    }

}
