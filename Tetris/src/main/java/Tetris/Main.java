package Tetris;

import Tetris.Keyboard.KeyboardStatus;

public class Main {

    public static void main(String[] args) {

        KeyboardStatus kbStatus = new KeyboardStatus();
        Clock clock = new Clock(16);
        ScoringSystem scoring = new ScoringSystem();
        Playfield playfield = new Playfield();
        GraphicsHandler gfx = new GraphicsHandler(clock, playfield, scoring, kbStatus);
    }
}
