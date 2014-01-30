package Tetris;

import Tetris.DataTypes.*;

public class BlockCoordinates {

    private static int[][][] relativeXcoordinates =
    {
        {{-2,-1,+0,+1}, {+0,+0,+0,+0}, {+1,+0,-1,-2}, {+0,+0,+0,+0}},  // type I tetromino (orientations 0-3)
        {{-1,+0,+1,+1}, {+0,+0,+0,-1}, {+1,+0,-1,-1}, {+0,+0,+0,+1}},  // type J tetromino (orientations 0-3)
        {{+1,+0,-1,-1}, {+0,+0,+0,-1}, {-1,+0,+1,+1}, {+0,+0,+0,+1}},  // type L tetromino (orientations 0-3)
        {{-1,-1,+0,+0}, {-1,+0,+0,-1}, {+0,+0,-1,-1}, {+0,-1,-1,+0}},  // type O tetromino (orientations 0-3)
        {{-1,+0,+0,+1}, {+0,+0,+1,+1}, {+1,+0,+0,-1}, {+1,+1,+0,+0}},  // type S tetromino (orientations 0-3)
        {{-1,+0,+1,+0}, {+0,+0,+0,-1}, {+1,+0,-1,+0}, {+0,+0,+0,+1}},  // type T tetromino (orientations 0-3)
        {{+1,+0,+0,-1}, {+0,+0,+1,+1}, {-1,+0,+0,+1}, {+1,+1,+0,+0}}   // type Z tetromino (orientations 0-3)
    };

    private static int[][][] relativeYcoordinates =
    {
        {{+0,+0,+0,+0}, {+2,+1,+0,-1}, {+0,+0,+0,+0}, {-1,+0,+1,+2}},  // type I tetromino (orientations 0-3)
        {{+0,+0,+0,-1}, {+1,+0,-1,-1}, {+0,+0,+0,+1}, {-1,+0,+1,+1}},  // type J tetromino (orientations 0-3)
        {{+0,+0,+0,-1}, {-1,+0,+1,+1}, {+0,+0,+0,+1}, {+1,+0,-1,-1}},  // type L tetromino (orientations 0-3)
        {{-1,+0,+0,-1}, {+0,+0,-1,-1}, {+0,-1,-1,+0}, {-1,-1,+0,+0}},  // type O tetromino (orientations 0-3)
        {{-1,-1,+0,+0}, {+1,+0,+0,-1}, {+0,+0,-1,-1}, {-1,+0,+0,+1}},  // type S tetromino (orientations 0-3)
        {{+0,+0,+0,-1}, {+1,+0,-1,+0}, {+0,+0,+0,+1}, {-1,+0,+1,+0}},  // type T tetromino (orientations 0-3)
        {{-1,-1,+0,+0}, {-1,+0,+0,+1}, {+0,+0,-1,-1}, {+1,+0,+0,-1}}   // type Z tetromino (orientations 0-3)
    };

    public static int[][] getRelativeXcoordinates(TetrominoType type) {

        switch (type) {
            case I: return relativeXcoordinates[0];
            case J: return relativeXcoordinates[1];
            case L: return relativeXcoordinates[2];
            case O: return relativeXcoordinates[3];
            case S: return relativeXcoordinates[4];
            case T: return relativeXcoordinates[5];
            case Z: return relativeXcoordinates[6];
        }

        return relativeXcoordinates[0];
    }

    public static int[][] getRelativeYcoordinates(TetrominoType type) {

        switch (type) {
            case I: return relativeYcoordinates[0];
            case J: return relativeYcoordinates[1];
            case L: return relativeYcoordinates[2];
            case O: return relativeYcoordinates[3];
            case S: return relativeYcoordinates[4];
            case T: return relativeYcoordinates[5];
            case Z: return relativeYcoordinates[6];
        }

        return relativeYcoordinates[0];
    }
}
