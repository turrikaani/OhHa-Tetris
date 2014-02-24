package StartMenu;

import javax.swing.SwingUtilities;

public class Main {

    public static void main(String[] args) {

        MainMenuWindow startWindow = new MainMenuWindow();
        SwingUtilities.invokeLater(startWindow);
    }
}
