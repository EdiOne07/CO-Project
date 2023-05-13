package Frontend.TestBenchmark;
import Frontend.Benchmark.IBenchmark;
import Frontend.Benchmark.CPU.DigitsOfPi;
import Frontend.Timing.ITimer;
import Frontend.Timing.Timer;

public class TestDigitsOfPi {
    private long time;
    private int load;
    TestDigitsOfPi(int load){
        IBenchmark bench = new DigitsOfPi();


        bench.warmUp();
        System.out.println("Finished warming up");
        ITimer timer = new Timer();

        timer.start();
        timer.resume();
        bench.run(this.load);
        time = timer.stop();
        System.out.println(timer.Convert("s",time));
        System.out.println("Iterations " + 50 + ":"+ timer.Convert("s",time) + "s");

        System.out.println("Finished in" + timer.Convert("s",timer.stop()) +"s");
    }
    public long getTime(){
        return time;
    }
}
