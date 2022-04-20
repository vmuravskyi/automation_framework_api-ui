package com.rest.serializationanddeserialization;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import java.util.HashMap;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class JacksonApi {

    @BeforeClass
    public void beforeClass() {
        RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder()
            .setBaseUri("https://api.postman.com")
            .addHeader("X-Api-Key", "PMAK-625c2e4609b5351d8e310a67-788d3e5153e6aa26f8eb98e0cd694ef81b")
            .setContentType(ContentType.JSON)
            .log(LogDetail.ALL);
        RestAssured.requestSpecification = requestSpecBuilder.build();

        ResponseSpecBuilder responseSpecBuilder = new ResponseSpecBuilder()
            .expectStatusCode(200)
            .expectContentType(ContentType.JSON)
            .log(LogDetail.ALL);
        RestAssured.responseSpecification = responseSpecBuilder.build();
    }

    @Test
    public void serializeMapToJson() throws JsonProcessingException {
        HashMap<String, Object> mainObject = new HashMap<>();

        HashMap<String, String> nestedObject = new HashMap<>();
        nestedObject.put("name", "myThirdWorkspace as map converted by objectmapper");
        nestedObject.put("type", "personal");
        nestedObject.put("description", "Rest Assured created this");

        mainObject.put("workspace", nestedObject);

        ObjectMapper objectMapper = new ObjectMapper();
        String mainObjectStr = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(mainObject);

//        given()
//            .body(mainObjectStr)
//            .when()
//            .post("/workspaces")
//            .then()
//            .log().all()
//            .assertThat()
//            .body("workspace.name", equalTo("myThirdWorkspace as map converted by objectmapper"),
//                "workspace.id", matchesPattern("^[a-z0-9-]{36}$"));
    }

    @Test
    public void serializeJsonAsObjectNode() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();

        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("name", "myThirdWorkspace as object node converted by objectmapper");
        objectNode.put("type", "personal");
        objectNode.put("description", "Rest Assured created this");

        ObjectNode mainObjectNode = objectMapper.createObjectNode();
        mainObjectNode.set("workspace", objectNode);

        String json = objectMapper.writeValueAsString(mainObjectNode);

        System.out.println(json);
    }

    @Test
    public void serializeJsonAsArrayNode() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();

        ArrayNode arrayNode = objectMapper.createArrayNode();

        ObjectNode objectNode1 = objectMapper.createObjectNode();
        objectNode1.put("name", "myThirdWorkspace as object node converted by objectmapper");
        objectNode1.put("type", "personal");
        objectNode1.put("description", "Rest Assured created this");

        ObjectNode objectNode2 = objectMapper.createObjectNode();
        objectNode2.put("name", "myThirdWorkspace as object node converted by objectmapper");
        objectNode2.put("type", "personal");
        objectNode2.put("description", "Rest Assured created this");

        arrayNode
            .add(objectNode1)
            .add(objectNode2);

        ObjectNode mainObjectNode = objectMapper.createObjectNode();
        mainObjectNode.set("workspaces", arrayNode);

        String json = objectMapper.writeValueAsString(arrayNode);

        System.out.println(json);
    }

}
