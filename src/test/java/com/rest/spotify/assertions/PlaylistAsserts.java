package com.rest.spotify.assertions;

import static org.assertj.core.api.Assertions.assertThat;

import com.rest.spotify.dto.errorDto.ErrorDto;
import com.rest.spotify.dto.playlistDto.PlaylistDto;
import io.qameta.allure.Step;

public class PlaylistAsserts {

    @Step
    public void assertPlaylistIsEqual(PlaylistDto responsePlaylistDto, PlaylistDto requestPlaylistDto) {
        assertThat(responsePlaylistDto.getName()).isEqualTo(requestPlaylistDto.getName());
        assertThat(responsePlaylistDto.getDescription()).isEqualTo(requestPlaylistDto.getDescription());
        assertThat(responsePlaylistDto.get_public()).isEqualTo(requestPlaylistDto.get_public());
    }

    @Step
    public void assertStatusCodeEqualTo(int actual, int expected) {
        assertThat(actual).isEqualTo(expected);
    }

    @Step
    public void assertError(ErrorDto errorDto, int statusCode, String errorMessage) {
        assertThat(errorDto.getError().getStatus()).isEqualTo(statusCode);
        assertThat(errorDto.getError().getMessage()).isEqualTo(errorMessage);
    }

}
