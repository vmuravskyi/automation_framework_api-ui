package com.rest.googleoauth2;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class GmailApi {

    private final String accessToken = "ya29.A0ARrdaM8HIwKVEbC66-02cikq3ABIhRXFJzyCw0hm1F4Xz8KGkFnnm9LpeH9mlihgWOIxrcd8k-bMqr_MHVYBjgBNEPhz9eFRzKseJ4iAcOKXdB-QRrupEU8wxIXEW75q9X7DyAERJjieVdokyyE4bmOmrfqc2Q";
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


}
