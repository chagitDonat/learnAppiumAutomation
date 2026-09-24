package ai.tools;

import com.openai.client.OpenAIClient;
import com.openai.client.okhttp.OpenAIOkHttpClient;
import com.openai.models.chat.completions.ChatCompletion;
import com.openai.models.chat.completions.ChatCompletionCreateParams;
import com.openai.models.chat.completions.ChatCompletionToolMessageParam;

import ai.tools.TestResultTool;
import base.BaseTest;

import org.junit.jupiter.api.Test;

import java.util.Collection;

class ToolCallingTest extends BaseTest {

    // private static final String API_KEY = "YOUR_VERISOFT_API_KEY";
    // private static final String BASE_URL = "https://llm.verisoft.io/v1";

    @Test
    void testToolCalling() {

 /*        System.setProperty(
                "javax.net.ssl.trustStore",
                "certs/verisoft-truststore.jks"
        );

        System.setProperty(
                "javax.net.ssl.trustStorePassword",
                "changeit"
        );*/

        OpenAIClient client = OpenAIOkHttpClient.builder()
                .apiKey(API_KEY)
                .baseUrl(BASE_URL)
                .build();

        ChatCompletionCreateParams.Builder paramsBuilder =
                ChatCompletionCreateParams.builder()
                        .model("gpt-5.4-nano")
                        .addTool(TestResultTool.class)
                        
                        .addUserMessage(
                                "Get the latest result of the ClockTest test " +
                                "and explain what the result means. " +
                                "Use the available tool."
                        );

                          
        ChatCompletion response =
                client.chat().completions().create(paramsBuilder.build());

        response.choices().stream()
                .map(ChatCompletion.Choice::message)
                .peek(paramsBuilder::addMessage)
                .flatMap(message -> {
                    message.content().ifPresent(System.out::println);
                    return message.toolCalls()
                            .stream()
                            .flatMap(Collection::stream);
                })
                .forEach(toolCall -> {

                    var function = toolCall.asFunction().function();

                    System.out.println("LLM requested tool: " + function.name());

                    String result = switch (function.name()) {
                        case "TestResultTool" ->
                                function.arguments(TestResultTool.class).execute();

                        default ->
                                throw new IllegalArgumentException(
                                        "Unknown function: " + function.name()
                                );
                    };

                    System.out.println("Tool result:");
                    System.out.println(result);

                    paramsBuilder.addMessage(
                            ChatCompletionToolMessageParam.builder()
                                    .toolCallId(toolCall.asFunction().id())
                                    .contentAsJson(result)
                                    .build()
                    );
                });

        ChatCompletion finalResponse =
                client.chat().completions().create(paramsBuilder.build());

        finalResponse.choices().stream()
                .flatMap(choice -> choice.message().content().stream())
                .forEach(content -> {
                    System.out.println("LLM final answer:");
                    System.out.println(content);
                });
    }
}