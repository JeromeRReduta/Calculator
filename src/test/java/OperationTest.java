import numbers_with_logs.NumberWithLogs;
import operations_with_logs.OperationWithLogs;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Arrays;
import java.util.function.BiFunction;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OperationTest {

    @ParameterizedTest
    @CsvSource({
            "5, 7, 12",
            "3, -3, 0",
            "-12, -17, -29"
    })
    public void additionTest(double first, double second, double result) {
        operationTest(first,
                "+",
                second,
                result,
                OperationWithLogs::addition);
    }

    @ParameterizedTest
    @CsvSource({
            "99, 55, 44",
            "12, 12, 0",
            "1, 5, -4"
    })
    public void subtractionTest(double first, double second, double result) {
        operationTest(first,
                "-",
                second,
                result,
                OperationWithLogs::subtraction);
    }

    @ParameterizedTest
    @CsvSource({
            "3, 12, 36",
            "-5, -5, 25",
            "1005, 0, 0"
    })
    public void multiplicationTest(double first, double second, double result) {
        operationTest(first,
                "*",
                second,
                result,
                OperationWithLogs::multiplication);
    }

    @ParameterizedTest
    @CsvSource({
            "12, 6, 2",
            "50, -10, -5",
            "12, 24, 0.5"
    })
    public void divisionTest(double first, double second, double result) {
        operationTest(first,
                "/",
                second,
                result,
                OperationWithLogs::division);
    }

    @ParameterizedTest
    @CsvSource({
            "12, 2, 144",
            "5, 5, 3125",
            "25, 0.5, 5"
    })
    public void powerTest(double first, double second, double result) {
        operationTest(first,
                "^",
                second,
                result,
                OperationWithLogs::power);

    }
    public void operationTest(
            double first,
            String operationSign,
            double second,
            double result,
            BiFunction<Number, Number, NumberWithLogs> operation
    ) {
        NumberWithLogs firstWithLogs = new NumberWithLogs(first);
        NumberWithLogs secondWithLogs = new NumberWithLogs(second);
        NumberWithLogs actual = OperationWithLogs.runWithLogs(
                firstWithLogs,
                secondWithLogs,
                operation);
        assertEquals(result, actual.value());
        String[] expectedLogs = new String[]{
                String.format("%f %s %f = %f", first, operationSign, second, result)
        };
        assertEquals(Arrays.toString(expectedLogs), actual.logs().toString());
    }
}