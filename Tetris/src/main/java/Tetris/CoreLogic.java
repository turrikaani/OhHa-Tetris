package Tetris;

import Tetris.DataTypes.*;
import Tetris.Keyboard.*;
import java.util.List;

public class CoreLogic {

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

    private int gravityDropsSinceAppearance;
    private int frameNumber = 0;
    private long currentTime, lastTime;
    private long totalTime = 0;
    private boolean gameIsOver = false;

    public CoreLogic(int gravity) {

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

        this.nextTetrominoType = this.randomizer.getRandomTetrominoType();

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
    }

    private void tetrominoAppearance() {

        this.currentTetrominoType = this.nextTetrominoType;
        this.nextTetrominoType = this.randomizer.getRandomTetrominoType();
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

        this.kbFrontend.resetState();
        this.gravityCounter.reset();
        this.gravityDropsSinceAppearance = 0;

        while (true) {

            this.gfxHandler.updatePlayfieldContents(this.tetromino);
            waitForNextFrame();
            if (this.kbFrontend.isGamePaused() == true) pause();

            int rotationDirection = this.kbFrontend.getRotationDirection();

            switch (rotationDirection) {

                case KeyboardFrontend.CLOCKWISE:
                    this.tetromino.rotateClockwise();
                    break;
                case KeyboardFrontend.COUNTERCLOCKWISE:
                    this.tetromino.rotateCounterclockwise();
                    break;
            }

            int movementDirection = this.kbFrontend.getMovementDirection();

            switch (movementDirection) {

                case KeyboardFrontend.LEFT:
                    this.tetromino.stepLeft();
                    break;
                case KeyboardFrontend.RIGHT:
                    this.tetromino.stepRight();
                    break;
                case KeyboardFrontend.DOWN:
                    boolean didNotCollide = this.tetromino.stepDown();
                    if (didNotCollide == false) return;
                    break;
            }

            if (this.gravityCounter.shouldTetrominoFall() == true) {
                this.gravityDropsSinceAppearance++;
                if (this.kbFrontend.isSoftDropActive() == false) {
                    boolean didNotCollide = this.tetromino.stepDown();
                    if (didNotCollide == false) return;
                }
            }
        }
    }

    private void tetrominoLocking() {

        this.gfxHandler.updatePlayfieldContents(this.tetromino);

        Coordinate[] blockCoordinates = this.tetromino.getBlockCoordinates();
        for (int i=0; i<4; i++) {
            this.playfield.reserveCellForBlock(blockCoordinates[i].x, blockCoordinates[i].y, this.tetromino.getBlockType());
        }

        this.tetromino = null;
        this.scoringSystem.addFastDropBonusToScore(this.gravityDropsSinceAppearance);
        this.rowsToClear = this.playfield.getListOfFullRows();

        for (int i=0; i<5; i++) {
            waitForNextFrame();
        }

        this.gfxHandler.updateScore();
    }

    private void showRowClearAnimation() {

        for (int i=0; i<10; i++) {
            this.gfxHandler.animateOneStepOfRowClearAnimation(i, this.rowsToClear);
            waitForNextFrame();
            waitForNextFrame();
            waitForNextFrame();
            waitForNextFrame();
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

        this.clock.waitRelative();

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
