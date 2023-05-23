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
         int score_final;

    public TestMatrixMultiplication() {
            bench = new MatrixMultiplication();
            timer = new Timer();
            bench.warmUp();
            System.out.println("Finished warming up");

     }
        public void run (int rowA, int colA, int colB, Integer b){
            this.rowA = rowA;
            this.colA = colA;
            this.rowB = colA;
            this.colB = colB;

            System.out.println("You choose the dimensions" + this.rowA + this.colA + this.rowB + this.colB);
            System.out.println("Starting the test...");

            timer.start();
            timer.resume();
            bench.run(rowA,colA,rowB,colB);
            time = timer.stop();
            timeInSeconds = timer.Convert("s", time);
            System.out.println("Finished in"+ timeInSeconds);
            double totalFloatingPointOperations =abs(rowA * colA * colB);
            System.out.println("The total number of operations is:"+totalFloatingPointOperations);
            double availableProcessorCores = Runtime.getRuntime().availableProcessors();
            System.out.println("The number of available threads is:"+ availableProcessorCores);
            double matrix_size=rowA*colB;
            System.out.println("The size of the new matrix is:"+ matrix_size);
            double scalingFactor = 10.0 * (matrix_size / 10000);
            double  score=matrix_size / totalFloatingPointOperations / (timeInSeconds * availableProcessorCores)*10000.0;
            System.out.println("The score is:"+score);
            score_final=(int)score;
            //score_final = (int) (Math.sqrt(rowA*rowB*colA*colB)/Math.sqrt(timeInSeconds)/10);

       }
        public float getTime () {

            return timeInSeconds;
        }
        public int getScore(){
            return score_final;
        }
}
