package model;

import java.time.LocalDateTime;

// Represents a lesson with the selected student including the starting time, ending time,
// the subject taught in the lesson, and the payment status of the lesson
public class Lesson {
    private LocalDateTime starting; // the starting time of the lesson
    private LocalDateTime ending; // the ending time of the lesson
    private String subject; // the subject taught in the lesson
    private boolean paymentStatus; // the payment status of the lesson

    // REQUIRES: ending is after starting
    // EFFECTS: constructs an unpaid lesson with the given date, time, and subject
    public Lesson(LocalDateTime starting, LocalDateTime ending, String subject) {
        this.starting = starting;
        this.ending = ending;
        this.subject = subject;
        this.paymentStatus = false;
    }

    // MODIFIES: this
    // EFFECTS: marks the lesson as paid
    public void markAsPaid() {
        this.paymentStatus = true;
    }

    // MODIFIES: this
    // EFFECTS: marks the lesson as unpaid
    public void markAsUnpaid() {
        this.paymentStatus = false;
    }

    public LocalDateTime getStarting() {
        return starting;
    }

    public LocalDateTime getEnding() {
        return ending;
    }

    public String getSubject() {
        return subject;
    }

    public boolean getPaymentStatus() {
        return paymentStatus;
    }
}