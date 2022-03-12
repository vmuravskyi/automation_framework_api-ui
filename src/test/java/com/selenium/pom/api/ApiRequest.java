package com.selenium.pom.api;

import com.selenium.pom.utils.ConfigLoader;
import io.restassured.http.Cookies;
import io.restassured.http.Headers;
import io.restassured.response.Response;

import java.util.Map;

import static com.selenium.pom.constants.Endpoints.BASE_URI;
import static io.restassured.RestAssured.given;

public class ApiRequest {

    public static Response post(Cookies cookies, Headers headers, Map<String, String> formParams, String endpoint) {
        return given()
                .baseUri(BASE_URI.getValue())
                .cookies(cookies)
                .headers(headers)
                .formParams(formParams)
                .log().all()
                .when()
                .post(endpoint)
                .then()
                .log().all()
                .extract()
                .response();
    }

    public static Response get(Cookies cookies, String endpoint) {
        return given()
                .baseUri(BASE_URI.getValue())
                .cookies(cookies)
                .log().all()
                .when()
                .get(endpoint)
                .then()
                .log().all()
                .extract()
                .response();
    }

}
