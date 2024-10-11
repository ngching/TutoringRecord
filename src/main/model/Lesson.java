package model;

import java.time.LocalDateTime;

// Represents a lesson with the selected student including the starting time, ending time,
// the subject taught in the lesson, and the payment status of the lesson
public class Lesson {
    private LocalDateTime starting;         // the starting time of the lesson
    private LocalDateTime ending;           // the ending time of the lesson
    private String subject;                 // the subject taught in the lesson
    private boolean paymentStatus;          // the payment status of the lesson

    // REQUIRES: ending is after starting
    // EFFECTS: constructs an unpaid lesson with the given date, time, and subject
    public Lesson(LocalDateTime starting, LocalDateTime ending, String subject) {
        // stub
    }

    // MODIFIES: this
    // EFFECTS: marks the lesson as paid
    public void markedAsPaid() {
        // stub
    }

    // MODIFIES: this
    // EFFECTS: marks the lesson as unpaid
    public void markedAsUnpaid() {
        // stub
    }

    public LocalDateTime getStarting() {
        return LocalDateTime.of(0, 0, 0, 0, 0); // stub
    }

    public LocalDateTime getEnding() {
        return LocalDateTime.of(0, 0, 0, 0, 0); // stub
    }

    public String getSubject() {
        return ""; // stub
    }

    public boolean getPaymentStatus() {
        return false; // stub
    }
}