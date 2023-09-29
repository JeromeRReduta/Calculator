package utility;

import java.util.Scanner;
import java.util.function.Predicate;

/**
 * Prompts the user for input until valid input is given
 */
public final class InputValidator {
    private final Scanner scan;

    private final String message;

    private final Predicate<String> tester;

    private boolean isValid;

    public InputValidator(
            Scanner scan,
            String message,
            Predicate<String> tester) {
        this.scan = scan;
        this.message = message;
        this.tester = tester;
        this.isValid = false;
    }

    /**
     * Prompts the user for input until valid input (as defined by the tester) is provided
     * @return user input that was deemed valid by the tester
     */
    public String getValidInput() {
        String input = null;
        while (!isValid) {
            System.out.println(message);
            input = scan.nextLine().strip();
            isValid = tester.test(input);
        }
        return input;
    }
}
