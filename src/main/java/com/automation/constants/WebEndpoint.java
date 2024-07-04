package com.automation.constants;

public enum WebEndpoint {
    STORE("/store"),
    CHECKOUT("/checkout"),
    CART("/cart");

    private String value;

    WebEndpoint(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
