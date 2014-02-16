package Tetris;

import Tetris.Keyboard.KeyboardStatus;

public class Main {

    public static void main(String[] args) {

        KeyboardStatus kbStatus = new KeyboardStatus();
        Clock clock = new Clock(16);
        ScoringSystem scoring = new ScoringSystem(1);
        Playfield playfield = new Playfield();
        GraphicsHandler gfx = new GraphicsHandler(playfield, scoring, kbStatus);
    }
}
