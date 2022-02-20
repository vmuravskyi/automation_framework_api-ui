package com.selenium.pom.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;

public class JacksonUtils {

    private final static Logger LOGGER = LogManager.getLogger();


    public static <T> T deserializeJsonToObject(String fileName, Class<T> T) {
        InputStream inputStream = JacksonUtils.class.getClassLoader().getResourceAsStream(fileName + ".json");
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            LOGGER.info("Deserializing [{}] into object", fileName);
            return objectMapper.readValue(inputStream, T);
        } catch (IOException e) {
            LOGGER.info("Failed to deserialize [{}] into object", "fileName");
            e.printStackTrace();
        }
        return null;
    }
}
