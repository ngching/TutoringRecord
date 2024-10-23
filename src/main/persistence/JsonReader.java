package persistence;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import org.json.JSONArray;
import org.json.JSONObject;

import model.Lesson;
import model.Student;
import model.TutoringRecord;

// code reference to the sample (JsonSeriallizationDemo)
// Represents a reader that reads tutor record from JSON data to file
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        // stub
    }

    // EFFECTS: reads tutor record from file and returns it,
    // throws IOException if an error occurs reading data from file
    public TutoringRecord read() throws IOException {
        return new TutoringRecord(); // stub
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        return ""; // stub
    }

    // EFFECTS: parses tutor record from JSON object and returns it
    private TutoringRecord parseTutorRecord(JSONObject jsonObject) {
        return new TutoringRecord(); // stub
    }

    // MODIFIES: tr
    // EFFECTS: parses students from JSON object and adds them to tutor record
    private void addStudents(TutoringRecord tr, JSONObject jsonObject) {
        // stub
    }

    // MODIFIES: tr
    // EFFECTS: parses student from JSON object and adds it to tutor record
    private void addStudent(TutoringRecord tr, JSONObject jsonObject) {
        // stub
    }

    // EFFECTS: parses lessons from JSON object
    private List<Lesson> parseLessonsFromJson(JSONObject jsonObject) {
        return new ArrayList<>(); // stub
    }

    // EFFECTS: parses lesson from JSON object
    private Lesson parseLessonFromJson(JSONObject jsonObject) {
        LocalDateTime time = LocalDateTime.of(2024, 10, 10, 10, 00);
        return new Lesson(time, time, ""); // stub
    }
}
