package com.rest.spotify.common;

import com.rest.spotify.common.RestResource;
import com.rest.spotify.common.TokenDto;
import com.rest.spotify.utils.ConfigLoader;
import io.restassured.response.Response;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import org.apache.http.HttpStatus;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TokenManager {

    private static final Logger LOGGER = LogManager.getLogger();
    private static TokenDto tokenDto;

    private static void renewToken() {
        // parameters
        Map<String, Object> formParams = new HashMap<>();
        formParams.put("client_id", ConfigLoader.getInstance().getClientId());
        formParams.put("client_secret", ConfigLoader.getInstance().getClientSecret());
        formParams.put("redirect_uri", ConfigLoader.getInstance().getRedirectUri());
        formParams.put("grant_type", ConfigLoader.getInstance().getGrantType());
        formParams.put("refresh_token", ConfigLoader.getInstance().getRefreshToken());

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
