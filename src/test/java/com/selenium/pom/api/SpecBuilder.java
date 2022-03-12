package com.selenium.pom.api;

import com.selenium.pom.constants.Endpoints;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class SpecBuilder {

    public static RequestSpecification getRequestSpec() {
        return new RequestSpecBuilder()
                .setBaseUri(Endpoints.BASE_URI.getValue())
                .log(LogDetail.ALL)
                .build();
    }

    public static ResponseSpecification getResponseSpec() {
        return new ResponseSpecBuilder()
                .log(LogDetail.METHOD)
                .log(LogDetail.URI)
                .log(LogDetail.PARAMS)
                .log(LogDetail.STATUS)
                .log(LogDetail.HEADERS)
                .log(LogDetail.COOKIES)
                .build();
    }

}
