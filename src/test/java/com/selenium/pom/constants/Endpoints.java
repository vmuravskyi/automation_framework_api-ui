package com.selenium.pom.constants;

import com.selenium.pom.utils.ConfigLoader;

public enum Endpoints {
    BASE_URI(ConfigLoader.getInstance().getBaseUrl()),
    ACCOUNT("/account"),
    ADD_TO_CART("/?wc-ajax=add_to_cart");

    private String value;

    Endpoints(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}
