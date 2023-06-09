package Frontend.Benchmark.HDD;


import Frontend.Timing.Timer;
import java.io.*;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Random;

public class FileWriter {

	private static final int MIN_BUFFER_SIZE = 2048; // KB
	private static final int MAX_BUFFER_SIZE = 1024 * 64; // MB
	private static final long MIN_FILE_SIZE = 1024 * 128; // MB
	private static final long MAX_FILE_SIZE = 1024 * 1024 * 1024; // MB
	private final Timer timer = new Timer();
	private final Random rand = new Random();
	private double benchScore = 0;
	private StringBuilder result;
	public FileWriter() {
		result = new StringBuilder(); // Initialize StringBuilder
	}
	/**
	 * Writes files on disk using a variable write buffer and fixed file size.
	 * 
	 * @param filePrefix
	 *            - Path and file name
	 * @param fileSuffix
	 *            - file extension
	 * @param minIndex
	 *            - start buffer size index
	 * @param maxIndex
	 *            - end buffer size index
	 * @param fileSize
	 *            - size of the benchmark file to be written in the disk
	 * @throws IOException
	 */

	public void streamWriteFixedFileSize(String filePrefix, String fileSuffix,
			int minIndex, int maxIndex, long fileSize, boolean clean)
			throws IOException {

		int currentBufferSize = MIN_BUFFER_SIZE;
		String fileName;
		int fileIndex = 0;
		benchScore = 0;

		while (currentBufferSize <= MAX_BUFFER_SIZE
				&& fileIndex <= maxIndex - minIndex) {
			fileName = filePrefix + fileIndex + fileSuffix;
			writeFile(fileName, currentBufferSize, fileSize, clean);
			// update buffer size
			currentBufferSize *= 2;
			fileIndex++;
		}

		benchScore /= (maxIndex - minIndex + 1);
		result.append("File write score on primary disk").append(": ").append(String.format("%.2f", benchScore)).append(" MB/sec").append("\n");

	}

	/**
	 * Writes files on disk using a variable file size and fixed buffer size.
	 * 
	 * @param filePrefix
	 *            - Path and file name
	 * @param fileSuffix
	 *            - file extension
	 * @param minIndex
	 *            - start file size index
	 * @param maxIndex
	 *            - end file size index
	 *
	 */
	public void streamWriteFixedBufferSize(String filePrefix, String fileSuffix,
			int minIndex, int maxIndex, int bufferSize, boolean clean)
			throws IOException {


		int fileIndex = 0;
		long currentFileSize = MIN_FILE_SIZE;
		
		while (currentFileSize <= MAX_FILE_SIZE
				&& fileIndex <= maxIndex - minIndex) {

			// update fileSize instead of bufferSize
			currentFileSize *= 2;
			fileIndex++;
		}

		benchScore /= (maxIndex - minIndex + 1);
		result.append("File write score on primary disk:").append(": ").append(String.format("%.2f", benchScore)).append(" MB/sec").append("\n");
	}

	/**
	 * Writes a file with random binary content on the disk, using a given file
	 * path and buffer size.
	 */
	private void writeFile(String fileName, int bufferSize,
			long fileSize, boolean clean) throws IOException {

		File folderPath = new File(fileName.substring(0,
				fileName.lastIndexOf(File.separator)));

		// create folder path to benchmark output
		if (!folderPath.isDirectory())
			folderPath.mkdirs();

		final File file = new File(fileName);
		// create stream writer with given buffer size
		OutputStream stream = new FileOutputStream(fileName);
		final BufferedOutputStream outputStream = new BufferedOutputStream(stream, bufferSize);

		byte[] buffer = new byte[bufferSize];
		int i = 0;
		long toWrite = fileSize / bufferSize;

		timer.start();
		while (i < toWrite) {
			// generate random content to write
			rand.nextBytes(buffer);

			outputStream.write(buffer);
			i++;
		}		
		printStats(fileName, fileSize, bufferSize);

		outputStream.close();
		if(clean)
			;
	}

	private void printStats(String fileName, long totalBytes, int bufferSize) {
		final long time = timer.stop();
		
		NumberFormat nf = new DecimalFormat("#.00");
		double seconds = (double)time/1000000000 ; // calculated from timer's 'time'
		double megabytes = (double) totalBytes / (1024*1024); //
		double rate = megabytes / seconds; // calculated from the previous two variables
		result.append("Done writing ").append(totalBytes)
				.append(" bytes to file: ").append(fileName).append(" in ")
				.append(nf.format(seconds)).append(" ms (").append(nf.format(rate))
				.append("MB/sec)").append(" with a buffer size of ")
				.append(bufferSize / 1024).append(" kB").append("\n");

		// actual score is write speed (MB/s)
		benchScore += rate;
	}
	public String getResult() {
		return result.toString();
	}
}