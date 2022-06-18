package com.selenium.pom.api;

import io.restassured.http.Header;
import io.restassured.http.Headers;

public enum RequestHeaders {

    CONTENT_TYPE(
            new Header("content-type", "application/x-www-form-urlencoded")
    );

    private final Header header;

    RequestHeaders(Header header) {
        this.header = header;
    }

    public Header getHeader() {
        return header;
    }

    public static Headers getHeaders(Header ... headers) {
        return new Headers(headers);
    }
}
