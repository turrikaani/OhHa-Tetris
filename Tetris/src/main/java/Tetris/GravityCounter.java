package Tetris;

public class GravityCounter {

    private int counter;
    private int maxValue;

    public GravityCounter(int gravityLevel) {
        this.counter = 0;
        this.maxValue = GravityTable.getGravityDropFrequencyInFrames(gravityLevel);
    }

    public boolean shouldTetrominoFall() {

        if (this.counter == this.maxValue-1) return true;
        else return false;

    }

    public void tick() {
        this.counter = (this.counter+1) % this.maxValue;
    }

    public void reset() {
        this.counter = 0;
    }
}
