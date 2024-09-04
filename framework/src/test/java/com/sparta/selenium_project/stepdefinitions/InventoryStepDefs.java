package com.sparta.selenium_project.stepdefinitions;

import com.sparta.selenium_project.pages.InventoryItem;
import com.sparta.selenium_project.pages.InventoryPage;
import com.sparta.selenium_project.utils.PicoContainerConfig;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.List;

public class InventoryStepDefs {
    private WebDriver webDriver;
    private InventoryPage inventoryPage;

    public InventoryStepDefs(){
        this.webDriver = PicoContainerConfig.getContainer().getComponent(WebDriver.class);
        inventoryPage = new InventoryPage(webDriver);
    }

    @Then("the user should see a list of available products")
    public void theUserShouldSeeAListOfAvailableProducts() {
        Assertions.assertFalse(inventoryPage.getItems().isEmpty());
    }

    @And("each product should display its name, description, and price")
    public void eachProductShouldDisplayItsNameDescriptionAndPrice() {
        List<InventoryItem> items = inventoryPage.getItems();
        for (InventoryItem item : items){
            Assertions.assertFalse(item.getName().isEmpty());
            Assertions.assertFalse(Float.toString(item.getPrice()).isEmpty());
            Assertions.assertFalse(item.getDescription().isEmpty());
        }
    }

    @When("the user selects the {string} option from the sort dropdown")
    public void theUserSelectsTheOptionFromTheSortDropdown(String sortMode) {
        inventoryPage.sortItems(sortMode);
    }

    @Then("the products should be displayed in ascending order of price")
    public void theProductsShouldBeDisplayedInAscendingOrderOfPrice() {
        float current = 0;
        for (InventoryItem item : inventoryPage.getItems()){
            Assertions.assertTrue(item.getPrice() >= current);
            current = item.getPrice();
        }
    }

    @Then("the products should be displayed in descending order of price")
    public void theProductsShouldBeDisplayedInDescendingOrderOfPrice() {
        List<InventoryItem> items = inventoryPage.getItems();
        float current = items.getFirst().getPrice();
        for (InventoryItem item : items){
            Assertions.assertTrue(item.getPrice() <= current);
            current = item.getPrice();
        }
    }



    @When("the user clicks the Add to cart button for a product")
    public void theUserClicksTheAddToCartButtonForAProduct() {
        List<InventoryItem> items = inventoryPage.getItems();
        items.getFirst().getAddToCartButton().click();
    }

    @And("the number displayed next to the cart icon should increment by one")
    public void theNumberDisplayedNextToTheCartIconShouldIncrementByOne() {
        int cartSize = Integer.parseInt(webDriver.findElement(By.className("shopping_cart_badge")).getText());
        Assertions.assertEquals(1,cartSize);
    }
}
