package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class TutoringTimetableTest {
    private TutoringTimetable testStudents;
    private Student testStudent1;
    private Student testStudent2;
    private Lesson testLesson1;
    private Lesson testLesson2;
    private Lesson testLesson3;
    
    @BeforeEach
    void runBefore() {
        testStudents = new TutoringTimetable();

        testStudent1 = new Student("Peter", true, 10, "math, phys");
        testStudent2 = new Student("Amy", false, 12, "biol");

        LocalDateTime testStarting1 = LocalDateTime.of(2023, 10, 1, 16, 0);
        LocalDateTime testEnding1 = LocalDateTime.of(2023, 10, 1, 18, 0);
        testLesson1 = new Lesson(testStarting1, testEnding1, "phys");
        LocalDateTime testStarting2 = LocalDateTime.of(2024, 10, 2, 12, 15);
        LocalDateTime testEnding2 = LocalDateTime.of(2024, 10, 2, 13, 45);
        testLesson2 = new Lesson(testStarting2, testEnding2, "biol");
        LocalDateTime testStarting3 = LocalDateTime.of(2024, 10, 10, 8, 0);
        LocalDateTime testEnding3 = LocalDateTime.of(2024, 10, 10, 9, 30);
        testLesson3 = new Lesson(testStarting3, testEnding3, "math");
    }

    @Test
    void testConstructor() {
        assertTrue(testStudents.isEmpty());
    }

    @Test
    void testAddStudent() {
        testStudents.addStudent(testStudent1);
        assertEquals(1, testStudents.getSize());
        assertEquals(testStudent1, testStudents.get(0));
    }

    @Test
    void testAddMutlipleStudents() {
        testStudents.addStudent(testStudent1);
        testStudents.addStudent(testStudent2);
        assertEquals(2, testStudents.getSize());
        assertEquals(testStudent1, testStudents.get(0));
        assertEquals(testStudent2, testStudents.get(1));
    }

    @Test
    void testRemoveStudent() {
        testStudents.addStudent(testStudent1);
        testStudents.addStudent(testStudent2);
        assertEquals(2, testStudents.getSize());

        testStudents.removeStudent(testStudent1);
        assertEquals(1, testStudents.getSize());
    }

    @Test
    void testRemoveMultipleStudents() {
        testStudents.addStudent(testStudent1);
        testStudents.addStudent(testStudent2);
        assertEquals(2, testStudents.getSize());
        
        testStudents.removeStudent(testStudent1);
        testStudents.removeStudent(testStudent2);
        assertTrue(testStudents.isEmpty());
    }

    @Test
    void testGetStudents() {
        testStudents.addStudent(testStudent1);
        testStudents.addStudent(testStudent2);
        ArrayList<Student> students = testStudents.getStudents();

        assertEquals(2, students.size());
        assertEquals(testStudent1, students.get(0));
        assertEquals(testStudent2, students.get(1));
    }

    @Test
    void testGetStudentsNames() {
        testStudents.addStudent(testStudent1);
        testStudents.addStudent(testStudent2);
        ArrayList<String> studentsNames = testStudents.getStudentsNames();

        assertEquals(2, studentsNames.size());
        assertEquals("Peter", studentsNames.get(0));
        assertEquals("Amy", studentsNames.get(1));
    }

    @Test
    void testGetAllLessons() {
        testStudent1.addLesson(testLesson1);
        testStudent1.addLesson(testLesson2);
        testStudent2.addLesson(testLesson3);
        testStudents.addStudent(testStudent1);
        testStudents.addStudent(testStudent2);
        ArrayList<Lesson> studentsLessons = testStudents.getAllLessons();

        assertEquals(3, studentsLessons.size());
        assertEquals(testLesson1, studentsLessons.get(0));
        assertEquals(testLesson2, studentsLessons.get(1));
        assertEquals(testLesson3, studentsLessons.get(2));
    }
}
