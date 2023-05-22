package Frontend.TestBenchmark;

import Frontend.Benchmark.CPU.MonteCarloAlgorithm;
import Frontend.Benchmark.IBenchmark;
import Frontend.Timing.ITimer;
import Frontend.Timing.Timer;

public class TestMonteCarlo {
    private long time;
    private float timeInSeconds;
    private int load;
    private float score;
    private IBenchmark bench;
    private ITimer timer;

    public TestMonteCarlo(){
        bench = new MonteCarloAlgorithm();
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
        System.out.println("Finished in: " + timeInSeconds + " s");
        score = (float) (load/ Math.sqrt(timeInSeconds)/70*2);
    }
    public float getTime(){
        return timeInSeconds;
    }
    public float getScore(){
        return score;
    }
    public int getLoad(){
        return load;
    }
}
