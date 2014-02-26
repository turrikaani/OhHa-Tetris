package Tetris;

public class GravityTable {

    private static int[] gTable = {48, 43, 38, 33, 28, 23, 18, 13, 8, 6};

    public static int getGravityDropFrequencyInFrames(int level) {

        if (level < 1 || level > 10) return gTable[0];
        else return gTable[level-1];
    }
}
