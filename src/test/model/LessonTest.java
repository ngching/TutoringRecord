package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;

public class LessonTest {
    private Lesson testLesson;
    private LocalDateTime testStarting;
    private LocalDateTime testEnding;

    @BeforeEach
    void runBefore() {
        testStarting = LocalDateTime.of(2024, 9, 25, 16, 00);
        testEnding = LocalDateTime.of(2024, 9, 25, 17, 30);
        testLesson = new Lesson(testStarting, testEnding, "math");
    }

    @Test
    void testConstructor() {
        assertEquals(testStarting,testLesson.getStarting());
        assertEquals(testEnding,testLesson.getEnding());
        assertEquals("math",testLesson.getSubject());
        assertFalse(testLesson.getPaymentStatus());
    }

    @Test
    void testMarkedAsPaid() {
        testLesson.markedAsPaid();
        assertTrue(testLesson.getPaymentStatus());
        testLesson.markedAsPaid();
        assertTrue(testLesson.getPaymentStatus());
    }

    @Test
    void testMarkedAsUnpaid() {
        testLesson.markedAsUnpaid();
        assertFalse(testLesson.getPaymentStatus());
        testLesson.markedAsUnpaid();
        assertFalse(testLesson.getPaymentStatus());
    }
}
