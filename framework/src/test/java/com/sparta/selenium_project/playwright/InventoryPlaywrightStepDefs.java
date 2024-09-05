package com.sparta.selenium_project.playwright;

import com.sparta.selenium_project.pages.PlaywrightCartPage;
import com.sparta.selenium_project.pages.PlaywrightInventoryItem;
import com.sparta.selenium_project.pages.PlaywrightInventoryPage;
import com.sparta.selenium_project.pages.PlaywrightProductPage;
import com.sparta.selenium_project.utils.TestContext;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;

import java.util.List;

public class InventoryPlaywrightStepDefs {
    private final TestContext testContext;
    private PlaywrightInventoryPage inventoryPage;
    private PlaywrightCartPage cartPage;
    private PlaywrightProductPage productPage;

    public InventoryPlaywrightStepDefs(TestContext testContext) {
        this.testContext = testContext;
        inventoryPage = new PlaywrightInventoryPage(testContext);
    }

    @Then("the user should see a list of available products")
    public void theUserShouldSeeAListOfAvailableProducts() {
        Assertions.assertFalse(inventoryPage.getItems().isEmpty());
    }

    @And("each product should display its name, description, and price")
    public void eachProductShouldDisplayItsNameDescriptionAndPrice() {
        List<PlaywrightInventoryItem> items = inventoryPage.getItems();
        for (PlaywrightInventoryItem item : items){
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
        for (PlaywrightInventoryItem item : inventoryPage.getItems()){
            Assertions.assertTrue(item.getPrice() >= current);
            current = item.getPrice();
        }
    }

    @Then("the products should be displayed in descending order of price")
    public void theProductsShouldBeDisplayedInDescendingOrderOfPrice() {
        List<PlaywrightInventoryItem> items = inventoryPage.getItems();
        float current = items.getFirst().getPrice();
        for (PlaywrightInventoryItem item : items){
            Assertions.assertTrue(item.getPrice() <= current);
            current = item.getPrice();
        }
    }

    @When("the user clicks the Add to cart button for a product")
    public void theUserClicksTheAddToCartButtonForAProduct() {
        List<PlaywrightInventoryItem> items = inventoryPage.getItems();
        items.getFirst().getAddToCartButton().click();
    }

    @Then("the product should be added to the cart")
    public void theProductShouldBeAddedToTheCart() {
        List<PlaywrightInventoryItem> items = inventoryPage.getItems();
        cartPage = new PlaywrightCartPage(inventoryPage.goToCart());
        Assertions.assertTrue(cartPage.hasItem(items.getFirst().getName()));
    }

    @And("the user has added a product to the cart")
    public void theUserHasAddedAProductToTheCart() {
        inventoryPage.getItems().getFirst().getAddToCartButton().click();
    }

    @When("the user clicks the Remove button for that product")
    public void theUserClicksTheRemoveButtonForThatProduct() {
        inventoryPage.getItems().getFirst().getAddToCartButton().click();
    }

    @Then("the product should be removed from the cart")
    public void theProductShouldBeRemovedFromTheCart() {
        List<PlaywrightInventoryItem> items = inventoryPage.getItems();
        cartPage = new PlaywrightCartPage(inventoryPage.goToCart());
        Assertions.assertFalse(cartPage.hasItem(items.getFirst().getName()));
    }

    @And("the number displayed next to the cart icon should decrement by one")
    public void theNumberDisplayedNextToTheCartIconShouldDecrementByOne() {
        Assertions.assertTrue(testContext.page.locator("#shopping_cart_badge").isHidden());
    }

    @When("the user adds {int} products to the cart")
    public void theUserAddsProductsToTheCart(int quantity) {
        List<PlaywrightInventoryItem> items = inventoryPage.getItems();
        for (int i = 0; i < quantity; i++) {
            items.get(i).getAddToCartButton().click();
        }
    }

    @Then("all {int} selected products should be added to the cart")
    public void allSelectedProductsShouldBeAddedToTheCart(int quantity) {
        List<PlaywrightInventoryItem> items = inventoryPage.getItems();
        cartPage = new PlaywrightCartPage(inventoryPage.goToCart());
        for (int i = 0; i < quantity; i++) {
            Assertions.assertTrue(cartPage.hasItem(items.get(i).getTitle()));
        }
    }

    @And("the cart icon should display the number {int}")
    public void theCartIconShouldDisplayTheNumber(int quantity) {
        int cartSize = Integer.parseInt(testContext.page.locator(".shopping_cart_badge").textContent());
        Assertions.assertEquals(quantity,cartSize);
    }

    @Then("the products should be displayed in alphabetical order \\(A to Z)")
    public void theProductsShouldBeDisplayedInAlphabeticalOrderAToZ() {
        List<PlaywrightInventoryItem> items = inventoryPage.getItems();
        Assertions.assertTrue(inventoryPage.itemsInOrderAZ(items));
    }

    @Then("the products should be displayed in reverse alphabetical order \\(Z to A)")
    public void theProductsShouldBeDisplayedInReverseAlphabeticalOrderZToA() {
        List<PlaywrightInventoryItem> items = inventoryPage.getItems();
        Assertions.assertTrue(inventoryPage.itemsInOrderZA(items));
    }

    @When("the user clicks on a product's name")
    public void theUserClicksOnAProductsName() {
        inventoryPage.getItems().getFirst().getItemLink().click();
        productPage = new PlaywrightProductPage(testContext);
    }

    @Then("the user should be redirected to the product's detail page")
    public void theUserShouldBeRedirectedToTheProductSDetailPage() {
        Assertions.assertEquals("https://www.saucedemo.com/v1/inventory-item.html?id=4",testContext.page.url());
    }

    @And("the user should see detailed information about the product")
    public void theUserShouldSeeDetailedInformationAboutTheProduct() {
        Assertions.assertFalse(productPage.getDescription().isEmpty());
    }
}
