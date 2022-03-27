package com.rest;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.Header;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.SpecificationQuerier;
import org.apache.hc.core5.http.HttpStatus;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.assertj.core.api.Assertions.assertThat;

public class RequestSpecificationExample {
    RequestSpecification requestSpecification;

    @BeforeClass
    public void setUp() {
//        requestSpecification = with()
//                .baseUri("https://api.postman.com")
//                .header("X-Api-Key", "PMAK-623731f9ae216434f3a7f279-a745745a851dd7107adc1360789eee7305")
//                .log().all();
        requestSpecification = new RequestSpecBuilder()
                .setBaseUri("https://api.postman.com")
                .addHeader("X-Api-Key", "PMAK-623731f9ae216434f3a7f279-a745745a851dd7107adc1360789eee7305")
                .log(LogDetail.ALL)
                .build();
    }

    @Test
    public void validateStatusCode() {
        Response response = given().spec(requestSpecification)
                .get("/workspaces")
                .then()
                .log().all()
                .extract()
                .response();
        assertThat(response.statusCode())
                .isEqualTo(HttpStatus.SC_OK);
    }

    @Test
    public void validateResponseBody() {
        Response response = given().spec(requestSpecification)
                .get("/workspaces")
                .then()
                .log().all()
                .extract()
                .response();
        assertThat(response.statusCode())
                .isEqualTo(HttpStatus.SC_OK);
        assertThat(response.path("workspaces[0].name").toString())
                .isEqualTo("Other");
    }

    @Test
    public void getDataFromRequestSpecification() {
        String baseUri = SpecificationQuerier.query(requestSpecification)
                .getBaseUri();
        Header header = SpecificationQuerier.query(requestSpecification)
                .getHeaders().get("X-Api-Key");
        System.out.println("baseUri=" + baseUri + "\n" + header.getName() + "=" + header.getValue());
    }

}
