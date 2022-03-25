package com.rest;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.hc.core5.http.HttpStatus;
import org.testng.annotations.Test;

public class AutomateHeaders {

    @Test
    public void multipleHeaders() {

        Header header = new Header("headerName", "value2");
        Header matchHeader = new Header("x-mock-match-request-headers", "headerName");
        Headers headers = new Headers(header, matchHeader);

        RestAssured.given()
            .baseUri("https://f524542b-bbe0-4831-b095-1ac2b891226b.mock.pstmn.io")
            .headers(headers)
            .when()
            .get("/get")
            .then()
            .log().all()
            .assertThat()
            .statusCode(HttpStatus.SC_OK);
    }

    @Test
    public void multipleHeadersUsingMap() {

        Map<String, String> headers = new HashMap<>();
        headers.put("headerName", "value2");
        headers.put("x-mock-match-request-headers", "headerName");

        RestAssured.given()
            .baseUri("https://f524542b-bbe0-4831-b095-1ac2b891226b.mock.pstmn.io")
            .headers(headers)
            .when()
            .get("/get")
            .then()
            .log().all()
            .assertThat()
            .statusCode(HttpStatus.SC_OK);
    }

    @Test
    public void multipleValueHeaderInRequest() {
        Header header1 = new Header("multiValueHeader", "value1");
        Header header2 = new Header("multiValueHeader", "value2");
        Headers headers = new Headers(header1, header2);
        RestAssured.given()
            .baseUri("https://f524542b-bbe0-4831-b095-1ac2b891226b.mock.pstmn.io")
            .headers(headers)
            .log().headers()
            .when()
            .get("/get")
            .then()
            .log().all()
            .assertThat()
            .statusCode(HttpStatus.SC_OK);
    }

    @Test
    public void assertResponseHeaders() {
        Map<String, String> headers = new HashMap<>();
        headers.put("headerName", "value2");
        headers.put("x-mock-match-request-headers", "headerName");

        RestAssured.given()
            .baseUri("https://f524542b-bbe0-4831-b095-1ac2b891226b.mock.pstmn.io")
            .headers(headers)
            .log().headers()
            .when()
            .get("/get")
            .then()
            .log().all()
            .assertThat()
            .statusCode(HttpStatus.SC_OK)
            .header("responseHeader", "resValue2");
    }

    @Test
    public void extractResponseHeaders() {
        Map<String, String> headers = new HashMap<>();
        headers.put("headerName", "value2");
        headers.put("x-mock-match-request-headers", "headerName");

        Headers extractedHeaders = RestAssured.given()
            .baseUri("https://f524542b-bbe0-4831-b095-1ac2b891226b.mock.pstmn.io")
            .headers(headers)
            .log().headers()
            .when()
            .get("/get")
            .then()
            .log().all()
            .assertThat()
            .statusCode(HttpStatus.SC_OK)
            .extract()
            .headers();
        System.out.println(extractedHeaders.get("responseHeader").getName());
        System.out.println(extractedHeaders.get("responseHeader").getValue());

        for (Header extractedHeader : extractedHeaders) {
            System.out.println(extractedHeader.getName() + " : " + extractedHeader.getValue());
        }
    }

    @Test
    public void extractMultiValueHeader() {
        Map<String, String> headers = new HashMap<>();
        headers.put("headerName", "value1");
        headers.put("x-mock-match-request-headers", "headerName");

        Headers extractedHeaders = RestAssured.given()
            .baseUri("https://f524542b-bbe0-4831-b095-1ac2b891226b.mock.pstmn.io")
            .headers(headers)
            .log().headers()
            .when()
            .get("/get")
            .then()
            .log().all()
            .assertThat()
            .statusCode(HttpStatus.SC_OK)
            .extract()
            .headers();

        List<String> multiValueHeader = extractedHeaders.getValues("multiValueHeader");
        System.out.println(multiValueHeader);
    }

}
