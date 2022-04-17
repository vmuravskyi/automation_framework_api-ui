package com.rest;

import io.restassured.RestAssured;
import java.io.File;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;

public class UploadFileTest {

    @Test
    public void uploadFileMultipartFormData() {
        String attributes = "{\"name\":\"New Text Document.txt\",\"parent\":{\"id\":\"12345\"}}";

        RestAssured.given()
            .baseUri("https://postman-echo.com")
            .multiPart(new File("src/test/resources/filesForRequests/temp.txt"))
            .multiPart("file", attributes, "application/json")
            .log().all()
            .when()
            .post("/post")
            .then()
            .log().all()
            .assertThat()
            .statusCode(HttpStatus.SC_OK);
    }

}
