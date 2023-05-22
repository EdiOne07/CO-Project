package Frontend.TestBenchmark;

import Frontend.Benchmark.HDD.HDDWriteBenchmark;
import Frontend.Benchmark.IBenchmark;
import Frontend.Timing.ITimer;
import Frontend.Timing.Timer;

public class TestHDDWriteSeq {
    private IBenchmark bench;
    private ITimer timer;
    private long totalTime;
    private long fileSize;
    private int bufferSize;
    public TestHDDWriteSeq() {
        bench = new HDDWriteBenchmark();
        timer = new Timer();
        bench.initialize();
    }

    public void run(long fileSize, int bufferSize) {
        this.fileSize = fileSize;
        this.bufferSize = bufferSize;
        timer.start();
        bench.run("fb", true, fileSize, bufferSize);
        timer.pause();
        bench.clean();
        timer.resume();
        bench.run("fs", true, fileSize, bufferSize);
        totalTime = timer.stop();
        bench.clean();
    }

    public void getResult() {
        System.out.println(bench.getResult());
    }

    public int getScore() {
        double score = (double) (fileSize * bufferSize) / (totalTime);
        return (int) Math.abs(score);
    }
}
