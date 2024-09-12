package com.sparta.selenium_project.pages;

import com.sparta.selenium_project.utils.InventoryItemBuilder;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;

public class InventoryPage {
    private WebDriver webDriver;
    private WebElement cartButton;

    public InventoryPage(WebDriver webDriver){
        this.webDriver = webDriver;
        if (this.webDriver == null) {
            throw new IllegalStateException("WebDriver instance is not initialized");
        }

        cartButton = webDriver.findElement(By.className("shopping_cart_link"));
    }

    public List<InventoryItem> getItems(){
        List<InventoryItem> items = new ArrayList<>();
        List<WebElement> elements = webDriver.findElements(By.className("inventory_item"));
        for (WebElement element : elements){
            InventoryItem item = new InventoryItemBuilder()
                    .setTitle(element.findElement(By.className("inventory_item_name")).getText())
                    .setDescription(element.findElement(By.className("inventory_item_desc")).getText())
                    .setPrice(Float.parseFloat(element.findElement(By.className("inventory_item_price")).getText().substring(1)))
                    .setImgURL(element.findElement(By.className("inventory_item_img")).findElement(By.tagName("a")).getAttribute("href"))
                    .setAddToCartButton(element.findElement(By.className("btn_inventory")))
                    .setItemLink(element.findElement(By.className("inventory_item_label")).findElement(By.tagName("a")))
                    .build();

            items.add(item);
        }
        return items;
    }

    public void sortItems(String sortMode){
        WebElement sortDropDownElement = webDriver.findElement(By.className("product_sort_container"));
        Select sortDropDown = new Select(sortDropDownElement);

        if (sortMode.equals("Price (low to high)")){
            sortDropDown.selectByValue("lohi");
        } else if (sortMode.equals("Price (high to low)")) {
            sortDropDown.selectByValue("hilo");
        } else if (sortMode.equals("Name (A to Z)")) {
            sortDropDown.selectByValue("az");
        } else if (sortMode.equals("Name (Z to A)")) {
            sortDropDown.selectByValue("za");
        }
    }

    public WebDriver goToCart(){
        cartButton.click();
        return webDriver;
    }

    public Boolean itemsInOrderAZ(List<InventoryItem> items){
        for (int i = 0; i < items.size() - 1; i++) {
            if (items.get(i).getTitle().compareTo(items.get(i + 1).getTitle()) > 0) {
                return false;  // The current string is greater than the next one
            }
        }
        return true;
    }

    public Boolean itemsInOrderZA(List<InventoryItem> items){
        for (int i = 0; i < items.size() - 1; i++) {
            if (items.get(i).getTitle().compareTo(items.get(i + 1).getTitle()) < 0) {
                return false;  // The current string is less than the next one
            }
        }
        return true;
    }
}
