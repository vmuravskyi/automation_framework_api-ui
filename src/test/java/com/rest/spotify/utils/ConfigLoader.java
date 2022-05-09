package com.rest.spotify.utils;

import java.util.Properties;

public class ConfigLoader {

    private static ConfigLoader configLoader;
    private final Properties properties;

    private ConfigLoader() {
        properties = PropertyUtils.propertyLoader("src/test/resources/restConfig.properties");
    }

    public static ConfigLoader getInstance() {
        if (configLoader == null) {
            configLoader = new ConfigLoader();
        }
        return configLoader;
    }

    public String getClientId() {
        String url = properties.getProperty("clientId");
        if (url != null) {
            return url;
        } else {
            throw new RuntimeException("Property 'clientId' is not specified in the restConfig.properties file");
        }
    }

    public String getClientSecret() {
        String prop = properties.getProperty("clientSecret");
        if (prop != null) {
            return prop;
        } else {
            throw new RuntimeException("Property 'clientSecret' is not specified in the restConfig.properties file");
        }
    }

    public String getRedirectUri() {
        String prop = properties.getProperty("redirectUri");
        if (prop != null) {
            return prop;
        } else {
            throw new RuntimeException("Property 'redirectUri' is not specified in the restConfig.properties file");
        }
    }

    public String getGrantType() {
        String prop = properties.getProperty("grantType");
        if (prop != null) {
            return prop;
        } else {
            throw new RuntimeException("Property 'grantType' is not specified in the restConfig.properties file");
        }
    }

    public String getRefreshToken() {
        String prop = properties.getProperty("refreshToken");
        if (prop != null) {
            return prop;
        } else {
            throw new RuntimeException("Property 'refreshToken' is not specified in the restConfig.properties file");
        }
    }

    public String getUserId() {
        String prop = properties.getProperty("userId");
        if (prop != null) {
            return prop;
        } else {
            throw new RuntimeException("Property 'userId' is not specified in the restConfig.properties file");
        }
    }

}
