package com.rest.spotify.dto.playlistDto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "spotify"
})
public class ExternalUrls {

    @JsonProperty("spotify")
    public String spotify;

    public String getSpotify() {
        return spotify;
    }

    public ExternalUrls setSpotify(String spotify) {
        this.spotify = spotify;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ExternalUrls that = (ExternalUrls) o;

        return new EqualsBuilder().append(spotify, that.spotify).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(spotify).toHashCode();
    }
}
