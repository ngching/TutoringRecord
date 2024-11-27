package model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import persistence.Writable;

// Represents a student with name, gender (where true represents male, and false represents female),
// current grade (in the range of 1-12 inclusively representing grade 1-12), subject(s) needed to
// tutoring in, and a list of past lessons with the student
public class Student implements Writable {
    private String name; // student name
    private boolean isMale; // student gender (is male or not?)
    private int grade; // current grade of student
    private String subjects; // subject(s) the student needs tutoring in
    private ArrayList<Lesson> lessons; // list of past lessons with the student

    // REQUIRES: grade >= 1 && grade <= 12
    // EFFECTS: constructs a student with the given name, gender, grade, tutoring
    // subject(s), and an empty list of lessons
    public Student(String name, boolean isMale, int grade, String subjects) {
        this.name = name;
        this.isMale = isMale;
        this.grade = grade;
        this.subjects = subjects;
        this.lessons = new ArrayList<>();
    }

    // MODIFIES: this
    // EFFECTS: adds the given lesson into the list of lessons of the student
    public void addLesson(Lesson lesson) {
        lessons.add(lesson);
        LocalDateTime startTime = lesson.getStarting();
        DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;
        String date = startTime.format(formatter).substring(0, 10);
        EventLog.getInstance().logEvent(new Event("Added lesson with " + name + ": on " + date));
    }

    // EFFECTS: return a string showing if the student is a male or a female
    public String getGender() {
        if (isMale) {
            return "male";
        } else {
            return "female";
        }
    }

    public String getName() {
        return name;
    }

    public boolean getIsMale() {
        return isMale;
    }

    public int getGrade() {
        return grade;
    }

    public String getSubjects() {
        return subjects;
    }

    public ArrayList<Lesson> getLessons() {
        return lessons;
    }

    // EFFECTS: converts the data (in student) into a JSON object
    @Override
    public JSONObject toJson() {
        JSONObject jsonStudent = new JSONObject();
        jsonStudent.put("name", name);
        jsonStudent.put("gender (male?)", isMale);
        jsonStudent.put("current grade", grade);
        jsonStudent.put("subject(s)", subjects);
        jsonStudent.put("lessons", lessonsToJson());
        return jsonStudent;
    }

    // EFFECTS: returns lessons as a JSON array
    private JSONArray lessonsToJson() {
        JSONArray jsonLessons = new JSONArray();

        for (Lesson l : lessons) {
            jsonLessons.put(l.toJson());
        }

        return jsonLessons;
    }
}