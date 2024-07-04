package com.automation.utils;

import com.automation.constants.EnvType;
import java.util.Properties;

public class ConfigLoader {

    private static ConfigLoader configLoader;
    private final Properties properties;

    private ConfigLoader() {
        String env = System.getProperty("env", String.valueOf(EnvType.QA));
        switch (EnvType.valueOf(env)) {
            case QA: {
                properties = PropertyUtils.propertyLoader("src/test/resources/qa_config.properties");
                break;
            }
            case PRODUCTION: {
                properties = PropertyUtils.propertyLoader("src/test/resources/production_config.properties");
                break;
            }
            default: {
                throw new IllegalStateException("Invalid environment type: " + env);
            }
        }
    }

    public static ConfigLoader getInstance() {
        if (configLoader == null) {
            configLoader = new ConfigLoader();
        }
        return configLoader;
    }

    public String getBaseUrl() {
        String url = properties.getProperty("baseUrl");
        if (url != null) {
            return url;
        } else {
            throw new RuntimeException("Property baseUrl is not specified in the qa_config.properties file");
        }
    }

    public String getUsername() {
        String prop = properties.getProperty("username");
        if (prop != null) {
            return prop;
        } else {
            throw new RuntimeException("Property username is not specified in the qa_config.properties file");
        }
    }

    public String getPassword() {
        String prop = properties.getProperty("password");
        if (prop != null) {
            return prop;
        } else {
            throw new RuntimeException("Property password is not specified in the qa_config.properties file");
        }
    }
}
