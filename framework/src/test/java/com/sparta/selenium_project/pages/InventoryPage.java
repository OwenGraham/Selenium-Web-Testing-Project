package com.sparta.selenium_project.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;

public class InventoryPage {
    private WebDriver webDriver;

    public InventoryPage(WebDriver webDriver){
        this.webDriver = webDriver;
        if (this.webDriver == null) {
            throw new IllegalStateException("WebDriver instance is not initialized");
        }
    }

    public List<InventoryItem> getItems(){
        List<InventoryItem> items = new ArrayList<>();
        List<WebElement> elements = webDriver.findElements(By.className("inventory_item"));
        for (WebElement element : elements){
            InventoryItem item = new InventoryItem();
            item.setTitle(element.findElement(By.className("inventory_item_name")).getText());
            item.setDescription(element.findElement(By.className("inventory_item_desc")).getText());
            item.setPrice(Float.parseFloat(element.findElement(By.className("inventory_item_price")).getText().substring(1)));
            item.setImgURL(element.findElement(By.className("inventory_item_img")).findElement(By.tagName("a")).getAttribute("href"));
            item.setAddToCartButton(element.findElement(By.className("btn_inventory")));

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
}
