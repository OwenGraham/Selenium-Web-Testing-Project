package com.github.owengraham.selenium_project.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductPage {
    private WebDriver webDriver;

    @FindBy(className = "inventory_details_name")
    private WebElement title;

    @FindBy(className = "inventory_details_desc")
    private WebElement description;

    @FindBy(className = "inventory_details_price")
    private WebElement price;

    @FindBy(className = "btn_inventory")
    private WebElement addToCartButton;

    public ProductPage(WebDriver webDriver){
        this.webDriver = webDriver;

        PageFactory.initElements(webDriver,this);
    }

    public String getTitle() {
        return title.getText();
    }

    public String getDescription() {
        return description.getText();
    }

    public Float getPrice() {
        return Float.valueOf(price.getText().substring(1));
    }

    public WebElement getAddToCartButton() {
        return addToCartButton;
    }
}
