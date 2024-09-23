package com.sparta.selenium_project.junit;

import com.sparta.selenium_project.pages.LoginPage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class LoginUnitTest {
    @Mock
    private WebDriver webDriver;

    @Mock
    private WebElement usernameField;

    @Mock
    private WebElement passwordField;

    @Mock
    private WebElement errorMessageElement;

    @InjectMocks
    private LoginPage loginPage;

    @BeforeEach
    public void setUp() throws NoSuchFieldException, IllegalAccessException {
        MockitoAnnotations.openMocks(this);
        // Using reflection to inject the mock WebElement into the LoginPage
        Field usernameFieldInPage = LoginPage.class.getDeclaredField("usernameField");
        usernameFieldInPage.setAccessible(true); // Allow access to the private field
        usernameFieldInPage.set(loginPage, usernameField); // Inject the mock WebElement

        Field passwordFieldInPage = LoginPage.class.getDeclaredField("passwordField");
        passwordFieldInPage.setAccessible(true);
        passwordFieldInPage.set(loginPage,passwordField);
    }

    @Test
    public void enterUsernameTest(){
        String username = "username";
        loginPage.enterUsername(username);
        verify(usernameField).sendKeys(username);
    }

    @Test
    public void enterPasswordTest(){
        String password = "password";
        loginPage.enterPassword(password);
        verify(passwordField).sendKeys(password);
    }

    @Test
    public void testGetErrorMessage(){
        String expectedErrorMessage = "Invalid credentials";

        when(webDriver.findElement(By.tagName("h3"))).thenReturn(errorMessageElement);
        when(errorMessageElement.getText()).thenReturn(expectedErrorMessage);

        assertEquals(expectedErrorMessage,loginPage.getErrorMessage());
    }
}
