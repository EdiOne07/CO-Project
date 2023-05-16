package Frontend.Benchmark.HDD;

import Frontend.Benchmark.IBenchmark;

import java.io.File;
import java.io.IOException;

public class HDDWriteBenchmark implements IBenchmark {
    private String prefix;
    private String suffix = ".dat";
    private int minIndex = 0;
    private int maxIndex = 7;
    @Override
    public void run() {
        throw new UnsupportedOperationException(
                "Method not implemented. Use run(Object) instead");
    }

    @Override
    public void run(Object... params) {
        FileWriter writer = new FileWriter();
        // either "fs" - fixed size, or "fb" - fixed buffer
        String option = (String) params[0];
        // true/false whether the written files should be deleted at the end
        Boolean clean = (Boolean) params[1];

        long fileSize = 1024 * 1024 * 1024; // 256, 512 MB, 1GB // type Long!
        int bufferSize = 1024; // 4 KB

        try {
            if (option.equals("fs"))
                writer.streamWriteFixedFileSize(prefix, suffix, minIndex, maxIndex, fileSize, clean);
            else if (option.equals("fb"))
                writer.streamWriteFixedBufferSize(prefix, suffix, minIndex, maxIndex, bufferSize, clean);
            else
                throw new IllegalArgumentException("Argument " + params[0].toString() + " is undefined");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(Object... params) {
        prefix = System.getProperty("java.io.tmpdir") + File.separator + "my-benchmark" + File.separator + "file-";
    }

    @Override
    public void clean() {
        for (int i = minIndex; i <= maxIndex; i++) {
            String filename = prefix + i + suffix;
            File file = new File(filename);
            if(file.exists()) {
                file.delete();
            }
        }
    }

    @Override
    public void cancel() {

    }

    @Override
    public void warmUp() {

    }

    @Override
    public String getResult() {
        return null;
    }
}
