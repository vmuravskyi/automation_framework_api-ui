package com.rest.examples;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.matchesPattern;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class AutomateDeleteTest {

    @BeforeClass
    public void beforeClass() {
        RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder()
            .setBaseUri("https://api.postman.com")
            .addHeader("X-Api-Key", System.getenv("postman_api_key"))
            .setContentType(ContentType.JSON)
            .log(LogDetail.ALL);
        RestAssured.requestSpecification = requestSpecBuilder.build();

        ResponseSpecBuilder responseSpecBuilder = new ResponseSpecBuilder()
            .expectStatusCode(200)
            .expectContentType(ContentType.JSON)
            .log(LogDetail.ALL);
        RestAssured.responseSpecification = responseSpecBuilder.build();
    }

    @Test
    public void validateDeleteRequestBddStyle() {
        String workspaceId = "7e90387f-fdcc-4113-aca1-f633a0d1c55c";
        given()
            .pathParam("workspaceId", workspaceId)
            .when()
            .delete("/workspaces/{workspaceId}")
            .then()
            .log().all()
            .assertThat()
            .body("workspace.id", matchesPattern("^[a-z0-9-]{36}$"),
                "workspace.id", equalTo(workspaceId));
    }
}
