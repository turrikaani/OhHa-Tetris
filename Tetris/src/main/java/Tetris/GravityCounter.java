package Tetris;

public class GravityCounter {

    private int counter;
    private int maxValue;
    private int[] gravityTable = {48, 43, 38, 33, 28, 23, 18, 13, 8, 6};

    public GravityCounter(int gravityLevel) {
        this.counter = 0;
        this.maxValue = getMaxValue(gravityLevel);
    }

    private int getMaxValue(int gravityLevel) {

        if (gravityLevel < 1 || gravityLevel > 10) return this.gravityTable[0];
        else return this.gravityTable[gravityLevel-1];
    }

    public boolean shouldTetrominoFall() {

        boolean retval;

        if (this.counter == this.maxValue-1) retval = true;
        else retval = false;

        this.counter = (this.counter+1) % this.maxValue;
        return retval;
    }

    public void reset() {
        this.counter = 0;
    }
}
