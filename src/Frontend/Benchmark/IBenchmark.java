package Frontend.Benchmark;
public interface IBenchmark {
    void run();
    void run(Object ... params);
    void initialize(Object ...params);
    void clean();
    void cancel();
    void warmUp();
    String getResult();
}
