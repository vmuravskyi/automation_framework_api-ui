package com.selenium.pom.tests;

import com.selenium.pom.base.BaseTest;
import com.selenium.pom.objects.Product;
import com.selenium.pom.pages.CartPage;
import com.selenium.pom.pages.StorePage;
import com.selenium.pom.utils.JacksonUtils;
import org.assertj.core.api.Assertions;
import org.testng.annotations.Test;

public class CartTest extends BaseTest {

    @Test
    public void validateThatCouponGivesFreeShipping() {
        Product product = new Product(1215);
        String coupon = "freeship";
        String expectedAppliedCoupon = "Free shipping coupon";

        CartPage cartPage = new StorePage(getDriver())
            .load()
            .getProductThumbnail()
            .clickAddToCartBtn(product.getName())
            .clickViewCart()
            .addCoupon(coupon);

        Assertions.assertThat(cartPage.getCartTotalCoupon())
            .contains(expectedAppliedCoupon);

    }

}
