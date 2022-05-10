package com.rest.spotify;

import static org.assertj.core.api.Assertions.assertThat;

import com.rest.spotify.applicationapi.PlaylistApi;
import com.rest.spotify.dto.errorDto.ErrorDto;
import com.rest.spotify.dto.playlistDto.PlaylistDto;
import com.selenium.pom.utils.JacksonUtils;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@Epic("API: Spotify")
@Feature("API: Playlist")
@Story("Playlist: User is able to create, get, and update a playlist")
public class PlaylistTests {

    private PlaylistDto playlistDto;
    private PlaylistDto createdPlaylistDto;
    private PlaylistAsserts playlistAsserts;

    @BeforeClass
    public void setUp() {
        playlistDto = new PlaylistDto()
            .setName("New Playlist")
            .setDescription("New playlist description")
            .set_public(false);
        playlistAsserts = new PlaylistAsserts();
    }

    @Test(description = "User is able to create a playlist")
    public void createPlaylist() {

        Response response = PlaylistApi.post(playlistDto);
        playlistAsserts.assertStatusCodeEqualTo(response.getStatusCode(), HttpStatus.SC_CREATED);
        createdPlaylistDto = response.as(PlaylistDto.class);

        playlistAsserts.assertPlaylistIsEqual(createdPlaylistDto, playlistDto);
    }

    @Test(description = "User is able to get a playlist")
    public void getPlaylist() {

        Response response = PlaylistApi.get(createdPlaylistDto.getId());
        playlistAsserts.assertStatusCodeEqualTo(response.getStatusCode(), HttpStatus.SC_OK);
        PlaylistDto playlist = response.as(PlaylistDto.class);

        playlistAsserts.assertPlaylistIsEqual(playlistDto, createdPlaylistDto);
    }

    @Test(description = "User is able to update a playlist")
    public void updatePlaylist() {
        // update info playlist user in 'createPlaylist' test
        playlistDto
            .setName("Updated Playlist Name")
            .setDescription("Updated playlist description");

        // to get 'id' use created playlistDto by 'createPlaylist' test
        Response response = PlaylistApi.put(createdPlaylistDto.getId(), playlistDto);

        // since there's empty body in response for PUT method, use only status code assertion
        playlistAsserts.assertStatusCodeEqualTo(response.getStatusCode(), HttpStatus.SC_OK);
    }

    @Test(description = "User is not able to create a playlist without playlist name")
    public void createPlaylistNegativeWithoutName() {
        String errorMessage = "Missing required field: name";

        PlaylistDto playlistDto = new PlaylistDto().setName("")
            .setDescription("Some description")
            .set_public(false);

        Response response = PlaylistApi.post(playlistDto);
        ErrorDto responseErrorDto = JacksonUtils.deserializeResponseToObject(response, ErrorDto.class);

        playlistAsserts.assertError(responseErrorDto, HttpStatus.SC_BAD_REQUEST, errorMessage);
    }

    @Test(description = "User is able to create a playlist with invalid token")
    public void createPlaylistNegativeWithoutToken() {
        String errorMessage = "Invalid access token";

        PlaylistDto playlistDto = new PlaylistDto().setName("Some name")
            .setDescription("Some description")
            .set_public(false);

        String authorizationInvalidToken = "bla bla bla";

        Response response = PlaylistApi.post(authorizationInvalidToken, playlistDto);
        ErrorDto responseErrorDto = JacksonUtils.deserializeResponseToObject(response, ErrorDto.class);

        playlistAsserts.assertError(responseErrorDto, HttpStatus.SC_UNAUTHORIZED, errorMessage);
    }

}
