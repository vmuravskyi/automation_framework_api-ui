package com.rest.spotify.dto.errorDto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "status",
    "message"
})
public class InnerError {

    @JsonProperty("status")
    public Integer status;
    @JsonProperty("message")
    public String message;

    public InnerError() {
        // default constructor
    }

    public Integer getStatus() {
        return status;
    }

    public InnerError setStatus(Integer status) {
        this.status = status;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public InnerError setMessage(String message) {
        this.message = message;
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

        InnerError innerError = (InnerError) o;

        return new EqualsBuilder().append(status, innerError.status)
            .append(message, innerError.message).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(status).append(message).toHashCode();
    }
}
