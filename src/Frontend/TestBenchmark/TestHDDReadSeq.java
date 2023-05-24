package Frontend.TestBenchmark;

import Frontend.Benchmark.HDD.HDDReadingBenchmark;
import Frontend.Benchmark.IBenchmark;
import Frontend.Timing.ITimer;
import Frontend.Timing.Timer;
import Backend.GetInfo;

public class TestHDDReadSeq {
    private IBenchmark bench;
    private ITimer timer;
    private int num_files;
    private int block_size;
    private int num_blocks;
    private long totalTime;
    private GetInfo info;
    public TestHDDReadSeq() {
        bench = new HDDReadingBenchmark();
        timer = new Timer();
    }
    public void initialize(int num_files, int block_size, int num_blocks) {
        info=new GetInfo();
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

    public void getResult() {
        System.out.println("File read on primary partition: " + bench.getResult());
    }
    public int getScore() {
        double score = (double) (1000 * (num_blocks * block_size)) / (totalTime);
        info.setScoreHddWrite((int)score);
        return (int) Math.abs(score);
    }
}
