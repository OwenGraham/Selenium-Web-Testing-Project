package com.sparta.selenium_project.pages;

import com.microsoft.playwright.Locator;
import com.sparta.selenium_project.utils.TestContext;

import java.util.ArrayList;
import java.util.List;

public class PlaywrightCartPage {
    private final TestContext testContext;

    public PlaywrightCartPage(TestContext testContext){
        this.testContext = testContext;
    }

    public List<PlaywrightCartItem> getItems(){
        List<PlaywrightCartItem> items = new ArrayList<>();
        List<Locator> elements = testContext.page.locator(".cart_item").all();
        for (Locator element : elements){
            PlaywrightCartItem item = new PlaywrightCartItem.Builder()
                    .setQuantity(Integer.parseInt(element.locator(".cart_quantity").textContent()))
                    .setItemLink(element.locator("a"))
                    .setTitle(element.locator(".inventory_item_name").textContent())
                    .setDescription(element.locator(".inventory_item_desc").textContent())
                    .setPrice(Float.valueOf(element.locator(".inventory_item_price").textContent()))
                    .setRemoveButton(element.locator(".cart_button"))
                    .build();

            items.add(item);
        }
        return items;
    }

    public Boolean hasItem(String title){
        Boolean found = false;
        for (PlaywrightCartItem item : getItems()){
            if (item.getTitle().equals(title)){
                found = true;
                break;
            }
        }
        return found;
    }
}
