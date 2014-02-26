package Tetris;

import java.util.ArrayList;
import java.util.List;
import Tetris.DataTypes.*;

public class Playfield {

    private final int NUM_COLS = 10;
    private final int NUM_ROWS = 22;

    private BlockType[][] grid;

    public Playfield() {

        this.grid = new BlockType[NUM_ROWS][NUM_COLS];

        for (int y=0; y<NUM_ROWS; y++) {
            for (int x=0; x<NUM_COLS; x++) {
                this.grid[y][x] = BlockType.EMPTY;
            }
        }
    }

    public boolean isCellFree(int xCoord, int yCoord) {

        if (xCoord < 0 || xCoord >= NUM_COLS) return false;
        if (yCoord < 0 || yCoord >= NUM_ROWS) return false;

        if (this.grid[yCoord][xCoord] == BlockType.EMPTY) return true;
        else return false;
    }

    public void reserveCellForBlock(int xCoord, int yCoord, BlockType type) {

        if (xCoord < 0 || xCoord >= NUM_COLS) return;
        if (yCoord < 0 || yCoord >= NUM_ROWS) return;

        if (type == BlockType.EMPTY) return;
        else this.grid[yCoord][xCoord] = type;
    }

    public BlockType getCellType(int xCoord, int yCoord) {

        if (xCoord < 0 || xCoord >= NUM_COLS) return BlockType.I;
        if (yCoord < 0 || yCoord >= NUM_ROWS) return BlockType.I;

        return this.grid[yCoord][xCoord];
    }

    public void clearFullRows() {

        List<Integer> rowsToBeCleared = getListOfFullRows();
        for (int i : rowsToBeCleared) {
            clearRow(i);
        }
    }

    public List<Integer> getListOfFullRows() {

        ArrayList<Integer> fullRows = new ArrayList<Integer>();

        for (int y=NUM_ROWS-1; y>=0; y--) {

            boolean emptyCellsInThisRowFound = false;

            for (int x=0; x<NUM_COLS; x++) {
                if (this.grid[y][x] == BlockType.EMPTY) emptyCellsInThisRowFound = true;
            }

            if (!emptyCellsInThisRowFound) fullRows.add(y);
        }

        return fullRows;
    }

    public void clearRow(int rowNumber) {

        if (rowNumber < 0 || rowNumber >= NUM_ROWS) return;

        for (int x=0; x<NUM_COLS; x++) {
            this.grid[rowNumber][x] = BlockType.EMPTY;
        }

        for (int y=rowNumber; y<NUM_ROWS-1; y++) {
            for (int x=0; x<NUM_COLS; x++) {
                this.grid[y][x] = this.grid[y+1][x];
            }
        }

        for (int x=0; x<NUM_COLS; x++) {
            this.grid[NUM_ROWS-1][x] = BlockType.EMPTY;
        }
    }
}
