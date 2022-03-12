package com.selenium.pom.api.actions;

import com.selenium.pom.api.ApiRequest;
import com.selenium.pom.constants.Endpoints;
import com.selenium.pom.objects.Product;
import io.restassured.http.Cookies;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;

public class CartApi {

    private Cookies cookies;

    public CartApi() {
        // default empty constructor
    }

    public CartApi(Cookies cookies) {
        this.cookies = cookies;
    }

    public Cookies getCookies() {
        return cookies;
    }

    public Response addToCart(Product product, int quantity) {
        Map<String, String> formParams = new HashMap<>();
        formParams.put("product_sku", "");
        formParams.put("product_id", String.valueOf(product.getId()));
        formParams.put("quantity", String.valueOf(quantity));

        if (cookies == null) {
            cookies = new Cookies();
        }

        Response response = ApiRequest.post(
                cookies,
                RequestHeaders.getHeaders(RequestHeaders.CONTENT_TYPE.getHeader()),
                formParams,
                Endpoints.ADD_TO_CART.getValue()
        );

        if (response.getStatusCode() != 200) {
            throw new RuntimeException(String.format("Failed to add product [%s] to cart, HTTP status code: %s",
                    product.getName(), response.getStatusCode()));
        }
        this.cookies = response.getDetailedCookies();
        return response;
    }

}
