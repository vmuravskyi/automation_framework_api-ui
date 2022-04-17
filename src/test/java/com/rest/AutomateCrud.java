package com.rest;

import com.rest.dto.DtoConverter;
import com.rest.dto.WorkSpacesDto;
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
    WorkSpacesDto workSpacesDto = new WorkSpacesDto();

    @BeforeClass
    public void beforeClass() {
        requestSpecification = new RequestSpecBuilder()
            .setBaseUri("https://api.postman.com")
            .addHeader("X-Api-Key", System.getenv("postman_api_key"))
            .log(LogDetail.ALL)
            .build();

        responseSpecification = new ResponseSpecBuilder()
            .expectStatusCode(200)
            .expectContentType(ContentType.JSON)
            .log(LogDetail.ALL)
            .build();

        workSpacesDto.setWorkspaces(new WorkSpacesDto().generateRandomListOfWorkspace());
    }

    @Test(priority = 1)
    public void post() {
        Response response = RestAssured.given(requestSpecification)
            .body(workSpacesDto)
            .when()
            .post("/workspaces")
            .then()
            .spec(responseSpecification)
            .statusCode(200)
            .extract()
            .response();
        Assertions.assertThat(DtoConverter.getResponseAsDto(response, WorkSpacesDto.class))
            .isEqualTo(workSpacesDto);
    }

    public void get() {
    }

    public void put() {
    }

    private void deleteWorkspace(String workspaceId) {
        RestAssured.given(requestSpecification)
            .pathParam("workspaceId", workspaceId)
            .when()
            .delete("/workspaces/{workspaceId}")
            .then()
            .spec(responseSpecification)
            .statusCode(200);
    }

    @Test(priority = 2)
    public void delete() {
        workSpacesDto.getWorkspaces()
            .forEach(workspaceDto -> deleteWorkspace(workspaceDto.getId()));
    }

}
