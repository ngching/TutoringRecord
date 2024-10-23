package model;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import persistence.Writable;

// Represents a list of students
public class TutoringRecord implements Writable {
    private String name;
    private ArrayList<Student> students; // a list of students

    // EFFECTS: constructs an empty student list
    public TutoringRecord(String name) {
        this.name = name;
        this.students = new ArrayList<>();
    }

    // MODIFIES: this
    // EFFECTS: adds the given student to tutoring timetable
    public void addStudent(Student student) {
        students.add(student);
    }

    // REQUIRES: students.contains(student) is true
    // MODIFIES: this
    // EFFECTS: removes the given student from tutoring timetable
    public void removeStudent(Student student) {
        students.remove(student);
    }

    // EFFECTS: returns true if the students list is empty, false otherwise
    public boolean isEmpty() {
        return students.isEmpty();
    }

    // EFFECTS: returns the size of the students
    public int getSize() {
        return students.size();
    }

    // REQUIRES: index >= 0 && index <= ( getSize() - 1 )
    // EFFECTS: returns the student with the given 0-based index from the students
    // list
    public Student get(int index) {
        return students.get(index);
    }

    // EFFECTS: returns a list of student
    public ArrayList<Student> getStudents() {
        return students;
    }

    // EFFECTS: returns a list of students' names
    public ArrayList<String> getStudentsNames() {
        ArrayList<String> name = new ArrayList<>();

        for (Student s : students) {
            name.add(s.getName());
        }
        return name;
    }

    // EFFECTS: returns a list of all lessons
    public ArrayList<Lesson> getAllLessons() {
        ArrayList<Lesson> lessons = new ArrayList<>();

        for (Student s : students) {
            lessons.addAll(s.getLessons());
        }
        return lessons;
    }

    // EFFECTS: returns name
    public String getName() {
        return ""; // stub
    }

    // EFFECTS: converts the data (in tutoring record) into a JSON object
    @Override
    public JSONObject toJson() {
        return new JSONObject(); // stub
    }

    // EFFECTS: returns students as a JSON array
    private JSONArray studentsToJson() {
        return new JSONArray(); // stub
    }
}
