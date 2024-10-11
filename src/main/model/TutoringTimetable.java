package model;

import java.util.ArrayList;

// Represents a list of students
public class TutoringTimetable {
    private ArrayList<Student> students;    // a list of students

    // EFFECTS: constructs an empty student list
    public TutoringTimetable() {
        // stub
    }

    // MODIFIES: this
    // EFFECTS: adds the given student to tutoring timetable
    public void addStudent(Student student) {
        // stub
    }

    // REQUIRES: students.contains(student) is true
    // MODIFIES: this
    // EFFECTS: removes the given student from tutoring timetable
    public void removeStudent(Student student) {
        // stub
    }

    // EFFECTS: returns true if the list is empty, otherwise false
    public boolean isEmpty() {
        return false; // stub
    }

    // EFFECTS: returns the size of the list
    public int getSize() {
        return 0; // stub
    }

    // EFFECTS: returns a list of student
    public ArrayList<Student> getStudents() {
        return new ArrayList<>(); // stub
    }

    // REQUIRES: index >= 0 && index <= ( getSize() - 1 )
    // EFFECTS: returns the student with the given 0-based index from tutoring
    //          timetable
    public Student get(int index) {
        return new Student(null, isEmpty(), index, null); // stub
    }

    // EFFECTS: returns a list of students' names
    public ArrayList<String> getStudentsNames() {
        return new ArrayList<>(); // stub
    }

    // EFFECTS: returns a list of all lessons
    public ArrayList<Lesson> getAllLessons() {
        return new ArrayList<>(); // stub
    }
}
