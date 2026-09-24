package ai.tools;

public class TestResultStore {

    private static TestExecutionResult latestResult;

    public static void save(TestExecutionResult result) {
        latestResult = result;
    }

    public static TestExecutionResult getLatestResult() {
        return latestResult;
    }
}