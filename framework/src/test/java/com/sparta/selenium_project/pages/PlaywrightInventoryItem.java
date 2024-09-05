package com.sparta.selenium_project.pages;

import com.microsoft.playwright.Locator;
import com.sparta.selenium_project.utils.InventoryItemBuilder;
import com.sparta.selenium_project.utils.PlaywrightInventoryItemBuilder;

public class PlaywrightInventoryItem {
    private String title;
    private String description;
    private float price;
    private String imgURL;
    private Locator addToCartButton;
    private Locator itemLink;

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public void setImgURL(String imgURL) {
        this.imgURL = imgURL;
    }

    public void setAddToCartButton(Locator addToCartButton) {
        this.addToCartButton = addToCartButton;
    }

    public void setItemLink(Locator itemLink) {
        this.itemLink = itemLink;
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

    public Locator getAddToCartButton() {
        return addToCartButton;
    }

    public String getTitle() {
        return title;
    }

    public Locator getItemLink() {
        return itemLink;
    }
}
