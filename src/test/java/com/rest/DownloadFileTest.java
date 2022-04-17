package com.rest;

import io.restassured.RestAssured;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.testng.annotations.Test;

public class DownloadFileTest {

    @Test
    public void downloadFile() throws IOException {
        InputStream inputStream = RestAssured.given()
            .baseUri("https://github.com")
            .log().all()
            .when()
            .get("/appium/appium/raw/master/sample-code/apps/ApiDemos-debug.apk")
            .then()
            .log().all()
            .extract()
            .asInputStream();

        OutputStream fileOutputStream =
            new FileOutputStream(new File("src/test/resources/filesToDownload/ApiDemos-debug.apk"));
        fileOutputStream.write(inputStream.read());
        fileOutputStream.close();
    }

}
