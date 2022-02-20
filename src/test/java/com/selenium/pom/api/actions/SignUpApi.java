package com.selenium.pom.api.actions;

import com.selenium.pom.objects.User;
import com.selenium.pom.utils.ConfigLoader;
import io.restassured.http.Cookies;
import io.restassured.response.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class SignUpApi {
    private Cookies cookies;

    public Cookies getCookies() {
        return cookies;
    }

    private String fetchRegisterNonceValueUsingGroovy() {
        Response response = getAccount();
        return response.htmlPath().getString("**.findAll { it.@name == 'woocommerce-register-nonce' }.@value");
    }

    /**
     * Parse html for getting 'Nonce' string from html response to use it in cookies
     *
     * @return the 'Nonce' string for cookies
     */
    private String fetchRegisterNonceValueUsingJsoup() {
        Response response = getAccount();
        Document document = Jsoup.parse(response.asString());
        Element element = document.selectFirst("#woocommerce-login-nonce");
        String value = element.attr("value");
        return value;
    }

    private Response getAccount() {
        Cookies cookies = new Cookies();
        Response response = given()
                .baseUri(ConfigLoader.getInstance().getBaseUrl())
                .cookies(cookies)
                .log().all()
                .when()
                .get("/account")
                .then()
                .log().all()
                .extract()
                .response();
        if (response.getStatusCode() != 200) {
            throw new RuntimeException("Failed to fetch the account, HTTP status code: " + response.getStatusCode());
        }
        return response;
    }

    public Response register(User user) {

        Cookies cookies = new Cookies();

        Map<String, String> formParams = new HashMap<>();
        formParams.put("username", user.getUsername());
        formParams.put("email", user.getEmail());
        formParams.put("password", user.getPassword());
        formParams.put("woocommerce-register-nonce", fetchRegisterNonceValueUsingJsoup());
        formParams.put("register", "Register");

        Response response = given()
                .baseUri(ConfigLoader.getInstance().getBaseUrl())
                .cookies(cookies)
                .headers(RequestHeaders.getHeaders(RequestHeaders.CONTENT_TYPE.getHeader()))
                .formParams(formParams)
                .log().all()
                .when()
                .post("/account")
                .then()
                .log().all()
                .extract()
                .response();
        if (response.getStatusCode() != 200) {
            throw new RuntimeException("Failed to register the account, HTTP status code: " + response.getStatusCode());
        }
        this.cookies = response.getDetailedCookies();
        return response;
    }
}
