package Tetris.Keyboard;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyboardStatus implements KeyListener {

    public final int LEFT = 1;
    public final int RIGHT = 2;
    public final int DOWN = 3;
    public final int NONE = 0;

    private boolean leftArrowKeyDown = false;
    private boolean rightArrowKeyDown = false;
    private boolean downArrowKeyDown = false;
    private boolean rotationKey1Down = false;
    private boolean rotationKey2Down = false;
    private boolean pauseKeyDown = false;

    public void keyPressed(KeyEvent e) {

        int keyCode = e.getKeyCode();

        if      (keyCode == KeyEvent.VK_LEFT)  this.leftArrowKeyDown = true;
        else if (keyCode == KeyEvent.VK_RIGHT) this.rightArrowKeyDown = true;
        else if (keyCode == KeyEvent.VK_DOWN)  this.downArrowKeyDown = true;
        else if (keyCode == KeyEvent.VK_SHIFT) this.rotationKey1Down = true;
        else if (keyCode == KeyEvent.VK_LESS)  this.rotationKey2Down = true;
        else if (keyCode == KeyEvent.VK_ENTER) this.pauseKeyDown = true;
    }

    public void keyReleased(KeyEvent e) {

        int keyCode = e.getKeyCode();

        if      (keyCode == KeyEvent.VK_LEFT)  this.leftArrowKeyDown = false;
        else if (keyCode == KeyEvent.VK_RIGHT) this.rightArrowKeyDown = false;
        else if (keyCode == KeyEvent.VK_DOWN)  this.downArrowKeyDown = false;
        else if (keyCode == KeyEvent.VK_SHIFT) this.rotationKey1Down = false;
        else if (keyCode == KeyEvent.VK_LESS)  this.rotationKey2Down = false;
        else if (keyCode == KeyEvent.VK_ENTER) this.pauseKeyDown = false;
    }

    public void keyTyped(KeyEvent e) {
    }

    public int whichArrowKeyIsPressed() {

        if (leftArrowKeyDown  && !rightArrowKeyDown && !downArrowKeyDown) return LEFT;
        if (rightArrowKeyDown && !leftArrowKeyDown  && !downArrowKeyDown) return RIGHT;
        if (downArrowKeyDown  && !rightArrowKeyDown && !leftArrowKeyDown) return DOWN;
        else return NONE;
    }

    public boolean getRotationKey1Status() {
        return this.rotationKey1Down;
    }

    public boolean getRotationKey2Status() {
        return this.rotationKey2Down;
    }

    public boolean getPauseKeyStatus() {
        return this.pauseKeyDown;
    }
}
