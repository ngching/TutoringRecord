package model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.json.JSONObject;

import persistence.Writable;

// Represents a lesson with the selected student including the starting time, ending time,
// the subject taught in the lesson, and the payment status of the lesson
public class Lesson implements Writable {
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
        DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;
        String date = starting.format(formatter).substring(0, 10);
        EventLog.getInstance().logEvent(new Event("Marked as paid: lesson on " + date));
    }

    // MODIFIES: this
    // EFFECTS: marks the lesson as unpaid
    public void markAsUnpaid() {
        this.paymentStatus = false;
        DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;
        String date = starting.format(formatter).substring(0, 10);
        EventLog.getInstance().logEvent(new Event("Marked as unpaid: lesson on " + date));
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

    // EFFECTS: converts the data (in lesson) into a JSON object
    @Override
    public JSONObject toJson() {
        JSONObject jsonLesson = new JSONObject();
        jsonLesson.put("starting time", starting);
        jsonLesson.put("ending time", ending);
        jsonLesson.put("subject", subject);
        jsonLesson.put("payment status", paymentStatus);
        return jsonLesson;
    }
}