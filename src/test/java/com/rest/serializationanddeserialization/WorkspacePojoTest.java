package com.rest.serializationanddeserialization;

import static io.restassured.RestAssured.given;

import com.rest.dto.WorkSpaceRoot;
import com.rest.dto.WorkspaceDto;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.assertj.core.api.Assertions;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class WorkspacePojoTest {

    RequestSpecification requestSpecification = new RequestSpecBuilder()
        .setBaseUri("https://api.postman.com")
        .addHeader("X-Api-Key", "PMAK-625c2e4609b5351d8e310a67-788d3e5153e6aa26f8eb98e0cd694ef81b")
        .setContentType(ContentType.JSON)
        .log(LogDetail.ALL)
        .build();

    ResponseSpecification responseSpecification = new ResponseSpecBuilder()
        .expectStatusCode(200)
        .expectContentType(ContentType.JSON)
        .log(LogDetail.ALL)
        .build();

    @Test
    public void workspacePojoTest() {
        WorkspaceDto workspaceDto = new WorkspaceDto().generateRandomWorkspace();
        WorkSpaceRoot workSpaceRoot = new WorkSpaceRoot(workspaceDto);

        WorkSpaceRoot deserialized = given(requestSpecification)
            .body(workSpaceRoot)
            .when()
            .post("/workspaces")
            .then()
            .spec(responseSpecification)
            .extract()
            .response().as(WorkSpaceRoot.class);

        Assertions.assertThat(deserialized.getWorkspace().getName())
            .isEqualTo(workSpaceRoot.getWorkspace().getName());
        Assertions.assertThat(deserialized.getWorkspace().getId()).matches("^[a-z0-9-]{36}$");
    }

    @DataProvider(name = "workspace", parallel = true)
    public Object[][] getWorkspaces() {
        return new Object[][]{
            {"my workspace123", "personal", "description"},
            {"my workspace234", "team", "description"}
        };
    }

}
