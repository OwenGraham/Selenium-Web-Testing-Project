package com.github.owengraham.selenium_project.pages;

public enum WebPage {
    LOGIN("https://www.saucedemo.com"),
    INVENTORY("https://www.saucedemo.com/inventory.html"),
    CART("https://www.saucedemo.com/cart.html"),
    CHECKOUT_ONE("https://www.saucedemo.com/checkout-step-one.html"),
    CHECKOUT_TWO("https://www.saucedemo.com/checkout-step-two.html"),
    CHECKOUT_THREE("https://www.saucedemo.com/checkout-complete.html");

    private final String URL;

    WebPage(String URL){
        this.URL = URL;
    }

    public String getURL() {
        return URL;
    }
}
