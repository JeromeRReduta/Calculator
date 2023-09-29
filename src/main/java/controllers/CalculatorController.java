package controllers;

/**
 * Handles the logic for the calculator and its view
 */
public interface CalculatorController {

    /**
     * The calculator needs to be setup before we can calculate(), or else the results will be undefined. This determines
     * whether the calculator is setup
     * @return if the calculator is setup
     */
    boolean isSetUp();

    /**
     * Sets the initial value for the calculator
     */
    void setInitialValue();

    /**
     * Moves the calculator forward 1 step. A step is defined as:
     * 1. If the calculator isn't setup, sets up the calculator by prompting the user for an initial value and an
     * operation.
     * 2. Prompts the user for a second value.
     * 3. Calculates the result.
     * 4. Prompts the user for an operation or to end the program.
     */
    default void step() {
        if (!isSetUp()) {
            setInitialValue();
            setOperation();
        }
        setSecondValue();
        calculate();
        setOperationOrEnd();
    }

    /**
     * Runs the calculator, looping between each step. Once done, we display the steps we took, and the final result.
     */
    default void run() {
        boolean shouldRunStep = true;
        while (shouldRunStep) {
            step();
            shouldRunStep = shouldGoAgain();
        }
        displaySteps();
        displayResult();
    }

    /**
     * Sets the calculator's operation
     */
    void setOperation();

    /**
     * Does the same as setOperation(), while also allowing the user to send an end request
     */
    void setOperationOrEnd();

    /**
     * Sets the second value on the calculator
     */
    void setSecondValue();

    /**
     * Calculates the result. If both numbers and the operation have not been setup, the result is undefined.
     */
    void calculate();

    /**
     * Returns whether the calculator should run another step
     * @return whether the calculator should run another step
     */
    boolean shouldGoAgain();

    /**
     * "Shows its work" - shows the steps the calculator took to get to the result
     */
    void displaySteps();

    /**
     * Displays the result
     */
    void displayResult();

}
