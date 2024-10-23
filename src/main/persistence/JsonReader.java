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
// import model.TutoringRecord;
import model.TutoringRecord;

// code reference to the sample (JsonSeriallizationDemo)
// Represents a reader that reads tutor record from JSON data to file
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads tutor record from file and returns it,
    // throws IOException if an error occurs reading data from file
    public TutoringRecord read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseTutorRecord(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses tutor record from JSON object and returns it
    private TutoringRecord parseTutorRecord(JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        TutoringRecord tr = new TutoringRecord(name);
        addStudents(tr, jsonObject);
        return tr;
    }

    // MODIFIES: tr
    // EFFECTS: parses students from JSON object and adds them to tutor record
    private void addStudents(TutoringRecord tr, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("students");
        for (Object json : jsonArray) {
            JSONObject nextStudent = (JSONObject) json;
            addStudent(tr, nextStudent);
        }
    }

    // MODIFIES: tr
    // EFFECTS: parses student from JSON object and adds it to tutor record
    private void addStudent(TutoringRecord tr, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        Boolean gender = jsonObject.getBoolean("gender (male?)");
        int grade = jsonObject.getInt("current grade");
        String subjects = jsonObject.getString("subject(s)");

        Student student = new Student(name, gender, grade, subjects);
        List<Lesson> lessons = parseLessonsFromJson(jsonObject);
        for (Lesson l : lessons) {
            student.addLesson(l);
        }

        tr.addStudent(student);
    }

    // MODIFIES: tr
    // EFFECTS: parses lessons from JSON object (and adds them to tutor record)
    private List<Lesson> parseLessonsFromJson(JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("lessons");
        List<Lesson> lessons = new ArrayList<>();
        for (Object json : jsonArray) {
            JSONObject nextLesson = (JSONObject) json;
            lessons.add(parseLessonFromJson(nextLesson));
        }
        return lessons;
    }

    // MODIFIES: tr
    // EFFECTS: parses lesson from JSON object (and adds them to tutor record)
    private Lesson parseLessonFromJson(JSONObject jsonObject) {
        String start = jsonObject.getString("starting time");
        LocalDateTime starting = LocalDateTime.parse(start);
        String end = jsonObject.getString("ending time");
        LocalDateTime ending = LocalDateTime.parse(end);
        String subject = jsonObject.getString("subject");

        Lesson lesson = new Lesson(starting, ending, subject);
        if (jsonObject.getBoolean("payment status")) {
            lesson.markAsPaid();
        }
        return lesson;

    }
}
