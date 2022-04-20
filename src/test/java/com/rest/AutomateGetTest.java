package com.rest;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.emptyArray;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasEntry;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.hasKey;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.hasValue;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.startsWith;

import com.rest.dto.DtoConverter;
import com.rest.dto.WorkSpaceRoot;
import com.rest.dto.WorkspaceDto;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import java.util.Collections;
import org.assertj.core.api.Assertions;
import org.hamcrest.MatcherAssert;
import org.testng.annotations.Test;

public class AutomateGetTest {

    @Test
    public void validateStatusCode() {
        given()
            .baseUri("https://api.postman.com")
            .header("X-Api-Key", System.getenv("postman_api_key"))
            .log().all()
            .when()
            .get("/workspaces")
            .then()
            .log().all()
            .assertThat()
            .statusCode(200);
    }

    @Test
    public void validateResponseBody() {
        given()
            .baseUri("https://api.postman.com")
            .header("X-Api-Key", System.getenv("postman_api_key"))
            .when()
            .get("/workspaces")
            .then()
            .log().all()
            .assertThat()
            .statusCode(200)
            .body("workspaces.name", hasItems("Other", "GreenCity", "CE"),
                "workspaces.type", hasItems("team", "personal", "personal"),
                "workspaces[0].name", equalTo("Other"),
                "workspaces[0].name", is(equalTo("Other")),
                "workspaces.size()", equalTo(8),
                "workspaces.name", hasItem("GreenCity")
            );
    }

    @Test
    public void validateResponseBodyHamcrestLearnings() {
        given()
            .baseUri("https://api.postman.com")
            .header("X-Api-Key", System.getenv("postman_api_key"))
            .when()
            .get("/workspaces")
            .then()
            .log().all()
            .assertThat()
            .statusCode(200)
            .body("workspaces.name", hasItems("Other", "GreenCity", "CE"),
                "workspaces.name", is(not(emptyArray())),
                "workspaces.name", hasSize(8),
                "workspaces[0]", hasKey("id"),
                "workspaces[0]", hasValue("Other"),
                "workspaces[0]", hasEntry("id", "3282e4de-eb57-4822-826c-9b0a25f13de7"),
                "workspaces[0]", not(equalTo(Collections.EMPTY_MAP)),
                "workspaces[3].name", allOf(startsWith("API"), containsString("Automation"))
            );
    }

    @Test
    public void extractResponse() {
        Response res = given()
            .baseUri("https://api.postman.com")
            .header("X-Api-Key", System.getenv("postman_api_key"))
            .when()
            .get("/workspaces")
            .then()
            .assertThat()
            .statusCode(200)
            .extract()
            .response();
        System.out.println("response = " + res.asString());
    }

    @Test
    public void extractSingleValueFromResponse() {
        Response res = given()
            .baseUri("https://api.postman.com")
            .header("X-Api-Key", System.getenv("postman_api_key"))
            .when()
            .get("/workspaces")
            .then()
            .assertThat()
            .statusCode(200)
            .extract()
            .response();

        // print a single value from json response
        System.out.println("Workspace name = " + res.path("workspaces[0].name"));

        // or by using jsonPath
        JsonPath jsonPath = new JsonPath(res.asString());
        System.out.println("Workspace name = " + jsonPath.getString("workspaces[0].name"));
    }

    @Test
    public void hamcrestAssertOnExtractedResponse() {
        Response response = given()
            .baseUri("https://api.postman.com")
            .header("X-Api-Key", System.getenv("postman_api_key"))
            .when()
            .get("/workspaces")
            .then()
            .assertThat()
            .statusCode(200)
            .extract()
            .response();
        String workspaceName = response.path("workspaces[0].name");
        MatcherAssert.assertThat(workspaceName, equalTo("Other"));
    }

    @Test
    public void getAllWorkspaces() {
        Response response = given()
            .baseUri("https://api.postman.com")
            .header("X-Api-Key", System.getenv("postman_api_key"))
            .when()
            .get("/workspaces")
            .then()
            .log().all()
            .assertThat()
            .statusCode(200)
            .extract()
            .response();

        WorkSpaceRoot workspaces = DtoConverter.getResponseAsDto(response, WorkSpaceRoot.class);
        WorkspaceDto workspace = workspaces.getWorkspace();
        Assertions.assertThat(workspace.getType())
            .isEqualTo("team");
    }

}
