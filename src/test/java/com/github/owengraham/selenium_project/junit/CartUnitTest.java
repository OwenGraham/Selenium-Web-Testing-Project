package com.github.owengraham.selenium_project.junit;

import com.github.owengraham.selenium_project.pages.CartItem;
import com.github.owengraham.selenium_project.pages.CartPage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CartUnitTest {
    @Mock
    private WebElement checkoutButton;

    @Mock
    private WebElement continueShoppingButton;

    private CartPage cartPage;

    @BeforeEach
    public void setUp() throws NoSuchFieldException, IllegalAccessException {
        // Initialise the mocks
        MockitoAnnotations.openMocks(this);

        // Initialise the SUT and inject mocks
        cartPage = new CartPage(checkoutButton,continueShoppingButton);
    }

    @Test
    public void testGetItems(){
        // Mock the first element
        WebElement element1 = mock(WebElement.class);

        WebElement quantityElement1 = mock(WebElement.class);
        when(quantityElement1.getText()).thenReturn("1");
        when(element1.findElement(By.className("cart_quantity"))).thenReturn(quantityElement1);

        WebElement itemLinkElement1 = mock(WebElement.class);
        when(element1.findElement(By.tagName("a"))).thenReturn(itemLinkElement1);

        WebElement titleElement1 = mock(WebElement.class);
        when(titleElement1.getText()).thenReturn("Cart item title");
        when(element1.findElement(By.className("inventory_item_name"))).thenReturn(titleElement1);

        WebElement descriptionElement1 = mock(WebElement.class);
        when(descriptionElement1.getText()).thenReturn("Item description");
        when(element1.findElement(By.className("inventory_item_desc"))).thenReturn(descriptionElement1);

        WebElement priceElement1 = mock(WebElement.class);
        when(priceElement1.getText()).thenReturn("$20.00");
        when(element1.findElement(By.className("inventory_item_price"))).thenReturn(priceElement1);

        WebElement removeButtonElement1 = mock(WebElement.class);
        when(element1.findElement(By.className("cart_button"))).thenReturn(removeButtonElement1);

        // Mock the second element
        WebElement element2 = mock(WebElement.class);

        WebElement quantityElement2 = mock(WebElement.class);
        when(quantityElement2.getText()).thenReturn("1");
        when(element2.findElement(By.className("cart_quantity"))).thenReturn(quantityElement2);

        WebElement itemLinkElement2 = mock(WebElement.class);
        when(element2.findElement(By.tagName("a"))).thenReturn(itemLinkElement2);

        WebElement titleElement2 = mock(WebElement.class);
        when(titleElement2.getText()).thenReturn("Cart item title");
        when(element2.findElement(By.className("inventory_item_name"))).thenReturn(titleElement2);

        WebElement descriptionElement2 = mock(WebElement.class);
        when(descriptionElement2.getText()).thenReturn("Item description");
        when(element2.findElement(By.className("inventory_item_desc"))).thenReturn(descriptionElement2);

        WebElement priceElement2 = mock(WebElement.class);
        when(priceElement2.getText()).thenReturn("$20.00");
        when(element2.findElement(By.className("inventory_item_price"))).thenReturn(priceElement2);

        WebElement removeButtonElement2 = mock(WebElement.class);
        when(element2.findElement(By.className("cart_button"))).thenReturn(removeButtonElement2);

        // Set the SUT's elements list to a list containing the two mocked elements
        cartPage.setElements(Arrays.asList(element1,element2));

        // Call the method
        List<CartItem> items = cartPage.getItems();

        // Check the list returned by the method has the correct size
        assertEquals(2,items.size());

        // Check that the objects returned by the method have the correct data
        assertEquals(1,items.getFirst().getQuantity());
        assertEquals(itemLinkElement1,items.getFirst().getItemLink());
        assertEquals("Cart item title",items.getFirst().getTitle());
        assertEquals("Item description",items.getFirst().getDescription());
        assertEquals(20f,items.getFirst().getPrice());
        assertEquals(removeButtonElement1,items.getFirst().getRemoveButton());
    }

    @Test
    void testHasItem_True() {
        // Mock an element
        WebElement element1 = mock(WebElement.class);

        WebElement quantityElement1 = mock(WebElement.class);
        when(quantityElement1.getText()).thenReturn("1");
        when(element1.findElement(By.className("cart_quantity"))).thenReturn(quantityElement1);

        WebElement itemLinkElement1 = mock(WebElement.class);
        when(element1.findElement(By.tagName("a"))).thenReturn(itemLinkElement1);

        WebElement titleElement1 = mock(WebElement.class);
        when(titleElement1.getText()).thenReturn("Cart item title");
        when(element1.findElement(By.className("inventory_item_name"))).thenReturn(titleElement1);

        WebElement descriptionElement1 = mock(WebElement.class);
        when(descriptionElement1.getText()).thenReturn("Item description");
        when(element1.findElement(By.className("inventory_item_desc"))).thenReturn(descriptionElement1);

        WebElement priceElement1 = mock(WebElement.class);
        when(priceElement1.getText()).thenReturn("$20.00");
        when(element1.findElement(By.className("inventory_item_price"))).thenReturn(priceElement1);

        WebElement removeButtonElement1 = mock(WebElement.class);
        when(element1.findElement(By.className("cart_button"))).thenReturn(removeButtonElement1);

        // Set the SUT's elements list as a singleton list containing the mocked WebElement
        cartPage.setElements(List.of(element1));

        // Check that the method returns true if the SUT's elements list contains an element with the given title
        assertTrue(cartPage.hasItem("Cart item title"));
    }

    @Test
    void testHasItem_False() {
        // Mock a WebElement
        WebElement element1 = mock(WebElement.class);

        WebElement quantityElement1 = mock(WebElement.class);
        when(quantityElement1.getText()).thenReturn("1");
        when(element1.findElement(By.className("cart_quantity"))).thenReturn(quantityElement1);

        WebElement itemLinkElement1 = mock(WebElement.class);
        when(element1.findElement(By.tagName("a"))).thenReturn(itemLinkElement1);

        WebElement titleElement1 = mock(WebElement.class);
        when(titleElement1.getText()).thenReturn("Cart item title");
        when(element1.findElement(By.className("inventory_item_name"))).thenReturn(titleElement1);

        WebElement descriptionElement1 = mock(WebElement.class);
        when(descriptionElement1.getText()).thenReturn("Item description");
        when(element1.findElement(By.className("inventory_item_desc"))).thenReturn(descriptionElement1);

        WebElement priceElement1 = mock(WebElement.class);
        when(priceElement1.getText()).thenReturn("$20.00");
        when(element1.findElement(By.className("inventory_item_price"))).thenReturn(priceElement1);

        WebElement removeButtonElement1 = mock(WebElement.class);
        when(element1.findElement(By.className("cart_button"))).thenReturn(removeButtonElement1);

        // Set the SUT's elements list as a singleton list containing the mocked WebElement
        cartPage.setElements(List.of(element1));

        // Check that the method returns false if the SUT's elements list contains no element with title matching the one given
        assertFalse(cartPage.hasItem("Not here"));
    }

    @Test
    void testHasItem_EmptyList() {
        // Set the SUT's elements list as an empty list
        cartPage.setElements(new ArrayList<>());

        // Check that the method returns false when there are no items
        assertFalse(cartPage.hasItem("Cart item title"));
    }
}
