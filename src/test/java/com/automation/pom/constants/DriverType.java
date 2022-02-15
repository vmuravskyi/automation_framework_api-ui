package com.automation.pom.constants;

public enum DriverType {
    CHROME("chrome"),
    FIREFOX("firefox");

    private String value;

    DriverType(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }

}
