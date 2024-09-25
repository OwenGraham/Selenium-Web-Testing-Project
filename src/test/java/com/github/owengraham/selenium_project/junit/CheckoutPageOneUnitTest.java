package com.github.owengraham.selenium_project.junit;

import com.github.owengraham.selenium_project.pages.CheckoutPageOne;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class CheckoutPageOneUnitTest {
    @Mock
    private WebDriver webDriver;

    @Mock
    private WebElement firstNameField;

    @Mock
    private WebElement lastNameField;

    @Mock
    private WebElement postCodeField;

    @Mock
    private WebElement continueButton;

    private CheckoutPageOne checkoutPageOne;

    @BeforeEach
    void setUp() throws NoSuchFieldException, IllegalAccessException {
        // Initialise the mocks
        MockitoAnnotations.openMocks(this);

        // Initialise the SUT and inject the mocks
        checkoutPageOne = new CheckoutPageOne(webDriver,firstNameField,lastNameField,postCodeField,continueButton);
    }

    @Test
    void testEnterFirstName() {
        // Call the method and pass it the string "first name"
        String firstName = "first name";
        checkoutPageOne.enterFirstName(firstName);

        // Check that the first name field on the page is sent the keys passed to the method
        verify(firstNameField).sendKeys(firstName);
    }

    @Test
    void testEnterLastName() {
        // Call the method and pass it the string "last name"
        String lastName = "last name";
        checkoutPageOne.enterLastName(lastName);

        // Check that the last name field on the page is sent the keys passed to the method
        verify(lastNameField).sendKeys(lastName);
    }

    @Test
    void testEnterPostCode() {
        // Call the method and pass it the string "postcode"
        String postCode = "postcode";
        checkoutPageOne.enterPostCode(postCode);

        // Check that the string passed to the method is entered into the postcode field on the page
        verify(postCodeField).sendKeys(postCode);
    }

    @Test
    void testClickContinue() {
        // Call the method
        checkoutPageOne.clickContinue();

        // Check that the continue button on the page is clicked
        verify(continueButton).click();
    }

    @Test
    void testGetErrorMessage() {
        // Mock the WebElement that contains the error message
        WebElement errorMessageElement = mock(WebElement.class);

        // Mock the behaviour of the error message element to return the string "error" when getText() is called
        String errorMessage = "error";
        when(errorMessageElement.getText()).thenReturn(errorMessage);

        // Mock the WebDriver to return the mocked error message element
        when(webDriver.findElement(By.tagName("h3"))).thenReturn(errorMessageElement);

        // Check that the method correctly extracts and returns the error message
        assertEquals(errorMessage,checkoutPageOne.getErrorMessage());
    }

    @Test
    void testGetErrorMessage_NoElement() {
        // Mock the behaviour of the WebDriver so that it throws an exception when it tries to locate a h3 HTML element
        when(webDriver.findElement(By.tagName("h3"))).thenThrow(NoSuchElementException.class);

        // Check that the method throws an exception when no h3 HTML element is found
        assertThrows(NoSuchElementException.class, () -> {
            checkoutPageOne.getErrorMessage();
        });
    }
}
