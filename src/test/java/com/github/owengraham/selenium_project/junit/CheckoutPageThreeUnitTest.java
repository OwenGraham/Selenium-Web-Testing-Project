package com.github.owengraham.selenium_project.junit;

import com.github.owengraham.selenium_project.pages.CheckoutPageThree;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.openqa.selenium.WebElement;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class CheckoutPageThreeUnitTest {
    @Mock
    private WebElement completeHeader;

    private CheckoutPageThree checkoutPageThree;

    @BeforeEach
    void setUp() {
        // Initialise mocks
        MockitoAnnotations.openMocks(this);

        checkoutPageThree = new CheckoutPageThree(completeHeader);
    }

    @Test
    void testGetMessage() {
        String expected = "test message";

        // Mock the behaviour of the completeHeader element
        when(completeHeader.getText()).thenReturn(expected);

        // Call the method
        String actual = checkoutPageThree.getMessage();

        // Check the method gets the text of the completeHeader element
        verify(completeHeader).getText();

        // Check the method correctly extracts and returns the message from the page
        assertEquals(expected, actual);
    }
}
