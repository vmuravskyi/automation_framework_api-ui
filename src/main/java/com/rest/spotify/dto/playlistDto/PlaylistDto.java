package com.rest.spotify.dto.playlistDto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import java.util.List;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "collaborative",
    "description",
    "external_urls",
    "followers",
    "href",
    "id",
    "images",
    "name",
    "owner",
    "primary_color",
    "public",
    "snapshot_id",
    "tracks",
    "type",
    "uri"
})
public class PlaylistDto {

    @JsonProperty("collaborative")
    private Boolean collaborative;
    @JsonProperty("description")
    private String description;
    @JsonProperty("external_urls")
    private ExternalUrls externalUrls;
    @JsonProperty("followers")
    private Followers followers;
    @JsonProperty("href")
    private String href;
    @JsonProperty("id")
    private String id;
    @JsonProperty("images")
    private List<Object> images = null;
    @JsonProperty("name")
    private String name;
    @JsonProperty("owner")
    private Owner owner;
    @JsonProperty("primary_color")
    private Object primaryColor;
    @JsonProperty("public")
    private Boolean _public;
    @JsonProperty("snapshot_id")
    private String snapshotId;
    @JsonProperty("tracks")
    private Tracks tracks;
    @JsonProperty("type")
    private String type;
    @JsonProperty("uri")
    private String uri;

    public PlaylistDto() {
        // default constructor
    }

    public Boolean getCollaborative() {
        return collaborative;
    }

    public PlaylistDto setCollaborative(Boolean collaborative) {
        this.collaborative = collaborative;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public PlaylistDto setDescription(String description) {
        this.description = description;
        return this;
    }

    public ExternalUrls getExternalUrls() {
        return externalUrls;
    }

    public PlaylistDto setExternalUrls(ExternalUrls externalUrls) {
        this.externalUrls = externalUrls;
        return this;
    }

    public Followers getFollowers() {
        return followers;
    }

    public PlaylistDto setFollowers(Followers followers) {
        this.followers = followers;
        return this;
    }

    public String getHref() {
        return href;
    }

    public PlaylistDto setHref(String href) {
        this.href = href;
        return this;
    }

    public String getId() {
        return id;
    }

    public PlaylistDto setId(String id) {
        this.id = id;
        return this;
    }

    public List<Object> getImages() {
        return images;
    }

    public PlaylistDto setImages(List<Object> images) {
        this.images = images;
        return this;
    }

    public String getName() {
        return name;
    }

    public PlaylistDto setName(String name) {
        this.name = name;
        return this;
    }

    public Owner getOwner() {
        return owner;
    }

    public PlaylistDto setOwner(Owner owner) {
        this.owner = owner;
        return this;
    }

    public Object getPrimaryColor() {
        return primaryColor;
    }

    public PlaylistDto setPrimaryColor(Object primaryColor) {
        this.primaryColor = primaryColor;
        return this;
    }

    public Boolean get_public() {
        return _public;
    }

    public PlaylistDto set_public(Boolean _public) {
        this._public = _public;
        return this;
    }

    public String getSnapshotId() {
        return snapshotId;
    }

    public PlaylistDto setSnapshotId(String snapshotId) {
        this.snapshotId = snapshotId;
        return this;
    }

    public Tracks getTracks() {
        return tracks;
    }

    public PlaylistDto setTracks(Tracks tracks) {
        this.tracks = tracks;
        return this;
    }

    public String getType() {
        return type;
    }

    public PlaylistDto setType(String type) {
        this.type = type;
        return this;
    }

    public String getUri() {
        return uri;
    }

    public PlaylistDto setUri(String uri) {
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

        PlaylistDto that = (PlaylistDto) o;

        return new EqualsBuilder().append(collaborative, that.collaborative)
            .append(description, that.description).append(externalUrls, that.externalUrls)
            .append(followers, that.followers)
            .append(href, that.href).append(id, that.id).append(images, that.images).append(name, that.name)
            .append(owner, that.owner).append(primaryColor, that.primaryColor).append(_public, that._public)
            .append(snapshotId, that.snapshotId).append(tracks, that.tracks).append(type, that.type)
            .append(uri, that.uri)
            .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(collaborative).append(description).append(externalUrls)
            .append(followers)
            .append(href).append(id).append(images).append(name).append(owner).append(primaryColor).append(_public)
            .append(snapshotId).append(tracks).append(type).append(uri).toHashCode();
    }
}
