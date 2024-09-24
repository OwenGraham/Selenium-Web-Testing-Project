package com.sparta.selenium_project.pages;

import com.sparta.selenium_project.utils.InventoryItemBuilder;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;

public class InventoryPage {
    private WebDriver webDriver;

    @FindBy(className = "shopping_cart_link")
    private WebElement cartButton;

    @FindBy(className = "product_sort_container")
    private WebElement sortByDropDownElement;

    @FindBy(className = "inventory_item")
    private List<WebElement> elements;

    public InventoryPage(WebDriver webDriver){
        this.webDriver = webDriver;
        if (this.webDriver == null) {
            throw new IllegalStateException("WebDriver instance is not initialized");
        }

        PageFactory.initElements(webDriver,this);
    }

    public void setElements(List<WebElement> elements){
        this.elements = elements;
    }

    public List<InventoryItem> getItems(){
        List<InventoryItem> items = new ArrayList<>();
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

    public enum SortOption{
        PRICE_LOW_TO_HIGH("lohi"),
        PRICE_HIGH_TO_LOW("hilo"),
        NAME_A_TO_Z("az"),
        NAME_Z_TO_A("za");

        private final String value;

        SortOption(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }

    public void sortItems(SortOption sortMode){
        Select sortDropDown = new Select(sortByDropDownElement);

        sortDropDown.selectByValue(sortMode.getValue());
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
