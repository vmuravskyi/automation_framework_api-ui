package com.rest.spotify.dto.playlistDto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "href",
    "total"
})
public class Followers {

    @JsonProperty("href")
    public Object href;
    @JsonProperty("total")
    public Integer total;

    public Object getHref() {
        return href;
    }

    public Followers setHref(Object href) {
        this.href = href;
        return this;
    }

    public Integer getTotal() {
        return total;
    }

    public Followers setTotal(Integer total) {
        this.total = total;
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

        Followers followers = (Followers) o;

        return new EqualsBuilder().append(href, followers.href)
            .append(total, followers.total).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(href).append(total).toHashCode();
    }
}
