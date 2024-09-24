package com.sparta.selenium_project.pages;

import com.sparta.selenium_project.utils.InventoryItemBuilder;
import org.openqa.selenium.WebElement;

public class InventoryItem {
    private String title;
    private String description;
    private float price;
    private String imgURL;
    private WebElement addToCartButton;
    private WebElement itemLink;

    public InventoryItem(InventoryItemBuilder builder){
        this.title = builder.title;
        this.description = builder.description;
        this.price = builder.price;
        this.imgURL = builder.imgURL;
        this.addToCartButton = builder.addToCartButton;
        this.itemLink = builder.itemLink;
    }

    public String getName() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public float getPrice() {
        return price;
    }

    public String getImgURL() {
        return imgURL;
    }

    public WebElement getAddToCartButton() {
        return addToCartButton;
    }

    public String getTitle() {
        return title;
    }

    public WebElement getItemLink() {
        return itemLink;
    }
}
