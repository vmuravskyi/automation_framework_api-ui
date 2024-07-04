package com.automation.dataproviders;

import com.automation.objects.Product;
import com.automation.utils.JacksonUtils;
import org.testng.annotations.DataProvider;

public class DataToProvide {

    @DataProvider(name = "getFeaturedProduct", parallel = true)
    public Object[] getFeaturedProduct() {
        return JacksonUtils.deserializeJsonToObject("products", Product[].class);
    }

}
