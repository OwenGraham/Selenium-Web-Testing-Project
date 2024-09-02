package com.sparta.selenium_project.stepdefinitions;

import com.sparta.selenium_project.utils.ConfigReader;
import com.sparta.selenium_project.utils.DriverManager;
import com.sparta.selenium_project.utils.PicoContainerConfig;
import io.cucumber.java.AfterAll;
import io.cucumber.java.Before;
import io.cucumber.java.After;
import io.cucumber.java.BeforeAll;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.picocontainer.DefaultPicoContainer;
import org.picocontainer.MutablePicoContainer;

public class Hooks {

    private WebDriver driver;

    @Before
    public void setUp() {
        DriverManager driverManager = PicoContainerConfig.getContainer().getComponent(DriverManager.class);
        this.driver = driverManager.getDriver();
        PicoContainerConfig.getContainer().removeComponent(WebDriver.class);
        PicoContainerConfig.getContainer().addComponent(WebDriver.class,this.driver);
    }

    @After
    public void tearDown() {
        DriverManager driverManager = PicoContainerConfig.getContainer().getComponent(DriverManager.class);
        driverManager.quitDriver();
    }
}

