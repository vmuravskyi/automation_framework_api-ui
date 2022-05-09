package com.rest.spotify.applicationapi;

import static com.rest.spotify.common.Endpoints.PLAYLISTS;
import static com.rest.spotify.common.Endpoints.USERS;
import static com.rest.spotify.common.TokenManager.getToken;

import com.rest.spotify.common.RestResource;
import com.rest.spotify.common.SpecBuilder;
import com.rest.spotify.dto.playlistDto.PlaylistDto;
import com.rest.spotify.utils.ConfigLoader;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class PlaylistApi {

    private static final RequestSpecification requestSpecification = SpecBuilder.getRequestSpec();
    private static final ResponseSpecification responseSpecification = SpecBuilder.getResponseSpec();

    public static Response post(PlaylistDto playlistDto) {
        return RestResource.post(getToken(), USERS + "/" + ConfigLoader.getInstance().getUserId() + PLAYLISTS,
            playlistDto);
    }

    public static Response post(String token, PlaylistDto playlistDto) {
        return RestResource.post(token, USERS + "/" + ConfigLoader.getInstance().getUserId() + PLAYLISTS, playlistDto);
    }

    public static Response get(String playlistId) {
        return RestResource.get(getToken(), PLAYLISTS + "/" + playlistId);
    }

    public static Response put(String playlistId, PlaylistDto newPlaylistPayload) {
        return RestResource.put(getToken(), PLAYLISTS + "/" + playlistId, newPlaylistPayload);
    }

}
