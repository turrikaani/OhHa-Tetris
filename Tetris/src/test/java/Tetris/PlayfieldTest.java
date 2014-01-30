/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Tetris;

import Tetris.DataTypes.*;
import java.util.List;
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
public class PlayfieldTest {

    private Playfield playfield;

    public PlayfieldTest() {
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
    public void newPlayfieldIsEmpty() {
        for (int y=0; y<22; y++) {
            for (int x=0; x<10; x++) {
                assertEquals(BlockType.EMPTY, this.playfield.getCellType(x, y));
            }
        }
    }

    @Test
    public void methodReserveCellWorks() {
        this.playfield.reserveCellForBlock(5, 5, BlockType.I);
        assertEquals(BlockType.I, this.playfield.getCellType(5, 5));
    }

    @Test
    public void accessingGridOutOfBounds() {
        this.playfield.reserveCellForBlock(10, 0, BlockType.J);
        this.playfield.reserveCellForBlock(0, 22, BlockType.L);
        assertEquals(BlockType.I, this.playfield.getCellType(10, 0));
        assertEquals(BlockType.I, this.playfield.getCellType(0, 22));
    }

    @Test
    public void methodGetFullRowsWorks() {
        for (int i=0; i<10; i++) {
            this.playfield.reserveCellForBlock(i, 8, BlockType.Z);
        }
        List<Integer> rivit = this.playfield.getListOfFullRows();
        assertEquals(1, rivit.size());
        assertEquals(8, (int)rivit.get(0));
    }

    @Test
    public void methodClearRowWorks() {
        for (int i=0; i<10; i++) {
            this.playfield.reserveCellForBlock(i, 8, BlockType.S);
        }
        assertEquals(BlockType.S, this.playfield.getCellType(8, 8));

        this.playfield.clearRow(8);
        List<Integer> rivit = this.playfield.getListOfFullRows();
        assertEquals(0, rivit.size());
        assertEquals(BlockType.EMPTY, this.playfield.getCellType(8, 8));
    }
}
