package com.selenium.pom.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class PropertyUtils {

    private final static Logger LOGGER = LogManager.getLogger();

    public static Properties propertyLoader(String filePath) {

        Properties properties = new Properties();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            try {
                LOGGER.info("Reading properties");
                properties.load(reader);
            } catch (IOException e) {
                e.printStackTrace();
                throw new RuntimeException("Failed to load properties file " + filePath);
            }
        } catch (IOException e) {
            LOGGER.info("Failed to read properties");
            e.printStackTrace();
            throw new RuntimeException("Properties file not found at " + filePath);
        }
        return properties;
    }

}
