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

    public void enterFirstName(String firstName){
        firstNameField.sendKeys(firstName);
    }

    public void enterLastName(String lastName){
        lastNameField.sendKeys(lastName);
    }

    public void enterPostCode(String postCode){
        postCodeField.sendKeys(postCode);
    }

    public void clickContinue(){
        continueButton.click();
    }

    public String getErrorMessage(){
        errorMessage = webDriver.findElement(By.tagName("h3"));
        return errorMessage.getText();
    }
}
