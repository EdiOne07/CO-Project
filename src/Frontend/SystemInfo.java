package Frontend;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.management.ManagementFactory;
import com.sun.management.OperatingSystemMXBean;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class SystemInfo {
    public static void main(String[] args) {
        //Operating system name
        System.out.println("Your OS name -> " + System.getProperty("os.name"));

        //Operating system version
        System.out.println("Your OS version -> " + System.getProperty("os.version"));

        //Operating system architecture
        System.out.println("Your OS Architecture -> " + System.getProperty("os.arch") + "\n");

        Runtime runtime = Runtime.getRuntime();
        int processors = runtime.availableProcessors();
        System.out.println("CPU cores: " + processors);

        OperatingSystemMXBean osBean = ManagementFactory.getPlatformMXBean(OperatingSystemMXBean.class);
        long totalMemory = osBean.getTotalPhysicalMemorySize();
        System.out.println("Total RAM: " + (totalMemory / 1024 / 1024) + " MB");

        String osName = System.getProperty("os.name");
        String cpuName = "";
        if (osName.startsWith("Windows")) {
            // On Windows, use WMI to get the CPU caption
            System.out.println(System.getenv("PROCESSOR_IDENTIFIER"));
            System.out.println(System.getenv("PROCESSOR_ARCHITECTURE"));
            System.out.println(System.getenv("PROCESSOR_ARCHITEW6432"));
            System.out.println(System.getenv("NUMBER_OF_PROCESSORS"));

        } else {
            // On other platforms, the CPU name can be obtained by running the "uname -p" command
            try {
                Process process = runtime.exec("uname -p");
                BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
                cpuName = reader.readLine();
            } catch (IOException e) {
                System.err.println("Error getting CPU name: " + e.getMessage());
            }
        }

        System.out.println("CPU name: " + cpuName);


        /* Get a list of all filesystem roots on this system */
        File[] roots = File.listRoots();

        /* For each filesystem root, print some info */
        for (File root : roots) {
            System.out.println("File system root: " + root.getAbsolutePath());
            System.out.println("Total space (bytes): " + root.getTotalSpace());
            System.out.println("Free space (bytes): " + root.getFreeSpace());
            System.out.println("Usable space (bytes): " + root.getUsableSpace() + "\n");

        }
    }
}