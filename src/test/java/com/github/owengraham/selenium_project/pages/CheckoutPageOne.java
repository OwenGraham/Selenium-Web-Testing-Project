package com.github.owengraham.selenium_project.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckoutPageOne {
    private WebDriver webDriver;

    @FindBy(id = "first-name")
    private WebElement firstNameField;

    @FindBy(id = "last-name")
    private WebElement lastNameField;

    @FindBy(id = "postal-code")
    private WebElement postCodeField;

    @FindBy(id = "continue")
    private WebElement continueButton;

    private WebElement errorMessage;

    public CheckoutPageOne(WebDriver webDriver){
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver,this);
    }

    // Constructor for dependency injection in tests
    public CheckoutPageOne(WebDriver webDriver, WebElement firstNameField, WebElement lastNameField, WebElement postCodeField, WebElement continueButton) {
        this.webDriver = webDriver;
        this.firstNameField = firstNameField;
        this.lastNameField = lastNameField;
        this.postCodeField = postCodeField;
        this.continueButton = continueButton;
    }

    // Enter the given string into the first name field on the page
    public void enterFirstName(String firstName){
        firstNameField.sendKeys(firstName);
    }

    // Enter the given string into the last name field on the page
    public void enterLastName(String lastName){
        lastNameField.sendKeys(lastName);
    }

    // Enter the given string into the postcode field on the page
    public void enterPostCode(String postCode){
        postCodeField.sendKeys(postCode);
    }

    // Click the continue button on the page
    public void clickContinue(){
        continueButton.click();
    }

    // Return the error message on the page
    public String getErrorMessage(){
        errorMessage = webDriver.findElement(By.tagName("h3"));
        return errorMessage.getText();
    }
}
