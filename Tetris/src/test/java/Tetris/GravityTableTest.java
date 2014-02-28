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
public class GravityTableTest {
    
    public GravityTableTest() {
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
    public void gravityDropFrequenciesAreCorrect() {
        assertEquals(28, GravityTable.getGravityDropFrequencyInFrames(0));
        assertEquals(48, GravityTable.getGravityDropFrequencyInFrames(1));
        assertEquals(43, GravityTable.getGravityDropFrequencyInFrames(2));
        assertEquals(38, GravityTable.getGravityDropFrequencyInFrames(3));
        assertEquals(33, GravityTable.getGravityDropFrequencyInFrames(4));
        assertEquals(28, GravityTable.getGravityDropFrequencyInFrames(5));
        assertEquals(23, GravityTable.getGravityDropFrequencyInFrames(6));
        assertEquals(18, GravityTable.getGravityDropFrequencyInFrames(7));
        assertEquals(13, GravityTable.getGravityDropFrequencyInFrames(8));
        assertEquals(8, GravityTable.getGravityDropFrequencyInFrames(9));
        assertEquals(6, GravityTable.getGravityDropFrequencyInFrames(10));
        assertEquals(28, GravityTable.getGravityDropFrequencyInFrames(11));
    }
}
