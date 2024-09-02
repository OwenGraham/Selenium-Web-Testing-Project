package com.sparta.selenium_project.pages;

import com.sparta.selenium_project.utils.DriverManager;
import com.sparta.selenium_project.utils.PicoContainerConfig;
import org.openqa.selenium.By;
import org.openqa.selenium.DeviceRotation;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage {
    private WebDriver webDriver;

    private WebElement usernameField;
    private WebElement passwordField;
    private WebElement loginButton;
    private WebElement errorMessage;

    public LoginPage(WebDriver webDriver){
        this.webDriver = webDriver;
        if (this.webDriver == null) {
            throw new IllegalStateException("WebDriver instance is not initialized");
        }
        usernameField = webDriver.findElement(By.id("user-name"));
        passwordField = webDriver.findElement(By.id("password"));
        loginButton = webDriver.findElement(By.id("login-button"));
    }


    public void enterUsername(String username){
        usernameField.sendKeys(username);
    }

    public void enterPassword(String password){
        passwordField.sendKeys(password);
    }

    public void login(){
        loginButton.click();
    }

    public String getErrorMessage(){
        errorMessage = webDriver.findElement(By.tagName("h3"));
        return errorMessage.getText();
    }
}
