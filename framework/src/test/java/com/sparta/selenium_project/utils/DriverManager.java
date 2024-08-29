package com.sparta.selenium_project.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.File;
import java.io.IOException;

public class DriverManager {

    private static WebDriver driver;
    private static ChromeDriverService service;

    // Singleton pattern to ensure only one instance of WebDriver is created
    public static WebDriver getDriver() {
        if (driver == null) {
            startService();
            driver = new ChromeDriver(service, getChromeOptions());
        }
        return driver;
    }

    // Start ChromeDriverService
    private static void startService() {
        if (service == null) {
            service = new ChromeDriverService.Builder()
                    .usingAnyFreePort()
                    .usingDriverExecutable(new File(ConfigReader.getProperty("base.url")))
                    .build();
            try {
                service.start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    // Provide ChromeOptions to configure the WebDriver
    private static ChromeOptions getChromeOptions() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-popup-blocking");
        options.addArguments("--disable-notifications");
        options.addArguments("--start-maximized");
        // Add more options as needed
        return options;
    }

    // Quit WebDriver and stop ChromeDriverService
    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
        if (service != null && service.isRunning()) {
            service.stop();
        }
    }
}

