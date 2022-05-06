package com.rest.spotify;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class SpecBuilder {

    private static final String TOKEN = "BQDFBu1BOEtm7h2K3pIQKzz5u3KypZAVJySVct6bRAkyqI-fuQps9xfMxnuhZruRNDih9nK9nGk2ZR41qW_puAzOS_5X5KerH2CzG9cEzamLRu-GfbscYQxKyiQ5cFPrhqcL36Ykvj5sAVHrb9AVsD023JQBFz29rGtDUj2c9WHWZrQy31M7qzNdVCebFHUJC_fSVyxlLDYDPHEYeaZBfZCpoyKVnnFKkKmkZUzAEN1gHI5E";

    public static RequestSpecification getRequestSpec() {
        return new RequestSpecBuilder()
            .setBaseUri("https://api.spotify.com")
            .setBasePath("/v1")
            .addHeader("Authorization",
                "Bearer " + TOKEN)
            .setContentType(ContentType.JSON)
            .log(LogDetail.ALL)
            .build();
    }

    public static ResponseSpecification getResponseSpec() {
        return new ResponseSpecBuilder()
            .log(LogDetail.ALL)
            .build();
    }

}
