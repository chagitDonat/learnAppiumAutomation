package ai.llm;

import ai.config.AppConfig;
import com.openai.client.OpenAIClient;
import com.openai.client.okhttp.OpenAIOkHttpClient;
import com.openai.models.ChatModel;
import com.openai.models.chat.completions.ChatCompletion;
import com.openai.models.chat.completions.ChatCompletionCreateParams;
import com.openai.models.responses.Response;
import com.openai.models.responses.ResponseCreateParams;

public class LlmClient {

        // Configuration resolves from env vars, JVM system properties, then local.properties.
        private static final String API_KEY = AppConfig.require("VERISOFT_API_KEY");
        private static final String BASE_URL =
                AppConfig.get("VERISOFT_BASE_URL", "https://llm.verisoft.io/v1");

        private final OpenAIClient client;

        public LlmClient() {
            client = OpenAIOkHttpClient.builder()
                    .apiKey(API_KEY)
                    .baseUrl(BASE_URL)
                    .build();
        }

        public String ask(String prompt) {

            ChatCompletionCreateParams params =
                    ChatCompletionCreateParams.builder()
                            .model("gpt-5.4-nano")
                            .addUserMessage(prompt)
                            .build();

            ChatCompletion response =
                    client.chat().completions().create(params);

            return response.choices()
                    .get(0)
                    .message()
                    .content()
                    .orElse("");
        }

            public ChatCompletion chat(ChatCompletionCreateParams params) {
        return client.chat().completions().create(params);
    }
    }
