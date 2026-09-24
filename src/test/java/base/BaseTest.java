package base;

import ai.config.AppConfig;
import org.junit.jupiter.api.BeforeEach;

import java.io.File;

public class BaseTest {

    // Configuration resolves from env vars, JVM system properties, then local.properties.
    public static final String API_KEY = AppConfig.get("VERISOFT_API_KEY");
    public static final String BASE_URL =
            AppConfig.get("VERISOFT_BASE_URL", "https://llm.verisoft.io/v1");

    @BeforeEach
    public void setUp() {
        System.setProperty(
                "javax.net.ssl.trustStore",
                new File(AppConfig.get("VERISOFT_TRUSTSTORE_PATH", "certs/verisoft-truststore.jks"))
                        .getAbsolutePath()
        );

        System.setProperty(
                "javax.net.ssl.trustStorePassword",
                AppConfig.get("VERISOFT_TRUSTSTORE_PASSWORD", "changeit")
        );
    }
}
