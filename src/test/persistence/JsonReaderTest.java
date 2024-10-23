package persistence;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.Lesson;
import model.Student;
import model.TutoringRecord;

public class JsonReaderTest extends JsonTest {
    private ArrayList<Lesson> testLessons1;
    private ArrayList<Lesson> testLessons2;
    private ArrayList<Lesson> testLessons3;
    private ArrayList<Lesson> testLessons4;

    @BeforeEach
    void runBefore() {
        LocalDateTime testStart31 = LocalDateTime.of(2023, 11, 30, 16, 00);
        LocalDateTime testEnd31 = LocalDateTime.of(2023, 11, 30, 17, 00);
        Lesson lesson31 = new Lesson(testStart31, testEnd31, "music");
        lesson31.markAsPaid();
        LocalDateTime testStart32 = LocalDateTime.of(2023, 12, 02, 16, 00);
        LocalDateTime testEnd32 = LocalDateTime.of(2023, 12, 02, 17, 00);
        Lesson lesson32 = new Lesson(testStart32, testEnd32, "music");
        lesson32.markAsPaid();

        LocalDateTime testStart4 = LocalDateTime.of(2024, 03, 03, 14, 30);
        LocalDateTime testEnd4 = LocalDateTime.of(2024, 03, 03, 15, 55);
        Lesson lesson4 = new Lesson(testStart4, testEnd4, "english");

        testLessons1 = new ArrayList<>();
        testLessons2 = new ArrayList<>();
        testLessons3 = new ArrayList<>();
        testLessons3.add(lesson31);
        testLessons3.add(lesson32);
        testLessons4 = new ArrayList<>();
        testLessons4.add(lesson4);
    }

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            TutoringRecord tr = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyTutoringRecord() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyTutoringRecord.json");
        try {
            TutoringRecord tr = reader.read();
            // pass
            assertEquals("Empty Tutoring Record", tr.getName());
            List<Student> students = tr.getStudents();
            assertEquals(0, students.size());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderEmptyLessons() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyLessons.json");
        try {
            TutoringRecord tr = reader.read();
            // pass
            assertEquals("Tutoring Record With No Lessons", tr.getName());
            List<Student> students = tr.getStudents();
            assertEquals(2, students.size());
            checkStudent(students.get(0), "Ashley", false, 11, "econ", testLessons1);
            checkStudent(students.get(1), "John", true, 3, "english", testLessons2);
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralTutoringRecord() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralTutoringRecord.json");
        try {
            TutoringRecord tr = reader.read();
            // pass
            assertEquals("General Tutoring Record", tr.getName());
            List<Student> students = tr.getStudents();
            assertEquals(2, students.size());
            checkStudent(students.get(0), "Sarah", false, 7, "music", testLessons3);
            checkStudent(students.get(1), "David", true, 10, "english", testLessons4);
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}
