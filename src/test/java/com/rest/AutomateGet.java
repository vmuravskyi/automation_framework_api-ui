package com.rest;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.apache.hc.core5.http.HttpStatus;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;

public class AutomateGet {

    @Test
    public void validateGetStatusCode() {
        RestAssured.given()
            .baseUri("https://api.postman.com")
            .header("X-Api-Key", "PMAK-623731f9ae216434f3a7f279-a745745a851dd7107adc1360789eee7305")
            .when()
            .get("/workspaces")
            .then()
            .log()
            .all()
            .assertThat()
            .statusCode(HttpStatus.SC_OK);
    }

    @Test
    public void validateResponseBody() {
        RestAssured.given()
            .baseUri("https://api.postman.com")
            .header("X-Api-Key", "PMAK-623731f9ae216434f3a7f279-a745745a851dd7107adc1360789eee7305")
            .when()
            .get("/workspaces")
            .then()
            .log()
            .all()
            .assertThat()
            .statusCode(HttpStatus.SC_OK)
            .body(
                "workspaces.name", Matchers.hasItems("Other", "GreenCity", "CE",
                    "RestAssured API Automation Framework", "Workspace from put method updated"),
                "workspaces.type", Matchers.hasItems("team", "personal", "team", "personal", "personal"),
                "workspaces[0].name", Matchers.equalTo("Other"));
    }

    @Test
    public void extractResponse() {
        Response response = RestAssured.given()
            .baseUri("https://api.postman.com")
            .header("X-Api-Key", "PMAK-623731f9ae216434f3a7f279-a745745a851dd7107adc1360789eee7305")
            .when()
            .get("/workspaces")
            .then()
            .log()
            .all()
            .assertThat()
            .statusCode(HttpStatus.SC_OK)
            .extract()
            .response();
    }

    @Test
    public void assertHamcrest() {
        RestAssured.given()
            .baseUri("https://api.postman.com")
            .header("X-Api-Key", "PMAK-623731f9ae216434f3a7f279-a745745a851dd7107adc1360789eee7305")
            .when()
            .get("/workspaces")
            .then()
            .assertThat()
            .statusCode(HttpStatus.SC_OK)
            .body("workspaces[0]", Matchers.hasEntry("id", "3282e4de-eb57-4822-826c-9b0a25f13de7"));
    }

    @Test
    public void requestResponseLogging() {
        RestAssured.given()
            .baseUri("https://api.postman.com")
            .header("X-Api-Key", "PMAK-623731f9ae216434f3a7f279-a745745a851dd7107adc1360789eee7305")
            .when()
            .log().all()
            .get("/workspaces")
            .then()
            .log().ifError()
            .assertThat()
            .statusCode(HttpStatus.SC_OK);
    }

}
