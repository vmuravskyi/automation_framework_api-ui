package com.rest.graphQL.dto;

/**
 * This is a main dto class for the complete query and json variables
 */
public class GraphQLQuery {

    private String query;
    private Object variables;

    public GraphQLQuery() {
        // default
    }

    public String getQuery() {
        return query;
    }

    public GraphQLQuery setQuery(String query) {
        this.query = query;
        return this;
    }

    public Object getVariables() {
        return variables;
    }

    public GraphQLQuery setVariables(Object variables) {
        this.variables = variables;
        return this;
    }
}
