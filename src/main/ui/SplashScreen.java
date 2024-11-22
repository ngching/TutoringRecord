package ui;

import java.awt.*;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JWindow;
import javax.swing.SwingConstants;

public class SplashScreen extends Frame {
    private JWindow window;

    // EFFECTS: create a splash screen when starting the application
    public SplashScreen() {
        window = new JWindow();
        window.setSize(400, 300);
        window.setLocationRelativeTo(null);
        window.setVisible(true);
        window.getContentPane().add(getJLabelImage());
        autoClose();
    }

    // EFFECTS: return the image from folder "assets"
    public JLabel getJLabelImage() {
        Icon icon = new ImageIcon("assets/splash.png");
        JLabel label = new JLabel("", icon, SwingConstants.CENTER);
        return label;
    }

    // MODIFIES: this
    // EFFECTS: automatically close the screen after a few seconds
    public void autoClose() {
        try {
            Thread.sleep(2500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        window.dispose();
    }
}