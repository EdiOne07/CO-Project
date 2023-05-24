package Frontend;

import Backend.SystemInfo;
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
        SystemInfo getInfo = new SystemInfo();

        OSLabel.setText("OS:" + osBean.getName() + " "+ osBean.getVersion());
        CPULabel.setText("CPU: " + getInfo.getCpuModel());
        RAMLabel.setText("RAM: " + (getInfo.getRAM() / (1024 * 1024)) + " MB");
        UserLabel.setText("Username: " + getInfo.getUserName());
        HDDLabel.setText(getInfo.getHDD());
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
