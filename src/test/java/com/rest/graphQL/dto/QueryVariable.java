package com.rest.graphQL.dto;

public class QueryVariable {

    private int limit;
    private String name;

    public int getLimit() {
        return limit;
    }

    public QueryVariable setLimit(int limit) {
        this.limit = limit;
        return this;
    }

    public String getName() {
        return name;
    }

    public QueryVariable setName(String name) {
        this.name = name;
        return this;
    }
}
