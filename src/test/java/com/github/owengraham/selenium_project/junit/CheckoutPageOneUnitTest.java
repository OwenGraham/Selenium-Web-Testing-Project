package com.github.owengraham.selenium_project.junit;

import com.github.owengraham.selenium_project.pages.CheckoutPageOne;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.lang.reflect.Field;

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

    @InjectMocks
    private CheckoutPageOne checkoutPageOne;

    @BeforeEach
    void setUp() throws NoSuchFieldException, IllegalAccessException {
        MockitoAnnotations.openMocks(this);

        Field firstNameFieldInPage = CheckoutPageOne.class.getDeclaredField("firstNameField");
        firstNameFieldInPage.setAccessible(true);
        firstNameFieldInPage.set(checkoutPageOne,firstNameField);

        Field lastNameFieldInPage = CheckoutPageOne.class.getDeclaredField("lastNameField");
        lastNameFieldInPage.setAccessible(true);
        lastNameFieldInPage.set(checkoutPageOne,lastNameField);

        Field postCodeFieldInPage = CheckoutPageOne.class.getDeclaredField("postCodeField");
        postCodeFieldInPage.setAccessible(true);
        postCodeFieldInPage.set(checkoutPageOne,postCodeField);

        Field continueButtonInPage = CheckoutPageOne.class.getDeclaredField("continueButton");
        continueButtonInPage.setAccessible(true);
        continueButtonInPage.set(checkoutPageOne,continueButton);
    }

    @Test
    void testEnterFirstName() {
        String firstName = "first name";
        checkoutPageOne.enterFirstName(firstName);
        verify(firstNameField).sendKeys(firstName);
    }

    @Test
    void testEnterLastName() {
        String lastName = "last name";
        checkoutPageOne.enterLastName(lastName);
        verify(lastNameField).sendKeys(lastName);
    }

    @Test
    void testEnterPostCode() {
        String postCode = "postcode";
        checkoutPageOne.enterPostCode(postCode);
        verify(postCodeField).sendKeys(postCode);
    }

    @Test
    void testClickContinue() {
        checkoutPageOne.clickContinue();
        verify(continueButton).click();
    }

    @Test
    void testGetErrorMessage() {
        String errorMessage = "error";
        WebElement errorMessageElement = mock(WebElement.class);
        when(errorMessageElement.getText()).thenReturn(errorMessage);
        when(webDriver.findElement(By.tagName("h3"))).thenReturn(errorMessageElement);

        assertEquals(errorMessage,checkoutPageOne.getErrorMessage());
    }

    @Test
    void testGetErrorMessage_NoElement() {
        when(webDriver.findElement(By.tagName("h3"))).thenThrow(NoSuchElementException.class);
        assertThrows(NoSuchElementException.class, () -> {
            checkoutPageOne.getErrorMessage();
        });
    }
}
