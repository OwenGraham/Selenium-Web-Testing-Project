package com.sparta.selenium_project.playwright;

import com.microsoft.playwright.*;
import com.sparta.selenium_project.utils.TestContext;
import io.cucumber.java.After;
import io.cucumber.java.Before;

public class Hooks {
    private final TestContext testContext;

    public Hooks(TestContext context) {
        this.testContext = context;
    }

    @Before
    public void setUp() {
        testContext.playwright = Playwright.create();
        testContext.browser = testContext.playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        testContext.context = testContext.browser.newContext();
        testContext.page = testContext.context.newPage();
    }

    @After
    public void tearDown() {
        testContext.page.close();
        testContext.context.close();
        testContext.browser.close();
        testContext.playwright.close();
    }
}
