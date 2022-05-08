package com.rest.spotify.applicationapi;

import com.rest.spotify.RestResource;
import com.rest.spotify.SpecBuilder;
import com.rest.spotify.TokenManager;
import com.rest.spotify.dto.playlistDto.PlaylistDto;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class PlaylistApi {

    private static final String USER_ID = "31rfa5nxoo3x3nhncyt2rshemuqu";
    private static final String PATH = String.format("/users/%s/playlists", "31rfa5nxoo3x3nhncyt2rshemuqu");

    private static final RequestSpecification requestSpecification = SpecBuilder.getRequestSpec();
    private static final ResponseSpecification responseSpecification = SpecBuilder.getResponseSpec();

    public static Response post(PlaylistDto playlistDto) {
        return RestResource.post(TokenManager.getToken(), "/users/31rfa5nxoo3x3nhncyt2rshemuqu/playlists", playlistDto);
    }

    public static Response post(String token, PlaylistDto playlistDto) {
        return RestResource.post(token, "/users/31rfa5nxoo3x3nhncyt2rshemuqu/playlists", playlistDto);
    }

    public static Response get(String playlistId) {
        return RestResource.get(TokenManager.getToken(), "/playlists/" + playlistId);
    }

    public static Response put(String playlistId, PlaylistDto newPlaylistPayload) {
        return RestResource.put(TokenManager.getToken(), "/playlists/" + playlistId, newPlaylistPayload);
    }

}
