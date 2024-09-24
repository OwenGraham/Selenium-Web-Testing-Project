package com.sparta.selenium_project.junit;

import com.sparta.selenium_project.pages.InventoryItem;
import com.sparta.selenium_project.pages.InventoryPage;
import com.sparta.selenium_project.utils.InventoryItemBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestClassOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class InventoryUnitTest {
    @Mock
    private WebDriver webDriver;

    @Mock
    private WebElement cartButton;

    @Mock
    private WebElement sortByDropDownElement;

    @Mock
    private Select sortBySelect;

    @Mock
    private WebElement element1;

    @Mock
    private WebElement element2;

    @InjectMocks
    private InventoryPage inventoryPage;

    @BeforeEach
    public void setUp() throws NoSuchFieldException, IllegalAccessException {
        // Initialize the mocks
        MockitoAnnotations.openMocks(this);

        // Set the InventoryPage's cart button as the mock using reflection
        Field cartButtonInPage = InventoryPage.class.getDeclaredField("cartButton");
        cartButtonInPage.setAccessible(true);
        cartButtonInPage.set(inventoryPage,cartButton);
    }


    @Test
    public void testGetItems(){
        List<WebElement> elements = new ArrayList<>();

        // Mock behavior for element1
        WebElement itemNameElement1 = mock(WebElement.class);
        when(itemNameElement1.getText()).thenReturn("Item name");
        when(element1.findElement(By.className("inventory_item_name"))).thenReturn(itemNameElement1);

        WebElement itemDescriptionElement1 = mock(WebElement.class);
        when(itemDescriptionElement1.getText()).thenReturn("Item description");
        when(element1.findElement(By.className("inventory_item_desc"))).thenReturn(itemDescriptionElement1);

        WebElement priceElement1 = mock(WebElement.class);
        when(priceElement1.getText()).thenReturn("$20.00");
        when(element1.findElement(By.className("inventory_item_price"))).thenReturn(priceElement1);

        WebElement imgLinkElement1 = mock(WebElement.class);
        when(imgLinkElement1.getAttribute("href")).thenReturn("img URL");
        WebElement imgElement1 = mock(WebElement.class);
        when(imgElement1.findElement(By.tagName("a"))).thenReturn(imgLinkElement1);
        when(element1.findElement(By.className("inventory_item_img"))).thenReturn(imgElement1);

        WebElement addToCartButtonElement1 = mock(WebElement.class);
        when(element1.findElement(By.className("btn_inventory"))).thenReturn(addToCartButtonElement1);

        WebElement itemLinkElement1 = mock(WebElement.class);
        WebElement itemLabelElement1 = mock(WebElement.class);
        when(itemLabelElement1.findElement(By.tagName("a"))).thenReturn(itemLinkElement1);
        when(element1.findElement(By.className("inventory_item_label"))).thenReturn(itemLabelElement1);

        elements.add(element1);


        // Mock behavior for element2
        WebElement itemNameElement2 = mock(WebElement.class);
        when(itemNameElement2.getText()).thenReturn("Item name");
        when(element2.findElement(By.className("inventory_item_name"))).thenReturn(itemNameElement2);

        WebElement itemDescriptionElement2 = mock(WebElement.class);
        when(itemDescriptionElement2.getText()).thenReturn("Item description");
        when(element2.findElement(By.className("inventory_item_desc"))).thenReturn(itemDescriptionElement2);

        WebElement priceElement2 = mock(WebElement.class);
        when(priceElement2.getText()).thenReturn("$20.00");
        when(element2.findElement(By.className("inventory_item_price"))).thenReturn(priceElement2);

        WebElement imgLinkElement2 = mock(WebElement.class);
        when(imgLinkElement2.getAttribute("href")).thenReturn("img URL");
        WebElement imgElement2 = mock(WebElement.class);
        when(imgElement2.findElement(By.tagName("a"))).thenReturn(imgLinkElement2);
        when(element2.findElement(By.className("inventory_item_img"))).thenReturn(imgElement2);

        WebElement addToCartButtonElement2 = mock(WebElement.class);
        when(element2.findElement(By.className("btn_inventory"))).thenReturn(addToCartButtonElement2);

        WebElement itemLinkElement2 = mock(WebElement.class);
        WebElement itemLabelElement2 = mock(WebElement.class);
        when(itemLabelElement2.findElement(By.tagName("a"))).thenReturn(itemLinkElement2);
        when(element2.findElement(By.className("inventory_item_label"))).thenReturn(itemLabelElement2);

        elements.add(element2);

        inventoryPage.setElements(elements);

        //call the method
        List<InventoryItem> items = inventoryPage.getItems();

        assertEquals(2,items.size());

        assertEquals("Item name",items.getFirst().getName());
        assertEquals("Item description",items.getFirst().getDescription());
        assertEquals(20.0,items.getFirst().getPrice());
        assertEquals("img URL",items.getFirst().getImgURL());
        assertEquals(addToCartButtonElement1,items.getFirst().getAddToCartButton());
        assertEquals(itemLinkElement1,items.getFirst().getItemLink());
    }

    @Test
    public void testGetItemsNoProducts(){
        List<WebElement> elements = new ArrayList<>();

        inventoryPage.setElements(elements);

        //call the method
        List<InventoryItem> items = inventoryPage.getItems();

        assertEquals(0,items.size());
    }

    @Test
    public void testSortItems(){
        InventoryPage.SortOption sortOption = InventoryPage.SortOption.NAME_A_TO_Z;

        inventoryPage.sortItems(sortOption,sortBySelect);

        verify(sortBySelect).selectByValue(sortOption.getValue());
    }

    @Test
    public void testGoToCart(){
        inventoryPage.goToCart();
        verify(cartButton).click();
    }

    @Test
    public void testItemsInOrderAZ_True(){
        List<InventoryItem> items = new ArrayList<>();

        InventoryItem item1 = mock(InventoryItem.class);
        when(item1.getTitle()).thenReturn("A");
        items.add(item1);

        InventoryItem item2 = mock(InventoryItem.class);
        when(item2.getTitle()).thenReturn("Z");
        items.add(item2);

        assertTrue(inventoryPage.itemsInOrderAZ(items));
    }

    @Test
    public void testItemsInOrderAZ_False(){
        List<InventoryItem> items = new ArrayList<>();

        InventoryItem item1 = mock(InventoryItem.class);
        when(item1.getTitle()).thenReturn("Z");
        items.add(item1);

        InventoryItem item2 = mock(InventoryItem.class);
        when(item2.getTitle()).thenReturn("A");
        items.add(item2);

        assertFalse(inventoryPage.itemsInOrderAZ(items));
    }

    @Test
    public void testItemsInOrderAZ_Equal(){
        List<InventoryItem> items = new ArrayList<>();

        InventoryItem item1 = mock(InventoryItem.class);
        when(item1.getTitle()).thenReturn("A");
        items.add(item1);

        InventoryItem item2 = mock(InventoryItem.class);
        when(item2.getTitle()).thenReturn("A");
        items.add(item2);

        assertTrue(inventoryPage.itemsInOrderAZ(items));
    }

    @Test
    public void testItemsInOrderZA_True(){
        List<InventoryItem> items = new ArrayList<>();

        InventoryItem item1 = mock(InventoryItem.class);
        when(item1.getTitle()).thenReturn("Z");
        items.add(item1);

        InventoryItem item2 = mock(InventoryItem.class);
        when(item2.getTitle()).thenReturn("A");
        items.add(item2);

        assertTrue(inventoryPage.itemsInOrderZA(items));
    }

    @Test
    public void testItemsInOrderZA_False(){
        List<InventoryItem> items = new ArrayList<>();

        InventoryItem item1 = mock(InventoryItem.class);
        when(item1.getTitle()).thenReturn("A");
        items.add(item1);

        InventoryItem item2 = mock(InventoryItem.class);
        when(item2.getTitle()).thenReturn("Z");
        items.add(item2);

        assertFalse(inventoryPage.itemsInOrderZA(items));
    }

    @Test
    public void testItemsInOrderZA_Equal(){
        List<InventoryItem> items = new ArrayList<>();

        InventoryItem item1 = mock(InventoryItem.class);
        when(item1.getTitle()).thenReturn("Z");
        items.add(item1);

        InventoryItem item2 = mock(InventoryItem.class);
        when(item2.getTitle()).thenReturn("Z");
        items.add(item2);

        assertTrue(inventoryPage.itemsInOrderZA(items));
    }


}