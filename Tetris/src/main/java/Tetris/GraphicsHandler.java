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

    private Graphics2D painter;
    private CustomPanel frontBuffer;

    private ResourceLoader resourceLoader;
    private BufferedImage background;
    private BufferedImage pauseScreen;
    private BufferedImage gameOverScreen;
    private BufferedImage[] blockImages;
    private BufferedImage[] tetrominoImages;

    public GraphicsHandler(Playfield p, ScoringSystem s, KeyboardStatus k) {

        this.playfield = p;
        this.scoringSystem = s;

        TetrisGUI gui = new TetrisGUI();
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
        this.blockImages = new BufferedImage[7];
        this.tetrominoImages = new BufferedImage[7];
        initializeGameWindow();
    }

    private void initializeGameWindow() {

        this.painter.setFont(this.resourceLoader.loadEmbeddedFont("/fonts/lucon.ttf"));
        this.background = this.resourceLoader.loadEmbeddedImage("/images/background2.png", 960, 640);
        this.pauseScreen = this.resourceLoader.loadEmbeddedImage("/images/pausescreen.png", 270, 540);
        this.gameOverScreen = this.resourceLoader.loadEmbeddedImage("/images/gameoverscreen.png", 179, 69);

        this.blockImages[0] = this.resourceLoader.loadEmbeddedImage("/images/block_I.png", 26, 26);
        this.blockImages[1] = this.resourceLoader.loadEmbeddedImage("/images/block_J.png", 26, 26);
        this.blockImages[2] = this.resourceLoader.loadEmbeddedImage("/images/block_L.png", 26, 26);
        this.blockImages[3] = this.resourceLoader.loadEmbeddedImage("/images/block_O.png", 26, 26);
        this.blockImages[4] = this.resourceLoader.loadEmbeddedImage("/images/block_S.png", 26, 26);
        this.blockImages[5] = this.resourceLoader.loadEmbeddedImage("/images/block_T.png", 26, 26);
        this.blockImages[6] = this.resourceLoader.loadEmbeddedImage("/images/block_Z.png", 26, 26);

        this.tetrominoImages[0] = this.resourceLoader.loadEmbeddedImage("/images/tetromino_I.png", 73, 37);
        this.tetrominoImages[1] = this.resourceLoader.loadEmbeddedImage("/images/tetromino_J.png", 73, 37);
        this.tetrominoImages[2] = this.resourceLoader.loadEmbeddedImage("/images/tetromino_L.png", 73, 37);
        this.tetrominoImages[3] = this.resourceLoader.loadEmbeddedImage("/images/tetromino_O.png", 73, 37);
        this.tetrominoImages[4] = this.resourceLoader.loadEmbeddedImage("/images/tetromino_S.png", 73, 37);
        this.tetrominoImages[5] = this.resourceLoader.loadEmbeddedImage("/images/tetromino_T.png", 73, 37);
        this.tetrominoImages[6] = this.resourceLoader.loadEmbeddedImage("/images/tetromino_Z.png", 73, 37);

        this.painter.drawImage(this.background, 0, 0, null);
        this.frontBuffer.repaint();
    }

    private void clearArea(int upperLeftX, int upperLeftY, int width, int height) {

        this.painter.setColor(Color.BLACK);
        this.painter.fillRect(upperLeftX, upperLeftY, width, height);
        this.painter.setColor(Color.WHITE);
    }

    public void updateScore() {

        StringBuilder formatter = new StringBuilder();
        String s = String.format("%09d", this.scoringSystem.getScore());
        formatter.append(s, 0, 3).append(" ");
        formatter.append(s, 3, 6).append(" ");
        formatter.append(s, 6, 9);

        clearArea(56, 78, 148, 34);
        this.painter.drawString(formatter.toString(), 65, 102);
        this.frontBuffer.repaint(56, 78, 148, 34);
    }

    public void updateLineStats() {

        clearArea(132, 216, 74, 180);
        this.painter.drawString(String.format("%03d", this.scoringSystem.getSingleLineClears()), 137, 236);
        this.painter.drawString(String.format("%03d", this.scoringSystem.getDoubleLineClears()), 137, 274);
        this.painter.drawString(String.format("%03d", this.scoringSystem.getTripleLineClears()), 137, 312);
        this.painter.drawString(String.format("%03d", this.scoringSystem.getQuadrupleLineClears()), 137, 350);
        this.painter.drawString(String.format("%03d", this.scoringSystem.getTotalLinesCleared()), 137, 388);
        this.frontBuffer.repaint(132, 216, 74, 180);
    }

    public void updateGravityLevel() {

        clearArea(163, 479, 34, 22);
        this.painter.drawString(String.format("%02d", this.scoringSystem.getGravityLevel()), 167, 497);
        this.frontBuffer.repaint(163, 479, 34, 22);
    }

    public void updateFPS(double fps) {

        clearArea(128, 567, 56, 23);
        this.painter.drawString(String.format("%04.1f", fps), 132, 586);
        this.frontBuffer.repaint(128, 567, 56, 23);
    }

    public void updateNextTetromino(TetrominoType type) {

        switch (type) {

            case I:
                this.painter.drawImage(this.tetrominoImages[0], 794, 79, null);
                break;
            case J:
                this.painter.drawImage(this.tetrominoImages[1], 794, 79, null);
                break;
            case L:
                this.painter.drawImage(this.tetrominoImages[2], 794, 79, null);
                break;
            case O:
                this.painter.drawImage(this.tetrominoImages[3], 794, 79, null);
                break;
            case S:
                this.painter.drawImage(this.tetrominoImages[4], 794, 79, null);
                break;
            case T:
                this.painter.drawImage(this.tetrominoImages[5], 794, 79, null);
                break;
            case Z:
                this.painter.drawImage(this.tetrominoImages[6], 794, 79, null);
                break;
        }

        this.frontBuffer.repaint(794, 79, 73, 37);
    }

    public void updateTetrominoStats() {

        clearArea(850, 235, 68, 354);
        this.painter.drawString(String.format("%03d", this.scoringSystem.getNumTetrominoes_I()), 854, 254);
        this.painter.drawString(String.format("%03d", this.scoringSystem.getNumTetrominoes_J()), 854, 297);
        this.painter.drawString(String.format("%03d", this.scoringSystem.getNumTetrominoes_L()), 854, 346);
        this.painter.drawString(String.format("%03d", this.scoringSystem.getNumTetrominoes_O()), 854, 395);
        this.painter.drawString(String.format("%03d", this.scoringSystem.getNumTetrominoes_S()), 854, 444);
        this.painter.drawString(String.format("%03d", this.scoringSystem.getNumTetrominoes_T()), 854, 493);
        this.painter.drawString(String.format("%03d", this.scoringSystem.getNumTetrominoes_Z()), 854, 542);
        this.painter.drawString(String.format("%03d", this.scoringSystem.getTotalNumberOfTetrominoes()), 854, 585);
        this.frontBuffer.repaint(850, 235, 68, 354);
    }

    public void updatePlayfieldContents(Tetromino t) {

        for (int y=0; y<20; y++) {
            for (int x=0; x<10; x++) {
                drawBlock(x, y, this.playfield.getCellType(x, y));
            }
        }

        if (t != null) drawTetromino(t);
        this.frontBuffer.repaint(346, 50, 270, 540);
    }

    private void drawBlock(int logicalX, int logicalY, BlockType blocktype) {

        switch (blocktype) {

            case I :
                this.painter.drawImage(this.blockImages[0], 346+27*logicalX, 563-27*logicalY, null);
                return;
            case J :
                this.painter.drawImage(this.blockImages[1], 346+27*logicalX, 563-27*logicalY, null);
                return;
            case L :
                this.painter.drawImage(this.blockImages[2], 346+27*logicalX, 563-27*logicalY, null);
                return;
            case O :
                this.painter.drawImage(this.blockImages[3], 346+27*logicalX, 563-27*logicalY, null);
                return;
            case S :
                this.painter.drawImage(this.blockImages[4], 346+27*logicalX, 563-27*logicalY, null);
                return;
            case T :
                this.painter.drawImage(this.blockImages[5], 346+27*logicalX, 563-27*logicalY, null);
                return;
            case Z :
                this.painter.drawImage(this.blockImages[6], 346+27*logicalX, 563-27*logicalY, null);
                return;
            default:
                this.painter.setColor(Color.BLACK);
                this.painter.fillRect(346+27*logicalX, 563-27*logicalY, 26, 26);
                return;
        }
    }

    private void drawTetromino(Tetromino t) {

        Coordinate[] blockCoordinates = t.getBlockCoordinates();
        BlockType type = t.getBlockType();

        for (int i=0; i<4; i++) {
            int xCoord = blockCoordinates[i].x;
            int yCoord = blockCoordinates[i].y;
            if (xCoord < 0 || xCoord >= 10) continue;
            if (yCoord < 0 || yCoord >= 20) continue;
            drawBlock(xCoord, yCoord, type);
        }
    }

    public void animateOneStepOfRowClearAnimation(int stepNumber, List<Integer> rowsToBeCleared) {

        for (int i : rowsToBeCleared) {
            if (i%2 == 0) drawBlock(stepNumber, i, BlockType.EMPTY);
            else drawBlock(9-stepNumber, i, BlockType.EMPTY);
        }
        this.frontBuffer.repaint(346, 50, 270, 540);
    }

    public void showPauseScreen() {
        this.painter.drawImage(this.pauseScreen, 346, 50, null);
        this.frontBuffer.repaint(346, 50, 270, 540);
    }

    public void showGameOverScreen() {
        this.painter.drawImage(this.gameOverScreen, 391, 231, null);
        this.frontBuffer.repaint(391, 231, 179, 69);
    }

    private void fillBackgroundWithColor(Color c) {
    }

    public void clearPlayfield() {
        clearArea(346, 50, 270, 540);
    }

    public void updateWholeScreen() {
        this.frontBuffer.repaint();
    }
}
