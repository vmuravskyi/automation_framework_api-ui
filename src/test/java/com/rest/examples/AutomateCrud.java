package com.rest.examples;

import com.rest.examples.dto.DtoConverter;
import com.rest.examples.dto.WorkSpaceRoot;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.assertj.core.api.Assertions;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class AutomateCrud {

    RequestSpecification requestSpecification;
    ResponseSpecification responseSpecification;
    WorkSpaceRoot workSpacesRoot = new WorkSpaceRoot();

    @BeforeClass
    public void beforeClass() {
        requestSpecification = new RequestSpecBuilder()
            .setBaseUri("https://api.postman.com")
            .setContentType(ContentType.JSON)
            .addHeader("X-Api-Key", System.getenv("postman_api_key"))
            .log(LogDetail.ALL)
            .build();

        responseSpecification = new ResponseSpecBuilder()
            .expectStatusCode(200)
            .expectContentType(ContentType.JSON)
            .log(LogDetail.ALL)
            .build();

//        workSpacesRoot.setWorkspaces(new WorkSpaceRoot().generateRandomListOfWorkspace());
    }

    @Test()
    public void post() {
        Response response = RestAssured.given(requestSpecification)
            .body(workSpacesRoot)
            .when()
            .post("/workspaces")
            .then()
            .spec(responseSpecification)
            .statusCode(200)
            .extract()
            .response();
        Assertions.assertThat(DtoConverter.getResponseAsDto(response, WorkSpaceRoot.class))
            .isEqualTo(workSpacesRoot);
    }

    public void get() {
    }

    public void put() {
    }

    @Test()
    public void delete() {

    }

}
