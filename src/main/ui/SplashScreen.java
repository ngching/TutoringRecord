package ui;

import java.awt.*;

import javax.swing.*;

public class SplashScreen extends Frame {
    private JWindow window;

    public SplashScreen() {
        window = new JWindow();

        window.getContentPane().add(getJLabelImage());

        window.setSize(400, 300);
        window.setLocationRelativeTo(null);
        window.setVisible(true);
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