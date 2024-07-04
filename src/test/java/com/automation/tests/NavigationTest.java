package com.automation.tests;

import com.automation.pages.HomePage;
import com.automation.pages.StorePage;
import org.assertj.core.api.Assertions;
import org.testng.annotations.Test;

public class NavigationTest extends BaseTest {

    @Test
    public void navigateFromHomeToStoreUsingMainMenu() {
        String expectedStorePageTitle = "Store";

        StorePage storePage = new HomePage(getDriver())
            .load()
            .getHeader()
            .navigateToStoreUsingMenu();
        Assertions.assertThat(storePage.getTitle())
            .isEqualTo(expectedStorePageTitle);
    }

}
