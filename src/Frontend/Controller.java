package Frontend;

import Backend.CSVWriter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static java.util.Map.entry;

public class Controller {
    @FXML
    private Stage stage;
    private Scene scene;
    private Parent layout;
    private HashMap<String, Integer> infoHash;
    private CSVWriter csvWriter = new CSVWriter();

    public void goToHDDScene(ActionEvent event) throws IOException {
        try{
            Parent layout = FXMLLoader.load(getClass().getClassLoader().getResource("Frontend/HDDScene.fxml"));
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
    public void goToCPUScene(ActionEvent event) throws IOException{
        try{
            Parent layout = FXMLLoader.load(getClass().getClassLoader().getResource("Frontend/CPUScene.fxml"));
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

    public void goToGraph(ActionEvent event) throws IOException{
        try{
            Parent layout = FXMLLoader.load(getClass().getClassLoader().getResource("Frontend/GraphScene.fxml"));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(layout);
            String css = this.getClass().getClassLoader().getResource("Frontend/Style.css").toExternalForm();
            scene.getStylesheets().add(css);
            stage.setScene(scene);
            stage.show();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void testCSV(ActionEvent event){
        infoHash = new HashMap<>(Map.ofEntries(
                entry("Monte Carlo",0),
                entry("Gauss-Legendre", 0),
                entry("Matrix Multiplication", 0),
                entry("Read/Write Memory", 0),
                entry("Read/Write Memory Random", 0)
        ));
        csvWriter.writeHashMapToCSV(infoHash, "Test2.csv");
    }

    public void goToSystem(ActionEvent event) throws IOException {
        try {
            Parent layout = FXMLLoader.load(getClass().getClassLoader().getResource("Frontend/SystemScene.fxml"));
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

}
