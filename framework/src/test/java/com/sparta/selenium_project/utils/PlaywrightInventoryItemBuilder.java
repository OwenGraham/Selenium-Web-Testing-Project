package com.sparta.selenium_project.utils;

import com.microsoft.playwright.Locator;
import com.sparta.selenium_project.pages.PlaywrightInventoryItem;

public class PlaywrightInventoryItemBuilder {
    public String title;
    public String description;
    public float price;
    public String imgURL;
    public Locator addToCartButton;
    public Locator itemLink;

    public PlaywrightInventoryItemBuilder setTitle(String title){
        this.title = title;
        return this;
    }

    public PlaywrightInventoryItemBuilder setDescription(String description){
        this.description = description;
        return this;
    }

    public PlaywrightInventoryItemBuilder setPrice(float price){
        this.price = price;
        return this;
    }

    public PlaywrightInventoryItemBuilder setImgURL(String imgURL){
        this.imgURL = imgURL;
        return this;
    }

    public PlaywrightInventoryItemBuilder setAddToCartButton(Locator addToCartButton){
        this.addToCartButton = addToCartButton;
        return this;
    }

    public PlaywrightInventoryItemBuilder setItemLink(Locator itemLink){
        this.itemLink = itemLink;
        return this;
    }

    public PlaywrightInventoryItem build(){
        PlaywrightInventoryItem item = new PlaywrightInventoryItem();
        item.setTitle(title);
        item.setDescription(description);
        item.setPrice(price);
        item.setImgURL(imgURL);
        item.setAddToCartButton(addToCartButton);
        item.setItemLink(itemLink);

        return item;
    }
}
