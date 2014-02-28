package Tetris;

public class Clock {

    private long nanosecsToWait;
    private long lastTime;

    public Clock(long millisecsToWait) {

        if (millisecsToWait < 1) millisecsToWait = 16;
        this.nanosecsToWait = 1000000 * millisecsToWait;
        this.lastTime = System.nanoTime();
    }

    public void restart() {
        this.lastTime = System.nanoTime();
    }

    public void waitRelativeToPreviousCall() {

        while (true) {

            long currentTime = System.nanoTime();

            if (currentTime - this.lastTime >= this.nanosecsToWait) {
                this.lastTime = currentTime;
                return;
            }
            else waitMilliseconds(1);
        }
    }

    public void waitMilliseconds(long millisecsToWait) {

        if (millisecsToWait < 1) millisecsToWait = 1;

        try {
            Thread.sleep(millisecsToWait);
        }
        catch (InterruptedException e) {
            return;
        }
    }
}
