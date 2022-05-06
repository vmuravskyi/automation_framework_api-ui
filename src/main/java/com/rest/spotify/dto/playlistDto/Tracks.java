package com.rest.spotify.dto.playlistDto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import java.util.List;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "href",
    "items",
    "limit",
    "next",
    "offset",
    "previous",
    "total"
})
public class Tracks {

    @JsonProperty("href")
    public String href;
    @JsonProperty("items")
    public List<Object> items = null;
    @JsonProperty("limit")
    public Integer limit;
    @JsonProperty("next")
    public Object next;
    @JsonProperty("offset")
    public Integer offset;
    @JsonProperty("previous")
    public Object previous;
    @JsonProperty("total")
    public Integer total;

    public String getHref() {
        return href;
    }

    public Tracks setHref(String href) {
        this.href = href;
        return this;
    }

    public List<Object> getItems() {
        return items;
    }

    public Tracks setItems(List<Object> items) {
        this.items = items;
        return this;
    }

    public Integer getLimit() {
        return limit;
    }

    public Tracks setLimit(Integer limit) {
        this.limit = limit;
        return this;
    }

    public Object getNext() {
        return next;
    }

    public Tracks setNext(Object next) {
        this.next = next;
        return this;
    }

    public Integer getOffset() {
        return offset;
    }

    public Tracks setOffset(Integer offset) {
        this.offset = offset;
        return this;
    }

    public Object getPrevious() {
        return previous;
    }

    public Tracks setPrevious(Object previous) {
        this.previous = previous;
        return this;
    }

    public Integer getTotal() {
        return total;
    }

    public Tracks setTotal(Integer total) {
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

        Tracks tracks = (Tracks) o;

        return new EqualsBuilder().append(href, tracks.href).append(items, tracks.items)
            .append(limit, tracks.limit).append(next, tracks.next).append(offset, tracks.offset)
            .append(previous, tracks.previous).append(total, tracks.total).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(href).append(items).append(limit).append(next).append(offset)
            .append(previous).append(total).toHashCode();
    }
}
