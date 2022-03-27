package com.rest;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import io.restassured.specification.SpecificationQuerier;
import org.apache.hc.core5.http.HttpStatus;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

public class ResponseSpecificationExample {

    private RequestSpecification requestSpecification;
    private ResponseSpecification responseSpecification;

    @BeforeClass
    public void setUp() {
        requestSpecification = new RequestSpecBuilder()
                .setBaseUri("https://api.postman.com")
                .addHeader("X-Api-Key", "PMAK-623731f9ae216434f3a7f279-a745745a851dd7107adc1360789eee7305")
                .log(LogDetail.ALL)
                .build();

//        responseSpecification = RestAssured.expect()
//                .statusCode(HttpStatus.SC_OK)
//                .contentType(ContentType.JSON)
//                .log().all(); // logger doesn't work here, only in ResponseSpecBuilder

        responseSpecification = new ResponseSpecBuilder()
                .expectStatusCode(HttpStatus.SC_OK)
                .expectContentType(ContentType.JSON)
                .log(LogDetail.ALL)
                .build();
    }

    @Test
    public void validateStatusCode() {
        given().spec(requestSpecification)
                .get("/workspaces")
                .then().spec(responseSpecification);
    }

    @Test
    public void validateResponseBody() {
        Response response = given().spec(requestSpecification)
                .get("/workspaces")
                .then().spec(responseSpecification)
                .extract()
                .response();
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
