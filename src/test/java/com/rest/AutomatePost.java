package com.rest;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import java.util.HashMap;
import java.util.Map;
import org.apache.hc.core5.http.HttpStatus;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.assertj.core.api.Assertions;
import org.hamcrest.Matchers;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.with;

public class AutomatePost {

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
    public void validatePostRequestBDDStyle() {
        String payload = "{\n" +
                "    \"workspace\": {\n" +
                "        \"name\": \"Workspace from post method from restAssured\",\n" +
                "        \"type\": \"personal\",\n" +
                "        \"description\": \"Some description\"\n" +
                "    }\n" +
                "}";

        ValidatableResponse response = given().spec(requestSpecification)
                .body(payload)
                .when()
                .post("/workspaces")
                .then().spec(responseSpecification)
                .assertThat()
                .body("workspace.name", Matchers.equalTo("Workspace from post method from restAssured"),
                        "workspace.id", Matchers.matchesPattern("^[a-z0-9-]{36}$"));
    }

    @Test
    public void validatePostRequestNonBDDStyle() {
        String payload = "{\n" +
                "    \"workspace\": {\n" +
                "        \"name\": \"Workspace from post method from restAssured\",\n" +
                "        \"type\": \"personal\",\n" +
                "        \"description\": \"Some description\"\n" +
                "    }\n" +
                "}";

        // NOTE! There's no possibility to log the response, as there's no responseSpecification in the request
        Response response = with().spec(requestSpecification)
                .body(payload)
                .post("/workspaces");
        LOGGER.info("\n" + response.body().prettyPrint());
        Assertions.assertThat(response.<String>path("workspace.name"))
                .isEqualTo("Workspace from post method from restAssured");
        Assertions.assertThat(response.<String>path("workspace.id"))
                .matches("^[a-z0-9-]{36}$");
    }

    @Test
    public void validatePostRequestPayloadFromFile() {
        File file = new File("src/test/resources/CreateWorkspacePayload.json");

        ValidatableResponse response = given().spec(requestSpecification)
                .body(file)
                .when()
                .post("/workspaces")
                .then().spec(responseSpecification)
                .assertThat()
                .body("workspace.name", Matchers.equalTo("Workspace from post method from json file"),
                        "workspace.id", Matchers.matchesPattern("^[a-z0-9-]{36}$"));
    }

    @Test
    public void validatePostRequestPayloadAsMap() {
        Map<String, Object> mainObject = new HashMap<>();
        Map<String, String> nestedObject = new HashMap<>();
        nestedObject.put("name", "Workspace from post method from restAssured as HashMap");
        nestedObject.put("type", "personal");
        nestedObject.put("description", "Some description");
        mainObject.put("workspace", nestedObject);

        ValidatableResponse response = given().spec(requestSpecification)
                .body(mainObject)
                .when()
                .post("/workspaces")
                .then().spec(responseSpecification)
                .assertThat()
                .body("workspace.name", Matchers.equalTo("Workspace from post method from restAssured as HashMap"),
                        "workspace.id", Matchers.matchesPattern("^[a-z0-9-]{36}$"));
    }

}
