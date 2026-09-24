package ai.config;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Properties;

/**
 * Simple configuration resolver for this plain (non-Spring) Maven project.
 *
 * <p>Values are looked up in the following order (first non-blank value wins):
 * <ol>
 *     <li>Environment variable</li>
 *     <li>JVM system property (e.g. {@code -DVERISOFT_API_KEY=...})</li>
 *     <li>Properties files named {@code application.properties} or {@code local.properties}
 *         (loaded from the classpath and from the project working directory)</li>
 * </ol>
 *
 * <p>Secrets should live in {@code local.properties}, which is git-ignored.
 * See {@code local.properties.example} for a template.
 */
public final class AppConfig {

    private static final Properties PROPERTIES = new Properties();

    static {
        // Loaded in order; later loads override earlier ones so that
        // local.properties takes precedence over application.properties.
        loadFromClasspath("application.properties");
        loadFromFile("application.properties");
        loadFromClasspath("local.properties");
        loadFromFile("local.properties");
    }

    private AppConfig() {
    }

    /**
     * Returns the configured value or {@code null} when the key is not set anywhere.
     */
    public static String get(String key) {
        return get(key, null);
    }

    /**
     * Returns the configured value, or {@code defaultValue} when the key is not set anywhere.
     */
    public static String get(String key, String defaultValue) {
        String value = System.getenv(key);
        if (isBlank(value)) {
            value = System.getProperty(key);
        }
        if (isBlank(value)) {
            value = PROPERTIES.getProperty(key);
        }
        return isBlank(value) ? defaultValue : value.trim();
    }

    /**
     * Returns the configured value, or throws a descriptive exception when it is missing.
     */
    public static String require(String key) {
        String value = get(key);
        if (value == null) {
            throw new IllegalStateException(
                    "Missing required configuration '" + key + "'. Provide it as an environment "
                            + "variable, a JVM system property (-D" + key + "=...), or in "
                            + "local.properties (copy local.properties.example).");
        }
        return value;
    }

    private static boolean isBlank(String value) {
        return value == null || value.isBlank();
    }

    private static void loadFromClasspath(String resource) {
        try (InputStream in = AppConfig.class.getClassLoader().getResourceAsStream(resource)) {
            if (in != null) {
                PROPERTIES.load(in);
            }
        } catch (IOException e) {
            // An absent or unreadable optional file must not break startup.
        }
    }

    private static void loadFromFile(String file) {
        Path path = Path.of(file);
        if (Files.isRegularFile(path)) {
            try (InputStream in = Files.newInputStream(path)) {
                PROPERTIES.load(in);
            } catch (IOException e) {
                // An absent or unreadable optional file must not break startup.
            }
        }
    }
}
