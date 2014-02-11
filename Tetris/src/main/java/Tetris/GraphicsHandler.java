package Tetris;

import Tetris.DataTypes.*;
import Tetris.GUI.*;
import Tetris.Keyboard.KeyboardStatus;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import javax.swing.SwingUtilities;

public class GraphicsHandler {

    private Clock clock;
    private Playfield playfield;
    private ScoringSystem scoringSystem;
    private ResourceLoader resourceLoader;
    private Graphics2D painter;
    private CustomPanel frontBuffer;

    public GraphicsHandler(Clock c, Playfield p, ScoringSystem s, KeyboardStatus k) {

        this.clock = c;
        this.playfield = p;
        this.scoringSystem = s;
        this.resourceLoader = new ResourceLoader();

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
        this.painter.setColor(Color.BLACK);
        this.painter.fillRect(0, 0, 960, 640);
        this.painter.setColor(Color.GREEN);
        this.painter.fillRect(920, 600, 40, 40);
        this.painter.setColor(Color.WHITE);
        this.painter.setFont(this.resourceLoader.loadEmbeddedFont("/fonts/lucon.ttf"));


        BufferedImage testImg = this.resourceLoader.loadEmbeddedImage("/images/L_large.png", 26, 26);
        long lasttime = System.nanoTime();
        for (int x=23; x>=0; x--) {
            for (int y=9; y>=0; y--) {
                this.painter.drawImage(testImg, 3+27*x, 3+27*y, null);
            }
        }
        //this.frontBuffer.repaint();
        long currtime = System.nanoTime();
        System.out.println((currtime-lasttime)/1000);
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
