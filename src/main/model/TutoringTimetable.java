package model;

import java.util.ArrayList;

// Represents a list of students
public class TutoringTimetable {
    private ArrayList<Student> students; // a list of students

    // EFFECTS: constructs an empty student list
    public TutoringTimetable() {
        students = new ArrayList<>();
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

    // EFFECTS: returns true if the list is empty, false otherwise
    public boolean isEmpty() {
        return students.isEmpty();
    }

    // EFFECTS: returns the size of the list
    public int getSize() {
        return students.size();
    }

    // REQUIRES: index >= 0 && index <= ( getSize() - 1 )
    // EFFECTS: returns the student with the given 0-based index from tutoring
    // timetable
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
}
