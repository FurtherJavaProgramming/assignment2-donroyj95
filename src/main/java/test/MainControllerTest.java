package test;

import controller.MainController;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.control.TextField;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;


import java.time.format.DateTimeParseException;

import static org.junit.jupiter.api.Assertions.*;
class MainControllerTest {

    @BeforeAll
    public static void initJFX() {
        Platform.startup(() -> {});
    }

    @BeforeEach
    public void initToolkit() {
        new JFXPanel(); // Initializes the JavaFX Toolkit
    }

    @org.junit.jupiter.api.Test
    void testIsInteger() {
        Assertions.assertTrue(MainController.isInteger("123")); // Test with a valid integer
        assertTrue(MainController.isInteger("-456")); // Test with a valid negative integer
        assertFalse(MainController.isInteger("abc")); // Test with a string
        assertFalse(MainController.isInteger("12.34")); // Test with a decimal number
        assertFalse(MainController.isInteger("")); // Test with an empty string
        assertFalse(MainController.isInteger(null)); // Test with a null input
    }
    @Test
    void testIsTextFieldEmpty() {
        TextField emptyTextField = new TextField("");

        // Create a new TextField with non-empty text
        TextField nonEmptyTextField = new TextField("Some Text");

        // Test the method with an empty TextField
        assertTrue(MainController.isTextFieldEmpty(emptyTextField));

        // Test the method with a non-empty TextField
        assertFalse(MainController.isTextFieldEmpty(nonEmptyTextField));
    }

    @ParameterizedTest
    @CsvSource({
            "'12345', '\\d+', true",  // Matching digits
            "'abcde', '[a-z]+', true", // Matching lowercase letters
            "'ABCDE', '[a-z]+', false", // Not matching lowercase letters
            "'!@#$', '\\W+', true" // Matching non-word characters
    })
    void testIsTextFieldMatches(String text, String regex, boolean expected) {
        TextField textField = new TextField(text);
        boolean result = MainController.isTextFieldMatches(textField, regex);
        assertEquals(expected, result);
    }

    @ParameterizedTest
    @CsvSource({
            "12/2099, true", // Future date
            "'01/' + @java.time.YearMonth@now().format(DateTimeFormatter.ofPattern('yyyy')), false", // Current month
            "01/2020, false", // Past date
            "2024/12, false", // Invalid format
            "null, false", // Null date
            "'', false" // Empty date
    })
    void testIsFutureDate(String expiryDate, boolean expected) {
        boolean result = false;
        try {
            result = MainController.isFutureDate(expiryDate);
        } catch (DateTimeParseException e) {
            // Handle parsing exceptions
            System.out.println("Invalid date format: " + expiryDate);
        }
        assertEquals(expected, result);
    }

    @ParameterizedTest
    @CsvSource({
            "12345, true", // Numeric string
            "-6789, true", // Negative numeric string
            "abc123, false", // Non-numeric string
            "'', true", // Empty string
            "' ', false", // Space
            "!@#, false" // Special characters
    })
    void testIsNumeric(String str, boolean expected) {
        boolean result = MainController.isNumeric(str);
        assertEquals(expected, result);
    }

}