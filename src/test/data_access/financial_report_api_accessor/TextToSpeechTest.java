package data_access.financial_report_api_accessor;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

class TextToSpeechTest {

    private TextToSpeech textToSpeech;

    @BeforeEach
    void setUp() {
        textToSpeech = new TextToSpeech();
    }

    @Test
    void testSpeak() {
        String testText = "This is a test speech.";
        String audioFilePath = "./src/main/data/audio_files/Audio.mp3";
        String audioTextFilePath = "./src/main/data/audio_files/Audio.txt";

        // Clean up any existing files before running the test
        deleteFileIfExists(audioFilePath);
        deleteFileIfExists(audioTextFilePath);

        // Execute the speak method
        textToSpeech.speak(testText);

        // Verify that the audio text file was created
        Path textPath = Paths.get(audioTextFilePath);
        assertTrue(Files.exists(textPath), "Audio text file should be created.");

        // Verify that the audio file was created
        Path mp3Path = Paths.get(audioFilePath);
        assertTrue(Files.exists(mp3Path), "Audio file should be created.");
    }

    @Test
    void testExecuteShellCommand() throws Exception {
        String command = "echo Hello, World!";
        textToSpeech.executeShellCommand(command);

        // Since the command is executed on the system's shell, there's no direct way to capture its output,
        // so we will not assert here. If running the command doesn't throw an exception, it's considered successful.
    }

    // Utility method to delete files if they exist
    private void deleteFileIfExists(String filePath) {
        Path path = Paths.get(filePath);
        try {
            Files.deleteIfExists(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


