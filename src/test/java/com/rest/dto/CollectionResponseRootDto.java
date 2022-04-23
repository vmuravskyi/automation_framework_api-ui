package com.rest.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class CollectionResponseRootDto {

    @JsonProperty("collection")
    CollectionResponseDto collection;

    public CollectionResponseRootDto() {
        // default constructor
    }

    public CollectionResponseDto getCollection() {
        return collection;
    }

    public void setCollection(CollectionResponseDto collection) {
        this.collection = collection;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        CollectionResponseRootDto that = (CollectionResponseRootDto) o;

        return new EqualsBuilder().append(collection,
            that.collection).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(collection).toHashCode();
    }
}
