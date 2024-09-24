package com.github.owengraham.selenium_project.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;

public class CartPage {
    private WebDriver webDriver;

    @FindBy(className = "cart_item")
    private List<WebElement> elements;

    @FindBy(id = "checkout")
    public WebElement checkoutButton;

    @FindBy(id = "continue-shopping")
    public WebElement continueShoppingButton;

    public CartPage(WebDriver webDriver){
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver,this);
    }

    public void setElements(List<WebElement> elements) {
        this.elements = elements;
    }

    public List<CartItem> getItems(){
        List<CartItem> items = new ArrayList<>();
        for (WebElement element : elements){
            CartItem item = new CartItem.Builder()
                    .setQuantity(Integer.parseInt(element.findElement(By.className("cart_quantity")).getText()))
                    .setItemLink(element.findElement(By.tagName("a")))
                    .setTitle(element.findElement(By.className("inventory_item_name")).getText())
                    .setDescription(element.findElement(By.className("inventory_item_desc")).getText())
                    .setPrice(Float.valueOf(element.findElement(By.className("inventory_item_price")).getText().substring(1)))
                    .setRemoveButton(element.findElement(By.className("cart_button")))
                    .build();

            items.add(item);
        }
        return items;
    }

    public Boolean hasItem(String title){
        Boolean found = false;
        for (CartItem item : getItems()){
            if (item.getTitle().equals(title)){
                found = true;
                break;
            }
        }
        return found;
    }
}
