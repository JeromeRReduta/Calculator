package views;

public interface CalculatorView {

    public default void tryDisplayResultValue() {
        try {
            displayResultValue();
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void displayResultValue();

    public default void tryDisplayHistory() {
        try {
            displayHistory();
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void displayHistory();
}
