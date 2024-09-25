package com.github.owengraham.selenium_project.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CheckoutPageTwo {
    private WebDriver webDriver;

    // Define the regex pattern to match any label followed by a dollar amount
    Pattern pattern = Pattern.compile(".*:\\s*\\$(\\d+\\.\\d{2})");

    @FindBy(className = "cart_item")
    private List<WebElement> itemElements;

    @FindBy(css = "[data-test='payment-info-value']")
    private WebElement paymentInfoLabel;

    @FindBy(css = "[data-test='shipping-info-value']")
    private WebElement deliveryInfoLabel;

    @FindBy(className = "summary_subtotal_label")
    private WebElement subTotal;

    @FindBy(className = "summary_tax_label")
    private WebElement tax;

    @FindBy(className = "summary_total_label")
    private WebElement totalPrice;

    @FindBy(id = "cancel")
    public WebElement cancelButton;

    @FindBy(id = "finish")
    public WebElement finishButton;

    public CheckoutPageTwo(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
    }

    // Constructor for dependency injection in unit tests
    public CheckoutPageTwo(List<WebElement> itemElements,WebElement paymentInfoLabel, WebElement deliveryInfoLabel, WebElement subTotal, WebElement tax, WebElement totalPrice, WebElement cancelButton, WebElement finishButton) {
        this.itemElements = itemElements;
        this.paymentInfoLabel = paymentInfoLabel;
        this.deliveryInfoLabel = deliveryInfoLabel;
        this.subTotal = subTotal;
        this.tax = tax;
        this.totalPrice = totalPrice;
        this.cancelButton = cancelButton;
        this.finishButton = finishButton;
    }

    // Setter for list of items for unit testing purposes
    public void setItemElements(List<WebElement> itemElements) {
        this.itemElements = itemElements;
    }

    // Return the payment info on the page
    public String getPaymentInfo(){
        return paymentInfoLabel.getText();
    }

    // Return the delivery info on the page
    public String getDeliveryInfo(){
        return deliveryInfoLabel.getText();
    }

    // Return the subtotal on the page
    public Float getSubTotal(){
        // Use the regex to extract the float value from the text in the subtotal element on the page
        Matcher matcher = pattern.matcher(subTotal.getText());
        if (matcher.find()) {
            String value = matcher.group(1);
            return Float.valueOf(value);
        }
        // Return 0 if no pattern matching the regex is found
        else return 0f;
    }

    // Return the subtotal on the page
    public Float getTax(){
        // Use the regex to extract the float value from the text in the tax element on the page
        Matcher matcher = pattern.matcher(tax.getText());
        if (matcher.find()) {
            String value = matcher.group(1);
            return Float.valueOf(value);
        }
        // Return 0 if no pattern matching the regex is found
        else return 0f;
    }

    public Float getTotalPrice(){
        // Use the regex to extract the float value from the text in the total price element on the page
        Matcher matcher = pattern.matcher(totalPrice.getText());
        if (matcher.find()) {
            String value = matcher.group(1);
            return Float.valueOf(value);
        }
        // Return 0 if no pattern matching the regex is found
        else return 0f;
    }

    // Return all products displayed on checkout page two as a List of CartItem objects
    public List<CartItem> getItems(){
        List<CartItem> items = new ArrayList<>();
        for (WebElement itemElement : itemElements){
            CartItem item = new CartItem.Builder()
                    .setItemLink(itemElement.findElement(By.tagName("a")))
                    .setTitle(itemElement.findElement(By.className("inventory_item_name")).getText())
                    .setDescription(itemElement.findElement(By.className("inventory_item_desc")).getText())
                    .setPrice(Float.valueOf(itemElement.findElement(By.className("inventory_item_price")).getText().substring(1)))
                    .setQuantity(Integer.parseInt(itemElement.findElement(By.className("cart_quantity")).getText()))
                    .build();
            items.add(item);
        }
        return items;
    }

    // Return the sum of the prices of all products in the cart
    public Float calculateSubTotal(){
        Float subTotal = 0.0f;
        for (CartItem item : getItems()){
            subTotal += item.getPrice();
        }
        return subTotal;
    }
}