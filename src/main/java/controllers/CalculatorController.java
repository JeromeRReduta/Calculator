package controllers;

public interface CalculatorController {

    boolean isSetUp();

    void setInitialValue();

    default void step() {
        if (!isSetUp()) {
            setInitialValue();
            setOperation();
        }
        setSecondValue();
        calculate();
        setOperationOrEnd();
        /* Then check if we loop again */
    }

    default void run() {
        boolean shouldRunStep = true;
        while (shouldRunStep) {
            step();
            shouldRunStep = shouldGoAgain();
        }
        displaySteps();
        displayResult();
    }

    void setOperation();

    void setOperationOrEnd();

    void setSecondValue();

    void calculate();

    boolean shouldGoAgain();

    void displaySteps();

    void displayResult();

}
