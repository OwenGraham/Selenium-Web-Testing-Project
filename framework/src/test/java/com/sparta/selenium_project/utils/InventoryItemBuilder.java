package com.sparta.selenium_project.utils;

import com.sparta.selenium_project.pages.InventoryItem;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class InventoryItemBuilder{
    private WebDriver webDriver;

    public String title;
    public String description;
    public float price;
    public String imgURL;
    public WebElement addToCartButton;
    public WebElement itemLink;

    public InventoryItemBuilder setTitle(String title){
        this.title = title;
        return this;
    }

    public InventoryItemBuilder setDescription(String description){
        this.description = description;
        return this;
    }

    public InventoryItemBuilder setPrice(float price){
        this.price = price;
        return this;
    }

    public InventoryItemBuilder setImgURL(String imgURL){
        this.imgURL = imgURL;
        return this;
    }

    public InventoryItemBuilder setAddToCartButton(WebElement addToCartButton){
        this.addToCartButton = addToCartButton;
        return this;
    }

    public InventoryItemBuilder setItemLink(WebElement itemLink){
        this.itemLink = itemLink;
        return this;
    }

    public InventoryItem build(){
        return new InventoryItem(this);
    }
}
