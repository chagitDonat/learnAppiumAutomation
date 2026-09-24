// package ai.tools;

// import com.fasterxml.jackson.annotation.JsonClassDescription;
// import com.fasterxml.jackson.annotation.JsonPropertyDescription;

// @JsonClassDescription("Returns the latest result of a test.")
// public class TestResultTool {

//     @JsonPropertyDescription("The name of the test to retrieve the result for.")
//     public String testName;

//     public String execute() {
//         return getTestResult();
//     }

//     private String getTestResult() {

//         if ("ClockTest".equals(testName)) {
//             return """
//                     Test: ClockTest
//                     Status: PASS
//                     Duration: 32 seconds
//                     """;
//         }

//         return "No result found for test: " + testName;
//     }
// }


package ai.tools;

import com.fasterxml.jackson.annotation.JsonClassDescription;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;

@JsonClassDescription("Returns the latest result of a test.")
public class TestResultTool {

    @JsonPropertyDescription("The name of the test to retrieve the result for.")
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

        return result.toString();
    }
}