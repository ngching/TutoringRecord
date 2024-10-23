package persistence;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.Lesson;
import model.Student;
import model.TutoringRecord;

import static org.junit.jupiter.api.Assertions.*;

public class JsonWriterTest extends JsonTest {
    private Student testStudent1;
    private Student testStudent2;
    private Student testStudent3;
    private Student testStudent4;
    private ArrayList<Lesson> testLessons1;
    private ArrayList<Lesson> testLessons2;
    private ArrayList<Lesson> testLessons3;
    private ArrayList<Lesson> testLessons4;

    @SuppressWarnings("methodlength")
    @BeforeEach
    void runBefore() {
        LocalDateTime testStart31 = LocalDateTime.of(2024, 8, 10, 10, 00);
        LocalDateTime testEnd31 = LocalDateTime.of(2024, 8, 10, 11, 00);
        Lesson lesson31 = new Lesson(testStart31, testEnd31, "english");
        lesson31.markAsPaid();
        LocalDateTime testStart32 = LocalDateTime.of(2024, 8, 20, 12, 00);
        LocalDateTime testEnd32 = LocalDateTime.of(2024, 8, 20, 13, 30);
        Lesson lesson32 = new Lesson(testStart32, testEnd32, "french");

        LocalDateTime testStart4 = LocalDateTime.of(2024, 10, 10, 20, 00);
        LocalDateTime testEnd4 = LocalDateTime.of(2024, 10, 10, 21, 45);
        Lesson lesson4 = new Lesson(testStart4, testEnd4, "math");
        lesson4.markAsPaid();

        testStudent1 = new Student("Ben", true, 8, "math");
        testStudent2 = new Student("Mary", false, 12, "phys, chem");
        testStudent3 = new Student("Peter", true, 6, "english, french");
        testStudent3.addLesson(lesson31);
        testStudent3.addLesson(lesson32);
        testStudent4 = new Student("Sonia", false, 9, "math");
        testStudent4.addLesson(lesson4);

        testLessons1 = new ArrayList<>();
        testLessons2 = new ArrayList<>();
        testLessons3 = new ArrayList<>();
        testLessons3.add(lesson31);
        testLessons3.add(lesson32);
        testLessons4 = new ArrayList<>();
        testLessons4.add(lesson4);
    }

    @Test
    void testWriterInvalidFile() {
        try {
            TutoringRecord tr = new TutoringRecord("My Tutoring Record");
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileNameERROR.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // expected
        }
    }

    @Test
    void testWriterEmptyTutoringRecord() {
        TutoringRecord tr = new TutoringRecord("Empty Tutoring Record");
        JsonWriter writer = new JsonWriter("./data/testWriterEmptyTutoringRecord.json");
        try {
            writer.open();
            writer.write(tr);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyTutoringRecord.json");
            tr = reader.read();
            // pass
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
        assertEquals("Empty Tutoring Record", tr.getName());
        assertEquals(0, tr.getSize());
        assertEquals(0, tr.getAllLessons().size());
    }

    @Test
    void testWriterEmptyLessons() {
        TutoringRecord tr = new TutoringRecord("Tutoring Record With No Lessons");
        tr.addStudent(testStudent1);
        tr.addStudent(testStudent2);
        JsonWriter writer = new JsonWriter("./data/testWriterEmptyLessons.json");
        try {
            writer.open();
            writer.write(tr);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyLessons.json");
            tr = reader.read();
            // expected
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
        assertEquals("Tutoring Record With No Lessons", tr.getName());
        List<Student> students = tr.getStudents();
        assertEquals(2, students.size());
        checkStudent(students.get(0), "Ben", true, 8, "math", testLessons1);
        checkStudent(students.get(1), "Mary", false, 12, "phys, chem", testLessons2);
    }

    @Test
    void testWriterGeneralTutoringRecord() {
        TutoringRecord tr = new TutoringRecord("General Tutoring Record");
        tr.addStudent(testStudent3);
        tr.addStudent(testStudent4);
        JsonWriter writer = new JsonWriter("./data/testWriterGeneralTutoringRecord.json");
        try {
            writer.open();
            writer.write(tr);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralTutoringRecord.json");
            tr = reader.read();
            // expected
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
        assertEquals("General Tutoring Record", tr.getName());
        List<Student> students = tr.getStudents();
        assertEquals(2, students.size());
        checkStudent(students.get(0), "Peter", true, 6, "english, french", testLessons3);
        checkStudent(students.get(1), "Sonia", false, 9, "math", testLessons4);
    }
}
