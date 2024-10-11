package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class StudentTest {
    private Student testMaleStudent;
    private Student testFemaleStudent;
    private Lesson testLesson1;
    private Lesson testLesson2;

    @BeforeEach
    void runBefore() {
        testMaleStudent = new Student("Peter", true, 10, "math, phys");
        testFemaleStudent = new Student("Amy", false, 12, "biol");

        LocalDateTime testStarting1 = LocalDateTime.of(2024, 8, 12, 10, 00);
        LocalDateTime testEnding1 = LocalDateTime.of(2024, 8, 12, 11, 00);
        testLesson1 = new Lesson(testStarting1, testEnding1, "math");

        LocalDateTime testStarting2 = LocalDateTime.of(2024, 9, 1, 20, 00);
        LocalDateTime testEnding2 = LocalDateTime.of(2024, 9, 1, 21, 30);
        testLesson2 = new Lesson(testStarting2, testEnding2, "phys");
    }

    @Test
    void testConstructor() {
        assertEquals("Peter", testMaleStudent.getName());
        assertTrue(testMaleStudent.getIsMale());
        assertEquals(10, testMaleStudent.getGrade());
        assertEquals("math, phys", testMaleStudent.getSubjects());
        assertTrue(testMaleStudent.getLessons().isEmpty());
    }

    @Test
    void testAddLesson() {
        testMaleStudent.addLesson(testLesson1);
        ArrayList<Lesson> testlessons = testMaleStudent.getLessons();
        assertEquals(1, testlessons.size());
        assertEquals(testLesson1, testlessons.get(0));
    }

    @Test
    void testAddMutlipleLessons() {
        testMaleStudent.addLesson(testLesson1);
        testMaleStudent.addLesson(testLesson2);
        ArrayList<Lesson> testlessons = testMaleStudent.getLessons();
        assertEquals(2, testlessons.size());
        assertEquals(testLesson1, testlessons.get(0));
        assertEquals(testLesson2, testlessons.get(1));
    }

    @Test
    void testGetGender() {
        assertEquals("male", testMaleStudent.getGender());
        assertEquals("female", testFemaleStudent.getGender());
    }
}
