package Frontend.TestBenchmark;
import Frontend.Benchmark.IBenchmark;
import Frontend.Benchmark.CPU.DigitsOfPi;
import Frontend.Timing.ITimer;
import Frontend.Timing.Timer;

public class TestDigitsOfPi {
    private final long time;
    private float timeInSeconds;
    private final int load;
    public TestDigitsOfPi(int load){
        this.load = load;
        IBenchmark bench = new DigitsOfPi();
        ITimer timer = new Timer();

        System.out.println("You choose the load "+ this.load);
        System.out.println("Starting the test...");

        bench.warmUp();
        System.out.println("Finished warming up");

        timer.start();
        timer.resume();
        bench.run(this.load);
        time = timer.stop();
        timeInSeconds = timer.Convert("s", time);
    }
    public float getTime(){

        return timeInSeconds;
    }
    public int getLoad(){
        return load;
    }
}
