package Frontend.Timing;

public class Timer implements ITimer{
    private long startTime;
    private long savedTime;
    private boolean running;

    public Timer() {
        running = false;
    }

    public void start() {
        savedTime = 0;
        startTime = System.nanoTime();
        running = true;
    }

    public long stop() {
        if (!running) {
            return savedTime;
        }
        long currentTime = System.nanoTime();
        savedTime += currentTime - startTime;
        running = false;
        return savedTime;
    }

    public void resume() {
        if (running) {
            return;
        }
        startTime = System.nanoTime();
        running = true;
    }

    public long pause() {
        if (!running) {
            return savedTime;
        }
        long currentTime = System.nanoTime();
        savedTime += currentTime - startTime;
        long toReturn = currentTime - startTime;
        startTime = currentTime;
        //return savedTime;
        return toReturn;
    }
    public float Convert(String s, long time) {
        if(s.equals(new String("ns"))) {
            //nanoseconds
            return time;
        }else
        if(s.equals(new String("s"))) {
            return (float)time/1000000000;
            //seconds
        }else
        if(s.equals(new String("ms"))) {
            //miliseconds
            return (float)time/1000000;
        }else {
            System.out.println("\nChoose between ns, s or ms");
            return -1;
        }
    }
}
