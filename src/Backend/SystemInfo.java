package Backend;

import java.io.File;
import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.util.Scanner;

public class SystemInfo {

    public String getUserName() {
        return System.getProperty("user.name");
    }
    public long getRAM() {
        return ((com.sun.management.OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean()).getTotalMemorySize();
    }
    public String getHDD() {
        StringBuilder hdd = new StringBuilder();
        File[] roots = File.listRoots();
        hdd.append("HDDs/SSDs:\n");
        for (File root : roots) {
            long totalSpace = root.getTotalSpace();
            long freeSpace = root.getFreeSpace();
            hdd.append(root.getPath()).append(" - Total Space: ").append(totalSpace / (1024 * 1024 * 1024)).append(" GB\nFree Space: ").append(freeSpace / (1024 * 1024 * 1024)).append(" \n");
        }
        return hdd.toString();
    }
    public String getCpuModel() {
        String osName = System.getProperty("os.name").toLowerCase();
        String arch = System.getProperty("os.arch").toLowerCase();
        String cpuModelWindows = "Unknown";

        if (osName.contains("win")) { // Windows
            try {
                Process process = Runtime.getRuntime().exec("wmic cpu get name");
                Scanner scanner = new Scanner(process.getInputStream());
                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine().trim();
                    if (!line.isEmpty() && !line.equalsIgnoreCase("name")) {
                        cpuModelWindows = line;
                        break;
                    }
                }
                scanner.close();
                return  cpuModelWindows.trim();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (osName.contains("mac")) { // macOS
            try {
                Process process = Runtime.getRuntime().exec("sysctl -n machdep.cpu.brand_string");
                Scanner scanner = new Scanner(process.getInputStream());
                String cpuModel = scanner.nextLine();
                scanner.close();
                return cpuModel.trim();
            } catch (IOException e) {
                e.printStackTrace();
                return "Unknown";
            }
        } else if (osName.contains("nix") || osName.contains("nux") || osName.contains("aix")) { // Linux/Unix
            try {
                Process process = Runtime.getRuntime().exec("cat /proc/cpuinfo | grep 'model name' | uniq");
                Scanner scanner = new Scanner(process.getInputStream());
                String cpuModel = scanner.nextLine();
                scanner.close();
                return cpuModel.substring(cpuModel.indexOf(':') + 1).trim();
            } catch (IOException e) {
                e.printStackTrace();
                return "Unknown";
            }
        } else { // Unsupported OS
            return "Unknown";
        }

        return "Unknown";
    }
}
