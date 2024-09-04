package com.sparta.selenium_project.playwright;

import com.sparta.selenium_project.pages.WebPage;
import com.sparta.selenium_project.utils.TestContext;
import io.cucumber.java.en.Given;

public class CommonPlaywrightStepDefs {
    private final TestContext testContext;

    public CommonPlaywrightStepDefs(TestContext testContext) {
        this.testContext = testContext;
    }

    @Given("the user is on the {string} page")
    public void theUserIsOnThePage(String page) {
        WebPage webPage = WebPage.valueOf(page);
        testContext.page.navigate(webPage.getURL());
    }

    @Given("the user is logged in as {string}")
    public void theUserIsLoggedInAs(String username) {
        testContext.page.evaluate("window.sessionStorage.setItem('session-username', '" + username + "');");
    }
}
