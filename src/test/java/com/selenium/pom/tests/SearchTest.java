package com.selenium.pom.tests;

import com.selenium.pom.base.BaseTest;
import com.selenium.pom.pages.StorePage;
import org.assertj.core.api.Assertions;
import org.testng.annotations.Test;

public class SearchTest extends BaseTest {

    @Test
    public void searchWithPartialMatch() {
        String searchFor = "Blue";
        StorePage storePage = new StorePage()
            .load()
            .search(searchFor);
        Assertions.assertThat(storePage.getTitleAfterSearch())
            .isEqualTo(String.format("Search results: “%s”", searchFor));
    }

}
