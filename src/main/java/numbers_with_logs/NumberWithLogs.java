package numbers_with_logs;

import utility.Concatenate;

import java.util.LinkedList;
import java.util.List;
import java.util.function.BiFunction;

/**
 * A number that logs what operations have been applied to it
 */
public final class NumberWithLogs {
    private final Number value;

    private final List<String> logs;

    public NumberWithLogs(Number value) {
        this.value = value;
        this.logs = new LinkedList<>();
    }

    public NumberWithLogs(Number value, List<String> history) {
        this(value);
        this.logs.addAll(history);
    }

    /**
     * Returns the value
     * @return value
     */
    public Number value() {
        return value;
    }

    /**
     * Returns the logs
     * @return logs
     */
    public List<String> logs() {
        return new LinkedList<>(logs);
    }

    @Override
    public String toString() {
        return String.format(
                        """
                        Value: %f
                        Logs: %s
                        """,
                value.doubleValue(),
                logs.toString());
    }

    /**
     * Represents an operation, which creates a number with logs and records the operation
     */
    public interface Operation {

        /**
         * Monad pattern - applies a function to two numbers with logs to return a new number with log, while logging
         * what happened
         * @param first first number
         * @param second second number
         * @param function function to apply to two numbers
         * @return a new number with logs with a history of all the steps that happened to get to this result
         */
        public static NumberWithLogs runWithLogs(
                NumberWithLogs first,
                NumberWithLogs second,
                BiFunction<Number, Number, NumberWithLogs> function) {
            NumberWithLogs transformedNumber = function.apply(first.value(), second.value());
            return new NumberWithLogs(
                    transformedNumber.value(),
                    Concatenate.lists(first.logs(), transformedNumber.logs()));
        }

        /**
         * Addition with logs
         * @param first first number
         * @param second second number
         * @return a number with logs, with an addition log added
         */
        public static NumberWithLogs addition(Number first, Number second) {
            Equation addition = new Equation(
                    first,
                    "+",
                    second,
                    first.doubleValue() + second.doubleValue());
            return new NumberWithLogs(
                    addition.result,
                    List.of(addition.toString()));
        }

        /**
         * Subtraction w/ logs
         * @param first first number
         * @param second second number
         * @return a number with logs, with a subtraction log
         */
        public static NumberWithLogs subtraction(Number first, Number second) {
            Equation subtraction = new Equation(
                    first,
                    "-",
                    second,
                    first.doubleValue() - second.doubleValue());
            return new NumberWithLogs(
                    subtraction.result,
                    List.of(subtraction.toString()));

        }

        /**
         * Multiplication w/ logs
         * @param first first number
         * @param second second number
         * @return a number with logs, with a multiplication log
         */
        public static NumberWithLogs multiplication(Number first, Number second) {
            Equation multiplication = new Equation(
                    first,
                    "*",
                    second,
                    first.doubleValue() * second.doubleValue());
            return new NumberWithLogs(
                    multiplication.result,
                    List.of(multiplication.toString()));

        }

        /**
         * Division w/ logs
         * @param first first number
         * @param second second number
         * @return a number with logs, with a division log
         */
        public static NumberWithLogs division(Number first, Number second) {
            Equation division = new Equation(
                    first,
                    "/",
                    second,
                    first.doubleValue() / second.doubleValue());
            return new NumberWithLogs(
                    division.result,
                    List.of(division.toString()));
        }

        /**
         * Exponent w/ logs
         * @param first first number
         * @param second second number
         * @return a number with logs, with an exponent log
         */
        public static NumberWithLogs power(Number first, Number second) {
            Equation power = new Equation(
                    first,
                    "^",
                    second,
                    Math.pow(first.doubleValue(), second.doubleValue()));
            return new NumberWithLogs(
                    power.result,
                    List.of(power.toString()));
        }

        /** A helper class, representing an equation, e.g. "5 + 10 = 15", or "12 ^ 2 = 144"
         * This was created to make logging easier to understand and implement.
         */
        class Equation {

            private final Number first;
            private final String operationSign;
            private final Number second;
            private final Number result;

            private Equation(
                    Number first,
                    String operationSign,
                    Number second,
                    Number result) {
                this.first = first;
                this.operationSign = operationSign;
                this.second = second;
                this.result = result;
            }

            /** Works for printing and conveniently as a log message */
            @Override
            public String toString() {
                return String.format("%f %s %f = %f",
                        first.doubleValue(),
                        operationSign,
                        second.doubleValue(),
                        result.doubleValue());
            }
        }
    }
}
