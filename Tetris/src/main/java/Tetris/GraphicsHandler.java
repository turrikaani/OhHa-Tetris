package Tetris;

import Tetris.DataTypes.*;
import Tetris.GUI.*;
import Tetris.Keyboard.KeyboardStatus;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import javax.swing.SwingUtilities;

public class GraphicsHandler {

    private Playfield playfield;
    private ScoringSystem scoringSystem;
    private TetrisGUI gui;
    private Graphics2D painter;
    private CustomPanel frontBuffer;
    private ResourceLoader resourceLoader;

    private BufferedImage backgroundImage;
    private BufferedImage pauseScreen;
    private BufferedImage gameOverScreen;
    private BufferedImage[] blockImages;
    private BufferedImage[] tetrominoImages;

    public GraphicsHandler(Playfield p, ScoringSystem s, KeyboardStatus k) {

        this.playfield = p;
        this.scoringSystem = s;

        gui = new TetrisGUI();
        SwingUtilities.invokeLater(gui);

        do {
            try {Thread.sleep(100);}
            catch (InterruptedException e) {}
            this.painter = gui.getPainter();
        }
        while (this.painter == null);

        this.frontBuffer = gui.getFrontBuffer();
        gui.addKeyListener(k);

        this.resourceLoader = new ResourceLoader();
        initializeGameWindow();
    }

    private void initializeGameWindow() {

        painter.setFont(resourceLoader.loadEmbeddedFont("/fonts/lucon.ttf"));
        backgroundImage = resourceLoader.loadEmbeddedImage("/images/background.png", 960, 640);
        pauseScreen = resourceLoader.loadEmbeddedImage("/images/pausescreen.png", 270, 540);
        gameOverScreen = resourceLoader.loadEmbeddedImage("/images/gameoverscreen.png", 179, 69);

        blockImages = new BufferedImage[8];
        blockImages[0] = resourceLoader.loadEmbeddedImage("/images/block_I.png", 26, 26);
        blockImages[1] = resourceLoader.loadEmbeddedImage("/images/block_J.png", 26, 26);
        blockImages[2] = resourceLoader.loadEmbeddedImage("/images/block_L.png", 26, 26);
        blockImages[3] = resourceLoader.loadEmbeddedImage("/images/block_O.png", 26, 26);
        blockImages[4] = resourceLoader.loadEmbeddedImage("/images/block_S.png", 26, 26);
        blockImages[5] = resourceLoader.loadEmbeddedImage("/images/block_T.png", 26, 26);
        blockImages[6] = resourceLoader.loadEmbeddedImage("/images/block_Z.png", 26, 26);
        blockImages[7] = resourceLoader.loadEmbeddedImage("/images/block_E.png", 26, 26);

        tetrominoImages = new BufferedImage[7];
        tetrominoImages[0] = resourceLoader.loadEmbeddedImage("/images/tetromino_I.png", 73, 37);
        tetrominoImages[1] = resourceLoader.loadEmbeddedImage("/images/tetromino_J.png", 73, 37);
        tetrominoImages[2] = resourceLoader.loadEmbeddedImage("/images/tetromino_L.png", 73, 37);
        tetrominoImages[3] = resourceLoader.loadEmbeddedImage("/images/tetromino_O.png", 73, 37);
        tetrominoImages[4] = resourceLoader.loadEmbeddedImage("/images/tetromino_S.png", 73, 37);
        tetrominoImages[5] = resourceLoader.loadEmbeddedImage("/images/tetromino_T.png", 73, 37);
        tetrominoImages[6] = resourceLoader.loadEmbeddedImage("/images/tetromino_Z.png", 73, 37);

        painter.drawImage(backgroundImage, 0, 0, null);
        frontBuffer.repaint();
    }

    public void updateScore() {

        StringBuilder formatter = new StringBuilder();
        String s = String.format("%09d", scoringSystem.getScore());
        formatter.append(s, 0, 3).append(" ");
        formatter.append(s, 3, 6).append(" ");
        formatter.append(s, 6, 9);

        clearArea(56, 78, 148, 34);
        painter.drawString(formatter.toString(), 65, 102);
        frontBuffer.repaint(56, 78, 148, 34);
    }

    public void updateLineStats() {

        clearArea(132, 216, 74, 180);
        painter.drawString(String.format("%03d", scoringSystem.getSingleLineClears()), 137, 236);
        painter.drawString(String.format("%03d", scoringSystem.getDoubleLineClears()), 137, 274);
        painter.drawString(String.format("%03d", scoringSystem.getTripleLineClears()), 137, 312);
        painter.drawString(String.format("%03d", scoringSystem.getQuadrupleLineClears()), 137, 350);
        painter.drawString(String.format("%03d", scoringSystem.getTotalLinesCleared()), 137, 388);
        frontBuffer.repaint(132, 216, 74, 180);
    }

    public void updateGravityLevel() {

        clearArea(163, 479, 34, 22);
        painter.drawString(String.format("%02d", scoringSystem.getGravityLevel()), 167, 497);
        frontBuffer.repaint(163, 479, 34, 22);
    }

    public void updateFPS(double fps) {

        clearArea(128, 567, 80, 23);
        painter.drawString(String.format("%04.1f", fps), 132, 586);
        frontBuffer.repaint(128, 567, 80, 23);
    }

    public void updateNextTetromino(TetrominoType type) {

        int index = getIndexByTetrominoType(type);
        painter.drawImage(tetrominoImages[index], 794, 79, null);
        frontBuffer.repaint(794, 79, 73, 37);
    }

    public void updateTetrominoStats() {

        clearArea(850, 235, 68, 354);
        painter.drawString(String.format("%03d", scoringSystem.getNumTetrominoes_I()), 854, 254);
        painter.drawString(String.format("%03d", scoringSystem.getNumTetrominoes_J()), 854, 297);
        painter.drawString(String.format("%03d", scoringSystem.getNumTetrominoes_L()), 854, 346);
        painter.drawString(String.format("%03d", scoringSystem.getNumTetrominoes_O()), 854, 395);
        painter.drawString(String.format("%03d", scoringSystem.getNumTetrominoes_S()), 854, 444);
        painter.drawString(String.format("%03d", scoringSystem.getNumTetrominoes_T()), 854, 493);
        painter.drawString(String.format("%03d", scoringSystem.getNumTetrominoes_Z()), 854, 542);
        painter.drawString(String.format("%03d", scoringSystem.getTotalNumberOfTetrominoes()), 854, 585);
        frontBuffer.repaint(850, 235, 68, 354);
    }

    public void clearPlayfield() {
        clearArea(346, 50, 270, 540);
    }

    public void updatePlayfieldContents(Tetromino t) {

        for (int y=0; y<20; y++) {
            for (int x=0; x<10; x++) {
                drawBlock(x, y, playfield.getCellType(x, y));
            }
        }

        drawTetromino(t);
        frontBuffer.repaint(346, 50, 270, 540);
    }

    public void updateWholeScreen() {
        frontBuffer.repaint();
    }

    public void drawTetromino(Tetromino t) {

        if (t == null) return;

        Coordinate[] blockCoordinates = t.getBlockCoordinates();
        BlockType type = t.getBlockType();

        for (int i=0; i<4; i++) {
            if (blockCoordinates[i].x < 0 || blockCoordinates[i].x >= 10) continue;
            if (blockCoordinates[i].y < 0 || blockCoordinates[i].y >= 20) continue;
            drawBlock(blockCoordinates[i].x, blockCoordinates[i].y, type);
        }
    }

    public void drawLockingTetromino(Tetromino t) {

        if (t == null) return;

        Coordinate[] blockCoordinates = t.getBlockCoordinates();

        for (int i=0; i<4; i++) {
            if (blockCoordinates[i].x < 0 || blockCoordinates[i].x >= 10) continue;
            if (blockCoordinates[i].y < 0 || blockCoordinates[i].y >= 20) continue;
            drawLockingBlock(blockCoordinates[i].x, blockCoordinates[i].y);
        }
    }

    public void animateOneStepOfRowClearAnimation(int stepNumber, List<Integer> rowsToBeCleared) {

        for (int i : rowsToBeCleared) {
            if (i%2 == 0) drawBlock(stepNumber, i, BlockType.EMPTY);
            else drawBlock(9-stepNumber, i, BlockType.EMPTY);
        }
        frontBuffer.repaint(346, 50, 270, 540);
    }

    public void showPauseScreen() {
        painter.drawImage(this.pauseScreen, 346, 50, null);
        frontBuffer.repaint(346, 50, 270, 540);
    }

    public void showGameOverScreen() {
        painter.drawImage(this.gameOverScreen, 391, 231, null);
        frontBuffer.repaint(391, 231, 179, 69);
    }

    public void closeWindow() {
        gui.closeWindow();
    }

    private void clearArea(int upperLeftX, int upperLeftY, int width, int height) {

        painter.setColor(Color.BLACK);
        painter.fillRect(upperLeftX, upperLeftY, width, height);
        painter.setColor(Color.WHITE);
    }

    private void drawBlock(int x, int y, BlockType type) {

        int index = getIndexByBlockType(type);
        painter.drawImage(blockImages[index], 346+27*x, 563-27*y, null);
    }

    private void drawLockingBlock(int x, int y) {

        painter.setColor(Color.WHITE);
        painter.fillRect(346+27*x, 563-27*y, 26, 26);
    }

    private int getIndexByBlockType(BlockType type) {

        switch (type) {

            case I : return 0;
            case J : return 1;
            case L : return 2;
            case O : return 3;
            case S : return 4;
            case T : return 5;
            case Z : return 6;
            default: return 7;
        }
    }

    private int getIndexByTetrominoType(TetrominoType type) {

        switch (type) {

            case I : return 0;
            case J : return 1;
            case L : return 2;
            case O : return 3;
            case S : return 4;
            case T : return 5;
            default: return 6;
        }
    }

    public void fillBackgroundWithColor(Color c) {

        painter.setColor(c);
        painter.fillRect(  0,   0,  24, 640);
        painter.fillRect(235,   0,  78, 640);
        painter.fillRect(648,   0,  77, 640);
        painter.fillRect(936,   0,  24, 640);
        painter.fillRect( 24,   0, 211,  20);
        painter.fillRect( 24, 140, 211,   9);
        painter.fillRect( 24, 425, 211,  10);
        painter.fillRect( 24, 529, 211,  10);
        painter.fillRect( 24, 620, 211,  20);
        painter.fillRect(313,   0, 335,  18);
        painter.fillRect(313, 622, 335,  18);
        painter.fillRect(725,   0, 211,  20);
        painter.fillRect(725, 153, 211,   9);
        painter.fillRect(725, 620, 211,  20);
        frontBuffer.repaint();
    }
}
