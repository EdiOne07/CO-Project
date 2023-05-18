package Frontend.TestBenchmark;

import Frontend.Benchmark.HDD.HDDRandomAccess;
import Frontend.Benchmark.IBenchmark;

public class TestRandomHDD {
    private IBenchmark bench;
    private long fileSize;
    private int bufferSize;
    private StringBuilder result;
    public TestRandomHDD() {
        bench = new HDDRandomAccess();
        result = new StringBuilder();
    }

    public void initialize(long fileSize) {
        this.fileSize = fileSize;
        bench.initialize(fileSize);
    }

    public void run(int bufferSize) {
        this.bufferSize = bufferSize;
        bench.run("w", "ft", bufferSize);
        result.append(bench.getResult()).append("\n");
        bench.run("w", "fs", bufferSize);
        result.append(bench.getResult()).append("\n");
        bench.run("r", "ft", bufferSize);
        result.append(bench.getResult()).append("\n");
        bench.run("r", "fs", bufferSize);
        result.append(bench.getResult()).append("\n");
    }

    public void clean() {
        bench.clean();
    }

    public void getResult() {
        System.out.println(result);
    }
}
