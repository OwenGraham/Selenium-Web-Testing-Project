package com.github.owengraham.selenium_project.stepdefinitions;

import com.github.owengraham.selenium_project.utils.DriverManager;
import com.github.owengraham.selenium_project.utils.PicoContainerConfig;
import io.cucumber.java.Before;
import io.cucumber.java.After;
import org.openqa.selenium.WebDriver;

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

