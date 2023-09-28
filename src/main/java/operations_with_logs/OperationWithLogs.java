package operations_with_logs;

import java.util.List;
import java.util.function.BiFunction;

import numbers_with_logs.NumberWithLogs;
import utility.Util;


public interface OperationWithLogs {

    public static NumberWithLogs runWithLogs(
            NumberWithLogs first,
            NumberWithLogs second,
            BiFunction<Number, Number, NumberWithLogs> function) {
        NumberWithLogs transformedNumber = function.apply(first.value(), second.value());
        return new NumberWithLogs(
                transformedNumber.value(),
                Util.listConcat(first.logs(), transformedNumber.logs()));
    }

    public static NumberWithLogs addition(Number first, Number second) {
        Operation addition = new Operation(
                first,
                "+",
                second,
                first.doubleValue() + second.doubleValue());
        return new NumberWithLogs(
                addition.result,
                List.of(addition.toString()));
    }

    public static NumberWithLogs subtraction(Number first, Number second) {
        Operation subtraction = new Operation(
                first,
                "-",
                second,
                first.doubleValue() - second.doubleValue());
        return new NumberWithLogs(
                subtraction.result,
                List.of(subtraction.toString()));

    }

    public static NumberWithLogs multiplication(Number first, Number second) {
        Operation multiplication = new Operation(
                first,
                "*",
                second,
                first.doubleValue() * second.doubleValue());
        return new NumberWithLogs(
                multiplication.result,
                List.of(multiplication.toString()));

    }

    public static NumberWithLogs division(Number first, Number second) {
        Operation division = new Operation(
                first,
                "/",
                second,
                first.doubleValue() / second.doubleValue());
        return new NumberWithLogs(
                division.result,
                List.of(division.toString()));

    }

    public static NumberWithLogs power(Number first, Number second) {
        Operation power = new Operation(
                first,
                "^",
                second,
                Math.pow(first.doubleValue(), second.doubleValue()));
        return new NumberWithLogs(
                power.result,
                List.of(power.toString()));
    }

    public class Operation {

        private final Number first;
        private final String operationSign;
        private final Number second;
        private final Number result;

        public Operation(
                Number first,
                String operationSign,
                Number second,
                Number result) {
            this.first = first;
            this.operationSign = operationSign;
            this.second = second;
            this.result = result;
        }

        public Number getResult() {
            return result;
        }

        public String toString() {
            return String.format("%f %s %f = %f",
                    first.doubleValue(),
                    operationSign,
                    second.doubleValue(),
                    result.doubleValue());
        }
    }

}
