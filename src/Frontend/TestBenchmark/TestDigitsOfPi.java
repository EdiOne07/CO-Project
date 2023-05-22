package Frontend.TestBenchmark;
import Frontend.Benchmark.IBenchmark;
import Frontend.Benchmark.CPU.DigitsOfPi;
import Frontend.Timing.ITimer;
import Frontend.Timing.Timer;

public class TestDigitsOfPi {
    private long time;
    private float timeInSeconds;
    private int load;
    private IBenchmark bench;
    private ITimer timer;
    private int score;

    public TestDigitsOfPi(){
        bench = new DigitsOfPi();
        timer = new Timer();
        bench.warmUp();
        System.out.println("Finished warming up");

    }
    public void run(int load){
        this.load = load;

        System.out.println("You choose the load "+ this.load);
        System.out.println("Starting the test...");

        timer.start();
        timer.resume();
        bench.run(this.load);
        time = timer.stop();
        timeInSeconds = timer.Convert("s", time);
        score = (int)(load/ Math.sqrt(timeInSeconds)/50*3);

    }
    public float getTime(){

        return timeInSeconds;
    }
    public int getLoad(){
        return load;
    }
    public int getScore(){
        return score;
    }
}
