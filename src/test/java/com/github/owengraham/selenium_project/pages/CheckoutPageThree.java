package com.github.owengraham.selenium_project.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckoutPageThree {
    private WebDriver webDriver;

    @FindBy(id = "back-to-products")
    private WebElement backHomeButton;

    @FindBy(css = "[data-test='complete-header']")
    private WebElement completeHeader;

    public CheckoutPageThree(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver,this);
    }

    // Constructor for dependency injection in unit tests
    public CheckoutPageThree(WebElement completeHeader) {
        this.completeHeader = completeHeader;
    }

    // Return the message on the page
    public String getMessage(){
        return completeHeader.getText();
    }
}
