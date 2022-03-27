package com.rest;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.apache.hc.core5.http.HttpStatus;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hamcrest.Matchers;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class AutomatePut {

    private static Logger LOGGER = LogManager.getLogger();
    private RequestSpecification requestSpecification;
    private ResponseSpecification responseSpecification;

    @BeforeClass
    public void setUp() {
        requestSpecification = new RequestSpecBuilder()
                .setBaseUri("https://api.postman.com")
                .addHeader("X-Api-Key", "PMAK-623731f9ae216434f3a7f279-a745745a851dd7107adc1360789eee7305")
                .setContentType(ContentType.JSON)
                .log(LogDetail.ALL)
                .build();

        responseSpecification = new ResponseSpecBuilder()
                .expectStatusCode(HttpStatus.SC_OK)
                .expectContentType(ContentType.JSON)
                .log(LogDetail.ALL)
                .build();
    }

    @Test
    public void validatePutRequestBDDStyle() {
        String workspaceId = "4781f375-b7eb-4ec9-944f-1d676d609bbe";
        String payload = "{\n" +
                "    \"workspace\": {\n" +
                "        \"name\": \"Workspace from put method updated from restAssured\",\n" +
                "        \"type\": \"personal\",\n" +
                "        \"description\": \"Some description from put\"\n" +
                "    }\n" +
                "}";

        given().spec(requestSpecification)
                .body(payload)
                .when()
                .pathParams("workspaceId", workspaceId)
                .put("/workspaces/{workspaceId}")
                .then()
                .spec(responseSpecification)
                .assertThat()
                .body("workspace.name", Matchers.equalTo("Workspace from put method updated from restAssured"),
                        "workspace.id", Matchers.equalTo(workspaceId));
    }

}
