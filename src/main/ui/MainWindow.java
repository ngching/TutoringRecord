package ui;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.*;

import model.Event;
import model.EventLog;
import model.TutoringRecord;

// Represents the main window frame of the application
public class MainWindow extends JFrame {
    private static final int WIDTH = 700;
    private static final int HEIGHT = 550;
    private JPanel mainPanel;
    private CardLayout cl;
    private MainMenu mainMenu;
    private TutoringRecord tr;
    private EventLog eventLog;

    // EFFECTS: construct a main window frame of the application
    public MainWindow() {
        super("Tutoring Record");
        // setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle("Tutoring Record");
        setSize(WIDTH, HEIGHT);
        setLocationRelativeTo(null);

        cl = new CardLayout();
        mainPanel = new JPanel(cl);
        tr = new TutoringRecord("null");
        eventLog = EventLog.getInstance();

        addMainMenuToPane();
        add(mainPanel);
        revalidate();
        repaint();
        setVisible(true);
        quitApplication();
    }

    // MODIFIES: this
    // EFFECTS: add the main menu panel to the mainPanel with cardLayout
    public void addMainMenuToPane() {
        mainMenu = new MainMenu(mainPanel, tr);
        mainPanel.add(mainMenu, "MainMenu");
    }

    // EFFECTS: prints log when quitting the application
    public void quitApplication() {
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent event) {
                System.out.println("Event Log: " + "\n");
                for (Event e : eventLog) {
                    System.out.println(e + "\n");
                }
                dispose();
                System.exit(0);
            }
        });
    }
}
