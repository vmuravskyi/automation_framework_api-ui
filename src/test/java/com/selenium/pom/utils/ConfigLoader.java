package com.selenium.pom.utils;

import com.selenium.pom.constants.EnvType;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;
import java.util.stream.Collectors;

public class ConfigLoader {

    private static ConfigLoader configLoader;
    private final Properties properties;

    private ConfigLoader() {
        String env = System.getProperty("env", String.valueOf(EnvType.QA));
        switch (EnvType.valueOf(env)) {
            case QA: {
                properties = PropertyUtils.propertyLoader("src/test/resources/qa_config.properties");
//                properties = PropertyUtils.propertyLoader("/qa_config.properties");
                break;
            }
            case PRODUCTION: {
                properties = PropertyUtils.propertyLoader("src/test/resources/production_config.properties");
//                properties = PropertyUtils.propertyLoader("/production_config.properties");
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
