package com.rest;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.with;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.matchesPattern;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import java.io.File;
import java.util.HashMap;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class AutomatePostTest {

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
    public void validatePostRequestBddStyle() {
        String payload = "{\n" +
            "    \"workspace\": {\n" +
            "        \"name\": \"myFirstWorkspace\",\n" +
            "        \"type\": \"personal\",\n" +
            "        \"description\": \"Rest Assured created this\"\n" +
            "    }\n" +
            "}";

        given()
            .body(payload)
            .when()
            .post("/workspaces")
            .then()
            .log().all()
            .assertThat()
            .body("workspace.name", equalTo("myFirstWorkspace"),
                "workspace.id", matchesPattern("^[a-z0-9-]{36}$"));
    }

    @Test
    public void validatePostRequestNonBddStyle() {
        String payload = "{\n" +
            "    \"workspace\": {\n" +
            "        \"name\": \"myFirstWorkspace2\",\n" +
            "        \"type\": \"personal\",\n" +
            "        \"description\": \"Rest Assured created this\"\n" +
            "    }\n" +
            "}";

        Response response = with()
            .body(payload)
            .post("/workspaces");
        assertThat(response.<String>path("workspace.name"), equalTo("myFirstWorkspace2"));
        assertThat(response.<String>path("workspace.id"), matchesPattern("^[a-z0-9-]{36}$"));
    }

    @Test
    public void validatePostRequestPayloadFromFile() {
        File file = new File("src/main/resources/CreateWorkspacePayload.json");
        given()
            .body(file)
            .when()
            .post("/workspaces")
            .then()
            .log().all()
            .assertThat()
            .body("workspace.name", equalTo("mySecondWorkspace"),
                "workspace.id", matchesPattern("^[a-z0-9-]{36}$"));
    }

    @Test
    public void validatePostRequestPayloadAsMap() {
        HashMap<String, Object> mainObject = new HashMap<>();

        HashMap<String, String> nestedObject = new HashMap<>();
        nestedObject.put("name", "myThirdWorkspace");
        nestedObject.put("type", "personal");
        nestedObject.put("description", "Rest Assured created this");

        mainObject.put("workspace", nestedObject);

        given()
            .body(mainObject)
            .when()
            .post("/workspaces")
            .then()
            .log().all()
            .assertThat()
            .body("workspace.name", equalTo("myThirdWorkspace"),
                "workspace.id", matchesPattern("^[a-z0-9-]{36}$"));
    }

}
