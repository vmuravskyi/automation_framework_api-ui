package com.rest.cookie;

import io.restassured.RestAssured;
import io.restassured.authentication.FormAuthConfig;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.session.SessionFilter;
import io.restassured.http.Cookie;
import io.restassured.http.Cookies;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.hamcrest.Matchers;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class CookieTest {

    @BeforeClass
    public void setup() {
        RestAssured.requestSpecification = new RequestSpecBuilder()
            .setRelaxedHTTPSValidation()
            .setBaseUri("https://localhost:8443")
            .build();
    }

    @Test
    public void cookieExample() {
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

        Cookie cookie = new Cookie.Builder("JSESSIONID", sessionFilter.getSessionId())
            .setSecured(true)
            .setHttpOnly(true)
            .setComment("my cookie")
            .build();
        Cookie cookie2 = new Cookie.Builder("dummy", "dummyValue").build();

        Cookies cookies = new Cookies(cookie, cookie2);

        // get profile
        RestAssured.given()
            .cookies(cookies) // send the cookie to maintain the user session
            .log().all()
            .when()
            .get("/profile/index")
            .then()
            .log().all()
            .assertThat()
            .statusCode(HttpStatus.SC_OK)
            .body("html.body.div.p",
                Matchers.equalTo("This is User Profile\\Index. Only authenticated people can see this"));
    }

    @Test
    public void fetchSingleCookie() {
        Response response = RestAssured.given()
            .log().all()
            .when()
            .get("/profile/index")
            .then()
            .log().all()
            .assertThat()
            .statusCode(HttpStatus.SC_OK)
            .extract()
            .response();

        Cookie jsessionid = response.getDetailedCookie("JSESSIONID");
        System.out.println(jsessionid);
    }

    @Test
    public void fetchMultipleCookies() {
        Response response = RestAssured.given()
            .log().all()
            .when()
            .get("/profile/index")
            .then()
            .log().all()
            .assertThat()
            .statusCode(HttpStatus.SC_OK)
            .extract()
            .response();

        Cookies detailedCookies = response.getDetailedCookies();
        System.out.println(detailedCookies);
    }

}
