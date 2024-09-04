package com.sparta.selenium_project.stepdefinitions;

import com.sparta.selenium_project.pages.WebPage;
import com.sparta.selenium_project.utils.ConfigReader;
import com.sparta.selenium_project.utils.DriverManager;
import com.sparta.selenium_project.utils.PicoContainerConfig;
import io.cucumber.java.en.Given;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

public class CommonStepDefs {
    private WebDriver webDriver;

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
        JavascriptExecutor js = (JavascriptExecutor) webDriver;
        js.executeScript("window.sessionStorage.setItem('session-username', '" + username + "');");
    }
}
