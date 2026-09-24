package base;

import org.junit.jupiter.api.BeforeEach;

import java.io.File;

public class BaseTest {


    // Secrets are read from environment variables (VERISOFT_API_KEY) so they are never committed.
    public static final String API_KEY = System.getenv("VERISOFT_API_KEY");
    public static final String BASE_URL =
            System.getenv().getOrDefault("VERISOFT_BASE_URL", "https://llm.verisoft.io/v1");


    @BeforeEach
    public void setUp() {
        System.setProperty(
                "javax.net.ssl.trustStore",
                new File("certs/verisoft-truststore.jks").getAbsolutePath()
        );

        System.setProperty(
                "javax.net.ssl.trustStorePassword",
                "changeit"
        );
    }
}
