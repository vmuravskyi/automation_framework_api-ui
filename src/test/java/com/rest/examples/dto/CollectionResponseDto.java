package com.rest.examples.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

@JsonInclude(Include.NON_DEFAULT) // when building an object exclude fields which are null (e.g. Include.NON_NULL) and primitives with default value
@JsonIgnoreProperties(value = "id", allowSetters = true) // allow only when deserializing response to this object, but not when reading this object to POST
public class CollectionResponseDto {

    private String id;
    private String name;
    private String uid;

    public CollectionResponseDto() {
        //
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        CollectionResponseDto that = (CollectionResponseDto) o;

        return new EqualsBuilder().append(id, that.id).append(name, that.name)
            .append(uid, that.uid).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(id).append(name).append(uid).toHashCode();
    }
}
