package Frontend;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javafx.scene.control.Label;
import java.io.File;
import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.lang.management.OperatingSystemMXBean;
import java.lang.management.RuntimeMXBean;
import java.util.Scanner;

public class SystemInfoController {
    @FXML
    private Stage stage;
    private Scene scene;
    private Parent layout;

    @FXML
    private Label CPULabel;
    @FXML
    private Label OSLabel;
    @FXML
    private Label RAMLabel;
    @FXML
    private Label UserLabel;
    @FXML
    private Label HDDLabel;

    @FXML
    public void initialize() {
        OperatingSystemMXBean osBean = (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();
        RuntimeMXBean processorname = ManagementFactory.getRuntimeMXBean();
        Runtime memory = Runtime.getRuntime();
        OperatingSystemMXBean operatingSystemBean = (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();
        long ram = ((com.sun.management.OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean()).getTotalMemorySize();

        String userName = System.getProperty("user.name");

        StringBuilder hdd = new StringBuilder();
        File[] roots = File.listRoots();
        hdd.append("HDDs/SSDs:\n");
        for (File root : roots) {
            long totalSpace = root.getTotalSpace();
            long freeSpace = root.getFreeSpace();
            hdd.append(root.getPath()).append(" - Total Space: ").append(totalSpace / (1024 * 1024 * 1024)).append(" GB\nFree Space: ").append(freeSpace / (1024 * 1024 * 1024)).append(" \n");
        }


        OSLabel.setText("OS:" + osBean.getName() + " "+ osBean.getVersion());
        CPULabel.setText("CPU: " + getCpuModel());
        RAMLabel.setText("RAM: " + (ram / (1024 * 1024)) + " MB");
        UserLabel.setText("Username: " + userName);
        HDDLabel.setText(hdd.toString());

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

    public void goBack(ActionEvent event) throws IOException {
        try{
            Parent layout = FXMLLoader.load(getClass().getClassLoader().getResource("Frontend/Main.fxml"));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(layout);
            String css = this.getClass().getClassLoader().getResource("Frontend/Style.css").toExternalForm();
            scene.getStylesheets().add(css);
            stage.setScene(scene);
            stage.show();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
