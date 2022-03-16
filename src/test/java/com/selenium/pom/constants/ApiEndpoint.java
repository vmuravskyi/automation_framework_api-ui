package com.selenium.pom.constants;

import com.selenium.pom.utils.ConfigLoader;

public enum ApiEndpoint {
    BASE_URI(ConfigLoader.getInstance().getBaseUrl()),
    ACCOUNT("/account"),
    ADD_TO_CART("/?wc-ajax=add_to_cart");

    private String value;

    ApiEndpoint(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}
