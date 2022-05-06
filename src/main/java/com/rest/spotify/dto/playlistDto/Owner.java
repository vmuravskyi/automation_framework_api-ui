package com.rest.spotify.dto.playlistDto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "display_name",
    "external_urls",
    "href",
    "id",
    "type",
    "uri"
})
public class Owner {

    @JsonProperty("display_name")
    public String displayName;
    @JsonProperty("external_urls")
    public ExternalUrls externalUrls;
    @JsonProperty("href")
    public String href;
    @JsonProperty("id")
    public String id;
    @JsonProperty("type")
    public String type;
    @JsonProperty("uri")
    public String uri;

    public String getDisplayName() {
        return displayName;
    }

    public Owner setDisplayName(String displayName) {
        this.displayName = displayName;
        return this;
    }

    public ExternalUrls getExternalUrls() {
        return externalUrls;
    }

    public Owner setExternalUrls(ExternalUrls externalUrls) {
        this.externalUrls = externalUrls;
        return this;
    }

    public String getHref() {
        return href;
    }

    public Owner setHref(String href) {
        this.href = href;
        return this;
    }

    public String getId() {
        return id;
    }

    public Owner setId(String id) {
        this.id = id;
        return this;
    }

    public String getType() {
        return type;
    }

    public Owner setType(String type) {
        this.type = type;
        return this;
    }

    public String getUri() {
        return uri;
    }

    public Owner setUri(String uri) {
        this.uri = uri;
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

        Owner owner = (Owner) o;

        return new EqualsBuilder().append(displayName, owner.displayName)
            .append(externalUrls, owner.externalUrls).append(href, owner.href).append(id, owner.id)
            .append(type, owner.type)
            .append(uri, owner.uri).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(displayName).append(externalUrls).append(href).append(id).append(type)
            .append(uri).toHashCode();
    }
}
