package ai.llm;

import com.openai.client.OpenAIClient;
import com.openai.client.okhttp.OpenAIOkHttpClient;
import com.openai.models.ChatModel;
import com.openai.models.chat.completions.ChatCompletion;
import com.openai.models.chat.completions.ChatCompletionCreateParams;
import com.openai.models.responses.Response;
import com.openai.models.responses.ResponseCreateParams;

public class LlmClient {

        // Secrets are read from environment variables so they are never committed.
        // Set VERISOFT_API_KEY before running. Optionally override VERISOFT_BASE_URL.
        private static final String API_KEY = requireEnv("VERISOFT_API_KEY");
        private static final String BASE_URL =
                System.getenv().getOrDefault("VERISOFT_BASE_URL", "https://llm.verisoft.io/v1");

        private final OpenAIClient client;

        public LlmClient() {
            client = OpenAIOkHttpClient.builder()
                    .apiKey(API_KEY)
                    .baseUrl(BASE_URL)
                    .build();
        }

        private static String requireEnv(String name) {
            String value = System.getenv(name);
            if (value == null || value.isBlank()) {
                throw new IllegalStateException(
                        "Missing required environment variable '" + name + "'. "
                                + "Set it in your shell (e.g. set " + name + "=your-key) "
                                + "or in your IDE run configuration before starting.");
            }
            return value;
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
