package com.rest.spotify.common;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class SpecBuilder {

    public static RequestSpecification getRequestSpec() {
        return new RequestSpecBuilder()
            .setBaseUri("https://api.spotify.com")
            .setBasePath(Endpoints.BASE_PATH)
            .setContentType(ContentType.JSON)
            .log(LogDetail.ALL)
            .build();
    }

    public static RequestSpecification getAccountRequestSpec() {
        return new RequestSpecBuilder()
            .setBaseUri("https://accounts.spotify.com")
            .setContentType(ContentType.URLENC) // parameters should be form-urlencoded
            .log(LogDetail.ALL)
            .build();
    }

    public static ResponseSpecification getResponseSpec() {
        return new ResponseSpecBuilder()
            .log(LogDetail.ALL)
            .build();
    }

}
