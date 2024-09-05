package com.sparta.selenium_project.pages;

import com.microsoft.playwright.Locator;
import com.sparta.selenium_project.utils.TestContext;

public class PlaywrightProductPage {
    private final TestContext testContext;

    public PlaywrightProductPage(TestContext testContext) {
        this.testContext = testContext;
    }

    public String getTitle(){
        return testContext.page.locator(".inventory_details_name").textContent();
    }

    public String getDescription(){
        return testContext.page.locator(".inventory_details_desc").textContent();
    }

    public Float getPrice(){
        return Float.valueOf(testContext.page.locator(".inventory_details_price").textContent().substring(1));
    }

    public Locator getAddToCartButton(){
        return testContext.page.locator(".btn_inventory");
    }
}
