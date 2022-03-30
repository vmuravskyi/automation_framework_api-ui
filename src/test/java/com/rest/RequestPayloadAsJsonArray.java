package com.rest;

import static io.restassured.RestAssured.config;
import static io.restassured.RestAssured.given;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.config.EncoderConfig;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.CharSet;
import org.apache.hc.core5.http.HttpStatus;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hamcrest.Matchers;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class RequestPayloadAsJsonArray {

    private static Logger LOGGER = LogManager.getLogger();
    private RequestSpecification requestSpecification;
    private ResponseSpecification responseSpecification;

    @BeforeClass
    public void setUp() {
        requestSpecification = new RequestSpecBuilder()
            .setBaseUri("https://f524542b-bbe0-4831-b095-1ac2b891226b.mock.pstmn.io")
            .addHeader("X-Api-Key", "PMAK-623731f9ae216434f3a7f279-a745745a851dd7107adc1360789eee7305")
            .addHeader("x-mock-match-request-headers", "true")
//            .setConfig(config.encoderConfig(EncoderConfig.encoderConfig()
//                .appendDefaultContentCharsetToContentTypeIfUndefined(false)))
//            .setContentType("application/json;charset=utf-8")
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
    public void validatePostRequestPayloadAsList() {
        Map<String, String> obj5001 = new HashMap<>();
        obj5001.put("id", "5001");
        obj5001.put("type", "None");

        Map<String, String> obj5002 = new HashMap<>();
        obj5002.put("id", "5002");
        obj5002.put("type", "Glazed");

        List<Map<String, String>> jsonList = new ArrayList<>();
        jsonList.add(obj5001);
        jsonList.add(obj5002);

        given().spec(requestSpecification)
            .body(jsonList)
            .when()
            .post("/post")
            .then().spec(responseSpecification)
            .assertThat()
            .body("msg", Matchers.equalTo("Success"));
    }

}
