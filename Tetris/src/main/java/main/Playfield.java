package main;

import java.util.ArrayList;
import java.util.List;

public class Playfield {

    private final int NUM_COLS = 10;
    private final int NUM_ROWS = 22;

    private int[][] grid = new int[NUM_ROWS][NUM_COLS];

    public boolean isCellFree(int xCoord, int yCoord) {

        if (xCoord < 0 || xCoord >= NUM_COLS) return false;
        if (yCoord < 0 || yCoord >= NUM_ROWS) return false;

        if (grid[yCoord][xCoord] == 0) return true;
        else return false;
    }

    public void reserveCellForBlock(int xCoord, int yCoord, int blockType) {

        if (xCoord < 0 || xCoord >= NUM_COLS) return;
        if (yCoord < 0 || yCoord >= NUM_ROWS) return;

        if (blockType < 1 || blockType > 7) return;
        else grid[yCoord][xCoord] = blockType;
    }

    public int getCellType(int xCoord, int yCoord) {

        if (xCoord < 0 || xCoord >= NUM_COLS) return 0;
        if (yCoord < 0 || yCoord >= NUM_ROWS) return 0;

        return grid[yCoord][xCoord];
    }

    public List<Integer> getListOfFullRows() {

        ArrayList<Integer> fullRows = new ArrayList<Integer>();

        for (int y=0; y<NUM_ROWS; y++) {

            boolean emptyCellsInThisRowFound = false;

            for (int x=0; x<NUM_COLS; x++) {
                if (grid[y][x] == 0) emptyCellsInThisRowFound = true;
            }

            if (!emptyCellsInThisRowFound) fullRows.add(y);
        }

        return fullRows;
    }

    public void clearRow(int rowNumber) {

        int x, y;

        for (x=0; x<NUM_COLS; x++) grid[rowNumber][x] = 0;

        for (y=rowNumber; y<NUM_ROWS-1; y++) {
            for (x=0; x<NUM_COLS; x++) {
                grid[y][x] = grid[y+1][x];
            }
        }

        for (x=0; x<NUM_COLS; x++) grid[NUM_ROWS-1][x] = 0;
    }
}
