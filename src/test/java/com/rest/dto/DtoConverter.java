package com.rest.dto;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import org.apache.http.HttpStatus;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DtoConverter {

    private static final Logger LOGGER = LogManager.getLogger();

    public static <T> T getResponseAsDto(Response response, Class<T> t) {
        LOGGER.info("Deserializing response into object of class [{}]", t);
        return response.getBody().as(t);
    }

    public static <T> T getJsonFileAsDto(String filepath, Class<T> t) {
        try {
            return new ObjectMapper()
                .readValue(new File("src/test/resources/filesToReadAsJson/complexJson.json"), t);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Response getAllWorkSpaces() {
        return RestAssured.given()
            .baseUri("https://api.postman.com")
            .header("X-Api-Key", System.getenv("postman_api_key"))
            .accept(ContentType.JSON)
            .when()
            .get("/workspaces")
            .then()
            .extract()
            .response();
    }

    private void deleteJob(int id) {
        RestAssured.given()
            .baseUri("https://api.postman.com/workspaces/" + id)
            .header("X-Api-Key", System.getenv("postman_api_key"))
            .accept(ContentType.JSON)
            .contentType(ContentType.JSON)
            .when()
            .delete()
            .then()
            .statusCode(HttpStatus.SC_OK);
    }


    public void deleteAllJobs() {
        List<WorkspaceDto> jobs = Arrays.asList(getAllWorkSpaces().getBody().as(WorkspaceDto[].class));
    }

}
