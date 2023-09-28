import numbers_with_logs.NumberWithLogs;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class NumberWithLogsTest {
    @Test
    @DisplayName("test")
    public void sampleTest() {
        assertEquals(20, 4 * 5, "Regular multiplication");
    }

    @Test
    @DisplayName("Second")
    public void testNumberWithLogsConstruction() {
        NumberWithLogs numWithLogs = new NumberWithLogs(5);
        assertEquals(
                true,
                numWithLogs.value().intValue() == 5 && numWithLogs.logs().isEmpty(),
                "Given a new number with logs of value 5, the value should 5 and the" +
                        "logs should be empty");

    }
}