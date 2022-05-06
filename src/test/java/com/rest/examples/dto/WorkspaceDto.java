package com.rest.examples.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.selenium.pom.utils.FakerUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

@JsonInclude(Include.NON_DEFAULT) // when building an object exclude fields which are null (e.g. Include.NON_NULL) and primitives with default value
@JsonIgnoreProperties(value = "id", allowSetters = true) // allow only when deserializing response to this object, but not when reading this object to POST
public class WorkspaceDto {

    @JsonProperty("id")
    private String id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("type")
    private String type;
    @JsonProperty("description")
    private String description;

    public WorkspaceDto() {
        // default constructor
    }

    public String getId() {
        return id;
    }

    public WorkspaceDto setId(String id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public WorkspaceDto setName(String name) {
        this.name = name;
        return this;
    }

    public String getType() {
        return type;
    }

    public WorkspaceDto setType(String type) {
        this.type = type;
        return this;
    }

    public String getDescription() {
        return type;
    }

    public WorkspaceDto setDescription(String description) {
        this.description = description;
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

        WorkspaceDto that = (WorkspaceDto) o;

        return new EqualsBuilder()
            .append(id, that.id)
            .append(name, that.name)
            .append(type, that.type)
            .append(description, that.description)
            .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
            .append(id)
            .append(name)
            .append(type)
            .append(description)
            .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
            .append("id", id)
            .append("name", name)
            .append("type", type)
            .append("description", description)
            .toString();
    }

    public WorkspaceDto generateRandomWorkspace() {
        FakerUtils fakerUtils = new FakerUtils();
        return new WorkspaceDto()
            .setName(String.valueOf(fakerUtils.generateRandomNumber()))
            .setType("personal")
            .setDescription("randomly generated workspace");
    }

}
