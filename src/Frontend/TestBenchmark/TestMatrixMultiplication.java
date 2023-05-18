package Frontend.TestBenchmark;

import Frontend.Benchmark.CPU.MatrixMultiplication;
import Frontend.Benchmark.IBenchmark;
import Frontend.Timing.ITimer;
import Frontend.Timing.Timer;

import static java.lang.Math.abs;

public class TestMatrixMultiplication {

        long time;
         float timeInSeconds;
         int rowA;
         int colA;
         int rowB;
         int colB;
         IBenchmark bench;
         ITimer timer;

    public TestMatrixMultiplication() {
            bench = new MatrixMultiplication();
            timer = new Timer();
            bench.warmUp();
            System.out.println("Finished warming up");

     }
        public void run ( int rowA, int colA, int rowB, int colB){
            this.rowA = rowA;
            this.colA = colA;
            this.rowB = rowB;
            this.colB = colB;

            System.out.println("You choose the dimensions" + this.rowA + this.colA + this.rowB + this.colB);
            System.out.println("Starting the test...");

            timer.start();
            timer.resume();
            bench.run(200,155,300,325);
            time = timer.stop();
            timeInSeconds = timer.Convert("s", time);
            System.out.println("Finished in"+ timeInSeconds);
            double totalFloatingPointOperations = rowA * colA * colB;
            System.out.println("The total number of operations is:"+totalFloatingPointOperations);
            double availableProcessorCores = Runtime.getRuntime().availableProcessors();
            System.out.println("The number of available threads is:"+ availableProcessorCores);
            double matrix_size=rowA*colB;
            System.out.println("The size of the new matrix is:"+ matrix_size);
            double score=(totalFloatingPointOperations/(timeInSeconds*availableProcessorCores))*matrix_size*10.0;
            System.out.println("The score is:"+score);
       }
        public float getTime () {

            return timeInSeconds;
        }
}
