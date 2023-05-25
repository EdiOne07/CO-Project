package Frontend.TestBenchmark;

import Frontend.Benchmark.HDD.HDDRandomAccess;
import Frontend.Benchmark.IBenchmark;
import Frontend.Timing.ITimer;
import Frontend.Timing.Timer;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import Backend.GetInfo;

public class TestRandomHDD {
    private IBenchmark bench;
    private ITimer timer;
    private long fileSize;
    private int bufferSize;
    private long totalTime;
    private StringBuilder result;
    private GetInfo info;
    public TestRandomHDD() {
        bench = new HDDRandomAccess();
        result = new StringBuilder();
        timer = new Timer();
    }

    public void initialize(long fileSize) {
        this.fileSize = fileSize;
        bench.initialize(fileSize);
    }

    public void run(int bufferSize) {
        info=new GetInfo();
        this.bufferSize = bufferSize;
        timer.start();
        bench.run("w", "ft", bufferSize);
        timer.pause();
        result.append(bench.getResult()).append("\n");
        timer.resume();
        bench.run("w", "fs", bufferSize);
        timer.pause();
        result.append(bench.getResult()).append("\n");
        timer.resume();
        bench.run("r", "ft", bufferSize);
        timer.pause();
        result.append(bench.getResult()).append("\n");
        timer.resume();
        bench.run("r", "fs", bufferSize);
        totalTime = timer.stop();
        result.append(bench.getResult());
    }

    public void clean() {
        bench.clean();
    }

    public String getResult() {
        NumberFormat nf = new DecimalFormat("#.00");
        double score = (double) ((fileSize * bufferSize) / (totalTime*10));
        info.setScoreRandomAcces((int)score);
        return  Integer.valueOf((int) score).toString();
    }
}
