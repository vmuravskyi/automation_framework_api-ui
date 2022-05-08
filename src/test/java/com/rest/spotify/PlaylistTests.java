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

    @Test
    public void createPlaylist() {

        Response response = PlaylistApi.post(playlistDto);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.SC_CREATED);
        createdPlaylistDto = response.as(PlaylistDto.class);

        assertThat(createdPlaylistDto.getName()).isEqualTo(playlistDto.getName());
        assertThat(createdPlaylistDto.getDescription()).isEqualTo(playlistDto.getDescription());
        assertThat(createdPlaylistDto.get_public()).isEqualTo(playlistDto.get_public());
    }

    @Test
    public void getPlaylist() {

        Response response = PlaylistApi.get(createdPlaylistDto.getId());
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.SC_OK);
        PlaylistDto playlist = response.as(PlaylistDto.class);

        assertThat(playlist.getName()).isEqualTo(playlistDto.getName());
        assertThat(playlist.getDescription()).isEqualTo(playlistDto.getDescription());
        assertThat(playlist.get_public()).isEqualTo(playlistDto.get_public());
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
        assertThat(response.statusCode()).isEqualTo(HttpStatus.SC_OK);
    }

    @Test
    public void createPlaylistNegativeWithoutName() {
        PlaylistDto playlistDto = new PlaylistDto().setName("")
            .setDescription("Some description")
            .set_public(false);

        Response response = PlaylistApi.post(playlistDto);
        ErrorDto responseErrorDto = JacksonUtils.deserializeResponseToObject(response, ErrorDto.class);

        assertThat(responseErrorDto.getError().getMessage()).isEqualTo("Missing required field: name");
        assertThat(responseErrorDto.getError().getStatus()).isEqualTo(HttpStatus.SC_BAD_REQUEST);
    }

    @Test
    public void createPlaylistNegativeWithoutToken() {
        PlaylistDto playlistDto = new PlaylistDto().setName("Some name")
            .setDescription("Some description")
            .set_public(false);

        String authorizationInvalidToken = "bla bla bla";

        Response response = PlaylistApi.post(authorizationInvalidToken, playlistDto);
        ErrorDto responseErrorDto = JacksonUtils.deserializeResponseToObject(response, ErrorDto.class);

        assertThat(responseErrorDto.getError().getStatus()).isEqualTo(HttpStatus.SC_UNAUTHORIZED);
        assertThat(responseErrorDto.getError().getMessage()).isEqualTo("Invalid access token");
    }

}
