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
public class KeyboardFrontendTest {

    private KeyboardStatus kbStatus = new KeyboardStatus();
    private KeyboardFrontend kbFrontend = new KeyboardFrontend(kbStatus);
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
    
    public KeyboardFrontendTest() {
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
    public void holdingDownLeftArrowTest() {
        assertEquals(KeyboardFrontend.NONE, kbFrontend.getMovementDirection());
        kbStatus.keyPressed(leftArrowDown);
        assertEquals(KeyboardFrontend.LEFT, kbFrontend.getMovementDirection());
        
        for (int i=0; i<15; i++) {
            assertEquals(KeyboardFrontend.NONE, kbFrontend.getMovementDirection());
        }
        assertEquals(KeyboardFrontend.LEFT, kbFrontend.getMovementDirection());
        
        for (int i=0; i<4; i++) {
            assertEquals(KeyboardFrontend.NONE, kbFrontend.getMovementDirection());
        }
        assertEquals(KeyboardFrontend.LEFT, kbFrontend.getMovementDirection());
        assertEquals(KeyboardFrontend.NONE, kbFrontend.getMovementDirection());
    }
    
    @Test
    public void holdingDownRightArrowTest() {
        assertEquals(KeyboardFrontend.NONE, kbFrontend.getMovementDirection());
        kbStatus.keyPressed(rightArrowDown);
        assertEquals(KeyboardFrontend.RIGHT, kbFrontend.getMovementDirection());
        
        for (int i=0; i<15; i++) {
            assertEquals(KeyboardFrontend.NONE, kbFrontend.getMovementDirection());
        }
        assertEquals(KeyboardFrontend.RIGHT, kbFrontend.getMovementDirection());
        
        for (int i=0; i<4; i++) {
            assertEquals(KeyboardFrontend.NONE, kbFrontend.getMovementDirection());
        }
        assertEquals(KeyboardFrontend.RIGHT, kbFrontend.getMovementDirection());
        assertEquals(KeyboardFrontend.NONE, kbFrontend.getMovementDirection());
    }
    
    @Test
    public void holdingDownDownArrowTest() {
        assertEquals(KeyboardFrontend.NONE, kbFrontend.getMovementDirection());
        kbStatus.keyPressed(downArrowDown);
        
        for (int i=0; i<4; i++) {
            assertEquals(KeyboardFrontend.NONE, kbFrontend.getMovementDirection());
        }
        assertEquals(KeyboardFrontend.DOWN, kbFrontend.getMovementDirection());
        assertEquals(KeyboardFrontend.NONE, kbFrontend.getMovementDirection());
        assertEquals(KeyboardFrontend.DOWN, kbFrontend.getMovementDirection());
        assertEquals(KeyboardFrontend.NONE, kbFrontend.getMovementDirection());
    }
    
    @Test
    public void isSoftDropActiveTest() {
        assertEquals(false, kbFrontend.isSoftDropActive());
        kbFrontend.getMovementDirection();
        assertEquals(false, kbFrontend.isSoftDropActive());
        
        kbStatus.keyPressed(downArrowDown);
        kbFrontend.getMovementDirection();
        assertEquals(false, kbFrontend.isSoftDropActive());
        kbFrontend.getMovementDirection();
        assertEquals(false, kbFrontend.isSoftDropActive());
        kbFrontend.getMovementDirection();
        assertEquals(false, kbFrontend.isSoftDropActive());
        kbFrontend.getMovementDirection();
        assertEquals(false, kbFrontend.isSoftDropActive());
        
        kbFrontend.getMovementDirection();
        assertEquals(true, kbFrontend.isSoftDropActive());
        kbFrontend.getMovementDirection();
        assertEquals(true, kbFrontend.isSoftDropActive());
    }
    
    @Test
    public void rotationKey1Test() {
        kbStatus.keyPressed(rotate1Down);
        assertEquals(KeyboardFrontend.NEITHER, kbFrontend.getRotationDirection());
        assertEquals(KeyboardFrontend.NEITHER, kbFrontend.getRotationDirection());
        kbStatus.keyReleased(rotate1Up);
        assertEquals(KeyboardFrontend.NEITHER, kbFrontend.getRotationDirection());
        assertEquals(KeyboardFrontend.NEITHER, kbFrontend.getRotationDirection());
        kbStatus.keyPressed(rotate1Down);
        assertEquals(KeyboardFrontend.CLOCKWISE, kbFrontend.getRotationDirection());
        assertEquals(KeyboardFrontend.NEITHER, kbFrontend.getRotationDirection());
    }
    
    @Test
    public void rotationKey2Test() {
        kbStatus.keyPressed(rotate2Down);
        assertEquals(KeyboardFrontend.NEITHER, kbFrontend.getRotationDirection());
        assertEquals(KeyboardFrontend.NEITHER, kbFrontend.getRotationDirection());
        kbStatus.keyReleased(rotate2Up);
        assertEquals(KeyboardFrontend.NEITHER, kbFrontend.getRotationDirection());
        assertEquals(KeyboardFrontend.NEITHER, kbFrontend.getRotationDirection());
        kbStatus.keyPressed(rotate2Down);
        assertEquals(KeyboardFrontend.COUNTERCLOCKWISE, kbFrontend.getRotationDirection());
        assertEquals(KeyboardFrontend.NEITHER, kbFrontend.getRotationDirection());
    }
    
    @Test
    public void bothRotationKeysPressedSimultaneouslyTest() {
        assertEquals(KeyboardFrontend.NEITHER, kbFrontend.getRotationDirection());
        kbStatus.keyPressed(rotate1Down);
        kbStatus.keyPressed(rotate2Down);
        assertEquals(KeyboardFrontend.NEITHER, kbFrontend.getRotationDirection());
        assertEquals(KeyboardFrontend.NEITHER, kbFrontend.getRotationDirection());
    }
    
    @Test
    public void isPauseStateChangeTriggeredTest() {
        kbStatus.keyPressed(pauseDown);
        assertEquals(false, kbFrontend.isPauseStateChangeTriggered());
        assertEquals(false, kbFrontend.isPauseStateChangeTriggered());
        kbStatus.keyReleased(pauseUp);
        assertEquals(false, kbFrontend.isPauseStateChangeTriggered());
        assertEquals(false, kbFrontend.isPauseStateChangeTriggered());
        kbStatus.keyPressed(pauseDown);
        assertEquals(true, kbFrontend.isPauseStateChangeTriggered());
        assertEquals(false, kbFrontend.isPauseStateChangeTriggered());
    }
    
    @Test
    public void isGamePausedTest() {
        kbStatus.keyPressed(pauseDown);
        assertEquals(false, kbFrontend.isGamePaused());
        assertEquals(false, kbFrontend.isGamePaused());
        kbStatus.keyReleased(pauseUp);
        assertEquals(false, kbFrontend.isGamePaused());
        assertEquals(false, kbFrontend.isGamePaused());
        kbStatus.keyPressed(pauseDown);
        assertEquals(true, kbFrontend.isGamePaused());
        assertEquals(true, kbFrontend.isGamePaused());
        kbStatus.keyReleased(pauseUp);
        assertEquals(true, kbFrontend.isGamePaused());
        kbStatus.keyPressed(pauseDown);
        assertEquals(false, kbFrontend.isGamePaused());
    }
}
