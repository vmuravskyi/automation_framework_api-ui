package com.rest.googleoauth2;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class GmailApi {

    private final String accessToken = "ya29.A0ARrdaM-mh9b58Yy-471HDsxiDTEk32p4HP6mhZaZWE8ifISNNCgljslfTIgZKvGB3ypc1p5kaCa54bVFs68-csYeIi7gPBmleW_s15V4w2VLiZ2bhbVchywHlbYLz9W1EBvFxjzJuAqrmNA5eILH9N3LKa1hSQ";
    private RequestSpecification requestSpecification;
    private ResponseSpecification responseSpecification;

    @BeforeClass
    public void setup() {
        requestSpecification = new RequestSpecBuilder()
            .setBaseUri("https://gmail.googleapis.com")
            .addHeader("Authorization", "Bearer " + accessToken)
            .setContentType(ContentType.JSON)
            .log(LogDetail.ALL)
            .build();

        responseSpecification = new ResponseSpecBuilder()
            .expectStatusCode(200)
            .expectContentType(ContentType.JSON)
            .log(LogDetail.ALL)
            .build();
    }

    @Test
    public void getUserProfile() {
        RestAssured.given(requestSpecification)
            .basePath("/gmail/v1")
            .pathParam("userId", "v.muravskyi@gmail.com")
            .when()
            .get("users/{userId}/profile")
            .then()
            .spec(responseSpecification);
    }

    @Test
    public void sendEmail() {
        String message = "From: v.muravskyi@gmail.com\n"
            + "To: v.muravskyi@gmail.com\n"
            + "Subject: Test from postman\n"
            + "\n"
            + "Sending from RestAssured using gmail API";

        // must be urlEncoded to Base64
        String toBase64 = String.valueOf(
            Base64.getUrlEncoder().encodeToString(message.getBytes(StandardCharsets.UTF_8))
        );
        Map<String, Object> json = new HashMap<>();
        json.put("raw", toBase64);

        RestAssured.given(requestSpecification)
            .basePath("/gmail/v1")
            .pathParam("userId", "v.muravskyi@gmail.com")
            .when()
            .body(json)
            .post("users/{userId}/messages/send")
            .then()
            .spec(responseSpecification);
    }

    @Test
    public void getListOfMessages() {
        RestAssured.given(requestSpecification)
            .basePath("/gmail/v1")
            .pathParam("userId", "v.muravskyi@gmail.com")
            .when()
            .get("users/{userId}/messages/18066a302562d8ee")
            .then()
            .spec(responseSpecification);
    }

}
