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

     }
        public void run (int rowA, int colA, int colB, Integer b){
            this.rowA = rowA;
            this.colA = colA;
            this.rowB = colA;
            this.colB = colB;
            timer.start();
            timer.resume();
            bench.run(rowA,colA,rowB,colB);
            time = timer.stop();
            timeInSeconds = timer.Convert("s", time);
            double totalFloatingPointOperations =abs(rowA * colA * colB);
            double availableProcessorCores = Runtime.getRuntime().availableProcessors();
            double matrix_size=rowA*colB;
            double scalingFactor = 10.0 * (matrix_size / 10000);
            //double  score=matrix_size / totalFloatingPointOperations / (timeInSeconds * availableProcessorCores)*10000.0;
            double score=(Math.cbrt(totalFloatingPointOperations)/ (Math.sqrt(timeInSeconds) * availableProcessorCores))*2;
            score_final=(int)score;
            //score_final = (int) (Math.sqrt(rowA*rowB*colA*colB)/Math.sqrt(timeInSeconds)/10);

       }
        public int getScore(){
            return score_final;
        }
}
