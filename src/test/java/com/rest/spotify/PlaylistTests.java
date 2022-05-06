package com.rest.spotify;

import com.rest.spotify.dto.errorDto.ErrorDto;
import com.rest.spotify.dto.playlistDto.PlaylistDto;
import com.selenium.pom.utils.JacksonUtils;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.apache.http.HttpStatus;
import org.assertj.core.api.Assertions;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class PlaylistTests {

    private static final String USER_ID = "31rfa5nxoo3x3nhncyt2rshemuqu";

    private RequestSpecification requestSpecification;
    private ResponseSpecification responseSpecification;

    private PlaylistDto playlistDto;
    private PlaylistDto createdPlaylistDto;

    @BeforeClass
    public void setUp() {
        requestSpecification = SpecBuilder.getRequestSpec();
        responseSpecification = SpecBuilder.getResponseSpec();

        playlistDto = new PlaylistDto()
            .setName("New Playlist")
            .setDescription("New playlist description")
            .set_public(false);
    }

    @Test
    public void createPlaylist() {
        createdPlaylistDto = RestAssured.given(requestSpecification)
            .pathParam("userId", USER_ID)
            .body(playlistDto)
            .when()
            .post("/users/{userId}/playlists")
            .then()
            .spec(responseSpecification)
            .assertThat()
            .statusCode(HttpStatus.SC_CREATED)
            .extract()
            .as(PlaylistDto.class);

        Assertions.assertThat(createdPlaylistDto.getName()).isEqualTo(playlistDto.getName());
        Assertions.assertThat(createdPlaylistDto.getDescription()).isEqualTo(playlistDto.getDescription());
        Assertions.assertThat(createdPlaylistDto.get_public()).isEqualTo(playlistDto.get_public());
    }

    @Test
    public void getPlaylist() {
        PlaylistDto response = RestAssured.given(requestSpecification)
            .pathParam("playlistId", createdPlaylistDto.getId())
            .when()
            .get("/playlists/{playlistId}")
            .then()
            .spec(responseSpecification)
            .assertThat()
            .statusCode(HttpStatus.SC_OK)
            .extract()
            .response()
            .as(PlaylistDto.class);

        Assertions.assertThat(response.getName()).isEqualTo(playlistDto.getName());
        Assertions.assertThat(response.getDescription()).isEqualTo(playlistDto.getDescription());
        Assertions.assertThat(response.get_public()).isEqualTo(playlistDto.get_public());
    }

    @Test
    public void updatePlaylist() {
        // update info playlist user in 'createPlaylist' test
        playlistDto
            .setName("Updated Playlist Name")
            .setDescription("Updated playlist description");

        // to get 'id' pathParam use created playlistDto by 'createPlaylist' test
        Response response = RestAssured.given(requestSpecification)
            .pathParam("playlistId", createdPlaylistDto.getId())
            .body(playlistDto)
            .when()
            .put("/playlists/{playlistId}")
            .then()
            .spec(responseSpecification)
            .extract()
            .response();

        // since there's empty body in response for PUT method, use only status code assertion
        Assertions.assertThat(response.statusCode()).isEqualTo(HttpStatus.SC_OK);
    }

    @Test
    public void createPlaylistNegativeWithoutName() {
        PlaylistDto playlistDto = new PlaylistDto().setName("")
            .setDescription("Some description")
            .set_public(false);

        Response response = RestAssured.given(requestSpecification)
            .pathParam("userId", USER_ID)
            .body(playlistDto)
            .when()
            .post("/users/{userId}/playlists")
            .then()
            .spec(responseSpecification)
            .extract()
            .response();

        ErrorDto responseErrorDto = JacksonUtils.deserializeResponseToObject(response, ErrorDto.class);

        Assertions.assertThat(responseErrorDto.getError().getMessage()).isEqualTo("Missing required field: name");
        Assertions.assertThat(responseErrorDto.getError().getStatus()).isEqualTo(HttpStatus.SC_BAD_REQUEST);
    }

    @Test
    public void createPlaylistNegativeWithoutToken() {
        PlaylistDto playlistDto = new PlaylistDto().setName("Some name")
            .setDescription("Some description")
            .set_public(false);

        Response response = RestAssured.given(new RequestSpecBuilder()
                .setBaseUri("https://api.spotify.com")
                .setBasePath("/v1")
                .addHeader("Authorization",
                    "Bearer " + "QWE123OIU")
                .setContentType(ContentType.JSON)
                .log(LogDetail.ALL)
                .build()
            )
            .pathParam("userId", USER_ID)
            .body(playlistDto)
            .when()
            .post("/users/{userId}/playlists")
            .then()
            .spec(responseSpecification)
            .extract()
            .response();

        ErrorDto responseErrorDto = JacksonUtils.deserializeResponseToObject(response, ErrorDto.class);

        Assertions.assertThat(responseErrorDto.getError().getStatus()).isEqualTo(HttpStatus.SC_UNAUTHORIZED);
        Assertions.assertThat(responseErrorDto.getError().getMessage()).isEqualTo("Invalid access token");
    }

}
