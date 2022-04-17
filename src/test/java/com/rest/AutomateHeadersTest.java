package com.rest;

import static io.restassured.RestAssured.given;

import io.restassured.http.Header;
import io.restassured.http.Headers;
import java.util.HashMap;
import java.util.List;
import org.testng.annotations.Test;

public class AutomateHeadersTest {

    @Test
    public void multipleHeaders() {
        Header header = new Header("header", "value1");
        Header matchHeader = new Header("x-mock-match-request-headers", "header");
        given()
            .baseUri("https://8f6d7436-aba9-4c1f-bc81-fdc881a11fb1.mock.pstmn.io")
            .header(header)
            .header(matchHeader)
            .log().all()
            .when()
            .get("/get")
            .then()
            .log().all()
            .assertThat()
            .statusCode(200);
    }

    @Test
    public void multipleHeadersUsingHeaders() {
        Header header = new Header("header", "value2");
        Header matchHeader = new Header("x-mock-match-request-headers", "header");

        Headers headers = new Headers(header, matchHeader);

        given()
            .baseUri("https://8f6d7436-aba9-4c1f-bc81-fdc881a11fb1.mock.pstmn.io")
            .headers(headers)
            .when()
            .get("/get")
            .then()
            .log().all()
            .assertThat()
            .statusCode(200);
    }

    @Test
    public void multipleHeadersUsingMap() {
        HashMap<String, String> headers = new HashMap<String, String>();
        headers.put("header", "value2");
        headers.put("x-mock-match-request-headers", "header");

        given()
            .baseUri("https://8f6d7436-aba9-4c1f-bc81-fdc881a11fb1.mock.pstmn.io")
            .headers(headers)
            .when()
            .get("/get")
            .then()
            .log().all()
            .assertThat()
            .statusCode(200);
    }

    @Test
    public void multiValueHeaderInTheRequest() {
        Header header1 = new Header("multiValueHeader", "value1");
        Header header2 = new Header("multiValueHeader", "value2");

        Headers headers = new Headers(header1, header2);

        given()
            .baseUri("https://8f6d7436-aba9-4c1f-bc81-fdc881a11fb1.mock.pstmn.io")
            .headers(headers)
            .log().headers()
            .when()
            .get("/get")
            .then()
            .log().all()
            .assertThat()
            .statusCode(200);
    }

    @Test
    public void assertResponseHeaders() {
        HashMap<String, String> headers = new HashMap<>();
        headers.put("header", "value1");
        headers.put("x-mock-match-request-headers", "header");

        given()
            .baseUri("https://8f6d7436-aba9-4c1f-bc81-fdc881a11fb1.mock.pstmn.io")
            .headers(headers)
            .when()
            .get("/get")
            .then()
            .assertThat()
            .statusCode(200)
            .headers("responseHeader", "resValue1",
                "X-RateLimit-Limit", "120");
    }

    @Test
    public void extractResponseHeaders() {
        HashMap<String, String> headers = new HashMap<>();
        headers.put("header", "value1");
        headers.put("x-mock-match-request-headers", "header");

        Headers extractedHeaders = given()
            .baseUri("https://8f6d7436-aba9-4c1f-bc81-fdc881a11fb1.mock.pstmn.io")
            .headers(headers)
            .when()
            .get("/get")
            .then()
            .assertThat()
            .statusCode(200)
            .extract()
            .headers();

        for (Header header : extractedHeaders) {
            System.out.print("header name = " + header.getName() + ", ");
            System.out.println("header value = " + header.getValue());
        }
    }

    @Test
    public void extractMultiValueResponseHeader() {
        HashMap<String, String> headers = new HashMap<>();
        headers.put("header", "value1");
        headers.put("x-mock-match-request-headers", "header");

        Headers extractedHeaders = given()
            .baseUri("https://8f6d7436-aba9-4c1f-bc81-fdc881a11fb1.mock.pstmn.io")
            .headers(headers)
            .when()
            .get("/get")
            .then()
            .assertThat()
            .statusCode(200)
            .extract()
            .headers();

        List<String> values = extractedHeaders.getValues("multiValueHeader");
        for (String value : values) {
            System.out.println(value);
        }
    }

}
