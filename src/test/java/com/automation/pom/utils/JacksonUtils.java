package com.automation.pom.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.io.InputStream;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class JacksonUtils {

    private static Logger LOGGER = LogManager.getLogger();

    public static <T> T deserializeJsonToObject(String fileName, Class<T> T) {
        InputStream is = JacksonUtils.class.getClassLoader().getResourceAsStream(fileName);
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            LOGGER.debug("deserializing {} into {}", is, T);
            return objectMapper.readValue(is, T);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
