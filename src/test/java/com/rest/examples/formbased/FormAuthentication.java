package com.rest.examples.formbased;

import io.restassured.RestAssured;
import io.restassured.authentication.FormAuthConfig;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.session.SessionFilter;
import org.apache.http.HttpStatus;
import org.hamcrest.Matchers;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class FormAuthentication {

    @BeforeClass
    public void setup() {
        RestAssured.requestSpecification = new RequestSpecBuilder()
            .setRelaxedHTTPSValidation()
            .setBaseUri("https://localhost:8443")
            .build();
    }

    // start an app 'Form' from app -> Spring Security
    @Test
    public void formAuthenticationUsingCsrfToken() {
        SessionFilter sessionFilter = new SessionFilter();

        // SessionFilter can extract 'JSESSIONID' by default if the name of Cookie is 'JSESSIONID'
        // there's an option to change default 'JSESSIONID' if needed:
        // RestAssured.config = RestAssured.config().sessionConfig(new SessionConfig().sessionIdName("anotherSESSIONID"));

        RestAssured.given()
            .auth().form("dan", "dan123",
                new FormAuthConfig("/signin", "txtUsername", "txtPassword")
                    .withAutoDetectionOfCsrf())
            .filter(
                sessionFilter) // will extract the sessionID from response header of FormAuthConfig api call and will store sessionID in this sessionFiler object
            .log().all()
            .when()
            .get("/login")
            .then()
            .log().all()
            .statusCode(200);

        System.out.println(sessionFilter.getSessionId());

        // get profile
        RestAssured.given()
            .sessionId(
                sessionFilter.getSessionId()) // in order to be logged in, need to send session id from sessionFilter
            .log().all()
            .when()
            .get("/profile/index")
            .then()
            .log().all()
            .assertThat()
            .statusCode(HttpStatus.SC_OK)
            .body("html.body.div.p", Matchers.equalTo("This is User Profile\\Index. Only authenticated people can see this"));
    }

}
