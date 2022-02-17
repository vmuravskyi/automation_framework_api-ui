package com.selenium.pom.tests;

import com.selenium.pom.base.BaseTest;
import com.selenium.pom.pages.HomePage;
import com.selenium.pom.pages.StorePage;
import org.assertj.core.api.Assertions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class NavigationTest extends BaseTest {

    @Test
    public void NavigateFromHomeToStoreUsingMainMenu(){
        String expected = "Store";

        StorePage storePage = new HomePage(getDriver())
            .load()
            .getMyHeader()
            .navigateToStoreUsingMenu();

        Assertions.assertThat(storePage.getTitle())
            .isEqualTo(expected);
    }
}
