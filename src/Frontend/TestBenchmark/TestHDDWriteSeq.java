package Frontend.TestBenchmark;

import Frontend.Benchmark.HDD.HDDWriteBenchmark;
import Frontend.Benchmark.IBenchmark;

public class TestHDDWriteSeq {
    private IBenchmark bench;
    private long fileSize;
    private int bufferSize;
    public TestHDDWriteSeq() {
        bench = new HDDWriteBenchmark();
        bench.initialize();
    }

    public void run(long fileSize, int bufferSize) {
        this.fileSize = fileSize;
        this.bufferSize = bufferSize;

        bench.run("fs", true, fileSize, bufferSize);
        bench.clean();
        bench.run("fb", true, fileSize, bufferSize);
        bench.clean();
    }

    public void getResult() {
        System.out.println(bench.getResult());
    }
}
