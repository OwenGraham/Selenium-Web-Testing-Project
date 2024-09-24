package com.github.owengraham.selenium_project.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ProductPage {
    private WebDriver webDriver;

    private String title;
    private String description;
    private Float price;
    private WebElement addToCartButton;

    public ProductPage(WebDriver webDriver){
        this.webDriver = webDriver;
        if (this.webDriver == null) {
            throw new IllegalStateException("WebDriver instance is not initialized");
        }

        title = webDriver.findElement(By.className("inventory_details_name")).getText();
        description = webDriver.findElement(By.className("inventory_details_desc")).getText();
        price = Float.valueOf(webDriver.findElement(By.className("inventory_details_price")).getText().substring(1));
        addToCartButton = webDriver.findElement(By.className("btn_inventory"));
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public Float getPrice() {
        return price;
    }

    public WebElement getAddToCartButton() {
        return addToCartButton;
    }
}
