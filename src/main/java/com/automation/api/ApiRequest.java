package com.automation.api;

import io.restassured.RestAssured;
import io.restassured.http.Cookies;
import io.restassured.http.Headers;
import io.restassured.response.Response;

import java.util.Map;

public class ApiRequest {

    public static Response post(Cookies cookies, Headers headers, Map<String, String> formParams, String endpoint) {
        return RestAssured.given(SpecBuilder.getRequestSpec())
                .headers(headers)
                .formParams(formParams)
                .cookies(cookies)
                .when()
                .post(endpoint)
                .then()
                .spec(SpecBuilder.getResponseSpec())
                .extract()
                .response();
    }

    public static Response get(Cookies cookies, String endpoint) {
        return RestAssured.given(SpecBuilder.getRequestSpec())
                .cookies(cookies)
                .log().all()
                .when()
                .get(endpoint)
                .then()
                .spec(SpecBuilder.getResponseSpec())
                .extract()
                .response();
    }

}
