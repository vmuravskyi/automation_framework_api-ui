package com.automation;

import com.automation.pom.base.BaseTest;
import com.automation.pom.pages.CartPage;
import com.automation.pom.pages.CheckOutPage;
import com.automation.pom.pages.HomePage;
import com.automation.pom.pages.StorePage;
import org.assertj.core.api.Assertions;
import org.testng.annotations.Test;

public class FirstTest extends BaseTest {

    private final String expectedNoticeText = "Thank you. Your order has been received.";
    private final String searchText = "blue";
    private final String addToCardItem = "Blue Shoes";


    @Test
    public void dummyTest() throws InterruptedException {
        HomePage homePage = new HomePage(driver);
        StorePage storePage = homePage.navigateToStoreUsingMenu();
        Thread.sleep(1000);
        storePage.search(searchText);

        new HomePage(driver).navigateToStoreUsingMenu().search(searchText);

        Thread.sleep(2000);

        Assertions.assertThat(storePage.getTitle())
            .contains(searchText);

        storePage.clickAddToCard(addToCardItem);
        Thread.sleep(3000);
        CartPage cartPage = storePage.clickViewCard();

        Assertions.assertThat(
                cartPage.getProductName())
            .isEqualTo(addToCardItem);

        CheckOutPage checkOutPage = cartPage.checkout();
        checkOutPage
            .enterFirstName("Volodymyr")
            .enterLastName("Muravskyi")
            .enterAddressLineFieldOne("Street")
            .enterCity("Lviv")
            .enterState("Nevada")
            .enterPostcode("12345")
            .enterEmail("v.muravskyi@gmail.com")
            .placeOrder();

        Thread.sleep(3000);

        Assertions.assertThat(checkOutPage.getNotice())
            .isEqualTo(expectedNoticeText);

    }

}
