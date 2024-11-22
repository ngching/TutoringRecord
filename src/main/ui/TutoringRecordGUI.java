package ui;

import javax.swing.SwingUtilities;

public class TutoringRecordGUI {

    public static void main(String[] args) {
        new SplashScreen();
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MainWindow();
            }
        });
    }
}
