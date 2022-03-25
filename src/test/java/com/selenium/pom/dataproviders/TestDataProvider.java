package com.selenium.pom.dataproviders;

import com.selenium.pom.objects.Product;
import com.selenium.pom.utils.JacksonUtils;
import org.testng.annotations.DataProvider;

public class TestDataProvider {

    @DataProvider(name = "getFeaturedProducts", parallel = true)
    public Object[] getFeaturedProducts() {
        return JacksonUtils.deserializeJsonFileToJsonObject("products", Product[].class);
    }

}
