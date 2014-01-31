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
public class Tetromino1stTest {

    private Playfield playfield;
    private Tetromino tetromino;

    public Tetromino1stTest() {
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
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void type_I_tetrominoBlockPositions() {
        this.tetromino = new Tetromino(TetrominoType.I, this.playfield);
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
    public void type_J_tetrominoBlockPositions() {
        this.tetromino = new Tetromino(TetrominoType.J, this.playfield);
        Coordinate[] blocks = this.tetromino.getBlockCoordinates();
        assertEquals(4, blocks[0].x);
        assertEquals(5, blocks[1].x);
        assertEquals(6, blocks[2].x);
        assertEquals(6, blocks[3].x);
        assertEquals(19, blocks[0].y);
        assertEquals(19, blocks[1].y);
        assertEquals(19, blocks[2].y);
        assertEquals(18, blocks[3].y);
    }

    @Test
    public void type_L_tetrominoBlockPositions() {
        this.tetromino = new Tetromino(TetrominoType.L, this.playfield);
        Coordinate[] blocks = this.tetromino.getBlockCoordinates();
        assertEquals(6, blocks[0].x);
        assertEquals(5, blocks[1].x);
        assertEquals(4, blocks[2].x);
        assertEquals(4, blocks[3].x);
        assertEquals(19, blocks[0].y);
        assertEquals(19, blocks[1].y);
        assertEquals(19, blocks[2].y);
        assertEquals(18, blocks[3].y);
    }

    @Test
    public void type_O_tetrominoBlockPositions() {
        this.tetromino = new Tetromino(TetrominoType.O, this.playfield);
        Coordinate[] blocks = this.tetromino.getBlockCoordinates();
        assertEquals(4, blocks[0].x);
        assertEquals(4, blocks[1].x);
        assertEquals(5, blocks[2].x);
        assertEquals(5, blocks[3].x);
        assertEquals(18, blocks[0].y);
        assertEquals(19, blocks[1].y);
        assertEquals(19, blocks[2].y);
        assertEquals(18, blocks[3].y);
    }

    @Test
    public void type_S_tetrominoBlockPositions() {
        this.tetromino = new Tetromino(TetrominoType.S, this.playfield);
        Coordinate[] blocks = this.tetromino.getBlockCoordinates();
        assertEquals(4, blocks[0].x);
        assertEquals(5, blocks[1].x);
        assertEquals(5, blocks[2].x);
        assertEquals(6, blocks[3].x);
        assertEquals(18, blocks[0].y);
        assertEquals(18, blocks[1].y);
        assertEquals(19, blocks[2].y);
        assertEquals(19, blocks[3].y);
    }

    @Test
    public void type_T_tetrominoBlockPositions() {
        this.tetromino = new Tetromino(TetrominoType.T, this.playfield);
        Coordinate[] blocks = this.tetromino.getBlockCoordinates();
        assertEquals(4, blocks[0].x);
        assertEquals(5, blocks[1].x);
        assertEquals(6, blocks[2].x);
        assertEquals(5, blocks[3].x);
        assertEquals(19, blocks[0].y);
        assertEquals(19, blocks[1].y);
        assertEquals(19, blocks[2].y);
        assertEquals(18, blocks[3].y);
    }

    @Test
    public void type_Z_tetrominoBlockPositions() {
        this.tetromino = new Tetromino(TetrominoType.Z, this.playfield);
        Coordinate[] blocks = this.tetromino.getBlockCoordinates();
        assertEquals(6, blocks[0].x);
        assertEquals(5, blocks[1].x);
        assertEquals(5, blocks[2].x);
        assertEquals(4, blocks[3].x);
        assertEquals(18, blocks[0].y);
        assertEquals(18, blocks[1].y);
        assertEquals(19, blocks[2].y);
        assertEquals(19, blocks[3].y);
    }

    @Test
    public void methodGetBlockTypeWorks() {
        Tetromino i = new Tetromino(TetrominoType.I, this.playfield);
        Tetromino j = new Tetromino(TetrominoType.J, this.playfield);
        Tetromino l = new Tetromino(TetrominoType.L, this.playfield);
        Tetromino o = new Tetromino(TetrominoType.O, this.playfield);
        Tetromino s = new Tetromino(TetrominoType.S, this.playfield);
        Tetromino t = new Tetromino(TetrominoType.T, this.playfield);
        Tetromino z = new Tetromino(TetrominoType.Z, this.playfield);
        assertEquals(BlockType.I, i.getBlockType());
        assertEquals(BlockType.J, j.getBlockType());
        assertEquals(BlockType.L, l.getBlockType());
        assertEquals(BlockType.O, o.getBlockType());
        assertEquals(BlockType.S, s.getBlockType());
        assertEquals(BlockType.T, t.getBlockType());
        assertEquals(BlockType.Z, z.getBlockType());
    }
}
