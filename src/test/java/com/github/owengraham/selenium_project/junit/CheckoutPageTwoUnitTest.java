package com.github.owengraham.selenium_project.junit;

import com.github.owengraham.selenium_project.pages.CartItem;
import com.github.owengraham.selenium_project.pages.CheckoutPageTwo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class CheckoutPageTwoUnitTest {
    private List<WebElement> itemElements;

    @Mock
    private WebElement paymentInfoLabel;

    @Mock
    private WebElement deliveryInfoLabel;

    @Mock
    private WebElement subTotal;

    @Mock
    private WebElement tax;

    @Mock
    private WebElement totalPrice;

    @Mock
    private WebElement cancelButton;

    @Mock
    private WebElement finishButton;

    private CheckoutPageTwo checkoutPageTwo;

    @BeforeEach
    void setUp() {
        // Initialise the mocks
        MockitoAnnotations.openMocks(this);

        // Initialise the list of items
        itemElements = new ArrayList<>();

        // Create a spy of the SUT and inject mocks
        checkoutPageTwo = spy(new CheckoutPageTwo(itemElements, paymentInfoLabel, deliveryInfoLabel, subTotal, tax, totalPrice, cancelButton, finishButton));
    }

    @Test
    void testGetPaymentInfo() {
        String expected = "test payment info";
        when(paymentInfoLabel.getText()).thenReturn(expected);

        // Call the method
        String actual = checkoutPageTwo.getPaymentInfo();

        // Make sure the method gets the text of the payment info element
        verify(paymentInfoLabel).getText();

        // Make sure the method correctly extracts and returns the payment info from the page
        assertEquals(expected,actual);
    }

    @Test
    void testGetDeliveryInfo() {
        String expected = "test delivery info";
        when(deliveryInfoLabel.getText()).thenReturn(expected);

        // Call the method
        String actual = checkoutPageTwo.getDeliveryInfo();

        // Make sure the method gets the text of the delivery info element
        verify(deliveryInfoLabel).getText();

        // Make sure the method correctly extracts and returns the delivery info from the page
        assertEquals(expected,actual);
    }

    @Test
    void testGetSubTotal() {
        String input = "Item total: $29.99";
        Float expected = 29.99f;

        // Mock the behaviour of the subtotal element
        when(subTotal.getText()).thenReturn(input);

        // Call the method
        Float actual = checkoutPageTwo.getSubTotal();

        // Make sure the method gets the text of the subtotal element
        verify(subTotal).getText();

        // Make sure the method correctly extracts and returns the subtotal from the page
        assertEquals(expected,actual);
    }

    @Test
    void testGetTax() {
        String input = "Tax: $2.40";
        Float expected = 2.40f;

        // Make sure the method gets the text of the totalPrice element
        // Mock the behaviour of the tax element
        when(tax.getText()).thenReturn(input);

        // Call the method
        Float actual = checkoutPageTwo.getTax();

        // Make sure the method gets the text of the totalPrice element
        verify(tax).getText();

        // Make sure the method correctly extracts and returns the tax from the page
        assertEquals(actual,expected);
    }

    @Test
    void testGetTotalPrice() {
        String input = "Total: $32.39";
        Float expected = 32.39f;

        // Mock the behaviour of the totalPrice element
        when(totalPrice.getText()).thenReturn(input);

        // Call the method
        Float actual = checkoutPageTwo.getTotalPrice();

        // Make sure the method gets the text of the totalPrice element
        verify(totalPrice).getText();

        // Make sure the method correctly extracts and returns the total price from the page
        assertEquals(actual,expected);
    }

    @Test
    void testGetItems() {
        // Expected values for item 1
        WebElement expectedItemLink1 = mock(WebElement.class);
        String expectedTitle1 = "test item title 1";
        String expectedDescription1 = "test item 1 description";
        Float expectedPrice1 = 29.99f;
        int expectedQuantity1 = 1;

        // Expected values for item 1
        WebElement expectedItemLink2 = mock(WebElement.class);
        String expectedTitle2 = "test item title 2";
        String expectedDescription2 = "test item 2 description";
        Float expectedPrice2 = 29.99f;
        int expectedQuantity2 = 2;

        // Mock the first product element on the page
        WebElement item1 = mock(WebElement.class);

        when(item1.findElement(By.tagName("a"))).thenReturn(expectedItemLink1);

        WebElement titleElement1 = mock(WebElement.class);
        when(titleElement1.getText()).thenReturn(expectedTitle1);
        when(item1.findElement(By.className("inventory_item_name"))).thenReturn(titleElement1);

        WebElement descriptionElement1 = mock(WebElement.class);
        when(descriptionElement1.getText()).thenReturn(expectedDescription1);
        when(item1.findElement(By.className("inventory_item_desc"))).thenReturn(descriptionElement1);

        WebElement priceElement1 = mock(WebElement.class);
        when(priceElement1.getText()).thenReturn("$29.99");
        WebElement priceElement2 = null;
        when(item1.findElement(By.className("inventory_item_price"))).thenReturn(priceElement1);

        WebElement quantityElement1 = mock(WebElement.class);
        when(quantityElement1.getText()).thenReturn("1");
        when(item1.findElement(By.className("cart_quantity"))).thenReturn(quantityElement1);

        itemElements.add(item1);


        // Mock the second product element on the page
        WebElement item2 = mock(WebElement.class);

        when(item2.findElement(By.tagName("a"))).thenReturn(expectedItemLink2);

        WebElement titleElement2 = mock(WebElement.class);
        when(titleElement2.getText()).thenReturn(expectedTitle2);
        when(item2.findElement(By.className("inventory_item_name"))).thenReturn(titleElement2);

        WebElement descriptionElement2 = mock(WebElement.class);
        when(descriptionElement2.getText()).thenReturn(expectedDescription2);
        when(item2.findElement(By.className("inventory_item_desc"))).thenReturn(descriptionElement2);

        priceElement2 = mock(WebElement.class);
        when(priceElement2.getText()).thenReturn("$29.99");
        when(item2.findElement(By.className("inventory_item_price"))).thenReturn(priceElement2);

        WebElement quantityElement2 = mock(WebElement.class);
        when(quantityElement2.getText()).thenReturn("2");
        when(item2.findElement(By.className("cart_quantity"))).thenReturn(quantityElement2);

        itemElements.add(item2);

        // Call the method and store the items in a list
        List<CartItem> items = checkoutPageTwo.getItems();

        // Check the list has the correct size
        assertEquals(2,items.size());

        // Check the first item returned has the correct name, description, link to product page, price, and quantity.
        assertEquals(expectedItemLink1,items.get(0).getItemLink());
        assertEquals(expectedTitle1,items.get(0).getTitle());
        assertEquals(expectedDescription1,items.get(0).getDescription());
        assertEquals(expectedPrice1,items.get(0).getPrice());
        assertEquals(expectedQuantity1,items.get(0).getQuantity());

        // Check the second item returned has the correct name, description, link to product page, price, and quantity.
        assertEquals(expectedItemLink2,items.get(1).getItemLink());
        assertEquals(expectedTitle2,items.get(1).getTitle());
        assertEquals(expectedDescription2,items.get(1).getDescription());
        assertEquals(expectedPrice2,items.get(1).getPrice());
        assertEquals(expectedQuantity2,items.get(1).getQuantity());
    }

    @Test
    void testGetItems_NoItems() {
        checkoutPageTwo.setItemElements(itemElements);

        List<CartItem> items = checkoutPageTwo.getItems();

        // Check that the method returns an empty list when there are no items on the page.
        assertEquals(0,items.size());
    }

    @Test
    void testCalculateSubTotal() {
        // Mock the first cart item
        CartItem item1 = mock(CartItem.class);
        when(item1.getPrice()).thenReturn(10f);

        // Mock the second cart item
        CartItem item2 = mock(CartItem.class);
        when(item2.getPrice()).thenReturn(5f);

        // Add the items to a list
        List<CartItem> items = Arrays.asList(item1,item2);

        // Mock the behaviour of the SUT's getItems method to return the list of mocked cart items.
        when(checkoutPageTwo.getItems()).thenReturn(items);

        Float expected = 15f;
        Float actual = checkoutPageTwo.calculateSubTotal();

        assertEquals(expected,actual);
    }

    @Test
    void testCalculateSubTotal_NoItems() {
        // Check that the method returns 0 when there are no items in the cart
        when(checkoutPageTwo.getItems()).thenReturn(new ArrayList<CartItem>());
        assertEquals(0f,checkoutPageTwo.calculateSubTotal());
    }
}
