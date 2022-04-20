package com.rest.serializationanddeserialization.pojo.complex;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class Batters {

    private List<Batter> batter = new ArrayList<Batter>();

    public Batters() {
        // default constructor
    }

    public List<Batter> getBatter() {
        return batter;
    }

    public void setBatter(List<Batter> batter) {
        this.batter = batter;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Batters batters = (Batters) o;

        return new EqualsBuilder().append(batter, batters.batter).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(batter).toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
            .append("batter", batter)
            .toString();
    }
}
