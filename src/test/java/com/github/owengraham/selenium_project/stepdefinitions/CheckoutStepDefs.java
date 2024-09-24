package com.github.owengraham.selenium_project.stepdefinitions;

import com.github.owengraham.selenium_project.pages.*;
import com.github.owengraham.selenium_project.utils.ConfigReader;
import com.github.owengraham.selenium_project.utils.PicoContainerConfig;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;

public class CheckoutStepDefs {
    private WebDriver webDriver;
    private InventoryPage inventoryPage;
    private CartPage cartPage;
    private CheckoutPageOne checkoutPageOne;
    private CheckoutPageTwo checkoutPageTwo;
    private CheckoutPageThree checkoutPageThree;

    public CheckoutStepDefs() {
        this.webDriver = PicoContainerConfig.getContainer().getComponent(WebDriver.class);
    }

    @And("the user has products in their cart")
    public void theUserHasProductsInTheirCart(){
        webDriver.navigate().to(WebPage.INVENTORY.getURL());
        inventoryPage = new InventoryPage(webDriver);
        inventoryPage.getItems().getFirst().getAddToCartButton().click();
    }

    @When("the user clicks the Checkout button on the CART page")
    public void theUserClicksTheCheckoutButtonOnTheCARTPage() {
        webDriver.get(WebPage.CART.getURL());
        cartPage = new CartPage(webDriver);
        cartPage.checkoutButton.click();
        checkoutPageOne = new CheckoutPageOne(webDriver);
    }

    @Then("the user should be redirected to the Checkout Information page")
    public void theUserShouldBeRedirectedToTheCheckoutInformationPage() {
        Assertions.assertEquals("https://www.saucedemo.com/checkout-step-one.html", webDriver.getCurrentUrl());
    }

    @When("the user enters {string} as first name")
    public void theUserEntersAsFirstName(String firstName) {
        if (checkoutPageOne == null) checkoutPageOne = new CheckoutPageOne(webDriver);
        checkoutPageOne.enterFirstName(firstName);
    }

    @And("the user enters {string} as last name")
    public void theUserEntersAsLastName(String lastName) {
        if (checkoutPageOne == null) checkoutPageOne = new CheckoutPageOne(webDriver);
        checkoutPageOne.enterLastName(lastName);
    }

    @And("the user enters {string} as post code")
    public void theUserEntersAsPostCode(String postcode) {
        if (checkoutPageOne == null) checkoutPageOne = new CheckoutPageOne(webDriver);
        checkoutPageOne.enterPostCode(postcode);
    }

    @And("the user clicks the Continue button")
    public void theUserClicksTheContinueButton() {
        if (checkoutPageOne == null) checkoutPageOne = new CheckoutPageOne(webDriver);
        checkoutPageOne.clickContinue();
    }

    @Then("the user should be redirected to the Order Summary page")
    public void theUserShouldBeRedirectedToTheOrderSummaryPage() {
        Assertions.assertEquals("https://www.saucedemo.com/checkout-step-two.html",webDriver.getCurrentUrl());
    }

    @Then("an error message should be displayed indicating that a first name is required")
    public void anErrorMessageShouldBeDisplayedIndicatingThatAFirstNameIsRequired() {
        Assertions.assertEquals("Error: First Name is required", checkoutPageOne.getErrorMessage());
    }

    @Then("an error message should be displayed indicating that a last name is required")
    public void anErrorMessageShouldBeDisplayedIndicatingThatALastNameIsRequired() {
        Assertions.assertEquals("Error: Last Name is required", checkoutPageOne.getErrorMessage());
    }

    @Then("an error message should be displayed indicating that a postcode is required")
    public void anErrorMessageShouldBeDisplayedIndicatingThatAPostcodeIsRequired() {
        Assertions.assertEquals("Error: Postal Code is required", checkoutPageOne.getErrorMessage());
    }

    @Then("the item total, tax, and total price should be displayed correctly")
    public void theItemTotalTaxAndTotalPriceShouldBeDisplayedCorrectly() {
        webDriver.get(WebPage.CHECKOUT_TWO.getURL());
        checkoutPageTwo = new CheckoutPageTwo(webDriver);

        Float actualSubTotal = checkoutPageTwo.calculateSubTotal();
        Float actualTax = Math.round((actualSubTotal * Float.parseFloat(ConfigReader.getProperty("vat.rate"))) * 100) / 100f;

        Assertions.assertEquals(actualSubTotal,checkoutPageTwo.getSubTotal());
        Assertions.assertEquals(actualTax, (Float) checkoutPageTwo.getTax());
        Assertions.assertEquals(actualSubTotal + actualTax, checkoutPageTwo.getTotalPrice());
    }

    @When("the user clicks the Finish button")
    public void theUserClicksTheFinishButton() {
        if (checkoutPageTwo == null) checkoutPageTwo = new CheckoutPageTwo(webDriver);
        checkoutPageTwo.finishButton.click();
    }

    @Then("the user should be redirected to the Thank You page")
    public void theUserShouldBeRedirectedToTheThankYouPage() {
        Assertions.assertEquals("https://www.saucedemo.com/checkout-complete.html",webDriver.getCurrentUrl());
    }

    @And("the user should see a thank you message")
    public void theUserShouldSeeAThankYouMessage() {
        if(checkoutPageThree == null) checkoutPageThree = new CheckoutPageThree(webDriver);
        Assertions.assertEquals("Thank you for your order!",checkoutPageThree.getMessage());
    }
}
