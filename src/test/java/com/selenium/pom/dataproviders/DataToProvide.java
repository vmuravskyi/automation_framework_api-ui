package com.selenium.pom.dataproviders;

import com.selenium.pom.objects.Product;
import com.selenium.pom.utils.JacksonUtils;
import org.testng.annotations.DataProvider;

public class DataToProvide {

    @DataProvider(name = "getFeaturedProduct", parallel = true)
    public Object[] getFeaturedProduct() {
        return JacksonUtils.deserializeJsonFileToJsonObject("products", Product[].class);
    }

}
