package Tetris;

import StartMenu.MainMenuWindow;
import Tetris.DataTypes.*;
import Tetris.Keyboard.*;
import java.awt.Color;
import java.util.List;

public class Tetris {

    private KeyboardFrontend kbFrontend;
    private MainMenuWindow mainMenuWindow;
    private Clock clock;
    private GravityCounter gravityCounter;
    private ScoringSystem scoringSystem;
    private TetrominoRandomizer randomizer;
    private Playfield playfield;
    private GraphicsHandler gfxHandler;

    private Tetromino tetromino;
    private TetrominoType currentTetrominoType;
    private TetrominoType nextTetrominoType;

    private boolean gameIsOver = false;
    private int framesSinceTetrominoAppearance;
    private List<Integer> rowsToClear;

    private int frameNumber = 0;
    private long currentTime = 0, lastTime = 0, totalTime = 0;

    public Tetris(int gravity, MainMenuWindow wnd) {

        KeyboardStatus kbStatus = new KeyboardStatus();

        this.kbFrontend = new KeyboardFrontend(kbStatus);
        this.mainMenuWindow = wnd;
        this.clock = new Clock(16);
        this.gravityCounter = new GravityCounter(gravity);
        this.scoringSystem = new ScoringSystem(gravity);
        this.randomizer = new TetrominoRandomizer();
        this.playfield = new Playfield();
        this.gfxHandler = new GraphicsHandler(playfield, scoringSystem, kbStatus);
    }

    public void letsPlay() {

        gfxHandler.initializeGameWindow();
        gfxHandler.updateGravityLevel();
        clock.waitAbsolute(1000);
        gfxHandler.clearPlayfield();

        nextTetrominoType = randomizer.getRandomTetrominoType();
        lastTime = System.nanoTime();

        while (true) {

            tetrominoAppearance();
            if (gameIsOver) break;
            tetrominoFreeMovement();
            tetrominoLocking();
            rowsToClear = playfield.getListOfFullRows();
            if (!rowsToClear.isEmpty()) showRowClearAnimation();
        }

        clock.waitAbsolute(500);
        gfxHandler.showGameOverScreen();

        while (true) {
            clock.waitAbsolute(50);
            if (kbFrontend.isPauseStateChangeTriggered() == true) break;
        }

        gfxHandler.closeGameWindow();
        mainMenuWindow.openWindow();
    }

    private void tetrominoAppearance() {

        waitForMultipleFrames(5);

        currentTetrominoType = nextTetrominoType;
        nextTetrominoType = randomizer.getRandomTetrominoType();
        tetromino = new Tetromino(currentTetrominoType, playfield);

        scoringSystem.addTetrominoToStats(currentTetrominoType);

        gfxHandler.updateNextTetromino(nextTetrominoType);
        gfxHandler.updateTetrominoStats();
        gfxHandler.updatePlayfieldContents(tetromino);

        waitForNextFrame();
        if (tetromino.detectCollisionsWithEnvironment() == true) gameIsOver = true;
    }

    private void tetrominoFreeMovement() {

        kbFrontend.resetState();
        gravityCounter.reset();
        framesSinceTetrominoAppearance = 0;

        while (true) {

            gfxHandler.updatePlayfieldContents(tetromino);
            waitForNextFrame();
            framesSinceTetrominoAppearance++;
            boolean hasDroppedInThisFrame = false;

            if (kbFrontend.isGamePaused() == true) pause();

            switch (kbFrontend.getRotationDirection()) {

                case KeyboardFrontend.CLOCKWISE:
                    tetromino.rotateClockwise();
                    break;

                case KeyboardFrontend.COUNTERCLOCKWISE:
                    tetromino.rotateCounterclockwise();
            }

            switch (kbFrontend.getMovementDirection()) {

                case KeyboardFrontend.LEFT:
                    tetromino.stepLeft();
                    break;

                case KeyboardFrontend.RIGHT:
                    tetromino.stepRight();
                    break;

                case KeyboardFrontend.DOWN:
                    boolean fellFreely = tetromino.stepDown();
                    if (!fellFreely) return;
                    hasDroppedInThisFrame = true;
            }

            if (kbFrontend.isSoftDropActive() == true) {
                gravityCounter.reset();
                continue;
            }

            if (gravityCounter.shouldTetrominoFall() == true && !hasDroppedInThisFrame) {
                boolean fellFreely = tetromino.stepDown();
                if (!fellFreely) return;
            }

            gravityCounter.tick();
        }
    }

    private void tetrominoLocking() {

        gfxHandler.updatePlayfieldContents(tetromino);
        gfxHandler.drawLockingTetromino(tetromino);

        waitForMultipleFrames(8);

        Coordinate[] blockCoordinates = tetromino.getBlockCoordinates();

        for (int i=0; i<4; i++) {
            playfield.reserveCellForBlock(blockCoordinates[i].x, blockCoordinates[i].y, tetromino.getBlockType());
        }

        tetromino = null;

        gfxHandler.updatePlayfieldContents(null);
        scoringSystem.addFastDropBonusToScore(framesSinceTetrominoAppearance);
        gfxHandler.updateScore();
        waitForMultipleFrames(2);
    }

    private void showRowClearAnimation() {

        for (int i=0; i<10; i++) {
            gfxHandler.animateOneStepOfRowClearAnimation(i, rowsToClear);
            waitForMultipleFrames(4);
        }

        playfield.clearFullRows();
        gfxHandler.updatePlayfieldContents(null);

        scoringSystem.addClearedRowsToScore(rowsToClear);
        gfxHandler.updateScore();
        gfxHandler.updateLineStats();
    }

    private void pause() {

        waitForNextFrame();
        gfxHandler.showPauseScreen();

        while (kbFrontend.isGamePaused() == true) {
            waitForNextFrame();
        }

        gfxHandler.clearPlayfield();
        gfxHandler.updatePlayfieldContents(tetromino);
        waitForNextFrame();
    }

    private void waitForMultipleFrames(int numFrames) {

        for (int i=0; i<numFrames; i++) {
            waitForNextFrame();
        }
    }

    private void waitForNextFrame() {

        clock.waitAbsolute(10);

        frameNumber++;
        currentTime = System.nanoTime();
        totalTime += currentTime-lastTime;
        lastTime = currentTime;

        if (frameNumber == 30) {
            gfxHandler.updateFPS(30000000000.0/totalTime);
            frameNumber = 0;
            totalTime = 0;
        }
    }
}
