package ui;

import java.awt.*;
import java.awt.event.*;
import java.time.LocalDateTime;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import model.Lesson;
import model.Student;

// Represents the panel of the application when adding lesson to the selected student
public class AddLesson extends JPanel implements ActionListener {
    private JPanel mainP;
    private CardLayout cl;
    private Student selected;
    private JTextField yearTextBox;
    private JTextField monthTextBox;
    private JTextField dayTextBox;
    private JTextField startHrTextBox;
    private JTextField startMinTextBox;
    private JTextField endHrTextBox;
    private JTextField endMinTextBox;

    // EFFECTS: construct a panel to add lesson to the given student
    public AddLesson(JPanel mainPanel, Student student) {
        super();
        setBackground(Color.LIGHT_GRAY);
        setLayout(new BorderLayout(10, 5));

        this.mainP = mainPanel;
        cl = (CardLayout) mainPanel.getLayout();
        this.selected = student;

        JPanel heading = addHeading();
        add(heading, BorderLayout.NORTH);

        JPanel main = addComponent();
        add(main, BorderLayout.CENTER);

        JPanel buttons = addButtons();
        add(buttons, BorderLayout.SOUTH);
    }

    // EFFECTS: return a panel with a heading of the screen
    public JPanel addHeading() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 30));
        panel.setBackground(Color.LIGHT_GRAY);

        JLabel heading = new JLabel("Please enter the details of the lesson:", JLabel.LEFT);
        heading.setFont(new Font("Serif", Font.PLAIN, 25));
        panel.add(heading);

        return panel;
    }

    // EFFECTS: return a panel including all the text fields to input information
    public JPanel addComponent() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(7, 0, 5, 15));
        panel.setBorder(new EmptyBorder(30, 30, 10, 30));
        panel.setBackground(Color.LIGHT_GRAY);

        panel.add(addYear());
        panel.add(addMonth());
        panel.add(addDay());
        panel.add(addStartHour());
        panel.add(addStartMinute());
        panel.add(addEndHour());
        panel.add(addEndMinute());

        return panel;
    }

    // MODIFIES: this
    // EFFECTS: return a panel including a text field for year
    public JPanel addYear() {
        JPanel panel = new JPanel();
        panel.setBackground(Color.LIGHT_GRAY);

        JLabel year = new JLabel("Year: ");
        year.setFont(new Font(null));
        panel.add(year);

        yearTextBox = new JTextField(20);
        panel.add(yearTextBox);

        return panel;
    }

    // MODIFIES: this
    // EFFECTS: return a panel including a text field for month
    public JPanel addMonth() {
        JPanel panel = new JPanel();
        panel.setBackground(Color.LIGHT_GRAY);

        JLabel month = new JLabel("Month: ");
        month.setFont(new Font(null));
        panel.add(month);

        monthTextBox = new JTextField(20);
        panel.add(monthTextBox);

        return panel;
    }

    // MODIFIES: this
    // EFFECTS: return a panel including a text field for day
    public JPanel addDay() {
        JPanel panel = new JPanel();
        panel.setBackground(Color.LIGHT_GRAY);

        JLabel day = new JLabel("Day: ");
        day.setFont(new Font(null));
        panel.add(day);

        dayTextBox = new JTextField(20);
        panel.add(dayTextBox);

        return panel;
    }

    // MODIFIES: this
    // EFFECTS: return a panel including a text field for starting hour
    public JPanel addStartHour() {
        JPanel panel = new JPanel();
        panel.setBackground(Color.LIGHT_GRAY);

        JLabel startHr = new JLabel("Starting Hour: ");
        startHr.setFont(new Font(null));
        panel.add(startHr);

        startHrTextBox = new JTextField(16);
        panel.add(startHrTextBox);

        return panel;
    }

    // MODIFIES: this
    // EFFECTS: return a panel including a text field for starting minute
    public JPanel addStartMinute() {
        JPanel panel = new JPanel();
        panel.setBackground(Color.LIGHT_GRAY);

        JLabel startMin = new JLabel("Starting Minute: ");
        startMin.setFont(new Font(null));
        panel.add(startMin);

        startMinTextBox = new JTextField(16);
        panel.add(startMinTextBox);

        return panel;
    }

    // MODIFIES: this
    // EFFECTS: return a panel including a text field for ending hour
    public JPanel addEndHour() {
        JPanel panel = new JPanel();
        panel.setBackground(Color.LIGHT_GRAY);

        JLabel endHr = new JLabel("Ending Hour: ");
        endHr.setFont(new Font(null));
        panel.add(endHr);

        endHrTextBox = new JTextField(17);
        panel.add(endHrTextBox);

        return panel;
    }

    // MODIFIES: this
    // EFFECTS: return a panel including a text field for ending minute
    public JPanel addEndMinute() {
        JPanel panel = new JPanel();
        panel.setBackground(Color.LIGHT_GRAY);

        JLabel endMin = new JLabel("Ending Minute: ");
        endMin.setFont(new Font(null));
        panel.add(endMin);

        endMinTextBox = new JTextField(16);
        panel.add(endMinTextBox);

        return panel;
    }

    // EFFECTS: return a panel with different functionable buttons
    public JPanel addButtons() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 30));
        panel.setBackground(Color.LIGHT_GRAY);

        JButton back = new JButton("Back");
        back.setActionCommand("backToStudentDetails");
        back.addActionListener(this);
        panel.add(back);

        JButton done = new JButton("Done");
        done.setActionCommand("addLesson");
        done.addActionListener(this);
        panel.add(done);

        return panel;
    }

    @Override
    // MODIFIES: this
    // EFFECTS: handle the action when the the buttons are clicked
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("backToStudentDetails")) {
            cl.show(mainP, "ViewStudentDetails");
        } else if (e.getActionCommand().equals("addLesson")) {
            addLessonToStudent();
            JPanel viewDetailPanel = new StudentDetails(mainP, selected);
            mainP.add(viewDetailPanel, "ViewStudentDetails");
            cl.show(mainP, "ViewStudentDetails");
        }
    }

    // MODIFIES: this
    // EFFECTS: reset all the text fields
    public void reset() {
        yearTextBox.setText("");
        monthTextBox.setText("");
        dayTextBox.setText("");
        startHrTextBox.setText("");
        startMinTextBox.setText("");
        endHrTextBox.setText("");
        endMinTextBox.setText("");
    }

    // EFFECTS: return a lesson according to the inputs
    public Lesson getLesson() {
        int year = Integer.parseInt(yearTextBox.getText());
        int month = Integer.parseInt(monthTextBox.getText());
        int day = Integer.parseInt(dayTextBox.getText());
        int startHour = Integer.parseInt(startHrTextBox.getText());
        int startMin = Integer.parseInt(startMinTextBox.getText());
        int endHour = Integer.parseInt(endHrTextBox.getText());
        int endMin = Integer.parseInt(endMinTextBox.getText());
        String subject = "subject";

        LocalDateTime start = LocalDateTime.of(year, month, day, startHour, startMin);
        LocalDateTime end = LocalDateTime.of(year, month, day, endHour, endMin);

        Lesson l = new Lesson(start, end, subject);
        return l;
    }

    // MODIFIES: this
    // EFFECTS: add the lesson to the selected student
    public void addLessonToStudent() {
        Lesson lesson = getLesson();
        selected.addLesson(lesson);
    }
}