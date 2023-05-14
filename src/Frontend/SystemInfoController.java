package Frontend;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.lang.management.ManagementFactory;

public class SystemInfoController {
    @FXML
    private Stage stage;
    private Scene scene;
    private Parent layout;

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

    public SystemInfoController() {
        // Get OS information
        String os = System.getProperty("os.name") + " " + System.getProperty("os.version");
        System.out.println("OS: " + os);

        // Get CPU information
        String cpuModel = System.getenv("PROCESSOR_IDENTIFIER");
        System.out.println("CPU Model: " + cpuModel);

        // Get RAM information
        long ram = ((com.sun.management.OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean()).getTotalPhysicalMemorySize();
        System.out.println("RAM: " + (ram / (1024 * 1024)) + " MB");

        // Get HDD/SSD information
        File[] roots = File.listRoots();
        for (File root : roots) {
            long totalSpace = root.getTotalSpace();
            long freeSpace = root.getFreeSpace();
            long usableSpace = root.getUsableSpace();
            System.out.println(root.getPath() + " - Total Space: " + (totalSpace / (1024 * 1024 * 1024)) + " GB, Free Space: " + (freeSpace / (1024 * 1024 * 1024)) + " GB, Usable Space: " + (usableSpace / (1024 * 1024 * 1024)) + " GB");
        }
    }
}
