package numbers_with_logs;

public final class NumberWithLogs {
    private final Number value;

    private final StringBuilder logBuilder;

    public NumberWithLogs(Number value) {
        this(value, new StringBuilder());
    }

    public NumberWithLogs(Number value, StringBuilder logBuilder) {
        this.value = value;
        this.logBuilder = logBuilder;
    }

    public Number value() {
        return value;
    }

    /* TODO: Figure out how I want to display logs to user */


}
