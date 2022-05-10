package com.rest.spotify;

import static org.assertj.core.api.Assertions.assertThat;

import com.rest.spotify.applicationapi.PlaylistApi;
import com.rest.spotify.dto.errorDto.ErrorDto;
import com.rest.spotify.dto.playlistDto.PlaylistDto;
import com.selenium.pom.utils.JacksonUtils;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class PlaylistTests {

    private PlaylistDto playlistDto;
    private PlaylistDto createdPlaylistDto;

    @BeforeClass
    public void setUp() {
        playlistDto = new PlaylistDto()
            .setName("New Playlist")
            .setDescription("New playlist description")
            .set_public(false);
    }

    private void assertPlaylistIsEqual(PlaylistDto responsePlaylistDto, PlaylistDto requestPlaylistDto) {
        assertThat(responsePlaylistDto.getName()).isEqualTo(requestPlaylistDto.getName());
        assertThat(responsePlaylistDto.getDescription()).isEqualTo(requestPlaylistDto.getDescription());
        assertThat(responsePlaylistDto.get_public()).isEqualTo(requestPlaylistDto.get_public());
    }

    private void assertStatusCodeEqualTo(int actual, int expected) {
        assertThat(actual).isEqualTo(expected);
    }

    private void assertError(ErrorDto errorDto, int statusCode, String errorMessage) {
        assertThat(errorDto.getError().getStatus()).isEqualTo(statusCode);
        assertThat(errorDto.getError().getMessage()).isEqualTo(errorMessage);
    }

    @Test
    public void createPlaylist() {

        Response response = PlaylistApi.post(playlistDto);
        assertStatusCodeEqualTo(response.getStatusCode(), HttpStatus.SC_CREATED);
        createdPlaylistDto = response.as(PlaylistDto.class);

        assertPlaylistIsEqual(createdPlaylistDto, playlistDto);
    }

    @Test
    public void getPlaylist() {

        Response response = PlaylistApi.get(createdPlaylistDto.getId());
        assertStatusCodeEqualTo(response.getStatusCode(), HttpStatus.SC_OK);
        PlaylistDto playlist = response.as(PlaylistDto.class);

        assertThat(playlist.getName()).isEqualTo(playlistDto.getName());
        assertThat(playlist.getDescription()).isEqualTo(playlistDto.getDescription());
        assertThat(playlist.get_public()).isEqualTo(playlistDto.get_public());

        assertPlaylistIsEqual(playlistDto, createdPlaylistDto);
    }

    @Test
    public void updatePlaylist() {
        // update info playlist user in 'createPlaylist' test
        playlistDto
            .setName("Updated Playlist Name")
            .setDescription("Updated playlist description");

        // to get 'id' use created playlistDto by 'createPlaylist' test
        Response response = PlaylistApi.put(createdPlaylistDto.getId(), playlistDto);

        // since there's empty body in response for PUT method, use only status code assertion
        assertStatusCodeEqualTo(response.getStatusCode(), HttpStatus.SC_OK);
    }

    @Test
    public void createPlaylistNegativeWithoutName() {
        String errorMessage = "Missing required field: name";

        PlaylistDto playlistDto = new PlaylistDto().setName("")
            .setDescription("Some description")
            .set_public(false);

        Response response = PlaylistApi.post(playlistDto);
        ErrorDto responseErrorDto = JacksonUtils.deserializeResponseToObject(response, ErrorDto.class);

        assertError(responseErrorDto, HttpStatus.SC_BAD_REQUEST, errorMessage);
    }

    @Test
    public void createPlaylistNegativeWithoutToken() {
        String errorMessage = "Invalid access token";

        PlaylistDto playlistDto = new PlaylistDto().setName("Some name")
            .setDescription("Some description")
            .set_public(false);

        String authorizationInvalidToken = "bla bla bla";

        Response response = PlaylistApi.post(authorizationInvalidToken, playlistDto);
        ErrorDto responseErrorDto = JacksonUtils.deserializeResponseToObject(response, ErrorDto.class);

        assertError(responseErrorDto, HttpStatus.SC_UNAUTHORIZED, errorMessage);
    }

}
