package com.github.owengraham.selenium_project.stepdefinitions;

import com.github.owengraham.selenium_project.pages.CartItem;
import com.github.owengraham.selenium_project.pages.CartPage;
import com.github.owengraham.selenium_project.utils.PicoContainerConfig;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;

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

    @When("the user clicks the Continue Shopping button")
    public void theUserClicksTheContinueShoppingButton() {
        cartPage.continueShoppingButton.click();
    }
}
