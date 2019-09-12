package Utils;

public class Stopwatch {

    private long start;
    private long end;
    private boolean stopped;

    public Stopwatch() {
        this.stopped = false;
        this.start = System.currentTimeMillis();
    }

    public synchronized long stop() {
        if (this.stopped) {
            throw new IllegalStateException("Stopwatch has already been stopped");
        }

        this.stopped = true;
        this.end = System.currentTimeMillis();

        return getElapsedMilliseconds();
    }

    public synchronized long getElapsedMilliseconds() {
        if (this.stopped) {
            return this.end - this.start;
        }

        throw new IllegalStateException("Stopwatch still running, cannot get elapsed time.");
    }
}
