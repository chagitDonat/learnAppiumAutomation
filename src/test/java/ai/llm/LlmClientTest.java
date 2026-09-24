package ai.llm;

import ai.config.AppConfig;
import base.BaseTest;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class LlmClientTest extends BaseTest {

    @Test
    void testLlm() {
        LlmClient llmClient = new LlmClient();

        String response = llmClient.ask(
                //"In one short sentence, explain what an API is."
                "Which day is tody?"
        );

        System.out.println("******************* Llm Response: " + response + "**************");
    }


    private static final String API_KEY = AppConfig.get("VERISOFT_API_KEY");

    //@Test
    void getModels() throws Exception {


        System.setProperty(
                "javax.net.ssl.trustStore",
                new File(AppConfig.get("VERISOFT_TRUSTSTORE_PATH", "certs/verisoft-truststore.jks"))
                        .getAbsolutePath()
        );

        System.setProperty(
                "javax.net.ssl.trustStorePassword",
                AppConfig.get("VERISOFT_TRUSTSTORE_PASSWORD", "changeit")
        );

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://llm.verisoft.io/v1/models"))
                .header("Authorization", "Bearer " + API_KEY)
                .GET()
                .build();

        HttpResponse<String> response =
                HttpClient.newHttpClient()
                        .send(request, HttpResponse.BodyHandlers.ofString());

        System.out.println("STATUS: " + response.statusCode());
        System.out.println(response.body());
    }

}
