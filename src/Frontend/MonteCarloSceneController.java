package Frontend;

import Frontend.TestBenchmark.TestMonteCarlo;
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

public class MonteCarloSceneController {
    @FXML
    private Label scoreLabel;
    private TestMonteCarlo test;
    private Stage stage;
    private Scene scene;
    private Parent layout;
    @FXML
    private Slider slider;
    public MonteCarloSceneController(){
        test = new TestMonteCarlo();

    }

    public void TestMonteCarlo(ActionEvent event){
        Integer load = (int) (slider.getValue());
        test.run(load);
        //System.out.println("Finished in " + test.getTime() + " s");
        scoreLabel.setText(String.valueOf(test.getScore()));
    }
    public void goBack(ActionEvent event) throws IOException {
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
}
