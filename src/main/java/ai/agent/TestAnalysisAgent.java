package ai.agent;

import ai.llm.LlmClient;
import ai.skills.TestAnalysisSkill;
import ai.tools.AssertionFailureTool;
import ai.tools.TestResultTool;

import com.openai.models.chat.completions.ChatCompletion;
import com.openai.models.chat.completions.ChatCompletionCreateParams;
import com.openai.models.chat.completions.ChatCompletionToolMessageParam;

import java.util.Collection;

public class TestAnalysisAgent {

    private static final int MAX_ITERATIONS = 10;

    private final LlmClient llmClient;

    public TestAnalysisAgent() {
        this.llmClient = new LlmClient();
    }

    /**
     * Runs the agent loop: the model is called repeatedly. On every turn the
     * requested tools are executed and their results are fed back to the
     * model. The loop finishes as soon as the model answers without asking
     * for another tool.
     */
    public String analyze(String userRequest) {

        TestAnalysisSkill skill = new TestAnalysisSkill();

        ChatCompletionCreateParams.Builder paramsBuilder =
                ChatCompletionCreateParams.builder()
                        .model("gpt-5.4-nano")
                        .addSystemMessage(skill.getInstructions())
                        .addTool(TestResultTool.class)
                        .addTool(AssertionFailureTool.class)
                        .addUserMessage(userRequest);

        for (int iteration = 1; iteration <= MAX_ITERATIONS; iteration++) {

            System.out.println(
                    "----- Agent loop iteration " + iteration + " -----"
            );

            ChatCompletion response =
                    llmClient.chat(paramsBuilder.build());

            var toolCalls = response.choices().stream()
                    .map(ChatCompletion.Choice::message)
                    .peek(paramsBuilder::addMessage)
                    .flatMap(message ->
                            message.toolCalls()
                                    .stream()
                                    .flatMap(Collection::stream)
                    )
                    .toList();

            // No more tools requested: the model produced its final answer.
            if (toolCalls.isEmpty()) {
                return response.choices()
                        .get(0)
                        .message()
                        .content()
                        .orElse("");
            }

            for (var toolCall : toolCalls) {

                var function = toolCall.asFunction().function();

                System.out.println(
                        "Agent requested tool: " + function.name()
                );

                String result = switch (function.name()) {

                    case "TestResultTool" ->
                            function.arguments(TestResultTool.class)
                                    .execute();

                    case "AssertionFailureTool" ->
                            function.arguments(AssertionFailureTool.class)
                                    .execute();

                    default ->
                            "Unknown function: " + function.name();
                };

                System.out.println("Tool result:");
                System.out.println(result);

                paramsBuilder.addMessage(
                        ChatCompletionToolMessageParam.builder()
                                .toolCallId(
                                        toolCall.asFunction().id()
                                )
                                .contentAsJson(result)
                                .build()
                );
            }
        }

        return "Agent stopped: reached the maximum number of iterations ("
                + MAX_ITERATIONS
                + ") without producing a final answer.";
    }
}
