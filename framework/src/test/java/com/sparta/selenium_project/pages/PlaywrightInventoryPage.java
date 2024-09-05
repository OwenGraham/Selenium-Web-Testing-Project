package com.sparta.selenium_project.pages;

import com.microsoft.playwright.Locator;
import com.sparta.selenium_project.utils.PlaywrightInventoryItemBuilder;
import com.sparta.selenium_project.utils.TestContext;

import java.util.ArrayList;
import java.util.List;

public class PlaywrightInventoryPage {
    private final TestContext context;
    private Locator cartButton;

    public PlaywrightInventoryPage(TestContext context) {
        this.context = context;
        cartButton = context.page.locator(".shopping_cart_link");
    }

    public List<PlaywrightInventoryItem> getItems(){
        List<PlaywrightInventoryItem> items = new ArrayList<>();
        List<Locator> elements = context.page.locator(".inventory_item").all();

        for (Locator element : elements){
            PlaywrightInventoryItem item = new PlaywrightInventoryItemBuilder()
                    .setTitle(element.locator(".inventory_item_name").textContent())
                    .setDescription(element.locator(".inventory_item_desc").textContent())
                    .setPrice(Float.valueOf(element.locator(".inventory_item_price").textContent().substring(1)))
                    .setImgURL(element.locator(".inventory_item_img").locator("a").getAttribute("href"))
                    .setAddToCartButton(element.locator(".btn_inventory"))
                    .setItemLink(element.locator(".inventory_item_label").locator("a"))
                    .build();
            items.add(item);
        }

        return items;
    }

    public void sortItems(String sortMode){
        Locator sortDropDown = context.page.locator(".product_sort_container");

        if (sortMode.equals("Price (low to high)")){
            sortDropDown.selectOption("lohi");
        } else if (sortMode.equals("Price (high to low)")) {
            sortDropDown.selectOption("hilo");
        } else if (sortMode.equals("Name (A to Z)")) {
            sortDropDown.selectOption("az");
        } else if (sortMode.equals("Name (Z to A)")) {
            sortDropDown.selectOption("za");
        }
    }

    public TestContext goToCart(){
        cartButton.click();
        return context;
    }

    public Boolean itemsInOrderAZ(List<PlaywrightInventoryItem> items){
        for (int i = 0; i < items.size() - 1; i++) {
            if (items.get(i).getTitle().compareTo(items.get(i + 1).getTitle()) > 0) {
                return false;  // The current string is greater than the next one
            }
        }
        return true;
    }

    public Boolean itemsInOrderZA(List<PlaywrightInventoryItem> items){
        for (int i = 0; i < items.size() - 1; i++) {
            if (items.get(i).getTitle().compareTo(items.get(i + 1).getTitle()) < 0) {
                return false;  // The current string is less than the next one
            }
        }
        return true;
    }
}
