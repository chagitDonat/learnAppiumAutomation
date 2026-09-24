package ai.skills;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class TestAnalysisSkill {

    public String getInstructions() {
        try (InputStream inputStream =
                     getClass()
                             .getClassLoader()
                             .getResourceAsStream("skills/test-analysis.md")) {

            if (inputStream == null) {
                throw new IllegalStateException(
                        "Skill file not found: skills/test-analysis.md"
                );
            }

            return new String(
                    inputStream.readAllBytes(),
                    StandardCharsets.UTF_8
            );

        } catch (IOException e) {
            throw new RuntimeException("Failed to read Skill file", e);
        }
    }
}