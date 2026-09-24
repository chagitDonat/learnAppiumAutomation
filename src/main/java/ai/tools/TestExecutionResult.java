package ai.tools;

import java.io.PrintWriter;
import java.io.StringWriter;

public class TestExecutionResult {

    private final String testName;
    private final String status;
    private final long durationMillis;
    private final String errorType;
    private final String errorMessage;
    private final String stackTrace;

    public TestExecutionResult(
            String testName,
            String status,
            long durationMillis) {

        this(testName, status, durationMillis, null, null, null);
    }

    public TestExecutionResult(
            String testName,
            String status,
            long durationMillis,
            String errorType,
            String errorMessage,
            String stackTrace) {

        this.testName = testName;
        this.status = status;
        this.durationMillis = durationMillis;
        this.errorType = errorType;
        this.errorMessage = errorMessage;
        this.stackTrace = stackTrace;
    }

    /**
     * Builds a failed result from a thrown error. When the error is an
     * {@code org.opentest4j.AssertionFailedError}, its fully qualified type
     * name, message and stack trace are captured so they can be sent to the
     * analysis agent.
     */
    public static TestExecutionResult failure(
            String testName,
            long durationMillis,
            Throwable error) {

        if (error == null) {
            return new TestExecutionResult(testName, "FAIL", durationMillis);
        }

        String errorType = error.getClass().getName();

        String errorMessage = error.getMessage() == null
                ? ""
                : error.getMessage();

        return new TestExecutionResult(
                testName,
                "FAIL",
                durationMillis,
                errorType,
                errorMessage,
                stackTraceOf(error)
        );
    }

    private static String stackTraceOf(Throwable error) {

        StringWriter writer = new StringWriter();

        error.printStackTrace(new PrintWriter(writer));

        return writer.toString();
    }

    public String getTestName() {
        return testName;
    }

    public String getStatus() {
        return status;
    }

    public long getDurationMillis() {
        return durationMillis;
    }

    public String getErrorType() {
        return errorType;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public String getStackTrace() {
        return stackTrace;
    }

    public boolean hasFailure() {
        return errorType != null && !errorType.isBlank();
    }

    @Override
    public String toString() {

        StringBuilder builder = new StringBuilder();

        builder.append("""
                Test: %s
                Status: %s
                Duration: %d ms
                """.formatted(
                testName,
                status,
                durationMillis
        ));

        if (hasFailure()) {
            builder.append("""
                    Error Type: %s
                    Error Message: %s
                    """.formatted(
                    errorType,
                    errorMessage
            ));
        }

        return builder.toString();
    }
}
