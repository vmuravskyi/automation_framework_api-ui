package com.cucumber.runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import io.cucumber.testng.CucumberOptions.SnippetType;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

@CucumberOptions(
            plugin = {"pretty", "html:target/cucumber.html", "summary"},
            snippets = SnippetType.CAMELCASE,
            dryRun = false,
            tags = "@bvt",
            glue = {"com/cucumber/stepdef", "com/cucumber/hooks"},
            features = "src/test/resources/com/cucumber"
)
public class CucumberTestRunner extends AbstractTestNGCucumberTests {

    @BeforeClass
    public void beforeClass() {
        System.out.println("This is before class");
    }

    @AfterClass
    public void afterClass() {
        System.out.println("This is after class");
    }

}
