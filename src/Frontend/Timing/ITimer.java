package Frontend.Timing;

public interface ITimer {
    void start();
    long stop();
    void resume();
    long pause();
    float Convert(String s, long time);

}
