package com.rest;

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

public class AutomatePutTest {

    @BeforeClass
    public void beforeClass() {
        RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder()
            .setBaseUri("https://api.postman.com")
            .addHeader("X-Api-Key", "PMAK-623731f9ae216434f3a7f279-a745745a851dd7107adc1360789eee7305")
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
    public void validatePutRequestBddStyle() {
        String workspaceId = "4781f375-b7eb-4ec9-944f-1d676d609bbe";
        String payload = "{\n"
            + "    \"workspace\": {\n"
            + "        \"name\": \"Workspace from put method updated from restAssured NEW\",\n"
            + "        \"type\": \"personal\",\n"
            + "        \"description\": \"Some description from put from restAssured\"\n"
            + "    }\n"
            + "}";

        given()
            .body(payload)
            .pathParam("workspaceId", workspaceId)
            .when()
            .put("/workspaces/{workspaceId}")
            .then()
            .log().all()
            .assertThat()
            .body("workspace.name", equalTo("Workspace from put method updated from restAssured NEW"),
                "workspace.id", matchesPattern("^[a-z0-9-]{36}$"),
                "workspace.id", equalTo(workspaceId));
    }
}
