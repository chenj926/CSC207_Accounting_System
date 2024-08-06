package data_access.financial_report_api_accessor;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


public class TextToSpeech {
    public void speak(String text) {
        String message = String.format("{'input':{'text':'%s'},'voice':{'languageCode':'en-gb','name':'en-GB-Standard-A','ssmlGender':'FEMALE'},'audioConfig':{'audioEncoding':'MP3'}}", text);
        String audioFilePath = "./src/main/data/AudioFiles/Audio.mp3";
        String audioTextFilePath = "./src/main/data/AudioFiles/Audio.txt";
        try {
            HttpRequest postRequest = HttpRequest.newBuilder()
                    .uri(new URI("https://texttospeech.googleapis.com/v1/text:synthesize"))
                    .header("X-goog-api-key", "AIzaSyDb_eJPPcBmSHBBxvgzn5xUHsNzSY9bfBk")
                    .header("Content-Type", "application/json; charset=utf-8")
                    .POST(BodyPublishers.ofString(message))
                    .build();
            HttpClient httpclient = HttpClient.newHttpClient();
            HttpResponse<String> postResponse = httpclient.send(postRequest, HttpResponse.BodyHandlers.ofString());

            Gson gson = new Gson();
            JsonObject audioTextJson = gson.fromJson(postResponse.body(), JsonObject.class);

            Path path = Paths.get(audioTextFilePath);
            Files.write(path, audioTextJson.get("audioContent").getAsString().getBytes(StandardCharsets.UTF_8));
            System.out.println("Audio text file written successfully.");

            String decodeCommand = String.format("base64 -D -i %s > %s", audioTextFilePath, audioFilePath);
            executeShellCommand(decodeCommand);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public void executeShellCommand(String command) throws Exception {
        ProcessBuilder processBuilder = new ProcessBuilder();

        // Detect the operating system
        String osName = System.getProperty("os.name").toLowerCase();
        if (osName.contains("win")) {
            processBuilder.command("cmd.exe", "/c", command);
        } else {
            processBuilder.command("sh", "-c", command);
        }

        Process process = processBuilder.start();

        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        String line;
        while ((line = reader.readLine()) != null) {
            System.out.println("Output: " + line);
        }

        BufferedReader errorReader = new BufferedReader(new InputStreamReader(process.getErrorStream()));
        while ((line = errorReader.readLine()) != null) {
            System.err.println("Error: " + line);
        }

        int exitCode = process.waitFor();
        System.out.println("Exited with code: " + exitCode);
    }
}




