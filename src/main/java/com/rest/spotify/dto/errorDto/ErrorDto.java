package com.rest.spotify.dto.errorDto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "error"
})
public class ErrorDto {

    @JsonProperty("error")
    public InnerError innerError;

    public ErrorDto() {
        // default constructor
    }

    public InnerError getError() {
        return innerError;
    }

    public ErrorDto setError(InnerError innerError) {
        this.innerError = innerError;
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

        ErrorDto errorDto = (ErrorDto) o;

        return new EqualsBuilder().append(innerError, errorDto.innerError).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(innerError).toHashCode();
    }
}
