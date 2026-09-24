package ai.tools;

import com.fasterxml.jackson.annotation.JsonClassDescription;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;

@JsonClassDescription(
        "Returns the details of the assertion failure "
                + "(org.opentest4j.AssertionFailedError) from the latest "
                + "test run, including the error message and stack trace."
)
public class AssertionFailureTool {

    @JsonPropertyDescription(
            "The name of the test whose assertion failure should be retrieved."
    )
    public String testName;

    public String execute() {

        TestExecutionResult result =
                TestResultStore.getLatestResult();

        if (result == null) {
            return "No test result is available.";
        }

        if (!result.getTestName().equals(testName)) {
            return "No result found for test: " + testName;
        }

        if (!result.hasFailure()) {
            return "The test '" + result.getTestName()
                    + "' passed without any assertion failure.";
        }

        StringBuilder builder = new StringBuilder();

        builder.append("Test: ")
                .append(result.getTestName())
                .append('\n');

        builder.append("Failure Type: ")
                .append(result.getErrorType())
                .append('\n');

        builder.append("Message: ")
                .append(result.getErrorMessage())
                .append('\n');

        builder.append("Stack Trace:")
                .append('\n');

        builder.append(result.getStackTrace());

        return builder.toString();
    }
}
