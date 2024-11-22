package ui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import model.Student;
import model.TutoringRecord;

// Represents the panel of the application when displaying all the students names
public class StudentNames extends JPanel implements ActionListener {
    private JPanel mainP;
    private CardLayout cl;
    private TutoringRecord record;
    private Student selected;
    private JList<String> nameList;

    // EFFECTS: construct a panel showing the names of all students in the record
    public StudentNames(JPanel mainPanel, TutoringRecord tr) {
        super();
        setBackground(Color.LIGHT_GRAY);
        setLayout(new BorderLayout(10, 5));

        this.mainP = mainPanel;
        cl = (CardLayout) mainPanel.getLayout();
        this.record = tr;

        JPanel heading = addHeading();
        add(heading, BorderLayout.NORTH);

        JPanel names = addNames();
        add(names);

        JPanel buttons = addButtons();
        add(buttons, BorderLayout.SOUTH);
    }

    // EFFECTS: return a panel with a heading of the screen
    public JPanel addHeading() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 30));
        panel.setBackground(Color.LIGHT_GRAY);

        JLabel heading = new JLabel("Select a student to edit or view his/her details:", JLabel.LEFT);
        heading.setFont(new Font("Serif", Font.PLAIN, 20));
        panel.add(heading);

        return panel;
    }

    // MODIFIES: this
    // EFFECTS: return a panel that shows all the names in the nameList
    public JPanel addNames() {
        JPanel panel = new JPanel();
        panel.setBackground(Color.LIGHT_GRAY);
        setBorder(new EmptyBorder(20, 50, 20, 50));

        ArrayList<String> names = record.getStudentsNames();
        String[] stuNames = stringArray(names);
        nameList = new JList<>(stuNames);
        nameList.setFixedCellWidth(340);

        JScrollPane scoller = new JScrollPane(nameList);
        panel.add(scoller);

        return panel;
    }

    // EFFECTS: return a string array of names according to the given arraylist
    public String[] stringArray(ArrayList<String> names) {
        int size = names.size();
        String[] str = new String[size];

        for (int i = 0; i < size; i++) {
            str[i] = names.get(i);
        }
        return str;
    }

    // EFFECTS: return a panel with different functionable buttons
    public JPanel addButtons() {
        JPanel buttons = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 30));
        buttons.setBackground(Color.LIGHT_GRAY);

        JButton back = new JButton("Back");
        back.setActionCommand("backToMainMenu");
        back.addActionListener(this);
        buttons.add(back);

        JButton done = new JButton("Select");
        done.setActionCommand("SelectStudent");
        done.addActionListener(this);
        buttons.add(done);

        JButton remove = new JButton("Remove");
        remove.setActionCommand("RemoveStudent");
        remove.addActionListener(this);
        buttons.add(remove);

        return buttons;
    }

    @Override
    // MODIFIES: this
    // EFFECTS: handle the action when different buttons are clicked
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("backToMainMenu")) {
            cl.show(mainP, "MainMenu");
        } else if (e.getActionCommand().equals("SelectStudent")) {
            selected = getSelectedStudent();
            JPanel viewDetailPanel = new StudentDetails(mainP, selected);
            mainP.add(viewDetailPanel, "ViewStudentDetails");
            cl.show(mainP, "ViewStudentDetails");
        } else if (e.getActionCommand().equals("RemoveStudent")) {
            selected = getSelectedStudent();
            record.removeStudent(selected);
            JPanel viewNamePanel = new StudentNames(mainP, record);
            mainP.add(viewNamePanel, "ViewStudentNames");
            cl.show(mainP, "ViewStudentNames");
        }
    }

    // EFFECTS: return the selected student from the nameList
    public Student getSelectedStudent() {
        int i = nameList.getSelectedIndex();
        ArrayList<Student> students = record.getStudents();
        return students.get(i);
    }
}
