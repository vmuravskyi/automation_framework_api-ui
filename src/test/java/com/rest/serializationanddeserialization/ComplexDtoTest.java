package com.rest.serializationanddeserialization;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rest.serializationanddeserialization.pojo.collectionDto.CollectionDto;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import java.io.File;
import java.io.IOException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class ComplexDtoTest {

    RequestSpecification requestSpecification;
    ResponseSpecification responseSpecification;

    @BeforeClass
    public void beforeClass() {
        requestSpecification = new RequestSpecBuilder()
            .setBaseUri("https://api.postman.com")
            .addHeader("X-Api-Key", "PMAK-625c2e4609b5351d8e310a67-788d3e5153e6aa26f8eb98e0cd694ef81b\n")
            .setContentType(ContentType.JSON)
            .log(LogDetail.ALL)
            .build();

        responseSpecification = new ResponseSpecBuilder()
            .expectContentType(ContentType.JSON)
            .log(LogDetail.ALL)
            .build();
    }

    @Test
    public void complexDtoCreateCollection() throws IOException {
        CollectionDto collectionDto = new ObjectMapper().readValue(
            new File("src/test/resources/filesToReadAsJson/collection.json"), CollectionDto.class);

        CollectionDto collectionResponse = RestAssured.given(requestSpecification)
            .body(collectionDto)
            .when()
            .post("/collections")
            .then()
            .spec(responseSpecification)
            .extract()
            .response()
            .as(CollectionDto.class);
        ObjectMapper objectMapper = new ObjectMapper();
        String response = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(collectionResponse);
        System.out.println(response);
    }

}