package com.automation.dataproviders;

import com.automation.objects.Product;
import com.automation.utils.JacksonUtils;
import org.testng.annotations.DataProvider;

public class TestDataProvider {

    @DataProvider(name = "getFeaturedProducts", parallel = true)
    public Object[] getFeaturedProducts() {
        return JacksonUtils.deserializeJsonToObject("products", Product[].class);
    }

}
