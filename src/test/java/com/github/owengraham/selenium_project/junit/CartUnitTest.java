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
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CartUnitTest {
    @Mock
    private WebElement checkoutButton;

    @Mock
    private WebElement continueShoppingButton;

    @Mock
    private WebElement element1;

    @Mock
    private WebElement element2;

    @InjectMocks
    private CartPage cartPage;

    @BeforeEach
    public void setUp() throws NoSuchFieldException, IllegalAccessException {
        MockitoAnnotations.openMocks(this);

        Field checkoutButtonInPage = CartPage.class.getDeclaredField("checkoutButton");
        checkoutButtonInPage.set(cartPage,checkoutButton);

        Field continueShoppingButtonInPage = CartPage.class.getDeclaredField("continueShoppingButton");
        continueShoppingButtonInPage.set(cartPage,continueShoppingButton);
    }

    @Test
    public void testGetItems(){
        List<WebElement> elements = new ArrayList<>();

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

        elements.add(element1);

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

        elements.add(element2);

        cartPage.setElements(elements);

        List<CartItem> items = cartPage.getItems();

        assertEquals(2,items.size());

        assertEquals(1,items.getFirst().getQuantity());
        assertEquals(itemLinkElement1,items.getFirst().getItemLink());
        assertEquals("Cart item title",items.getFirst().getTitle());
        assertEquals("Item description",items.getFirst().getDescription());
        assertEquals(20f,items.getFirst().getPrice());
        assertEquals(removeButtonElement1,items.getFirst().getRemoveButton());
    }

    @Test
    void testHasItem_True() {
        List<WebElement> elements = new ArrayList<>();

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

        elements.add(element1);

        cartPage.setElements(elements);

        assertTrue(cartPage.hasItem("Cart item title"));
    }

    @Test
    void testHasItem_False() {
        List<WebElement> elements = new ArrayList<>();

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

        elements.add(element1);

        cartPage.setElements(elements);

        assertFalse(cartPage.hasItem("Not here"));
    }

    @Test
    void testHasItem_EmptyList() {
        List<WebElement> elements = new ArrayList<>();
        cartPage.setElements(elements);

        assertFalse(cartPage.hasItem("Cart item title"));
    }
}
