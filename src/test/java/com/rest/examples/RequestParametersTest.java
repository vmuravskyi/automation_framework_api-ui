package com.rest.examples;

import io.restassured.RestAssured;
import io.restassured.config.EncoderConfig;
import io.restassured.config.RestAssuredConfig;
import java.io.File;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;

public class RequestParametersTest {

    private final String BASE_URL = "https://postman-echo.com";

    @Test
    public void sendQueryParameter() {
        RestAssured.given()
            .baseUri(BASE_URL)
            .queryParam("foo1", "bar1")
            .queryParam("foo2", "bar2")
            .log().all()
            .when()
            .get("/get")
            .then()
            .log().all()
            .statusCode(HttpStatus.SC_OK);
    }

    @Test
    public void sendMultiValueQueryParameter() {
        RestAssured.given()
            .baseUri(BASE_URL)
            .queryParam("foo1", "bar1, bar2, bar3")
            .log().all()
            .when()
            .get("/get")
            .then()
            .log().all()
            .statusCode(HttpStatus.SC_OK);
    }

    @Test
    public void sendPathParameter() {
        RestAssured.given()
            .baseUri("https://reqres.in")
            .pathParam("userId", "1")
            .log().all()
            .when()
            .get("/api/users/{userId}")
            .then()
            .log().all()
            .assertThat()
            .statusCode(HttpStatus.SC_OK);
    }

    @Test
    public void multipartFormData() {
        RestAssured.given()
            .baseUri("https://postman-echo.com")
            .multiPart("foo1", "bar 1")
            .multiPart(new File("src/test/resources/filesForRequests/temp.txt"))
            .log().all()
            .when()
            .post("/post")
            .then()
            .log().all()
            .assertThat()
            .statusCode(HttpStatus.SC_OK);
    }

    @Test
    public void formUrlEncoded() {
        RestAssured.given()
            .baseUri("https://postman-echo.com")
            .config(
                RestAssuredConfig.config().encoderConfig(
                    EncoderConfig.encoderConfig().appendDefaultContentCharsetToContentTypeIfUndefined(false)
                )
            )
            .formParam("key1", "value1")
            .formParam("key 1", "value 1")
            .log().all()
            .when()
            .post("/post")
            .then()
            .log().all()
            .assertThat()
            .statusCode(HttpStatus.SC_OK);
    }

}
    