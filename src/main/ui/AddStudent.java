package ui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Locale;

import javax.swing.*;

import model.Student;
import model.TutoringRecord;

// Represents the panel of the application when adding student
public class AddStudent extends JPanel implements ActionListener {
    private JPanel mainP;
    private CardLayout cl;
    private TutoringRecord record;
    private Student student;
    private JTextField nameTextBox;
    private JTextField genderTextBox;
    private JTextField gradeTextBox;
    private JTextField subjectTextBox;

    // EFFECTS: construct a panel to add students to the record
    public AddStudent(JPanel mainPanel, TutoringRecord tr) {
        super();
        setBackground(Color.LIGHT_GRAY);
        setLayout(new BorderLayout(10, 5));

        this.mainP = mainPanel;
        cl = (CardLayout) mainPanel.getLayout();
        this.record = tr;

        JPanel heading = addHeading();
        add(heading, BorderLayout.NORTH);

        JPanel main = addComponent();
        add(main, BorderLayout.CENTER);

        JPanel buttons = addButtons();
        add(buttons, BorderLayout.SOUTH);
    }

    // EFFECTS: return a panel with the heading of the screen
    public JPanel addHeading() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 30));
        panel.setBackground(Color.LIGHT_GRAY);

        JLabel heading = new JLabel("Please enter the details of the student:", JLabel.LEFT);
        heading.setFont(new Font("Serif", Font.PLAIN, 20));
        panel.add(heading);

        return panel;
    }

    // EFFECTS: return a panel including all the text fields to input information
    public JPanel addComponent() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 0, 5, 20));
        panel.setBackground(Color.LIGHT_GRAY);

        panel.add(addName());
        panel.add(addGender());
        panel.add(addGrade());
        panel.add(addSubject());

        return panel;
    }

    // MODIFIES: this
    // EFFECTS: return a panel including a text field for name
    public JPanel addName() {
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.CENTER, 30, 0));
        panel.setBackground(Color.LIGHT_GRAY);

        JLabel name = new JLabel("Name: ");
        name.setFont(new Font(null));
        panel.add(name);

        nameTextBox = new JTextField(25);
        panel.add(nameTextBox);

        return panel;
    }

    // MODIFIES: this
    // EFFECTS: return a panel including a text field for gender
    public JPanel addGender() {
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.CENTER));
        panel.setBackground(Color.LIGHT_GRAY);

        JLabel gender = new JLabel("Gender (male? female?): ");
        gender.setFont(new Font(null));
        panel.add(gender);

        genderTextBox = new JTextField(20);
        panel.add(genderTextBox);

        return panel;
    }

    // MODIFIES: this
    // EFFECTS: return a panel including a text field for grade
    public JPanel addGrade() {
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.CENTER));
        panel.setBackground(Color.LIGHT_GRAY);

        JLabel grade = new JLabel("Current Grade (1-12?): ");
        grade.setFont(new Font(null));
        panel.add(grade);

        gradeTextBox = new JTextField(20);
        panel.add(gradeTextBox);

        return panel;
    }

    // MODIFIES: this
    // EFFECTS: return a panel including a text field for subject
    public JPanel addSubject() {
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.CENTER));
        panel.setBackground(Color.LIGHT_GRAY);

        JLabel subject = new JLabel("Tutoring Subject(s): ");
        subject.setFont(new Font(null));
        panel.add(subject);

        subjectTextBox = new JTextField(20);
        subjectTextBox.setFont(new Font(null));
        panel.add(subjectTextBox);

        return panel;
    }

    // EFFECTS: return a panel with functionable buttons
    public JPanel addButtons() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 30));
        panel.setBackground(Color.LIGHT_GRAY);

        JButton back = new JButton("Back");
        back.setActionCommand("backToMainMenu");
        back.addActionListener(this);
        panel.add(back);

        JButton done = new JButton("Done");
        done.setActionCommand("addStudent");
        done.addActionListener(this);
        panel.add(done);

        return panel;
    }

    @Override
    // MODIFIES: this
    // EFFECTS: handle the action when the the buttons are clicked
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("backToMainMenu")) {
            reset();
            cl.show(mainP, "MainMenu");
        } else if (e.getActionCommand().equals("addStudent")) {
            addStudentAction();
            reset();
            cl.show(mainP, "MainMenu");
        }
    }

    // MODIFIES: this
    // EFFECTS: reset all the text fields to empty
    public void reset() {
        nameTextBox.setText("");
        genderTextBox.setText("");
        gradeTextBox.setText("");
        subjectTextBox.setText("");
    }

    // EFFECTS: return a student according to the inputs
    public Student getStudent() {
        String name = nameTextBox.getText();
        String gender = genderTextBox.getText();
        String grade = gradeTextBox.getText();
        String subject = subjectTextBox.getText();

        Locale english = Locale.forLanguageTag("en");
        boolean isMale = gender.toLowerCase(english).equals("male");
        int gradeInt = Integer.parseInt(grade);

        Student s = new Student(name, isMale, gradeInt, subject);
        return s;
    }

    // MODIFIES: this
    // EFFECTS add the inputed student to the record
    public void addStudentAction() {
        student = getStudent();
        record.addStudent(student);
        JOptionPane.showMessageDialog(mainP, "Student added!");
    }
}