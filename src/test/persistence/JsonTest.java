package persistence;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import model.Lesson;
import model.Student;

public class JsonTest {

    protected void checkStudent(Student s, String name, Boolean isMale, int grade,
            String subjects, ArrayList<Lesson> lessons) {
        assertEquals(name, s.getName());
        assertEquals(isMale, s.getIsMale());
        assertEquals(grade, s.getGrade());
        assertEquals(subjects, s.getSubjects());
        int index = 0;
        for (Lesson l : s.getLessons()) {
            Lesson expected = lessons.get(index);
            assertEquals(expected.getStarting(), l.getStarting());
            assertEquals(expected.getEnding(), l.getEnding());
            assertEquals(expected.getSubject(), l.getSubject());
            assertEquals(expected.getPaymentStatus(), l.getPaymentStatus());
            index++;
        }
    }
}
