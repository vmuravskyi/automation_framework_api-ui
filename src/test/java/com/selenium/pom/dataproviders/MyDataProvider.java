package com.selenium.pom.dataproviders;

import com.selenium.pom.objects.Product;
import com.selenium.pom.utils.JacksonUtils;
import org.testng.annotations.DataProvider;

import java.io.IOException;

public class MyDataProvider {

    @DataProvider(name = "getFeaturedProducts")
    public Object[] getFeaturedProducts() throws IOException {
        return JacksonUtils.deserializeJsonToObject("products", Product[].class);
    }
}
