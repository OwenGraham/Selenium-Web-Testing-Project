package com.sparta.selenium_project.pages;

import org.openqa.selenium.WebElement;

public class InventoryItem {
    private String title;
    private String description;
    private float price;
    private String imgURL;
    private WebElement addToCartButton;
    private WebElement removeFromCartButton;
    private WebElement itemLink;

    public String getName() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getImgURL() {
        return imgURL;
    }

    public void setImgURL(String imgURL) {
        this.imgURL = imgURL;
    }

    public WebElement getAddToCartButton() {
        return addToCartButton;
    }

    public void setAddToCartButton(WebElement addToCartButton) {
        this.addToCartButton = addToCartButton;
    }

    public WebElement getRemoveFromCartButton() {
        return removeFromCartButton;
    }

    public void setRemoveFromCartButton(WebElement removeFromCartButton) {
        this.removeFromCartButton = removeFromCartButton;
    }

    public WebElement getItemLink() {
        return itemLink;
    }

    public void setItemLink(WebElement itemLink) {
        this.itemLink = itemLink;
    }
}
