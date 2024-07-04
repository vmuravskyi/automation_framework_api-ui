package com.automation.tests;

import com.automation.pages.StorePage;
import org.assertj.core.api.Assertions;
import org.testng.annotations.Test;

public class SearchTest extends BaseTest {

    @Test
    public void searchWithPartialMatch() {
        String searchFor = "Blue";
        StorePage storePage = new StorePage(getDriver())
            .load()
            .search(searchFor);
        Assertions.assertThat(storePage.getTitleAfterSearch())
            .isEqualTo(String.format("Search results: “%s”", searchFor));
    }

}
