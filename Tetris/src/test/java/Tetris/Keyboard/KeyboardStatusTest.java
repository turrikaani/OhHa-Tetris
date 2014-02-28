/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Tetris.Keyboard;

import java.awt.event.KeyEvent;
import javax.swing.JFrame;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author admin
 */
public class KeyboardStatusTest {
    
    private KeyboardStatus kbStatus = new KeyboardStatus();
    private JFrame window = new JFrame();
    
    KeyEvent leftArrowDown = new KeyEvent(window, KeyEvent.KEY_PRESSED, 1, 0, KeyEvent.VK_LEFT, KeyEvent.CHAR_UNDEFINED);
    KeyEvent rightArrowDown = new KeyEvent(window, KeyEvent.KEY_PRESSED, 1, 0, KeyEvent.VK_RIGHT, KeyEvent.CHAR_UNDEFINED);
    KeyEvent downArrowDown = new KeyEvent(window, KeyEvent.KEY_PRESSED, 1, 0, KeyEvent.VK_DOWN, KeyEvent.CHAR_UNDEFINED);
    KeyEvent rotate1Down = new KeyEvent(window, KeyEvent.KEY_PRESSED, 1, 0, KeyEvent.VK_SHIFT, KeyEvent.CHAR_UNDEFINED);
    KeyEvent rotate2Down = new KeyEvent(window, KeyEvent.KEY_PRESSED, 1, 0, KeyEvent.VK_CONTROL, KeyEvent.CHAR_UNDEFINED);
    KeyEvent pauseDown = new KeyEvent(window, KeyEvent.KEY_PRESSED, 1, 0, KeyEvent.VK_ENTER, KeyEvent.CHAR_UNDEFINED);
    
    KeyEvent leftArrowUp = new KeyEvent(window, KeyEvent.KEY_RELEASED, 1, 0, KeyEvent.VK_LEFT, KeyEvent.CHAR_UNDEFINED);
    KeyEvent rightArrowUp = new KeyEvent(window, KeyEvent.KEY_RELEASED, 1, 0, KeyEvent.VK_RIGHT, KeyEvent.CHAR_UNDEFINED);
    KeyEvent downArrowUp = new KeyEvent(window, KeyEvent.KEY_RELEASED, 1, 0, KeyEvent.VK_DOWN, KeyEvent.CHAR_UNDEFINED);
    KeyEvent rotate1Up = new KeyEvent(window, KeyEvent.KEY_RELEASED, 1, 0, KeyEvent.VK_SHIFT, KeyEvent.CHAR_UNDEFINED);
    KeyEvent rotate2Up = new KeyEvent(window, KeyEvent.KEY_RELEASED, 1, 0, KeyEvent.VK_CONTROL, KeyEvent.CHAR_UNDEFINED);
    KeyEvent pauseUp = new KeyEvent(window, KeyEvent.KEY_RELEASED, 1, 0, KeyEvent.VK_ENTER, KeyEvent.CHAR_UNDEFINED);
    
    public KeyboardStatusTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void initialStatusRight() {
        assertEquals(kbStatus.NONE, this.kbStatus.whichArrowKeyIsPressed());
        assertEquals(false, kbStatus.getRotationKey1Status());
        assertEquals(false, kbStatus.getRotationKey2Status());
        assertEquals(false, kbStatus.getPauseKeyStatus());
    }
    
    @Test
    public void leftArrowKeyWorks() {
        this.kbStatus.keyPressed(leftArrowDown);
        assertEquals(kbStatus.LEFT, kbStatus.whichArrowKeyIsPressed());
        this.kbStatus.keyReleased(leftArrowUp);
        assertEquals(kbStatus.NONE, kbStatus.whichArrowKeyIsPressed());
    }
    
    @Test
    public void rightArrowKeyWorks() {
        this.kbStatus.keyPressed(rightArrowDown);
        assertEquals(kbStatus.RIGHT, kbStatus.whichArrowKeyIsPressed());
        this.kbStatus.keyReleased(rightArrowUp);
        assertEquals(kbStatus.NONE, kbStatus.whichArrowKeyIsPressed());
    }
    
    @Test
    public void downArrowKeyWorks() {
        this.kbStatus.keyPressed(downArrowDown);
        assertEquals(kbStatus.DOWN, kbStatus.whichArrowKeyIsPressed());
        this.kbStatus.keyReleased(downArrowUp);
        assertEquals(kbStatus.NONE, kbStatus.whichArrowKeyIsPressed());
    }
    
    @Test
    public void rotationKey1Works() {
        this.kbStatus.keyPressed(rotate1Down);
        assertEquals(true, kbStatus.getRotationKey1Status());
        this.kbStatus.keyReleased(rotate1Up);
        assertEquals(false, kbStatus.getRotationKey1Status());
    }
    
    @Test
    public void rotationKey2Works() {
        this.kbStatus.keyPressed(rotate2Down);
        assertEquals(true, kbStatus.getRotationKey2Status());
        this.kbStatus.keyReleased(rotate2Up);
        assertEquals(false, kbStatus.getRotationKey2Status());
    }
    
    @Test
    public void pauseKeyWorks() {
        this.kbStatus.keyPressed(pauseDown);
        assertEquals(true, kbStatus.getPauseKeyStatus());
        this.kbStatus.keyReleased(pauseUp);
        assertEquals(false, kbStatus.getPauseKeyStatus());
    }
    
    @Test
    public void multipleArrowKeysPressedSimultaneously1() {
        this.kbStatus.keyPressed(leftArrowDown);
        this.kbStatus.keyPressed(rightArrowDown);
        this.kbStatus.keyPressed(downArrowDown);
        assertEquals(kbStatus.NONE, this.kbStatus.whichArrowKeyIsPressed());
    }
    
    @Test
    public void multipleArrowKeysPressedSimultaneously2() {
        this.kbStatus.keyPressed(leftArrowDown);
        this.kbStatus.keyPressed(rightArrowDown);
        assertEquals(kbStatus.NONE, this.kbStatus.whichArrowKeyIsPressed());
    }
    
    @Test
    public void multipleArrowKeysPressedSimultaneously3() {
        this.kbStatus.keyPressed(leftArrowDown);
        this.kbStatus.keyPressed(downArrowDown);
        assertEquals(kbStatus.NONE, this.kbStatus.whichArrowKeyIsPressed());
    }
    
    @Test
    public void multipleArrowKeysPressedSimultaneously4() {
        this.kbStatus.keyPressed(rightArrowDown);
        this.kbStatus.keyPressed(downArrowDown);
        assertEquals(kbStatus.NONE, this.kbStatus.whichArrowKeyIsPressed());
    }
}
