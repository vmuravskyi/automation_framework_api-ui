package com.rest.examples.serializationanddeserialization;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rest.examples.serializationanddeserialization.pojo.simple.SimplePojo;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class PojoAutomationTest {

    RequestSpecification requestSpecification;
    ResponseSpecification responseSpecification;

    @BeforeClass
    public void beforeClass() {
        requestSpecification = new RequestSpecBuilder()
            .setBaseUri("https://f524542b-bbe0-4831-b095-1ac2b891226b.mock.pstmn.io")
            .setContentType(ContentType.JSON)
            .log(LogDetail.ALL)
            .build();

        responseSpecification = new ResponseSpecBuilder()
            .expectContentType(ContentType.JSON)
            .log(LogDetail.ALL)
            .build();
    }

    @Test
    public void simplePojoExample() throws JsonProcessingException {
        SimplePojo payload = new SimplePojo()
            .setKey1("value1")
            .setKey2("value2");

        SimplePojo response = RestAssured.given(requestSpecification)
            .body(payload)
            .when()
            .post("/postSimpleJson")
            .then()
            .spec(responseSpecification)
            .extract()
            .response().as(SimplePojo.class);

        ObjectMapper objectMapper = new ObjectMapper();
        String responseStr = objectMapper.writeValueAsString(response);
        String expectedStr = objectMapper.writeValueAsString(payload);
        MatcherAssert.assertThat(
            objectMapper.readTree(responseStr), Matchers.equalTo(objectMapper.readTree(expectedStr))
        );
    }

}
