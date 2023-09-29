package models;

import numbers_with_logs.NumberWithLogs;

import java.util.List;
import java.util.function.BiFunction;

import static numbers_with_logs.NumberWithLogs.Operation;

/**
 * Implementation of a calculator
 */
public class SimpleCalculator implements Calculator {

    /**
     * Note that this number is also used to track the result
     */
    private NumberWithLogs first;

    private NumberWithLogs second;

    private BiFunction<Number, Number, NumberWithLogs> operation;
    public SimpleCalculator() {
        this.first = new NumberWithLogs(0);
        this.second = new NumberWithLogs(0);
        this.operation = Operation::addition;
    }

    @Override
    public void setFirstNumber(NumberWithLogs number) {
        first = number;
    }

    @Override
    public void setSecondNumber(NumberWithLogs number) {
        second = number;
    }

    @Override
    public void setOperation(BiFunction<Number, Number, NumberWithLogs> newOp) {
        operation = newOp;
    }

    @Override
    public void calculate() {
        first = Operation.runWithLogs(first, second, operation);
    }

    @Override
    public NumberWithLogs getResult() {
        return first;
    }

    @Override
    public String toString() {
        List<String> history = first.logs();
        String lastOperation = history.isEmpty()
                ? "None"
                : history.get(history.size() - 1);
        return String.format(
                """
                Current number: %f
                Last operation: %s
                """,
                first.value().doubleValue(),
                lastOperation);
    }
}
