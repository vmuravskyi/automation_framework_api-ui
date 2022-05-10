package com.rest.spotify.common;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import java.util.Map;

public class RestResource {

    public static Response postAccount(Map<String, Object> formParams) {
        return RestAssured.given(SpecBuilder.getAccountRequestSpec())
            .formParams(formParams)
            .when()
            .post(Endpoints.API + Endpoints.TOKEN)
            .then()
            .spec(SpecBuilder.getResponseSpec())
            .extract()
            .response();
    }

    public static Response post(String token, String path, Object body) {
        return RestAssured.given(SpecBuilder.getRequestSpec())
            .auth().oauth2(token)
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
            .auth().oauth2(token)
            .when()
            .get(path)
            .then()
            .spec(SpecBuilder.getResponseSpec())
            .extract()
            .response();
    }

    public static Response put(String token, String path, Object body) {
        return RestAssured.given(SpecBuilder.getRequestSpec())
            .auth().oauth2(token)
            .body(body)
            .when()
            .put(path)
            .then()
            .spec(SpecBuilder.getResponseSpec())
            .extract()
            .response();
    }

}
