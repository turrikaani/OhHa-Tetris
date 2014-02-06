package Tetris;

import Tetris.DataTypes.*;
import Tetris.GUI.*;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import javax.swing.SwingUtilities;

public class GraphicsHandler {

    private Clock clock;
    private Playfield playfield;
    private ScoringSystem scoringSystem;
    private Graphics2D painter;
    private CustomPanel frontBuffer;

    public GraphicsHandler(Clock c, Playfield p, ScoringSystem s, KeyListener k) {

        this.clock = c;
        this.playfield = p;
        this.scoringSystem = s;

        TetrisGUI gui = new TetrisGUI();
        SwingUtilities.invokeLater(gui);

        do {
            this.clock.waitAbsolute(100);
            this.painter = gui.getPainter();
        }
        while (this.painter == null);

        this.frontBuffer = gui.getFrontBuffer();
        gui.addKeyListener(k);
        initializeGameWindow();
    }

    private void initializeGameWindow() {
    }

    public void updateScoresAndStats() {
    }

    public void updateFPS(double fps) {
    }

    public void updatePlayfieldContents(Tetromino t) {
    }

    public void animateRowClears(ArrayList<Integer> rowsToClear) {
    }

    public void showPauseScreen() {
    }

    public void showGameOverScreen() {
    }

    private void drawBlock(int xCoord, int yCoord, BlockType type) {
    }

    private void fillBackgroundWithColor(Color c) {
    }
}
