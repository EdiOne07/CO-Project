package Frontend.Benchmark.HDD;

import Frontend.Benchmark.IBenchmark;
import Frontend.Timing.Timer;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Random;
import java.util.RandomAccess;

public class HDDRandomAccess implements IBenchmark {
    private final static String PATH = System.getProperty("java.io.tmpdir") + File.separator + "test.raf";
    private String result;
    @Override
    public void run() {
        throw new UnsupportedOperationException("Use run(Object[]) instead");
    }

    @Override
    public void run(Object... params) {
        // ex. {"r", "fs", 4*1024}
        Object[] param = (Object[]) params;
        // used by the fixed size option
        final int steps = 25000; // number of I/O ops
        // used by the fixed time option
        final int runtime = 5000; // ms
        NumberFormat nf = new DecimalFormat("#.00");

        try {
            // read benchmark
            if (String.valueOf(param[0]).toLowerCase().equals("r")) {
                // buffer size given as parameter
                int bufferSize = Integer.parseInt(String.valueOf(param[2]));

                // read a fixed size and measure time
                if (String.valueOf(param[1]).toLowerCase().equals("fs")) {

                    long timeMs = new RandomAccess().randomReadFixedSize(PATH,
                            bufferSize, steps);
                    result = steps + " random reads in " + timeMs + " ms ["
                            + (steps * bufferSize / 1024 / 1024) + " MB, "
                            + nf.format(((double) (steps * bufferSize) / 1024 / 1024) / timeMs * 1000) + "MB/s]";

                }
                // read for a fixed time amount and measure time
                else if (String.valueOf(param[1]).toLowerCase().equals("ft")) {

                    int ios = new RandomAccess().randomReadFixedTime(PATH,
                            bufferSize, runtime);
                    result = ios + " I/Os per second ["
                            + Math.abs((ios * bufferSize / 1024 / 1024)) + " MB, "
                            + nf.format(Math.abs(((double) (ios * bufferSize) / 1024 / 1024) / runtime * 1000)) + "MB/s]";
                } else
                    throw new UnsupportedOperationException("Read option \""
                            + String.valueOf(param[1])
                            + "\" is not implemented");

            }
            // write benchmark
            else if (String.valueOf(param[0]).toLowerCase().equals("w")) {
                // your code here: implement all cases for param[[0]: fs, ft, other
                int bufferSize = Integer.parseInt(String.valueOf(param[2]));

                if(String.valueOf(param[1]).toLowerCase().equals("fs")) {
                    long timeMs = new RandomAccess().randomReadFixedSize(PATH,
                            bufferSize, steps);

                    result = steps + " random reads in " + timeMs + " ms ["
                            + (steps * bufferSize / 1024 / 1024) + " MB, "
                            + nf.format(((double) (steps * bufferSize) / 1024 / 1024) / timeMs * 1000) + "MB/s]";
                }

                else if (String.valueOf(param[1]).toLowerCase().equals("ft")) {
                    int ios = new RandomAccess().randomReadFixedTime(PATH,
                            bufferSize, runtime);

                    result = ios + " I/Os per second ["
                            + Math.abs((ios * bufferSize / 1024 / 1024)) + " MB, "
                            + nf.format(Math.abs(((double) (ios * bufferSize) / 1024 / 1024) / runtime * 1000)) + "MB/s]";
                } else
                    throw new UnsupportedOperationException("Read option\""
                            + String.valueOf(param[1]) + "\" is not imeplemented");
            } else
                throw new UnsupportedOperationException("Benchmark option \""
                        + String.valueOf(param[0]) + "\" is not implemented");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(Object... params) {
        // ex. {"r", "fs", 4*1024}
        File tempFile = new File(PATH);
        RandomAccessFile rafFile;
        long fileSizeInBytes = Long.parseLong(params[0].toString());

        // Create a temporary file with random content to be used for
        // reading and writing
        try {
            rafFile = new RandomAccessFile(tempFile, "rw");
            Random rand = new Random();
            int bufferSize = 4 * 1024; // 4KB
            long toWrite = fileSizeInBytes / bufferSize;
            byte[] buffer = new byte[bufferSize];
            long counter = 0;

            while (counter++ < toWrite) {
                rand.nextBytes(buffer);
                rafFile.write(buffer);
            }
            rafFile.close();
            tempFile.deleteOnExit();

        } catch (IOException e) {
            e.printStackTrace();
            System.exit(-1);
        }
    }

    @Override
    public void clean() {
        File file = new File(PATH);
        if (file.exists()) {
            file.delete();
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
        return result;
    }

    class RandomAccess {
        private Random random;

        RandomAccess() {
            random = new Random();
        }

        /**
         * Reads data from random positions into a fixed size buffer from a
         * given file using RandomAccessFile
         *
         * @param filePath
         *            Full path to file on disk
         * @param bufferSize
         *            Size of byte buffer to read at each step
         * @param toRead
         *            Number of steps to repeat random read
         * @return Amount of time needed to finish given reads in milliseconds
         * @throws IOException
         */
        public long randomReadFixedSize(String filePath, int bufferSize,
                                        int toRead) throws IOException {
            // file to read from
            RandomAccessFile file = new RandomAccessFile(filePath, "rw");
            // size of file
            long fileSize = file.getChannel().size();
            // counter for number of reads
            int counter = 0;
            // buffer for reading
            byte[] bytes = new byte[bufferSize];
            // timer
            Timer timer = new Timer();

            timer.start();
            while (counter++ < toRead) {
                // go to random spot in file
                // [0, filesize] -> long
                // read the bytes into buffer
                long randomPosition = (long) (Math.random() * fileSize);
                file.seek(randomPosition);
                file.read(bytes);
            }

            file.close();
            return timer.stop() / 1000000; // ns to ms!
        }

        /**
         * Reads data from random positions into a fixed size buffer from a
         * given file using RandomAccessFile for one second, or any other given
         * time
         *
         * @param filePath
         *            Full path to file on disk
         * @param bufferSize
         *            Size of byte buffer to read at each step
         * @param millis
         *            Total time to read from file
         * @return Number of reads in the given amount of time
         * @throws IOException
         */
        public int randomReadFixedTime(String filePath, int bufferSize,
                                       int millis) throws IOException {
            // file to read from
            RandomAccessFile file = new RandomAccessFile(filePath, "rw");
            // size of file
            long fileSize = file.getChannel().size();
            // counter for number of reads
            int counter = 0;
            // buffer for reading
            byte[] bytes = new byte[bufferSize];

            long now = System.nanoTime();
            // read for a fixed amount of time
            long totalTime = (System.nanoTime() - now) / 1000000;
            while (totalTime < millis) {
                // go to random spot in file

                // read the bytes into buffer
                long randomPosition = (long) (Math.random() * fileSize);
                file.seek(randomPosition);
                file.read(bytes);
                counter++;
                totalTime = (System.nanoTime() - now) / 1000000;
            }

            file.close();
            return counter;
        }

        /**
         * Read data from a file at a specific position
         *
         * @param filePath
         *            Path to file
         * @param position
         *            Position in file
         * @param size
         *            Number of bytes to reads from the given position
         * @return Data that was read
         * @throws IOException
         */
        public byte[] readFromFile(String filePath, int position, int size)
                throws IOException {

            RandomAccessFile file = new RandomAccessFile(filePath, "rw");
            file.seek(position);
            byte[] bytes = new byte[size];
            file.read(bytes);
            file.close();
            return bytes;
        }

        /**
         * Write data to a file at a specific position
         *
         * @param filePath
         *            Path to file
         * @param data
         *            Data to be written
         * @param position
         *            Start position in file
         * @throws IOException
         */
        public void writeToFile(String filePath, String data, int position)
                throws IOException {

            RandomAccessFile file = new RandomAccessFile(filePath, "rw");
            file.seek(position);
            file.write(data.getBytes());
            file.close();
        }
    }
}
