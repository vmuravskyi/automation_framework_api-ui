package com.selenium.pom.tests;

import com.selenium.pom.base.BaseTest;
import com.selenium.pom.dataproviders.DataToProvide;
import com.selenium.pom.objects.Product;
import com.selenium.pom.pages.CartPage;
import com.selenium.pom.pages.HomePage;
import com.selenium.pom.pages.StorePage;
import org.assertj.core.api.Assertions;
import org.testng.annotations.Test;

public class AddToCartTest extends BaseTest {

    @Test
    public void addToCartFromStorePage() {
        Product product = new Product(1215);

        CartPage cartPage = new StorePage(getDriver())
            .load()
            .getProductThumbnail()
            .clickAddToCartBtn(product.getName())
            .clickViewCart();
        Assertions.assertThat(cartPage.getProductName())
            .isEqualTo(product.getName());
    }

    @Test(dataProvider = "getFeaturedProduct", dataProviderClass = DataToProvide.class)
    public void addToCartFeaturedProduct(Product product) {
        CartPage cartPage = new HomePage(getDriver())
            .load()
            .getProductThumbnail()
            .clickAddToCartBtn(product.getName())
            .clickViewCart();
    }

}
