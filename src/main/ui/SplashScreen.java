package ui;

import java.awt.*;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JWindow;
import javax.swing.SwingConstants;

public class SplashScreen extends Frame {

    public SplashScreen() {
        JWindow window = new JWindow();

        window.getContentPane().add(getJLabelImage());

        window.setSize(400, 300);
        window.setLocationRelativeTo(null);
        window.setVisible(true);
        try {
            Thread.sleep(2500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        window.setVisible(false);
    }

    public JLabel getJLabelImage() {
        Icon icon = new ImageIcon("image/splash.png");
        JLabel label = new JLabel("", icon, SwingConstants.CENTER);
        return label;
    }
}