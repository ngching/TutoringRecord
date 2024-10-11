package model;

import java.util.ArrayList;

// Represents a student with name, gender (where true represents male, and false represents female),
// current grade (in the range of 1-12 inclusively representing grade 1-12), subject(s) needed to tutoring in,
// and a list of past lessons with the student
public class Student {
    private String name;                       // student name
    private boolean isMale;                    // student gender (is male or not?)
    private int grade;                         // current grade of student
    private String subjects;                   // subject(s) the student needs tutoring in 
    private ArrayList<Lesson> lessons;         // list of past lessons with the student

    // REQUIRES: grade >= 1 && grade <= 12
    // EFFECTS: constructs a student with the given name, gender, grade, tutoring subject(s),
    //          and an empty list of lessons
    public Student(String name, boolean isMale, int grade, String subjects) {
        // stub
    }

    // MODIFIES: this
    // EFFECTS: adds the given lesson into the list of lessons of the student
    public void addLesson(Lesson lesson) {
        // stub
    }

    // EFFECTS: return if the student is male or female
    public String getGender() {
        return ""; // stub
    }

    public String getName() {
        return ""; // stub
    }

    public boolean getIsMale() {
        return false; // stub
    }

    public int getGrade() {
        return 0; // stub
    }

    public String getSubjects() {
        return ""; // stub
    }

    public ArrayList<Lesson> getLessons() {
        return new ArrayList<>(); // stub
    }
}