package com.rest;

import static io.restassured.RestAssured.get;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class ResponseSpecificationExample {

    ResponseSpecification responseSpecificationCustom;

    @BeforeClass
    public void beforeClass() {
        RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder();
        requestSpecBuilder.setBaseUri("https://api.postman.com");
        requestSpecBuilder.addHeader("X-Api-Key", "PMAK-623731f9ae216434f3a7f279-a745745a851dd7107adc1360789eee7305");
        requestSpecBuilder.log(LogDetail.ALL);

        RestAssured.requestSpecification = requestSpecBuilder.build();

        ResponseSpecBuilder responseSpecBuilder = new ResponseSpecBuilder()
            .expectStatusCode(200)
            .expectContentType(ContentType.JSON)
            .log(LogDetail.ALL);

//        will not work for logging the response
//        RestAssured.responseSpecification = responseSpecBuilder.build();

//         for logging response need to create a custom response specification because the static one does not work
        responseSpecificationCustom = responseSpecBuilder.build();
    }

    @Test
    public void validateStatusCode() {
        get("/workspaces")
            .then()
            .spec(responseSpecificationCustom);
    }

    @Test
    public void validateResponseBody() {
        Response response = get("/workspaces").
            then().
            extract().
            response();
        assertThat(response.path("workspaces[0].name").toString(), equalTo("Other"));
    }

}
