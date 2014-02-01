/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Tetris;

import Tetris.DataTypes.*;
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
public class Tetromino2ndTest {

    private Playfield playfield;
    private Tetromino tetromino;

    public Tetromino2ndTest() {
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
        this.tetromino = new Tetromino(TetrominoType.I, this.playfield);
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void rotateClockwiseByOne() {
        boolean noCollision = this.tetromino.rotateClockwise();
        Coordinate[] blocks = this.tetromino.getBlockCoordinates();
        assertEquals(5, blocks[0].x);
        assertEquals(5, blocks[1].x);
        assertEquals(5, blocks[2].x);
        assertEquals(5, blocks[3].x);
        assertEquals(21, blocks[0].y);
        assertEquals(20, blocks[1].y);
        assertEquals(19, blocks[2].y);
        assertEquals(18, blocks[3].y);
        assertEquals(true, noCollision);
    }

    @Test
    public void rotateClockwiseByTwo() {
        this.tetromino.rotateClockwise();
        this.tetromino.rotateClockwise();
        Coordinate[] blocks = this.tetromino.getBlockCoordinates();
        assertEquals(6, blocks[0].x);
        assertEquals(5, blocks[1].x);
        assertEquals(4, blocks[2].x);
        assertEquals(3, blocks[3].x);
        assertEquals(19, blocks[0].y);
        assertEquals(19, blocks[1].y);
        assertEquals(19, blocks[2].y);
        assertEquals(19, blocks[3].y);
    }

    @Test
    public void rotateClockwiseByThree() {
        for (int i=0; i<3; i++) this.tetromino.rotateClockwise();
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
    public void rotateClockwiseByFour() {
        for (int i=0; i<4; i++) this.tetromino.rotateClockwise();
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
    public void rotateCounterclockwiseByOne() {
        boolean noCollision = this.tetromino.rotateCounterclockwise();
        Coordinate[] blocks = this.tetromino.getBlockCoordinates();
        assertEquals(5, blocks[0].x);
        assertEquals(5, blocks[1].x);
        assertEquals(5, blocks[2].x);
        assertEquals(5, blocks[3].x);
        assertEquals(18, blocks[0].y);
        assertEquals(19, blocks[1].y);
        assertEquals(20, blocks[2].y);
        assertEquals(21, blocks[3].y);
        assertEquals(true, noCollision);
    }

    @Test
    public void rotateCounterclockwiseByTwo() {
        this.tetromino.rotateCounterclockwise();
        this.tetromino.rotateCounterclockwise();
        Coordinate[] blocks = this.tetromino.getBlockCoordinates();
        assertEquals(6, blocks[0].x);
        assertEquals(5, blocks[1].x);
        assertEquals(4, blocks[2].x);
        assertEquals(3, blocks[3].x);
        assertEquals(19, blocks[0].y);
        assertEquals(19, blocks[1].y);
        assertEquals(19, blocks[2].y);
        assertEquals(19, blocks[3].y);
    }

    @Test
    public void rotateCounterclockwiseByThree() {
        for (int i=0; i<3; i++) this.tetromino.rotateCounterclockwise();
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
    public void rotateCounterclockwiseByFour() {
        for (int i=0; i<4; i++) this.tetromino.rotateCounterclockwise();
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
    public void rotateClockwiseByOneWithObstacle() {
        this.playfield.reserveCellForBlock(5, 18, BlockType.S);
        boolean noCollision = this.tetromino.rotateClockwise();
        Coordinate[] blocks = this.tetromino.getBlockCoordinates();
        assertEquals(3, blocks[0].x);
        assertEquals(4, blocks[1].x);
        assertEquals(5, blocks[2].x);
        assertEquals(6, blocks[3].x);
        assertEquals(19, blocks[0].y);
        assertEquals(19, blocks[1].y);
        assertEquals(19, blocks[2].y);
        assertEquals(19, blocks[3].y);
        assertEquals(false, noCollision);
    }

    @Test
    public void rotateCounterclockwiseByOneWithObstacle() {
        this.playfield.reserveCellForBlock(5, 18, BlockType.S);
        boolean noCollision = this.tetromino.rotateCounterclockwise();
        Coordinate[] blocks = this.tetromino.getBlockCoordinates();
        assertEquals(3, blocks[0].x);
        assertEquals(4, blocks[1].x);
        assertEquals(5, blocks[2].x);
        assertEquals(6, blocks[3].x);
        assertEquals(19, blocks[0].y);
        assertEquals(19, blocks[1].y);
        assertEquals(19, blocks[2].y);
        assertEquals(19, blocks[3].y);
        assertEquals(false, noCollision);
    }

    @Test
    public void stepTetrominoDownByOne() {
        boolean noCollision = this.tetromino.stepDown();
        Coordinate[] blocks = this.tetromino.getBlockCoordinates();
        assertEquals(3, blocks[0].x);
        assertEquals(4, blocks[1].x);
        assertEquals(5, blocks[2].x);
        assertEquals(6, blocks[3].x);
        assertEquals(18, blocks[0].y);
        assertEquals(18, blocks[1].y);
        assertEquals(18, blocks[2].y);
        assertEquals(18, blocks[3].y);
        assertEquals(true, noCollision);
    }

    @Test
    public void stepTetrominoDownByTwenty() {
        boolean noCollision = true;
        for (int i=0; i<20; i++) noCollision = this.tetromino.stepDown();
        Coordinate[] blocks = this.tetromino.getBlockCoordinates();
        assertEquals(3, blocks[0].x);
        assertEquals(4, blocks[1].x);
        assertEquals(5, blocks[2].x);
        assertEquals(6, blocks[3].x);
        assertEquals(0, blocks[0].y);
        assertEquals(0, blocks[1].y);
        assertEquals(0, blocks[2].y);
        assertEquals(0, blocks[3].y);
        assertEquals(false, noCollision);
    }

    @Test
    public void stepTetrominoRightByOne() {
        boolean noCollision = this.tetromino.stepRight();
        Coordinate[] blocks = this.tetromino.getBlockCoordinates();
        assertEquals(4, blocks[0].x);
        assertEquals(5, blocks[1].x);
        assertEquals(6, blocks[2].x);
        assertEquals(7, blocks[3].x);
        assertEquals(19, blocks[0].y);
        assertEquals(19, blocks[1].y);
        assertEquals(19, blocks[2].y);
        assertEquals(19, blocks[3].y);
        assertEquals(true, noCollision);
    }

    @Test
    public void stepTetrominoRightByTwenty() {
        boolean noCollision = true;
        for (int i=0; i<20; i++) noCollision = this.tetromino.stepRight();
        Coordinate[] blocks = this.tetromino.getBlockCoordinates();
        assertEquals(6, blocks[0].x);
        assertEquals(7, blocks[1].x);
        assertEquals(8, blocks[2].x);
        assertEquals(9, blocks[3].x);
        assertEquals(19, blocks[0].y);
        assertEquals(19, blocks[1].y);
        assertEquals(19, blocks[2].y);
        assertEquals(19, blocks[3].y);
        assertEquals(false, noCollision);
    }

    @Test
    public void stepTetrominoLeftByOne() {
        boolean noCollision = this.tetromino.stepLeft();
        Coordinate[] blocks = this.tetromino.getBlockCoordinates();
        assertEquals(2, blocks[0].x);
        assertEquals(3, blocks[1].x);
        assertEquals(4, blocks[2].x);
        assertEquals(5, blocks[3].x);
        assertEquals(19, blocks[0].y);
        assertEquals(19, blocks[1].y);
        assertEquals(19, blocks[2].y);
        assertEquals(19, blocks[3].y);
        assertEquals(true, noCollision);
    }

    @Test
    public void stepTetrominoLeftByTwenty() {
        boolean noCollision = true;
        for (int i=0; i<20; i++) noCollision = this.tetromino.stepLeft();
        Coordinate[] blocks = this.tetromino.getBlockCoordinates();
        assertEquals(0, blocks[0].x);
        assertEquals(1, blocks[1].x);
        assertEquals(2, blocks[2].x);
        assertEquals(3, blocks[3].x);
        assertEquals(19, blocks[0].y);
        assertEquals(19, blocks[1].y);
        assertEquals(19, blocks[2].y);
        assertEquals(19, blocks[3].y);
        assertEquals(false, noCollision);
    }

    @Test
    public void tetrominoDoesNotFallThroughObstacles() {
        this.playfield.reserveCellForBlock(6, 10, BlockType.Z);
        for (int i=0; i<20; i++) this.tetromino.stepDown();
        Coordinate[] blocks = this.tetromino.getBlockCoordinates();
        assertEquals(3, blocks[0].x);
        assertEquals(4, blocks[1].x);
        assertEquals(5, blocks[2].x);
        assertEquals(6, blocks[3].x);
        assertEquals(11, blocks[0].y);
        assertEquals(11, blocks[1].y);
        assertEquals(11, blocks[2].y);
        assertEquals(11, blocks[3].y);
    }

    @Test
    public void testCollisionWithOverlappingBlock() {
        assertEquals(false, this.tetromino.detectCollisionsWithEnvironment());
        this.playfield.reserveCellForBlock(3, 19, BlockType.T);
        assertEquals(true, this.tetromino.detectCollisionsWithEnvironment());
    }
}
