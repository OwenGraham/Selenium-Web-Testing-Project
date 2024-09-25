package com.github.owengraham.selenium_project.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
    private WebDriver webDriver;

    @FindBy(id = "user-name")
    private WebElement usernameField;

    @FindBy(id = "password")
    private WebElement passwordField;

    @FindBy(id = "login-button")
    private WebElement loginButton;

    public LoginPage(WebDriver webDriver){
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver,this);
    }

    // Constructor for dependency injection in tests
    public LoginPage(WebDriver webDriver, WebElement usernameField, WebElement passwordField, WebElement loginButton) {
        this.webDriver = webDriver;
        this.usernameField = usernameField;
        this.passwordField = passwordField;
        this.loginButton = loginButton;
    }

    // Enter the given string in the username field on the page
    public void enterUsername(String username){
        usernameField.sendKeys(username);
    }

    // Enter the given string in the password field on the page
    public void enterPassword(String password){
        passwordField.sendKeys(password);
    }

    // Click the login button on the page
    public void login(){
        loginButton.click();
    }

    // Return the text of the error message on the page
    public String getErrorMessage(){
        WebElement errorMessage = webDriver.findElement(By.tagName("h3"));
        return errorMessage.getText();
    }
}
