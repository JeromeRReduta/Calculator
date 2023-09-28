package models;

import numbers_with_logs.NumberWithLogs;

import java.util.List;
import java.util.function.BiFunction;
import operations_with_logs.OperationWithLogs;

public class SimpleCalculator implements Calculator {

    public static void main(String[] args) {
        System.out.println("First time using calculator: \n" + new SimpleCalculator());
        SimpleCalculator thing = new SimpleCalculator();
        thing.setFirstNumber(new NumberWithLogs(5));
        thing.setSecondNumber(new NumberWithLogs(10));
        thing.setOperation(OperationWithLogs::addition);
        thing.calculate();
        thing.setSecondNumber(new NumberWithLogs(15));
        thing.setOperation(OperationWithLogs::division);
        thing.calculate();
        System.out.println(thing);
        System.out.println(thing.getResult());
    }

    private NumberWithLogs first;

    private NumberWithLogs second;

    private BiFunction<Number, Number, NumberWithLogs> operation;
    public SimpleCalculator() {
        this.first = new NumberWithLogs(0);
        this.second = new NumberWithLogs(0);
        this.operation = OperationWithLogs::addition;
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
        first = OperationWithLogs.runWithLogs(first, second, operation);
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
