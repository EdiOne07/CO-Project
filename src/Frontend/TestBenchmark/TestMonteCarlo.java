package Frontend.TestBenchmark;

import Frontend.Benchmark.CPU.MonteCarloAlgorithm;
import Frontend.Benchmark.IBenchmark;
import Frontend.Timing.ITimer;
import Frontend.Timing.Timer;
import Backend.GetInfo;

public class TestMonteCarlo {
    private long time;
    private float timeInSeconds;
    private int load;
    private int score;
    private IBenchmark bench;
    private ITimer timer;
    private GetInfo info;

    public TestMonteCarlo(){
        bench = new MonteCarloAlgorithm();
        timer = new Timer();
        bench.warmUp();
        System.out.println("Finished warming up");
    }

    public void run(int load){
        this.load = load;
        info=new GetInfo();

        System.out.println("You choose the load "+ this.load);
        System.out.println("Starting the test...");

        timer.start();
        timer.resume();
        bench.run(this.load);
        time = timer.stop();
        timeInSeconds = timer.Convert("s", time);
        System.out.println("Finished in: " + timeInSeconds + " s");
        score = (int) (load*load/ Math.sqrt(timeInSeconds)/700000*2);
        info.setScoreMonteCarlo(score);
        System.out.println(info);
    }
    public float getTime(){
        return timeInSeconds;
    }
    public int getScore(){
        return score;
    }
    public int getLoad(){
        return load;
    }
}
