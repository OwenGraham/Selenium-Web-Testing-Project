package com.sparta.selenium_project.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.File;
import java.io.IOException;

public class DriverManager {

    private WebDriver driver;
    private ChromeDriverService service;

    public DriverManager() {
        startService();
        driver = new RemoteWebDriver(service.getUrl(), getChromeOptions());
    }

    // Get the WebDriver instance
    public WebDriver getDriver() {
        if (driver == null || !service.isRunning()) {
            startService();
            driver = new RemoteWebDriver(service.getUrl(), getChromeOptions());
        }
        return driver;
    }

    // Start ChromeDriverService
    private void startService() {
        if (service == null) {
            service = new ChromeDriverService.Builder()
                    .usingAnyFreePort()
                    .usingDriverExecutable(new File(ConfigReader.getProperty("driver.location")))
                    .build();
            try {
                service.start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    // Provide ChromeOptions to configure the WebDriver
    private ChromeOptions getChromeOptions() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-popup-blocking");
        options.addArguments("--disable-notifications");
        options.addArguments("--start-maximized");
        options.addArguments("headless");
        return options;
    }

    // Quit WebDriver and stop ChromeDriverService
    public void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}
