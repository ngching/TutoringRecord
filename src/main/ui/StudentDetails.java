package ui;

import java.awt.*;
import java.awt.event.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import model.Lesson;
import model.Student;

// Represents the panel of the application when displaying all the infomration of the selected student
public class StudentDetails extends JPanel implements ActionListener {
    private JPanel mainP;
    private CardLayout cl;
    private Student selected;
    private JTable lessonTable;
    private Object[][] lessonData;

    // EFFECTS: construct a panel showing the details of the selected students
    public StudentDetails(JPanel mainPanel, Student student) {
        super();
        setBackground(Color.LIGHT_GRAY);
        setLayout(new BorderLayout(10, 5));
        setBorder(new EmptyBorder(40, 60, 20, 60));

        this.mainP = mainPanel;
        cl = (CardLayout) mainPanel.getLayout();
        this.selected = student;

        JPanel details = showDetails();
        JPanel lessons = showLessons();

        JSplitPane split = new JSplitPane(SwingConstants.HORIZONTAL, details, lessons);
        add(split, BorderLayout.CENTER);

        JPanel buttons = addButtons();
        add(buttons, BorderLayout.SOUTH);
    }

    // EFFECTS: return a panel diplaying the information of the selected student
    public JPanel showDetails() {
        JPanel panel = new JPanel(new GridLayout(5, 0, 0, 2));
        panel.setBorder(new EmptyBorder(30, 30, 10, 30));

        JLabel name = new JLabel("Name: " + selected.getName());
        JLabel gender = new JLabel("Gender: " + selected.getGender());
        JLabel grade = new JLabel("Grade: " + selected.getGrade());
        JLabel subject = new JLabel("Tutoring Subject(s): " + selected.getSubjects());
        String lessonLabel = "Lessons:                      (all time in 24-hour clock)";
        JLabel lesson = new JLabel(lessonLabel);

        panel.add(name);
        panel.add(gender);
        panel.add(grade);
        panel.add(subject);
        panel.add(lesson);

        return panel;
    }

    // EFFECTS: return a panel displaying all the lessons in a scrollable table
    public JPanel showLessons() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(new EmptyBorder(20, 30, 20, 30));

        showTable();
        JScrollPane scoller = new JScrollPane(lessonTable);
        panel.add(scoller);

        return panel;
    }

    // EFFECTS: get a table displaying all the lesssons with the selected student
    public void showTable() {
        String[] columnNames = { "Date (YYYY-MM-DD)",
                "Starting Time",
                "Ending Time",
                "Paid?" };

        getLessonsDetails();

        lessonTable = new JTable(lessonData, columnNames) {
            public boolean isCellEditable(int row, int col) {
                return false;
            }
        };
    }

    // EFFECTS: convert lessons details into Object[][]
    public void getLessonsDetails() {
        ArrayList<Lesson> lessons = selected.getLessons();
        int size = lessons.size();
        lessonData = new Object[size][];

        for (int i = 0; i < size; i++) {
            Object[] lesson = getLessonDetails(lessons, i);
            lessonData[i] = lesson;
        }
    }

    // EFFECTS: return a object array that contains the details of a lesson
    public Object[] getLessonDetails(ArrayList<Lesson> lessons, int index) {
        Lesson les = lessons.get(index);
        LocalDateTime startTime = les.getStarting();
        LocalDateTime endTime = les.getEnding();
        DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;

        String date = startTime.format(formatter).substring(0, 10);
        String start = startTime.format(formatter).substring(11, 16);
        String end = endTime.format(formatter).substring(11, 16);
        boolean paid = les.getPaymentStatus();
        
        Object[] lesson = { date, start, end, paid };
        return lesson;
    }

    // EFFECTS: return a panel with different functionable buttons
    public JPanel addButtons() {
        JPanel buttons = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 0));
        buttons.setBackground(Color.LIGHT_GRAY);

        JButton back = new JButton("Back");
        back.setActionCommand("backToViewStudentNames");
        back.addActionListener(this);
        buttons.add(back);

        JButton paid = new JButton("Mark Lesson As Paid");
        paid.setActionCommand("PaidLesson");
        paid.addActionListener(this);
        buttons.add(paid);

        JButton unpaid = new JButton("Mark Lesson As Unpaid");
        unpaid.setActionCommand("UnpaidLesson");
        unpaid.addActionListener(this);
        buttons.add(unpaid);

        JButton add = new JButton("Add Lesson");
        add.setActionCommand("AddLesson");
        add.addActionListener(this);
        buttons.add(add);

        return buttons;
    }

    @Override
    // MODIFIES: this
    // EFFECTS: handle the action when different buttons are clicked
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("backToViewStudentNames")) {
            cl.show(mainP, "ViewStudentNames");
        } else if (e.getActionCommand().equals("PaidLesson")) {
            markPaidLesson();
            JPanel viewDetailPanel = new StudentDetails(mainP, selected);
            mainP.add(viewDetailPanel, "ViewStudentDetails");
            cl.show(mainP, "ViewStudentDetails");
        } else if (e.getActionCommand().equals("UnpaidLesson")) {
            markUnpaidLesson();
            JPanel viewDetailPanel = new StudentDetails(mainP, selected);
            mainP.add(viewDetailPanel, "ViewStudentDetails");
            cl.show(mainP, "ViewStudentDetails");
        } else if (e.getActionCommand().equals("AddLesson")) {
            JPanel addLessonPanel = new AddLesson(mainP, selected);
            mainP.add(addLessonPanel, "AddLesson");
            cl.show(mainP, "AddLesson");
        }
    }

    // MODIFIES: this
    // EFFECTS: mark the selected lesson as paid
    public void markPaidLesson() {
        int index = lessonTable.getSelectedRow();
        ArrayList<Lesson> lessons = selected.getLessons();
        Lesson lesson = lessons.get(index);
        lesson.markAsPaid();
    }

    // MODIFIES: this
    // EFFECTS: mark the selected lesson as unpaid
    public void markUnpaidLesson() {
        int index = lessonTable.getSelectedRow();
        ArrayList<Lesson> lessons = selected.getLessons();
        Lesson lesson = lessons.get(index);
        lesson.markAsUnpaid();
    }
}
