package com.rest.spotify;

import io.restassured.response.Response;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import org.apache.http.HttpStatus;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TokenManager {

    private static final Logger LOGGER = LogManager.getLogger();
    private static final String CLIENT_ID = "ebe56da47bd1436dace22a5de08f3c44";
    private static final String CLIENT_SECRET = "9bd505a72dd0490bbe2ebed353b854b7";
    private static final String REDIRECT_URI = "https://localhost:8080";
    private static final String GRAND_TYPE = "refresh_token";
    private static final String REFRESH_TOKEN = "AQC_HNrWd_rjsg3Ss9XsCtKOVSYQA-BZ_b0AWjDdZsbAX4h-kYbGTYUYrk1wuZn1ntZkDHQTVi9YHkDq3GhMjutnugQbg89w8myCIeDmorIzbwkj-t1fTsjq6_exNnts7UI";
    private static TokenDto tokenDto;

    private static void renewToken() {
        // parameters
        Map<String, Object> formParams = new HashMap<>();
        formParams.put("client_id", CLIENT_ID);
        formParams.put("client_secret", CLIENT_SECRET);
        formParams.put("redirect_uri", REDIRECT_URI);
        formParams.put("grant_type", GRAND_TYPE);
        formParams.put("refresh_token", REFRESH_TOKEN);

        Response response = RestResource.postAccount(formParams);

        if (response.statusCode() != HttpStatus.SC_OK) {
            throw new RuntimeException("Failed to Renew Token");
        }
        tokenDto = response.as(TokenDto.class);
    }

    private static Boolean isExpired() {
        Instant expiryTime = Instant.now().plusSeconds(tokenDto.getExpiresIn() - 300);
        return Instant.now().isAfter(expiryTime);
    }


    public static String getToken() {
        if (tokenDto == null || isExpired()) {
            LOGGER.info("Token is null or expired. Renewing token...");
            renewToken();
        } else {
            LOGGER.info("Token is valid, using existing token...");
        }
        return tokenDto.getAccessToken();
    }

}
