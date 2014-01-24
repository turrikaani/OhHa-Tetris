/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package main;

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
public class TetrominoTest {

    private Playfield playfield;
    private Tetromino tetromino;

    public TetrominoTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        this.playfield = new Playfield();
        this.tetromino = new Tetromino(0, this.playfield);
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void tetrominoInitialCoordinatesAreRight() {
        Coordinate[] blocks = this.tetromino.getBlockCoordinates();
        assertEquals(3, blocks[0].x);
        assertEquals(4, blocks[1].x);
        assertEquals(5, blocks[2].x);
        assertEquals(6, blocks[3].x);
        assertEquals(19, blocks[0].y);
        assertEquals(19, blocks[1].y);
        assertEquals(19, blocks[2].y);
        assertEquals(19, blocks[3].y);
    }

    @Test
    public void tetrominoRotateClockwiseOK() {
        this.tetromino.rotateClockwise();
        Coordinate[] blocks = this.tetromino.getBlockCoordinates();
        assertEquals(5, blocks[0].x);
        assertEquals(5, blocks[1].x);
        assertEquals(5, blocks[2].x);
        assertEquals(5, blocks[3].x);
        assertEquals(21, blocks[0].y);
        assertEquals(20, blocks[1].y);
        assertEquals(19, blocks[2].y);
        assertEquals(18, blocks[3].y);
    }

    @Test
    public void tetrominoRotateCounterclockwiseOK() {
        this.tetromino.rotateCounterclockwise();
        Coordinate[] blocks = this.tetromino.getBlockCoordinates();
        assertEquals(5, blocks[0].x);
        assertEquals(5, blocks[1].x);
        assertEquals(5, blocks[2].x);
        assertEquals(5, blocks[3].x);
        assertEquals(18, blocks[0].y);
        assertEquals(19, blocks[1].y);
        assertEquals(20, blocks[2].y);
        assertEquals(21, blocks[3].y);
    }

    @Test
    public void tetrominoStepDownOK() {
        for (int i=0; i<3; i++) this.tetromino.stepDown();
        Coordinate[] blocks = this.tetromino.getBlockCoordinates();
        assertEquals(3, blocks[0].x);
        assertEquals(4, blocks[1].x);
        assertEquals(5, blocks[2].x);
        assertEquals(6, blocks[3].x);
        assertEquals(16, blocks[0].y);
        assertEquals(16, blocks[1].y);
        assertEquals(16, blocks[2].y);
        assertEquals(16, blocks[3].y);
    }

    @Test
    public void tetrominoStaysWithinTheGrid() {
        for (int i=0; i<8; i++) this.tetromino.stepRight();
        for (int i=0; i<30; i++) this.tetromino.stepDown();
        Coordinate[] blocks = this.tetromino.getBlockCoordinates();
        assertEquals(6, blocks[0].x);
        assertEquals(7, blocks[1].x);
        assertEquals(8, blocks[2].x);
        assertEquals(9, blocks[3].x);
        assertEquals(0, blocks[0].y);
        assertEquals(0, blocks[1].y);
        assertEquals(0, blocks[2].y);
        assertEquals(0, blocks[3].y);
    }
}
