package com.rest.examples.serializationanddeserialization.pojo.complex;


import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;


public class Root {

    private String id;
    private String type;
    private String name;
    private double ppu;
    private Batters batters;
    private List<Topping> topping = new ArrayList<Topping>();

    public Root() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPpu() {
        return ppu;
    }

    public void setPpu(double ppu) {
        this.ppu = ppu;
    }

    public Batters getBatters() {
        return batters;
    }

    public void setBatters(Batters batters) {
        this.batters = batters;
    }

    public List<Topping> getTopping() {
        return topping;
    }

    public void setTopping(List<Topping> topping) {
        this.topping = topping;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Root root = (Root) o;

        return new EqualsBuilder().append(id, root.id).append(type, root.type)
            .append(name, root.name).append(ppu, root.ppu).append(batters, root.batters).append(topping, root.topping)
            .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(id).append(type).append(name).append(ppu).append(batters)
            .append(topping)
            .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
            .append("id", id)
            .append("type", type)
            .append("name", name)
            .append("ppu", ppu)
            .append("batters", batters)
            .append("topping", topping)
            .toString();
    }
}
