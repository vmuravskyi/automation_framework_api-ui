package com.automation;

import com.automation.pom.base.BaseTest;
import com.automation.pom.objects.BillingAddress;
import com.automation.pom.objects.Product;
import com.automation.pom.pages.CartPage;
import com.automation.pom.pages.CheckOutPage;
import com.automation.pom.pages.HomePage;
import com.automation.pom.pages.StorePage;
import com.automation.pom.utils.JacksonUtils;
import org.assertj.core.api.Assertions;
import org.testng.annotations.Test;

public class FirstTest extends BaseTest {

    private final String expectedNoticeText = "Thank you. Your order has been received.";
    private final String searchText = "blue";


    @Test
    public void guestCheckoutUsingDirectBankTransfer() throws InterruptedException {

        BillingAddress billingAddress =
            JacksonUtils.deserializeJsonToObject("BillingAddress.json", BillingAddress.class);
        Product product = new Product(1215);

        HomePage homePage = new HomePage(driver);
        StorePage storePage = homePage.navigateToStoreUsingMenu();
        Thread.sleep(1000);
        storePage.search(searchText);

        new HomePage(driver).navigateToStoreUsingMenu().search(searchText);

        Thread.sleep(2000);

        Assertions.assertThat(storePage.getTitle())
            .contains(searchText);

        storePage.clickAddToCard(product.getName());
        Thread.sleep(3000);
        CartPage cartPage = storePage.clickViewCard();

        Assertions.assertThat(
                cartPage.getProductName())
            .isEqualTo(product.getName());

        CheckOutPage checkOutPage = cartPage.checkout();

        checkOutPage.setBillingAddressWithDefaultState(billingAddress); // using dto for address information

        Thread.sleep(3000);

        Assertions.assertThat(checkOutPage.getNotice())
            .isEqualTo(expectedNoticeText);

    }

}
