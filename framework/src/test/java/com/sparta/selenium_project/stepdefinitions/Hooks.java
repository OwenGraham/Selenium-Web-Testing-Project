package com.sparta.selenium_project.stepdefinitions;

import com.sparta.selenium_project.utils.ConfigReader;
import com.sparta.selenium_project.utils.DriverManager;
import io.cucumber.java.Before;
import io.cucumber.java.After;
import org.openqa.selenium.WebDriver;

public class Hooks {

    private WebDriver driver;

    @Before
    public void setUp() {
        driver = DriverManager.getDriver();
        // Optionally, you could navigate to a base URL or perform other setup tasks here
    }

    @After
    public void tearDown() {
        DriverManager.quitDriver();
    }
}

