package Frontend.Benchmark.CPU;

import Frontend.Benchmark.IBenchmark;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MatrixMultiplication implements IBenchmark {
    @Override
    public void run() {
        double[][] A= new double[25][25];
        double[][] B= new double[25][25];
        int m = A.length;
        int n = A[0].length;
        int p = B[0].length;

        double[][] C = new double[m][p];
        Random random = new Random();
        for (int i = 0; i < 25; i++) {
            for (int j = 0; j < 25; j++) {
                A[i][j] = random.nextDouble();  // Random double between 0.0 (inclusive) and 1.0 (exclusive)
            }
        }
        for (int i = 0; i < 25; i++) {
            for (int j = 0; j < 25; j++) {
                B[i][j] = random.nextDouble();  // Random double between 0.0 (inclusive) and 1.0 (exclusive)
            }
        }

        // Create threads to compute each row of the result matrix
        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            final int row = i;
            threads.add(new Thread(() -> {
                for (int j = 0; j < p; j++) {
                    double sum = 0;
                    for (int k = 0; k < n; k++) {
                        sum += A[row][k] * B[k][j];
                    }
                    C[row][j] = sum;
                }
            }));
        }

        // Start the threads
        for (Thread t : threads) {
            t.start();
        }

        // Wait for all threads to finish
        for (Thread t : threads) {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
/*
        System.out.println("Result:");
        for (int i = 0; i < C.length; i++) {
            for (int j = 0; j < C[0].length; j++) {
                System.out.print(C[i][j] + " ");
            }
            System.out.println();
        }*/
    }

    @Override
    public void run(Object... params) {

        // Ask the user for the dimensions of matrix A
        System.out.print("Enter the number of rows for matrix A: ");
        int aRows = (Integer)params[0];
        System.out.print("Enter the number of columns for matrix A: ");
        int aCols = (Integer)params[1];

        // Ask the user for the dimensions of matrix B
        System.out.print("Enter the number of rows for matrix B: ");
        int bRows = (Integer)params[1];
        System.out.print("Enter the number of columns for matrix B: ");
        int bCols = (Integer)params[2];

        // Initialize matrices A and B
        double[][] matrixA = new double[aRows][aCols];
        double[][] matrixB = new double[bRows][bCols];

        // Populate matrices A and B with random values
        for (int i = 0; i < aRows; i++) {
            for (int j = 0; j < aCols; j++) {
                matrixA[i][j] = Math.random();
            }
        }
        for (int i = 0; i < bRows; i++) {
            for (int j = 0; j < bCols; j++) {
                matrixB[i][j] = Math.random();
            }
        }

        // Perform matrix multiplication
        double[][] result = multiplyMatrices(matrixA, matrixB);


        // Print the result
        /*
        System.out.println("Result:");
        for (int i = 0; i < result.length; i++) {
            for (int j = 0; j < result[0].length; j++) {
                System.out.print(result[i][j] + " ");
            }
            System.out.println();
        }*/
        matrixA=null;
        matrixB=null;
    }

    @Override
    public void initialize(Object... params) {

    }

    @Override
    public void clean() {

    }

    @Override
    public void cancel() {

    }

    @Override
    public void warmUp() {
        int[][] A= new int[25][25];
        int[][] B= new int[25][25];
        int m = A.length;
        int n = A[0].length;
        int p = B[0].length;

        int[][] C = new int[m][p];

        // Create threads to compute each row of the result matrix
        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            final int row = i;
            threads.add(new Thread(() -> {
                for (int j = 0; j < p; j++) {
                    int sum = 0;
                    for (int k = 0; k < n; k++) {
                        sum += A[row][k] * B[k][j];
                    }
                    C[row][j] = sum;
                }
            }));
        }

        // Start the threads
        for (Thread t : threads) {
            t.start();
        }

        // Wait for all threads to finish
        for (Thread t : threads) {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


    }

    @Override
    public String getResult() {
        return null;
    }
    public static double[][] multiplyMatrices(double[][] a, double[][] b) {
        int aRows = a.length;
        int aCols = a[0].length;
        int bCols = b[0].length;

        double[][] result = new double[aRows][bCols];
        List<Thread> threads = new ArrayList<>();

        // Split the work into equal parts for each thread
        int numThreads = Runtime.getRuntime().availableProcessors();
        int chunkSize = aRows / numThreads;

        // Create and start the threads
        for (int i = 0; i < numThreads; i++) {
            final int startRow = i * chunkSize;
            final int endRow = (i == numThreads - 1) ? aRows : (i + 1) * chunkSize;
            threads.add(new Thread(() -> {
                for (int j = startRow; j < endRow; j++) {
                    for (int k = 0; k < bCols; k++) {
                        for (int l = 0; l < aCols; l++) {
                            result[j][k] += a[j][l] * b[l][k];
                        }
                    }
                }
            }));
            threads.get(i).start();
        }

        // Wait for all threads to finish
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        return result;
    }
}
