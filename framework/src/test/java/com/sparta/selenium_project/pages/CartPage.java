package com.sparta.selenium_project.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class CartPage {
    private WebDriver webDriver;

    public CartPage(WebDriver webDriver){
        this.webDriver = webDriver;
        if (this.webDriver == null) {
            throw new IllegalStateException("WebDriver instance is not initialized");
        }
    }

    public List<CartItem> getItems(){
        List<CartItem> items = new ArrayList<>();
        List<WebElement> elements = webDriver.findElements(By.className("cart_item"));
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
