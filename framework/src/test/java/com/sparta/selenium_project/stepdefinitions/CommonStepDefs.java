package com.sparta.selenium_project.stepdefinitions;

import com.sparta.selenium_project.utils.ConfigReader;
import com.sparta.selenium_project.utils.DriverManager;
import com.sparta.selenium_project.utils.PicoContainerConfig;
import io.cucumber.java.en.Given;
import org.openqa.selenium.WebDriver;

public class CommonStepDefs {
    private WebDriver webDriver;

    public CommonStepDefs(){
        this.webDriver = PicoContainerConfig.getContainer().getComponent(WebDriver.class);
    }

    @Given("the user is on the {string} page")
    public void theUserIsOnThePage(String page) {
        webDriver.get(ConfigReader.getProperty("base.url"));
    }
}
