package ai.tool;

import ai.tools.AssertionFailureTool;
import ai.tools.TestExecutionResult;
import ai.tools.TestResultStore;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.opentest4j.AssertionFailedError;

import static org.junit.jupiter.api.Assertions.assertTrue;

class AssertionFailureToolTest {

    @AfterEach
    void clearStore() {
        TestResultStore.save(
                new TestExecutionResult("none", "PASS", 0)
        );
    }

    @Test
    void returnsAssertionFailureDetails() {

        AssertionFailedError error = new AssertionFailedError(
                "expected: <true> but was: <false>"
        );

        TestResultStore.save(
                TestExecutionResult.failure("ClockTest", 1234, error)
        );

        AssertionFailureTool tool = new AssertionFailureTool();
        tool.testName = "ClockTest";

        String result = tool.execute();

        System.out.println(result);

        assertTrue(result.contains("ClockTest"));
        assertTrue(result.contains("org.opentest4j.AssertionFailedError"));
        assertTrue(result.contains("expected: <true> but was: <false>"));
    }

    @Test
    void reportsPassedTestWithoutFailure() {

        TestResultStore.save(
                new TestExecutionResult("ClockTest", "PASS", 500)
        );

        AssertionFailureTool tool = new AssertionFailureTool();
        tool.testName = "ClockTest";

        String result = tool.execute();

        assertTrue(result.contains("passed without any assertion failure"));
    }
}
