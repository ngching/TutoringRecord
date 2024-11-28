package ui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import model.TutoringRecord;
import persistence.JsonReader;
import persistence.JsonWriter;

// Represents the panel of the main menu of the application
public class MainMenu extends JPanel implements ActionListener {
    public static final String JSON_STORE = "./data/tutoringRecord.json";
    public final JsonWriter jsonWriter = new JsonWriter(JSON_STORE);
    public final JsonReader jsonReader = new JsonReader(JSON_STORE);
    private JPanel mainP;
    private CardLayout cl;
    private TutoringRecord record;
    private Button addStudentButton;
    private Button viewStudentsButton;
    private Button saveButton;
    private Button loadButton;

    // EFFECTS: constructs a main menu panel containing the main menu of the
    // application
    public MainMenu(JPanel mainPanel, TutoringRecord tr) {
        super();
        setLayout(new BorderLayout(0, 20));
        setBackground(Color.LIGHT_GRAY);
        this.mainP = mainPanel;
        cl = (CardLayout) mainPanel.getLayout();
        this.record = tr;

        JPanel title = addTitle();
        add(title, BorderLayout.NORTH);

        JPanel image = addImage();
        add(image, BorderLayout.CENTER);

        JPanel panel = setupButton();
        add(panel, BorderLayout.SOUTH);
    }

    // EFFECTS: return a panel with a title
    public JPanel addTitle() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(1, 0, 0, 0));
        panel.setBorder(new EmptyBorder(30, 10, 0, 10));
        panel.setBackground(Color.LIGHT_GRAY);

        JLabel title = new JLabel("Welcome!", JLabel.CENTER);
        title.setFont(new Font("Serif", Font.BOLD, 33));
        panel.add(title);

        return panel;
    }

    // EFFECTS: set up a GridLayout panel
    public JPanel setupButton() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 0, 0, 10));
        panel.setBorder(new EmptyBorder(0, 150, 10, 150));
        panel.setBackground(Color.LIGHT_GRAY);

        addComponent(panel);
        return panel;
    }

    // EFFECTS: add different components to the given panel
    public void addComponent(Container pane) {
        addStudentButton = addButton();
        pane.add(addStudentButton);

        viewStudentsButton = viewButton();
        pane.add(viewStudentsButton);

        saveButton = saveButton();
        pane.add(saveButton);

        loadButton = loadButton();
        pane.add(loadButton);
    }

    // EFFECTS: return a button to add student
    public Button addButton() {
        Button button = new Button("Add a student");
        button.setActionCommand("addStudent");
        button.addActionListener(this);
        return button;
    }

    // EFFECTS: return a button to view students
    public Button viewButton() {
        Button button = new Button("View all students");
        button.setActionCommand("viewStudents");
        button.addActionListener(this);
        return button;
    }

    // EFFECTS: return a button to save the record to json file
    public Button saveButton() {
        Button button = new Button("Save the record");
        button.setActionCommand("saveData");
        button.addActionListener(this);
        return button;
    }

    // EFFECTS: return a panel with an image
    public JPanel addImage() {
        JPanel panel = new JPanel();
        panel.setBackground(Color.LIGHT_GRAY);
        Icon icon = new ImageIcon("assets/main.png");
        JLabel label = new JLabel(icon, SwingConstants.CENTER);
        panel.add(label);
        return panel;
    }

    // EFFECTS: return a button to load the record from json file
    public Button loadButton() {
        Button button = new Button("Load the record");
        button.setActionCommand("loadData");
        button.addActionListener(this);
        return button;
    }

    @Override
    // MODIFIES: this
    // EFFECTS: handle the action when the different buttons are clicked
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("addStudent")) {
            JPanel addStuPanel = new AddStudent(mainP, record);
            mainP.add(addStuPanel, "AddStudent");
            cl.show(mainP, "AddStudent");
            addStudentButton.setVisible(false);
        } else if (e.getActionCommand().equals("viewStudents")) {
            JPanel viewNamePanel = new StudentsNames(mainP, record);
            mainP.add(viewNamePanel, "ViewStudentNames");
            cl.show(mainP, "ViewStudentNames");
            viewStudentsButton.setVisible(false);
        } else if (e.getActionCommand().equals("saveData")) {
            saveTutoringRecord();
        } else if (e.getActionCommand().equals("loadData")) {
            loadTutoringRecord();
        }
    }

    // code based on the sample (JsonSeriallizationDemo)
    // EFFECTS: display a Saving Data message and saves the record to file
    private void saveTutoringRecord() {
        JOptionPane.showMessageDialog(mainP, "Data saved!");
        try {
            jsonWriter.open();
            jsonWriter.write(record);
            jsonWriter.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    // code based on the sample (JsonSeriallizationDemo)
    // MODIFIES: this
    // EFFECTS: display a Loading Data message and loads the record from file
    private void loadTutoringRecord() {
        JOptionPane.showMessageDialog(mainP, "Data loaded!");
        try {
            record = jsonReader.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
