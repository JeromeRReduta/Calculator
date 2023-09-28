package numbers_with_logs;

import java.util.LinkedList;
import java.util.List;


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

    public Number value() {
        return value;
    }

    public List<String> logs() {
        return new LinkedList<>(logs);
    }

    public String toString() {
        return String.format(
                        """
                        Value: %f
                        Logs: %s
                        """,
                value.doubleValue(),
                logs.toString());
    }
}
