/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Tetris;

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
public class GravityCounterTest {
    
    private GravityCounter gravityCounter;
    
    public GravityCounterTest() {
        this.gravityCounter = new GravityCounter(5);
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
    public void gravityCounterLoopsOK() {
        for (int i=0; i<100; i++) {
            if (i%28 == 27) assertEquals(true, gravityCounter.shouldTetrominoFall());
            else assertEquals(false, gravityCounter.shouldTetrominoFall());
            gravityCounter.tick();
        }
    }
    
    @Test
    public void methodResetWorks() {
        for (int i=0; i<10; i++) gravityCounter.tick();
        gravityCounter.reset();
        
        for (int i=0; i<100; i++) {
            if (i%28 == 27) assertEquals(true, gravityCounter.shouldTetrominoFall());
            else assertEquals(false, gravityCounter.shouldTetrominoFall());
            gravityCounter.tick();
        }
    }
}
