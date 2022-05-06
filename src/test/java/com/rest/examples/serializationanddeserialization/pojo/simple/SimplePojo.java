package com.rest.examples.serializationanddeserialization.pojo.simple;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class SimplePojo {

    private String key1;
    private String key2;

    public SimplePojo() {
        // default constructor
    }

    public String getKey1() {
        return key1;
    }

    public SimplePojo setKey1(String key1) {
        this.key1 = key1;
        return this;
    }

    public String getKey2() {
        return key2;
    }

    public SimplePojo setKey2(String key2) {
        this.key2 = key2;
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

        SimplePojo that = (SimplePojo) o;

        return new EqualsBuilder()
            .append(key1, that.key1)
            .append(key2, that.key2)
            .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(key1).append(key2).toHashCode();
    }
}
