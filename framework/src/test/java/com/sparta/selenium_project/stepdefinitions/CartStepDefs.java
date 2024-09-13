package com.sparta.selenium_project.stepdefinitions;

import com.sparta.selenium_project.pages.CartItem;
import com.sparta.selenium_project.pages.CartPage;
import com.sparta.selenium_project.utils.PicoContainerConfig;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;

import java.util.List;

public class CartStepDefs {
    private WebDriver webDriver;
    private CartPage cartPage;

    public CartStepDefs() {
        this.webDriver = PicoContainerConfig.getContainer().getComponent(WebDriver.class);
        this.cartPage = new CartPage(webDriver);
    }

    @And("the user clicks the Remove button for the {int} th product")
    public void theUserClicksTheRemoveButtonForTheThProduct(int n) {
        cartPage.getItems().get(n).getRemoveButton().click();
    }

    @And("each product should display its name, quantity, and price")
    public void eachProductShouldDisplayItsNameQuantityAndPrice() {
        for (CartItem item : cartPage.getItems()){
            Assertions.assertFalse(item.getTitle().isEmpty());
            Assertions.assertFalse(Float.toString(item.getQuantity()).isEmpty());
            Assertions.assertFalse(Float.toString(item.getPrice()).isEmpty());
        }
    }
}
