package com.github.owengraham.selenium_project.stepdefinitions;

import com.github.owengraham.selenium_project.utils.ConfigReader;
import com.github.owengraham.selenium_project.pages.WebPage;
import com.github.owengraham.selenium_project.utils.PicoContainerConfig;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;

public class CommonStepDefs {
    private WebDriver webDriver;
    private Object currentPage;

    public CommonStepDefs(){
        this.webDriver = PicoContainerConfig.getContainer().getComponent(WebDriver.class);
    }

    @Given("the user is on the {string} page")
    public void theUserIsOnThePage(String page) {
        WebPage webPage = WebPage.valueOf(page);
        webDriver.get(webPage.getURL());
    }

    @Given("the user is logged in as {string}")
    public void theUserIsLoggedInAs(String username) {
        webDriver.get(ConfigReader.getProperty("base.url"));
        Cookie cookie = new Cookie.Builder("session-username",username)
                .domain(".saucedemo.com")
                .build();
        webDriver.manage().addCookie(cookie);
    }

    @When("the user navigates to the {string} page")
    public void theUserNavigatesToThePage(String page) {
        webDriver.navigate().to(WebPage.valueOf(page).getURL());
    }
}
