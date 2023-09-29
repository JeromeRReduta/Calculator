package models;

import numbers_with_logs.NumberWithLogs;

import java.util.function.BiFunction;

/**
 * Represents a calculator. The user must set the first number, second number, and operation in the console, and the calculator
 * must be able to calculate the result and return that result and the steps it took to get there upon request.
 */
public interface Calculator {

    /**
     * Sets the first number
     * @param number number
     */
    public void setFirstNumber(NumberWithLogs number);

    /**
     * Sets the second number
     * @param number number
     */
    public void setSecondNumber(NumberWithLogs number);

    /**
     * Sets the operation this calculator will use
     * @param operation operation
     */
    public void setOperation(BiFunction<Number, Number, NumberWithLogs> operation);

    /**
     * Attempts to calcuate the answer. Note that if the first number, second number, and operation are not set,
     * the results are undefined.
     */
    public void calculate();

    /**
     * Returns the current result
     * @return the current result
     */
    public NumberWithLogs getResult();
}
