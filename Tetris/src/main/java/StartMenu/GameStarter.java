package StartMenu;

import Tetris.Tetris;

public class GameStarter implements Runnable {

    private MainMenuWindow mainMenuWindow;
    private int gravityLevel;

    public GameStarter(int gravityLevel, MainMenuWindow wnd) {

        this.gravityLevel = gravityLevel;
        this.mainMenuWindow = wnd;
    }

    public void run() {

        Tetris tetris = new Tetris(gravityLevel, mainMenuWindow);
        tetris.letsPlay();
    }
}
