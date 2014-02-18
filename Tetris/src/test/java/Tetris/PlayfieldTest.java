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

    private void fillRow(int rowNumber) {
        for (int i=0; i<10; i++) {
            this.playfield.reserveCellForBlock(i, rowNumber, BlockType.S);
        }
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
        assertEquals(BlockType.EMPTY, this.playfield.getCellType(4, 4));
        assertEquals(BlockType.EMPTY, this.playfield.getCellType(4, 5));
        assertEquals(BlockType.EMPTY, this.playfield.getCellType(4, 6));
        assertEquals(BlockType.EMPTY, this.playfield.getCellType(5, 4));
        assertEquals(BlockType.EMPTY, this.playfield.getCellType(5, 6));
        assertEquals(BlockType.EMPTY, this.playfield.getCellType(6, 4));
        assertEquals(BlockType.EMPTY, this.playfield.getCellType(6, 5));
        assertEquals(BlockType.EMPTY, this.playfield.getCellType(6, 6));
    }

    @Test
    public void methodReserveCellCanNotSetBlockTypeToEmpty() {
        this.playfield.reserveCellForBlock(5, 5, BlockType.I);
        this.playfield.reserveCellForBlock(5, 5, BlockType.EMPTY);
        assertEquals(BlockType.I, this.playfield.getCellType(5, 5));
    }

    @Test
    public void setAndGetBlockTypeOutsideOfGridBoundaries() {
        this.playfield.reserveCellForBlock(10, 0, BlockType.J);
        this.playfield.reserveCellForBlock(0, 22, BlockType.L);
        this.playfield.reserveCellForBlock(-1, 0, BlockType.O);
        this.playfield.reserveCellForBlock(0, -1, BlockType.S);
        assertEquals(BlockType.I, this.playfield.getCellType(10, 0));
        assertEquals(BlockType.I, this.playfield.getCellType(0, 22));
        assertEquals(BlockType.I, this.playfield.getCellType(-1, 0));
        assertEquals(BlockType.I, this.playfield.getCellType(0, -1));
    }

    @Test
    public void methodIsCellFreeWorks() {
        this.playfield.reserveCellForBlock(2, 3, BlockType.Z);
        assertEquals(false, this.playfield.isCellFree(2, 3));
        assertEquals(true, this.playfield.isCellFree(3, 2));
        assertEquals(false, this.playfield.isCellFree(10, 0));
        assertEquals(false, this.playfield.isCellFree(0, 22));
        assertEquals(false, this.playfield.isCellFree(-1, 0));
        assertEquals(false, this.playfield.isCellFree(0, -1));
    }

    @Test
    public void methodGetFullRowsWorks() {
        fillRow(8);
        fillRow(0);
        List<Integer> rivit = this.playfield.getListOfFullRows();
        assertEquals(2, rivit.size());
        assertEquals(8, rivit.get(0).intValue());
        assertEquals(0, rivit.get(1).intValue());
    }

    @Test
    public void clearingFullRowWorks() {
        fillRow(8);
        assertEquals(BlockType.S, this.playfield.getCellType(8, 8));

        this.playfield.clearRow(8);

        List<Integer> rivit = this.playfield.getListOfFullRows();
        assertEquals(0, rivit.size());
        assertEquals(BlockType.EMPTY, this.playfield.getCellType(8, 8));
    }

    @Test
    public void clearingRowBelowFullRowWorks() {
        fillRow(8);
        assertEquals(BlockType.S, this.playfield.getCellType(8, 8));

        this.playfield.clearRow(7);

        List<Integer> rivit = this.playfield.getListOfFullRows();
        assertEquals(1, rivit.size());
        assertEquals(BlockType.EMPTY, this.playfield.getCellType(8, 8));
        assertEquals(BlockType.S, this.playfield.getCellType(8, 7));
    }

    @Test
    public void clearingInvalidRow() {
        fillRow(8);
        assertEquals(BlockType.S, this.playfield.getCellType(8, 8));

        this.playfield.clearRow(22);

        List<Integer> rivit = this.playfield.getListOfFullRows();
        assertEquals(1, rivit.size());
        assertEquals(BlockType.S, this.playfield.getCellType(8, 8));
    }

    @Test
    public void clearingHighestRow() {
        fillRow(21);
        assertEquals(BlockType.S, this.playfield.getCellType(8, 21));

        this.playfield.clearRow(21);

        List<Integer> rivit = this.playfield.getListOfFullRows();
        assertEquals(0, rivit.size());
        assertEquals(BlockType.EMPTY, this.playfield.getCellType(8, 21));
    }
}
