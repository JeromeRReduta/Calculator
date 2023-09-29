package views;

/**
 * Handles display logic for the calculator
 */
public interface CalculatorView {

    /**
     * Tries to display the calculator's result. Handles errors
     */
    public default void tryDisplayResultValue() {
        try {
            displayResultValue();
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Displays the calculator's result.
     */
    public void displayResultValue();

    /**
     * Tries to display the calculator's history. Handles errors.
     */
    public default void tryDisplayHistory() {
        try {
            displayHistory();
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Displays the calculator's history
     */
    public void displayHistory();
}
