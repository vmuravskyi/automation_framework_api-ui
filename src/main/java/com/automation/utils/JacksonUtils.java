package com.automation.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.io.InputStream;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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
