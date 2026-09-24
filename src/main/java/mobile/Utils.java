package mobile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Utils {

    /**
     *
     * running adb command
     *
     * @param command
     * @return
     * @throws IOException
     */
    public static String runAdbCommand(String command) throws IOException {
        ProcessBuilder processBuilder = new ProcessBuilder("adb", "shell", command);
        processBuilder.redirectErrorStream(true);
        Process process = processBuilder.start();

        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        StringBuilder output = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            output.append(line).append("\n");
        }
        return output.toString().trim();
    }

}
