package StartMenu;

import Tetris.Tetris;
import javax.swing.SwingUtilities;

public class Main {

    public static void main(String[] args) {

        MainMenuWindow  startWindow = new MainMenuWindow();
        SwingUtilities.invokeLater(startWindow);
        // Tetris tetris = new Tetris(4);
        // tetris.letsPlay();
    }
}
