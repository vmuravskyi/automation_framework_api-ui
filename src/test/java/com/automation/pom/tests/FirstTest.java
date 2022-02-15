package com.automation.pom.tests;

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
    private final String expectedSearchText = "Search results: ";


    @Test
    public void guestCheckoutUsingDirectBankTransfer() {

        BillingAddress billingAddress =
            JacksonUtils.deserializeJsonToObject("BillingAddress.json", BillingAddress.class);
        Product product = new Product(1215);

        HomePage homePage = new HomePage(getDriver());
        StorePage storePage = homePage.navigateToStoreUsingMenu();
        storePage.search(searchText);

        new HomePage(getDriver()).navigateToStoreUsingMenu().search(searchText);

        Assertions.assertThat(storePage.getTitle())
            .contains(expectedSearchText);

        storePage.clickAddToCard(product.getName());
        CartPage cartPage = storePage.clickViewCard();

        Assertions.assertThat(
                cartPage.getProductName())
            .isEqualTo(product.getName());

        CheckOutPage checkOutPage = cartPage.checkout();

        checkOutPage.setBillingAddressWithDefaultState(billingAddress) // using dto for address information
            .selectDirectBankTransfer()
            .placeOrder();

        Assertions.assertThat(checkOutPage.getNotice())
            .isEqualTo(expectedNoticeText);

    }

    @Test
    public void guestCheckoutUsingDirectBankTransferTwo() {

        BillingAddress billingAddress =
            JacksonUtils.deserializeJsonToObject("BillingAddress.json", BillingAddress.class);
        Product product = new Product(1215);

        HomePage homePage = new HomePage(getDriver());
        StorePage storePage = homePage.navigateToStoreUsingMenu();
        storePage.search(searchText);

        new HomePage(getDriver()).navigateToStoreUsingMenu().search(searchText);

        Assertions.assertThat(storePage.getTitle())
            .contains(expectedSearchText);

        storePage.clickAddToCard(product.getName());
        CartPage cartPage = storePage.clickViewCard();

        Assertions.assertThat(
                cartPage.getProductName())
            .isEqualTo(product.getName());

        CheckOutPage checkOutPage = cartPage.checkout();

        checkOutPage.setBillingAddressWithDefaultState(billingAddress) // using dto for address information
            .selectDirectBankTransfer()
            .placeOrder();

        Assertions.assertThat(checkOutPage.getNotice())
            .isEqualTo(expectedNoticeText);
    }

}
