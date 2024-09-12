package com.sparta.selenium_project.pages;

public enum WebPage {
    LOGIN("https://www.saucedemo.com/v1/index.html"),
    INVENTORY("https://www.saucedemo.com/v1/inventory.html"),
    CART("https://www.saucedemo.com/v1/cart.html"),
    CHECKOUT_ONE("https://www.saucedemo.com/v1/checkout-step-one.html"),
    CHECKOUT_TWO("https://www.saucedemo.com/v1/checkout-step-two.html"),
    CHECKOUT_THREE("https://www.saucedemo.com/v1/checkout-complete.html");

    private final String URL;

    WebPage(String URL){
        this.URL = URL;
    }

    public String getURL() {
        return URL;
    }
}
