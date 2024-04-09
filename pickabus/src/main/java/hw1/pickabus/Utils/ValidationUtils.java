package hw1.pickabus.Utils;

import org.springframework.util.StringUtils;
import java.util.regex.Pattern;

public class ValidationUtils {

    private static final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
    private static final int MIN_NAME_LENGTH = 2;
    private static final int MAX_NAME_LENGTH = 80;

    public static boolean isValidEmail(String email) {
        return StringUtils.hasText(email) && Pattern.matches(EMAIL_REGEX, email);
    }
    
    public static boolean isValidName(String name) {
        return StringUtils.hasText(name) && name.length() >= MIN_NAME_LENGTH && name.length() <= MAX_NAME_LENGTH;
    }
}
