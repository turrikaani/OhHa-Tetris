package Tetris.Keyboard;

public class KeyboardFrontend {

    public static final int LEFT = 1;
    public static final int RIGHT = 2;
    public static final int DOWN = 3;
    public static  final int NONE = 0;

    public static final int CLOCKWISE = 1;
    public static final int COUNTERCLOCKWISE = 2;
    public static final int NEITHER = 0;

    private final int AUTOREPEAT_THRESHOLD = 16;
    private final int AUTOREPEAT_INTERVAL = 5;
    private final int SOFTDROP_THRESHOLD = 4;
    private final int SOFTDROP_INTERVAL = 2;

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

    public void resetState() {

        leftArrowKeyDownTime = 0;
        rightArrowKeyDownTime = 0;
        downArrowKeyDownTime = 0;
        rotationKey1DownInLastFrame = true;
        rotationKey2DownInLastFrame = true;
        pauseKeyDownInLastFrame = true;
    }

    public int getMovementDirection() {

        int arrowKeyPressed = kbStatus.whichArrowKeyIsPressed();

        if (arrowKeyPressed == kbStatus.LEFT) {

            leftArrowKeyDownTime++;
            rightArrowKeyDownTime = 0;
            downArrowKeyDownTime = 0;

            if (leftArrowKeyDownTime == 1) return LEFT;

            if (leftArrowKeyDownTime > AUTOREPEAT_THRESHOLD &&
                (leftArrowKeyDownTime - AUTOREPEAT_THRESHOLD) % AUTOREPEAT_INTERVAL == 1)
            {
                return LEFT;
            }
            else return NONE;
        }

        if (arrowKeyPressed == kbStatus.RIGHT) {

            leftArrowKeyDownTime = 0;
            rightArrowKeyDownTime++;
            downArrowKeyDownTime = 0;

            if (rightArrowKeyDownTime == 1) return RIGHT;

            if (rightArrowKeyDownTime > AUTOREPEAT_THRESHOLD &&
                (rightArrowKeyDownTime - AUTOREPEAT_THRESHOLD) % AUTOREPEAT_INTERVAL == 1)
            {
                return RIGHT;
            }
            else return NONE;
        }

        if (arrowKeyPressed == kbStatus.DOWN) {

            leftArrowKeyDownTime = 0;
            rightArrowKeyDownTime = 0;
            downArrowKeyDownTime++;

            if (downArrowKeyDownTime > SOFTDROP_THRESHOLD &&
                (downArrowKeyDownTime - SOFTDROP_THRESHOLD) % SOFTDROP_INTERVAL == 1)
            {
                return DOWN;
            }
            else return NONE;
        }

        else {

            leftArrowKeyDownTime = 0;
            rightArrowKeyDownTime = 0;
            downArrowKeyDownTime = 0;
            return NONE;
        }
    }

    public boolean isSoftDropActive() {

        if (downArrowKeyDownTime > SOFTDROP_THRESHOLD) return true;
        else return false;
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

        if (isPauseStateChangeTriggered()) {

            if (!gamePaused) gamePaused = true;
            else gamePaused = false;
        }

        return gamePaused;
    }

    public boolean isPauseStateChangeTriggered() {

        boolean isTriggered;
        boolean isPauseKeyDownNow = kbStatus.getPauseKeyStatus();

        if (isPauseKeyDownNow && !pauseKeyDownInLastFrame) isTriggered = true;
        else isTriggered = false;

        pauseKeyDownInLastFrame = isPauseKeyDownNow;
        return isTriggered;
    }
}
