package StartMenu;

import Tetris.Tetris;

public class GameStarter implements Runnable {

    private int gravityLevel;

    public GameStarter(int gravityLevel) {

        this.gravityLevel = gravityLevel;
    }

    public void run() {

        Tetris tetris = new Tetris(gravityLevel);
        tetris.letsPlay();
    }
}
