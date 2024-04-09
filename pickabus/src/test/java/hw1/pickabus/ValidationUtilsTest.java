package hw1.pickabus;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import hw1.pickabus.Utils.ValidationUtils;

class ValidationUtilsTest {

    @Test
    void testValidEmail() {
        Assertions.assertTrue(ValidationUtils.isValidEmail("example@example.com"));
    }

    @Test
    void testInvalidEmail() {
        Assertions.assertFalse(ValidationUtils.isValidEmail("invalid-email"));
    }

    @Test
    void testValidName() {
        Assertions.assertTrue(ValidationUtils.isValidName("John Doe"));
    }

    @Test
    void testInvalidName() {
        // name must be at least 2 characters long
        Assertions.assertFalse(ValidationUtils.isValidName("J"));
        // Testing the max length condition
        StringBuilder longName = new StringBuilder();
        for(int i = 0; i < 81; i++) { // 80 is max length
            longName.append("a");
        }
        Assertions.assertFalse(ValidationUtils.isValidName(longName.toString()));
    }
}
