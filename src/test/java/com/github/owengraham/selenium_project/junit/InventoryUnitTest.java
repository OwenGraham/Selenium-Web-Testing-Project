package com.github.owengraham.selenium_project.junit;

import com.github.owengraham.selenium_project.pages.InventoryItem;
import com.github.owengraham.selenium_project.pages.InventoryPage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class InventoryUnitTest {
    @Mock
    private WebElement cartButton;

    @Mock
    private Select sortBySelect;

    private InventoryPage inventoryPage;

    @BeforeEach
    public void setUp() throws NoSuchFieldException, IllegalAccessException {
        // Initialize the mocks
        MockitoAnnotations.openMocks(this);

        // Initialise the SUT and inject mocks
        inventoryPage = new InventoryPage(cartButton,sortBySelect);

        // Set the InventoryPage's cart button as the mock using reflection
        Field cartButtonInPage = InventoryPage.class.getDeclaredField("cartButton");
        cartButtonInPage.setAccessible(true);
        cartButtonInPage.set(inventoryPage,cartButton);
    }


    @Test
    public void testGetItems(){
        // Mock behavior for element1
        WebElement element1 = mock(WebElement.class);

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


        // Mock behavior for element2
        WebElement element2 = mock(WebElement.class);

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

        // Set the SUT's elements list as a list of the mocked WebElements
        inventoryPage.setElements(Arrays.asList(element1,element2));

        // Call the method
        List<InventoryItem> items = inventoryPage.getItems();

        // Check the size of the list returned is correct
        assertEquals(2,items.size());

        // Check the field of the returned InventoryItem objects are correct
        assertEquals("Item name",items.getFirst().getName());
        assertEquals("Item description",items.getFirst().getDescription());
        assertEquals(20.0,items.getFirst().getPrice());
        assertEquals("img URL",items.getFirst().getImgURL());
        assertEquals(addToCartButtonElement1,items.getFirst().getAddToCartButton());
        assertEquals(itemLinkElement1,items.getFirst().getItemLink());
    }

    @Test
    public void testGetItems_NoProducts(){
        // Set the SUT's elements list as an empty list
        List<WebElement> elements = new ArrayList<>();
        inventoryPage.setElements(elements);

        // Call the method
        List<InventoryItem> items = inventoryPage.getItems();

        // Check the method returns an empty list
        assertEquals(0,items.size());
    }

    @Test
    public void testSortItems(){
        // Call the method with the sort option Name (A to Z)
        InventoryPage.SortOption sortOption = InventoryPage.SortOption.NAME_A_TO_Z;
        inventoryPage.sortItems(sortOption,sortBySelect);

        // Check that the selectByValue method is called on the sortBySelect element on the page with the value corresponding to the sort option Name (A to Z)
        verify(sortBySelect).selectByValue(sortOption.getValue());
    }

    @Test
    public void testGoToCart(){
        // Call the method
        inventoryPage.goToCart();

        // Check that the cartButton element is clicked
        verify(cartButton).click();
    }

    @Test
    public void testItemsInOrderAZ_True(){
        // Mock the first item
        InventoryItem item1 = mock(InventoryItem.class);
        when(item1.getTitle()).thenReturn("A");

        // Mock the second item
        InventoryItem item2 = mock(InventoryItem.class);
        when(item2.getTitle()).thenReturn("Z");

        // Create a list with the two mocked items
        List<InventoryItem> items = Arrays.asList(item1,item2);

        // Check that the method returns true when the items in the list are in alphabetical order
        assertTrue(inventoryPage.itemsInOrderAZ(items));
    }

    @Test
    public void testItemsInOrderAZ_False(){
        // Mock the first item
        InventoryItem item1 = mock(InventoryItem.class);
        when(item1.getTitle()).thenReturn("Z");

        // Mock the second item
        InventoryItem item2 = mock(InventoryItem.class);
        when(item2.getTitle()).thenReturn("A");

        // Create a list with the two mocked items
        List<InventoryItem> items = Arrays.asList(item1,item2);

        // Check that the method returns false when the items in the list are not in alphabetical order
        assertFalse(inventoryPage.itemsInOrderAZ(items));
    }

    @Test
    public void testItemsInOrderAZ_Equal(){
        // Mock the first item
        InventoryItem item1 = mock(InventoryItem.class);
        when(item1.getTitle()).thenReturn("A");

        // Mock the second item
        InventoryItem item2 = mock(InventoryItem.class);
        when(item2.getTitle()).thenReturn("A");

        // Create a list with the two mocked items
        List<InventoryItem> items = Arrays.asList(item1,item2);

        // Check that the method returns true when the items in the list start with the same letter
        assertTrue(inventoryPage.itemsInOrderAZ(items));
    }

    @Test
    public void testItemsInOrderZA_True(){
        // Mock the first item
        InventoryItem item1 = mock(InventoryItem.class);
        when(item1.getTitle()).thenReturn("Z");

        // Mock the second item
        InventoryItem item2 = mock(InventoryItem.class);
        when(item2.getTitle()).thenReturn("A");

        // Create a list with the two mocked items
        List<InventoryItem> items = Arrays.asList(item1,item2);

        // Check that the method returns true when the items in the list are in reverse alphabetical order
        assertTrue(inventoryPage.itemsInOrderZA(items));
    }

    @Test
    public void testItemsInOrderZA_False(){
        // Mock the first item
        InventoryItem item1 = mock(InventoryItem.class);
        when(item1.getTitle()).thenReturn("A");

        // Mock the second item
        InventoryItem item2 = mock(InventoryItem.class);
        when(item2.getTitle()).thenReturn("Z");

        // Create a list with the two mocked items
        List<InventoryItem> items = Arrays.asList(item1,item2);

        // Check that the method returns false when the items in the list are not in reverse alphabetical order
        assertFalse(inventoryPage.itemsInOrderZA(items));
    }

    @Test
    public void testItemsInOrderZA_Equal(){
        // Mock the first item
        InventoryItem item1 = mock(InventoryItem.class);
        when(item1.getTitle()).thenReturn("Z");

        // Mock the second item
        InventoryItem item2 = mock(InventoryItem.class);
        when(item2.getTitle()).thenReturn("Z");

        // Create a list with the two mocked items
        List<InventoryItem> items = Arrays.asList(item1,item2);

        // Check that the method returns true when the two items in the list's titles start with the same letter.
        assertTrue(inventoryPage.itemsInOrderZA(items));
    }
}