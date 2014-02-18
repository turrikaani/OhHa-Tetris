package Tetris.Keyboard;

public class KeyboardFrontend {

    public static final int LEFT = 1;
    public static final int RIGHT = 2;
    public static final int DOWN = 3;
    public static  final int NONE = 0;

    public static final int CLOCKWISE = 1;
    public static final int COUNTERCLOCKWISE = 2;
    public static final int NEITHER = 0;

    private int leftArrowKeyDownTime;
    private int rightArrowKeyDownTime;
    private int downArrowKeyDownTime;

    private boolean rotationKey1DownInLastFrame;
    private boolean rotationKey2DownInLastFrame;

    private boolean pauseKeyDownInLastFrame;
    private boolean gamePaused = false;

    private KeyboardStatus kbStatus;

    public KeyboardFrontend(KeyboardStatus k) {
        this.kbStatus = k;
        resetState();
    }

    public int getMovementDirection() {

        int arrowKeyPressed = kbStatus.whichArrowKeyIsPressed();

        if (arrowKeyPressed == kbStatus.LEFT) {

            leftArrowKeyDownTime++;
            rightArrowKeyDownTime = 0;
            downArrowKeyDownTime = 0;

            if (leftArrowKeyDownTime == 1) return LEFT;
            if (leftArrowKeyDownTime > 16 && (leftArrowKeyDownTime-16) % 5 == 1) return LEFT;
            else return NONE;
        }

        if (arrowKeyPressed == kbStatus.RIGHT) {

            leftArrowKeyDownTime = 0;
            rightArrowKeyDownTime++;
            downArrowKeyDownTime = 0;

            if (rightArrowKeyDownTime == 1) return RIGHT;
            if (rightArrowKeyDownTime > 16 && (rightArrowKeyDownTime-16) % 5 == 1) return RIGHT;
            else return NONE;
        }

        if (arrowKeyPressed == kbStatus.DOWN) {

            leftArrowKeyDownTime = 0;
            rightArrowKeyDownTime = 0;
            downArrowKeyDownTime++;

            if (downArrowKeyDownTime == 1) return DOWN;
            if (downArrowKeyDownTime > 8 && (downArrowKeyDownTime-8) % 3 == 1) return DOWN;
            else return NONE;
        }

        else {

            leftArrowKeyDownTime = 0;
            rightArrowKeyDownTime = 0;
            downArrowKeyDownTime = 0;
            return NONE;
        }
    }

    public int getRotationDirection() {

        boolean clockwiseRotationTriggered = false;
        boolean counterclockwiseRotationTriggered = false;

        boolean rotationKey1Down = kbStatus.getRotationKey1Status();
        boolean rotationKey2Down = kbStatus.getRotationKey2Status();

        if (rotationKey1Down && !rotationKey1DownInLastFrame) {
            clockwiseRotationTriggered = true;
        }

        if (rotationKey2Down && !rotationKey2DownInLastFrame) {
            counterclockwiseRotationTriggered = true;
        }

        rotationKey1DownInLastFrame = rotationKey1Down;
        rotationKey2DownInLastFrame = rotationKey2Down;

        if (clockwiseRotationTriggered && !counterclockwiseRotationTriggered) return CLOCKWISE;
        if (counterclockwiseRotationTriggered && !clockwiseRotationTriggered) return COUNTERCLOCKWISE;
        else return NEITHER;
    }

    public boolean isGamePaused() {

        boolean pauseKeyDown = kbStatus.getPauseKeyStatus();

        if (pauseKeyDown && !pauseKeyDownInLastFrame) {
            if (!gamePaused) gamePaused = true;
            else gamePaused = false;
        }

        pauseKeyDownInLastFrame = pauseKeyDown;
        return gamePaused;
    }

    public void resetState() {

        leftArrowKeyDownTime = 0;
        rightArrowKeyDownTime = 0;
        downArrowKeyDownTime = 0;
        rotationKey1DownInLastFrame = true;
        rotationKey2DownInLastFrame = true;
        pauseKeyDownInLastFrame = true;
    }
}
