package Frontend.TestBenchmark;

import Frontend.Benchmark.HDD.HDDReadingBenchmark;
import Frontend.Benchmark.IBenchmark;
import Frontend.Timing.ITimer;
import Frontend.Timing.Timer;

public class TestHDDReadSeq {
    private IBenchmark bench;
    private ITimer timer;
    private int num_files;
    private int block_size;
    private int num_blocks;
    private long totalTime;
    public TestHDDReadSeq() {
        bench = new HDDReadingBenchmark();
        timer = new Timer();
    }
    public void initialize(int num_files, int block_size, int num_blocks) {
        this.num_files = num_files;
        this.block_size = block_size;
        this.num_blocks = num_blocks;
        bench.initialize(num_files, block_size, num_blocks);
    }
    public void run() {
        timer.start();
        bench.run();
        totalTime = timer.stop();
    }

    public void clean() {
        bench.clean();
    }

    public int getScore() {
        double score = (double) (1000 * (num_blocks * block_size)) / (totalTime);
        return (int) Math.abs(score);
    }
}
