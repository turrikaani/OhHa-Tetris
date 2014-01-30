package Tetris;

import Tetris.DataTypes.*;

public class Tetromino {

    private TetrominoType type;
    private int orientation;
    private Coordinate centerPoint;
    private Coordinate[] blockCoordinates;
    private Playfield playfield;

    public Tetromino(TetrominoType type, Playfield p) {

        this.type = type;
        this.orientation = 0;
        this.centerPoint = new Coordinate(5, 19);

        this.blockCoordinates = new Coordinate[4];
        for (int i=0; i<4; i++) this.blockCoordinates[i] = new Coordinate(0, 0);
        updateBlockCoordinates();

        this.playfield = p;
    }

    private void updateBlockCoordinates() {

        int blockNumber, relX, relY;

        int[][] relativeXcoordinates = BlockCoordinates.getRelativeXcoordinates(this.type);
        int[][] relativeYcoordinates = BlockCoordinates.getRelativeYcoordinates(this.type);

        for (blockNumber=0; blockNumber<4; blockNumber++) {

            relX = relativeXcoordinates[this.orientation][blockNumber];
            relY = relativeYcoordinates[this.orientation][blockNumber];

            this.blockCoordinates[blockNumber].x = this.centerPoint.x + relX;
            this.blockCoordinates[blockNumber].y = this.centerPoint.y + relY;
        }
    }

    public boolean detectCollisionsWithEnvironment() {

        boolean cellIsFree;

        for (int i=0; i<4; i++) {
            cellIsFree = this.playfield.isCellFree(this.blockCoordinates[i].x, this.blockCoordinates[i].y);
            if (!cellIsFree) return true;
        }

        return false;
    }

    private void incrementOrientation() {
        this.orientation++;
        if (this.orientation == 4) this.orientation = 0;
    }

    private void decrementOrientation() {
        this.orientation--;
        if (this.orientation == -1) this.orientation = 3;
    }

    public boolean stepLeft() {

        this.centerPoint.x--;
        updateBlockCoordinates();
        boolean collision = detectCollisionsWithEnvironment();

        if (collision) {
            this.centerPoint.x++;
            updateBlockCoordinates();
            return false;
        }
        else return true;
    }

    public boolean stepRight() {

        this.centerPoint.x++;
        updateBlockCoordinates();
        boolean collision = detectCollisionsWithEnvironment();

        if (collision) {
            this.centerPoint.x--;
            updateBlockCoordinates();
            return false;
        }
        else return true;
    }

    public boolean stepDown() {

        this.centerPoint.y--;
        updateBlockCoordinates();
        boolean collision = detectCollisionsWithEnvironment();

        if (collision) {
            this.centerPoint.y++;
            updateBlockCoordinates();
            return false;
        }
        else return true;
    }

    public boolean rotateClockwise() {

        incrementOrientation();
        updateBlockCoordinates();
        boolean collision = detectCollisionsWithEnvironment();

        if (collision) {
            decrementOrientation();
            updateBlockCoordinates();
            return false;
        }
        else return true;
    }

    public boolean rotateCounterclockwise() {

        decrementOrientation();
        updateBlockCoordinates();
        boolean collision = detectCollisionsWithEnvironment();

        if (collision) {
            incrementOrientation();
            updateBlockCoordinates();
            return false;
        }
        else return true;
    }

    public Coordinate[] getBlockCoordinates() {

        Coordinate[] coordinates = new Coordinate[4];

        for (int i=0; i<4; i++) {
            coordinates[i] = new Coordinate(this.blockCoordinates[i].x, this.blockCoordinates[i].y);
        }

        return coordinates;
    }

    public BlockType getType() {

        switch (this.type) {
            case I: return BlockType.I;
            case J: return BlockType.J;
            case L: return BlockType.L;
            case O: return BlockType.O;
            case S: return BlockType.S;
            case T: return BlockType.T;
            case Z: return BlockType.Z;
        }

        return BlockType.Z;
    }
}
