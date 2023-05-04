package Frontend;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class SystemInfoController {
    private Stage stage;
    private Scene scene;
    private Parent layout;

    public void goBack(ActionEvent event) throws IOException {
        try {
            Parent layout = FXMLLoader.load(getClass().getClassLoader().getResource("Frontend/Main.xml"));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(layout);
            String css = this.getClass().getClassLoader().getResource("Frontend/Style.css").toExternalForm();
            scene.getStylesheets().add(css);
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void SystemInfo(ActionEvent event) throws IOException {
        try {
            SystemInfo.main(null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
