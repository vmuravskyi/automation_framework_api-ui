package com.automation;

import com.automation.pom.base.BaseTest;
import com.automation.pom.pages.HomePage;
import com.automation.pom.pages.StorePage;
import org.assertj.core.api.Assertions;
import org.testng.annotations.Test;

public class FirstTest extends BaseTest {

    private final String expectedSearchTitle = "Search results: “Blue";
    private final String searchText = "Blue Shoes";


    @Test
    public void dummyTest() {
        HomePage homePage = new HomePage(driver);
        StorePage storePage = homePage.clickStoreMenuLink();
        storePage.search(searchText);

        Assertions.assertThat(storePage.getTitle())
            .isEqualTo(expectedSearchTitle);

        storePage.clickAddToCard(searchText);
    }

}
