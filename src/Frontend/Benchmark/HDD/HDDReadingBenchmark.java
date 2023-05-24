package Frontend.Benchmark.HDD;

import Frontend.Benchmark.IBenchmark;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class HDDReadingBenchmark implements IBenchmark {
    private static int NUM_FILES;
    private static int BLOCK_SIZE;
    private static int NUM_BLOCKS;
    private String prefix;
    private String suffix;
    private long duration;
    private double throughput;

    @Override
    public void initialize(Object... params) {
        NUM_FILES = (Integer) params[0];
        BLOCK_SIZE = (Integer) params[1];
        NUM_BLOCKS = (Integer) params[2];
        prefix = System.getProperty("java.io.tmpdir") + File.separator + "my-benchmark" + File.separator + "file-";
        suffix = ".dat";
        for (int i = 0; i < NUM_FILES; i++) {
            String filename = prefix + i + suffix;
            createFile(filename);
        }
    }

    @Override
    public void run() {
        long startTime = System.nanoTime();
        for (int i = 0; i < NUM_FILES; i++) {
            String filename = prefix + i + suffix;
            readFile(filename);
        }
        long endTime = System.nanoTime();
        duration = endTime - startTime;

        double totalBytes = (double) NUM_BLOCKS * BLOCK_SIZE * NUM_FILES;
        throughput = totalBytes / duration * 1e9 / 1e6;
    }

    @Override
    public void run(Object... params) {
        run();
    }

    @Override
    public void clean() {
        for (int i = 0; i < NUM_FILES; i++) {
            String filename = prefix + i + suffix;
            deleteFile(filename);
        }
    }

    @Override
    public void cancel() {
        // not supported
    }

    @Override
    public void warmUp() {
        // do nothing
    }

    @Override
    public String getResult() {
        return String.format("%.2f MB/s", throughput);
    }

    private void createFile(String filename) {
        try {
            File file = new File(filename);
            file.getParentFile().mkdirs();
            file.createNewFile();
            byte[] block = new byte[BLOCK_SIZE];
            for (int i = 0; i < NUM_BLOCKS; i++) {
                writeBlockToFile(file, i, block);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void writeBlockToFile(File file, int blockIndex, byte[] block) throws IOException {
        long offset = (long) blockIndex * BLOCK_SIZE;
        FileInputStream fileInputStream = new FileInputStream(file);
        fileInputStream.getChannel().position(offset);
        fileInputStream.read(block);
        fileInputStream.close();
    }

    private void readFile(String filename) {
        try {
            File file = new File(filename);
            byte[] block = new byte[BLOCK_SIZE];
            for (int i = 0; i < NUM_BLOCKS; i++) {
                readBlockFromFile(file, i, block);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void readBlockFromFile(File file, int blockIndex, byte[] block) throws IOException {
        long offset = (long) blockIndex * BLOCK_SIZE;
        FileInputStream fileInputStream = new FileInputStream(file);
        fileInputStream.getChannel().position(offset);
        fileInputStream.read(block);
        fileInputStream.close();
    }

    private void deleteFile(String filename) {
        File file = new File(filename);
        if (file.exists()) {
            file.delete();
        }
    }
}
