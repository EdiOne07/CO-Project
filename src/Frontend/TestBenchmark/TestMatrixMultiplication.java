package Frontend.TestBenchmark;

import Frontend.Benchmark.CPU.MatrixMultiplication;
import Frontend.Benchmark.IBenchmark;
import Frontend.Timing.ITimer;
import Frontend.Timing.Timer;

public class TestMatrixMultiplication {
    private long time;
    private float timeInSeconds;
    private int rowA;
    private int colA;
    private int rowB;
    private int colB;
    private IBenchmark bench;
    private ITimer timer;

    public TestMatrixMultiplication(){
        bench = new MatrixMultiplication();
        timer = new Timer();
        bench.warmUp();
        System.out.println("Finished warming up");

    }
    public void run(int rowA,int colA, int rowB, int colB){
        this.rowA = rowA;
        this.colA=colA;
        this.rowB=rowB;
        this.colB=colB;

        System.out.println("You choose the dimensions"+ this.rowA+this.colA+this.rowB+this.colB);
        System.out.println("Starting the test...");

        timer.start();
        timer.resume();
        bench.run(this.rowA,this.colA,this.rowB,this.colB);
        time = timer.stop();
        timeInSeconds = timer.Convert("s", time);
    }
    public float getTime(){

        return timeInSeconds;
    }
}
