package com.automation.tests;

import com.automation.objects.Product;
import com.automation.dataproviders.DataToProvide;
import com.automation.pages.CartPage;
import com.automation.pages.HomePage;
import com.automation.pages.StorePage;
import io.qameta.allure.*;
import org.assertj.core.api.Assertions;
import org.testng.annotations.Test;

@Epic("AskOmDch")
@Feature("Adding products to cart")
public class AddToCartTest extends BaseTest {

    @Story("Adding product to cart from Cart page")
    @Link("https.example.org")
    @Issue("1234567")
    @TmsLink("1234")
    @Description("Verify ability to add a product to cart on Cart page")
    @Test(description = "Verify ability to add a product to cart on Cart page")
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

    @Story("Adding a featured product to cart from Cart page")
    @TmsLink("1235")
    @Description("Verify ability to add a featured product to cart on Cart page")
    @Test(dataProvider = "getFeaturedProduct", dataProviderClass = DataToProvide.class,
            description = "Verify ability to add a featured product to cart on Cart page")
    public void addToCartFeaturedProduct(Product product) {
        CartPage cartPage = new HomePage(getDriver())
                .load()
                .getProductThumbnail()
                .clickAddToCartBtn(product.getName())
                .clickViewCart();

        Assertions.assertThat(cartPage.getProductName())
                .isEqualTo(product.getName());
    }

}
