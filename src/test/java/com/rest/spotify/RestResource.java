package com.rest.spotify;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import java.util.Map;

public class RestResource {

    public static Response postAccount(Map<String, Object> formParams) {
        return RestAssured.given()
            .baseUri("https://accounts.spotify.com")
            .contentType(ContentType.URLENC) // parameters should be form-urlencoded
            .formParams(formParams)
            .log().all()
            .when()
            .post("/api/token")
            .then()
            .spec(SpecBuilder.getResponseSpec())
            .extract()
            .response();
    }

    public static Response post(String token, String path, Object body) {
        return RestAssured.given(SpecBuilder.getRequestSpec())
            .header("Authorization", "Bearer " + token)
            .body(body)
            .when()
            .post(path)
            .then()
            .spec(SpecBuilder.getResponseSpec())
            .extract()
            .response();
    }

    public static Response get(String token, String path) {
        return RestAssured.given(SpecBuilder.getRequestSpec())
            .header("Authorization", "Bearer " + token)
            .when()
            .get(path)
            .then()
            .spec(SpecBuilder.getResponseSpec())
            .extract()
            .response();
    }

    public static Response put(String token, String path, Object body) {
        return RestAssured.given(SpecBuilder.getRequestSpec())
            .header("Authorization", "Bearer " + token)
            .body(body)
            .when()
            .put(path)
            .then()
            .spec(SpecBuilder.getResponseSpec())
            .extract()
            .response();
    }

}
