package com.github.owengraham.selenium_project.junit;

import com.github.owengraham.selenium_project.pages.LoginPage;
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
    private WebElement loginButton;

    @Mock
    private WebElement errorMessageElement;

    private LoginPage loginPage;

    @BeforeEach
    public void setUp() throws NoSuchFieldException, IllegalAccessException {
        // Initialise mocks
        MockitoAnnotations.openMocks(this);

        // Initialising LoginPage and injecting mocks
        loginPage = new LoginPage(webDriver,usernameField,passwordField,loginButton);
    }

    @Test
    public void testEnterUsername(){
        String username = "username";

        // Call the method and pass it the string "username"
        loginPage.enterUsername(username);

        // Check that the string "username" is sent to the username field on the page
        verify(usernameField).sendKeys(username);
    }

    @Test
    public void testEnterPassword(){
        String password = "password";

        // Call the method and pass it the string "password"
        loginPage.enterPassword(password);

        // Check that the string "password" is sent to the password field on the page
        verify(passwordField).sendKeys(password);
    }

    @Test
    public void testLogin(){
        // Call the method
        loginPage.login();

        // Check that the login button on the page is clicked
        verify(loginButton).click();
    }

    @Test
    public void testGetErrorMessage(){
        String expectedErrorMessage = "Invalid credentials";

        // Mock the behaviour of the error message element
        when(errorMessageElement.getText()).thenReturn(expectedErrorMessage);

        // Mock the behaviour of the WebDriver to return the mocked error message element
        when(webDriver.findElement(By.tagName("h3"))).thenReturn(errorMessageElement);

        // Call the method
        String actualErrorMessage = loginPage.getErrorMessage();

        // Check the method correctly extracts and returns the error message from the page
        assertEquals(expectedErrorMessage,actualErrorMessage);
    }

    @Test
    public void testGetErrorMessage_NoElement(){
        // Mock the behaviour of the WebElement to throw an exception when it tries to find a h3 HTML element
        when(webDriver.findElement(By.tagName("h3"))).thenThrow(NoSuchElementException.class);

        // Check that the method throws an exception when called
        assertThrows(NoSuchElementException.class, () -> {
            loginPage.getErrorMessage();
        });
    }
}
