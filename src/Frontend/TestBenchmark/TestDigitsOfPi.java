package Frontend.TestBenchmark;
import Frontend.Benchmark.IBenchmark;
import Frontend.Benchmark.CPU.DigitsOfPi;
import Frontend.Timing.ITimer;
import Frontend.Timing.Timer;
import Backend.GetInfo;

public class TestDigitsOfPi {
    private long time;
    private float timeInSeconds;
    private int load;
    private IBenchmark bench;
    private ITimer timer;
    private int score;
    private GetInfo info;

    public TestDigitsOfPi(){
        bench = new DigitsOfPi();
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
        score = (int)(load/ Math.sqrt(timeInSeconds)/50*3);
        info.setScoreDigitsOfPi(score);
        System.out.println(info);

    }
    public float getTime(){
        return timeInSeconds;
    }

    /*public void setScore() {
        info=new GetInfo();
        info.setScoreDigitsOfPi(score);
    }
*/
    public int getLoad(){
        return load;
    }
    public int getScore(){
        return score;
    }
}
