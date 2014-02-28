/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Tetris;

import Tetris.DataTypes.TetrominoType;
import java.util.ArrayList;
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
public class TetrominoRandomizerTest {
    
    private TetrominoRandomizer randomizer = new TetrominoRandomizer();
    
    public TetrominoRandomizerTest() {
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
    public void randomizerProducesAllTetrominoTypes() {
        ArrayList<TetrominoType> list = new ArrayList<TetrominoType>();
        
        for (int i=0; i<50; i++) {
            list.add(randomizer.getRandomTetrominoType());
        }
        
        assertEquals(true, list.contains(TetrominoType.I));
        assertEquals(true, list.contains(TetrominoType.J));
        assertEquals(true, list.contains(TetrominoType.L));
        assertEquals(true, list.contains(TetrominoType.O));
        assertEquals(true, list.contains(TetrominoType.S));
        assertEquals(true, list.contains(TetrominoType.T));
        assertEquals(true, list.contains(TetrominoType.Z));
    }
    
    @Test
    public void randomizerDoesNotProduceSameTetrominoTypeTwiceConsecutively() {
        TetrominoType lastTetrominoType = randomizer.getRandomTetrominoType();
        TetrominoType currentTetrominoType;
        
        for (int i=0; i<500; i++) {
            currentTetrominoType = randomizer.getRandomTetrominoType();
            assertTrue("The same tetromino type appeared twice in a row!", currentTetrominoType!=lastTetrominoType);
            lastTetrominoType = currentTetrominoType;
        }
    }
}
