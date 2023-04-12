package Backend.Benchmarks;

public interface IBenchmark {
    /**
     * run() is a function that runs the benchmark, or calls the methods that make up the benchmark.
     */
    public void run();

    /**
     * result() is a function that is used to retrieve the result of the benchmark
     * @return String
     */
    public String getResult();

    /**
     * warmup() is a function that is used to get the JVM warmed up for the actual benchmark.
     * @param iterations used to specify the number of calls for run()
     */
    public void warmup(int iterations);

}
