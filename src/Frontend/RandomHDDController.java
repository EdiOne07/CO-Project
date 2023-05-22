package Frontend;

import Frontend.TestBenchmark.TestRandomHDD;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.stage.Stage;

import java.io.IOException;

public class RandomHDDController {
    @FXML
    private Label scoreLabel;
    private TestRandomHDD test;
    private Stage stage;
    private Scene scene;
    private Parent layout;
    @FXML
    private Slider fileSize_slider;
    @FXML
    private Slider bufferSize_slider;
    public RandomHDDController() {
        test = new TestRandomHDD();
    }
    public void goBack(ActionEvent event) throws IOException {
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

    public void TestHDD(ActionEvent event) {
        Long fileSize = (long) (fileSize_slider.getValue())*1024*1024;
        Integer bufferSize = (int) (bufferSize_slider.getValue())*1024;
        scoreLabel.setText("Running the benchmark. Please wait!");
        test.initialize(fileSize);
        test.run(bufferSize);
        test.clean();
        String score = test.getResult();
        scoreLabel.setText(score + " " + "points");

    }
}
