package com.selenium.pom.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.response.Response;
import java.io.IOException;
import java.io.InputStream;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class JacksonUtils {

    private final static Logger LOGGER = LogManager.getLogger();

    public static <T> T deserializeJsonFileToJsonObject(String fileName, Class<T> T) {
        T data = null;
        InputStream inputStream = JacksonUtils.class.getClassLoader().getResourceAsStream(fileName + ".json");
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            LOGGER.info("Deserializing [{}] into object", fileName);
            data = objectMapper.readValue(inputStream, T);
        } catch (IOException e) {
            LOGGER.info("Failed to deserialize [{}] into object", "fileName");
            e.printStackTrace();
        }
        return data;
    }

    public static <T> T deserializeResponseToObject(Response response, Class<T> T) {
        T data = null;
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            LOGGER.info("Deserializing response into object");
            data = objectMapper.readValue(response.asString(), T);
        } catch (IOException e) {
            LOGGER.info("Failed to deserialize response into object");
            e.printStackTrace();
        }
        return data;
    }

}
