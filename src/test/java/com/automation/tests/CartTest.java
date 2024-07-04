package com.automation.tests;

import com.automation.objects.Product;
import com.automation.pages.CartPage;
import com.automation.pages.StorePage;
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
