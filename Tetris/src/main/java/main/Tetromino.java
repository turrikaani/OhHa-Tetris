package main;

public class Tetromino {

    private int type;
    private int orientation;
    private Coordinate centerPoint;

    private Coordinate[] blockCoordinates;
    private Playfield playfield;

    public Tetromino(int type, Playfield p) {

        if (type < 0 || type > 6) this.type = 0;
        else this.type = type;

        this.orientation = 0;
        this.centerPoint = new Coordinate(5, 19);

        this.blockCoordinates = new Coordinate[4];
        for (int i=0; i<4; i++) this.blockCoordinates[i] = new Coordinate(0, 0);
        updateBlockCoordinates();
        this.playfield = p;
    }

    private void updateBlockCoordinates() {

        int blockNumber, relX, relY;

        for (blockNumber=0; blockNumber<4; blockNumber++) {

            relX = TetrominoCoordinates.relativeXcoordinates[this.type][this.orientation][blockNumber];
            relY = TetrominoCoordinates.relativeYcoordinates[this.type][this.orientation][blockNumber];

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

    public int getType() {
        return this.type;
    }
}
