package Tetris;

import Tetris.DataTypes.*;
import Tetris.Keyboard.*;
import java.awt.Color;
import java.util.List;

public class Tetris {

    private KeyboardFrontend kbFrontend;
    private Clock clock;
    private GravityCounter gravityCounter;
    private ScoringSystem scoringSystem;
    private TetrominoRandomizer randomizer;
    private Playfield playfield;
    private GraphicsHandler gfxHandler;

    private Tetromino tetromino;
    private TetrominoType currentTetrominoType;
    private TetrominoType nextTetrominoType;
    private List<Integer> rowsToClear;

    private int framesSinceTetrominoAppearance;
    private int frameNumber = 0;
    private long currentTime, lastTime;
    private long totalTime = 0;
    private boolean gameIsOver = false;

    public Tetris(int gravity) {

        KeyboardStatus kbStatus = new KeyboardStatus();

        this.kbFrontend = new KeyboardFrontend(kbStatus);
        this.clock = new Clock(16);
        this.gravityCounter = new GravityCounter(gravity);
        this.scoringSystem = new ScoringSystem(gravity);
        this.randomizer = new TetrominoRandomizer();
        this.playfield = new Playfield();
        this.gfxHandler = new GraphicsHandler(this.playfield, this.scoringSystem, kbStatus);
    }

    public void letsPlay() {

        this.gfxHandler.updateGravityLevel();
        this.clock.waitAbsolute(1000);
        this.gfxHandler.clearPlayfield();

        this.nextTetrominoType = TetrominoType.I;

        this.clock.restart();
        this.lastTime = System.nanoTime();

        while (true) {

            tetrominoAppearance();
            if (gameIsOver) break;
            tetrominoFreeMovement();
            tetrominoLocking();
            if (this.rowsToClear.isEmpty()) continue;
            showRowClearAnimation();
        }

        showGameOverScreen();

        while (true) {
            clock.waitAbsolute(100);
            if (kbFrontend.isPauseStateChangeTriggered() == true) break;
        }
        gfxHandler.closeWindow();
    }

    private void tetrominoAppearance() {

        this.currentTetrominoType = this.nextTetrominoType;
        this.nextTetrominoType = TetrominoType.I;
        this.scoringSystem.addTetrominoToStats(this.currentTetrominoType);
        this.tetromino = new Tetromino(this.currentTetrominoType, this.playfield);

        for (int i=0; i<5; i++) {
            waitForNextFrame();
        }

        this.gfxHandler.updateNextTetromino(this.nextTetrominoType);
        this.gfxHandler.updateTetrominoStats();
        this.gfxHandler.updatePlayfieldContents(this.tetromino);

        waitForNextFrame();
        if (this.tetromino.detectCollisionsWithEnvironment() == true) this.gameIsOver = true;
    }

    private void tetrominoFreeMovement() {

        kbFrontend.resetState();
        gravityCounter.reset();
        framesSinceTetrominoAppearance = 0;

        while (true) {

            gfxHandler.updatePlayfieldContents(tetromino);
            waitForNextFrame();
            framesSinceTetrominoAppearance++;

            if (kbFrontend.isGamePaused() == true) pause();

            switch (kbFrontend.getRotationDirection()) {

                case KeyboardFrontend.CLOCKWISE:
                    tetromino.rotateClockwise();
                    break;

                case KeyboardFrontend.COUNTERCLOCKWISE:
                    tetromino.rotateCounterclockwise();
                    break;
            }

            switch (kbFrontend.getMovementDirection()) {

                case KeyboardFrontend.LEFT:
                    tetromino.stepLeft();
                    break;

                case KeyboardFrontend.RIGHT:
                    tetromino.stepRight();
                    break;

                case KeyboardFrontend.DOWN:
                    if (tetromino.stepDown() == false) return;
                    else continue;
            }

            if (gravityCounter.shouldTetrominoFall() == true) {
                if (kbFrontend.isSoftDropActive() == true) continue;
                if (tetromino.stepDown() == false) return;
            }
        }
    }

    private void tetrominoLocking() {

        gfxHandler.updatePlayfieldContents(tetromino);
        gfxHandler.drawLockingTetromino(tetromino);
        gfxHandler.updateWholeScreen();

        Coordinate[] blockCoordinates = this.tetromino.getBlockCoordinates();
        for (int i=0; i<4; i++) {
            this.playfield.reserveCellForBlock(blockCoordinates[i].x, blockCoordinates[i].y, this.tetromino.getBlockType());
        }

        this.tetromino = null;
        this.scoringSystem.addFastDropBonusToScore(this.framesSinceTetrominoAppearance);
        this.rowsToClear = this.playfield.getListOfFullRows();

        for (int i=0; i<5; i++) {
            waitForNextFrame();
        }

        this.gfxHandler.updateScore();
    }

    private void showRowClearAnimation() {

        int numRowsToClear = rowsToClear.size();

        for (int i=0; i<10; i++) {
            this.gfxHandler.animateOneStepOfRowClearAnimation(i, this.rowsToClear);
            if (numRowsToClear == 4 && i%2 == 0) gfxHandler.fillBackgroundWithColor(Color.WHITE);
            for (int j=0; j<2; j++) waitForNextFrame();
            if (numRowsToClear == 4 && i%2 == 0) gfxHandler.fillBackgroundWithColor(Color.BLACK);
            for (int j=0; j<4; j++) waitForNextFrame();
        }

        this.scoringSystem.addClearedRowsToScore(this.rowsToClear);
        for (int i : this.rowsToClear) this.playfield.clearRow(i);
        this.gfxHandler.updateScore();
        this.gfxHandler.updateLineStats();
        this.gfxHandler.updatePlayfieldContents(null);
    }

    private void showGameOverScreen() {
        this.clock.waitAbsolute(500);
        this.gfxHandler.showGameOverScreen();
    }

    public void pause() {

        waitForNextFrame();
        this.gfxHandler.showPauseScreen();

        while (this.kbFrontend.isGamePaused() == true) {
            waitForNextFrame();
        }

        this.gfxHandler.clearPlayfield();
        this.gfxHandler.updatePlayfieldContents(this.tetromino);
        waitForNextFrame();
    }

    private void waitForNextFrame() {

        this.clock.waitAbsolute(15);

        this.frameNumber++;
        this.currentTime = System.nanoTime();
        this.totalTime += this.currentTime - this.lastTime;
        this.lastTime = this.currentTime;

        if (this.frameNumber == 30) {
            this.gfxHandler.updateFPS(30000000000.0/this.totalTime);
            this.frameNumber = 0;
            this.totalTime = 0;
        }
    }
}
