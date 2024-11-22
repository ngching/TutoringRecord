package ui;

import java.awt.*;

import javax.swing.*;

import model.TutoringRecord;

// Represents the main window frame of the application
public class MainWindow extends JFrame {
    private static final int WIDTH = 700;
    private static final int HEIGHT = 550;
    private JPanel mainPanel;
    private CardLayout cl;
    private MainMenu mainMenu;
    private TutoringRecord tr;

    // EFFECTS: construct a main window frame of the application
    public MainWindow() {
        super("Tutoring Record");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle("Tutoring Record");
        setSize(WIDTH, HEIGHT);
        setLocationRelativeTo(null);

        cl = new CardLayout();
        mainPanel = new JPanel(cl);
        tr = new TutoringRecord("null");

        addMainMenuToPane();
        add(mainPanel);
        revalidate();
        repaint();
        setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: add the main menu panel to the mainPanel with cardLayout
    public void addMainMenuToPane() {
        mainMenu = new MainMenu(mainPanel, tr);
        mainPanel.add(mainMenu, "MainMenu");
    }
}
