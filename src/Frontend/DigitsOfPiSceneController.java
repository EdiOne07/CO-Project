package Frontend;

import Frontend.Logger.FileLogger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Slider;
import Frontend.TestBenchmark.TestDigitsOfPi;
import javafx.stage.Stage;
import Frontend.Logger.ILog;

import java.io.IOException;

public class DigitsOfPiSceneController {
    private  TestDigitsOfPi test;
    private Stage stage;
    private Scene scene;
    private Parent layout;
    private FileLogger logger;
    @FXML
    private Slider slider;
    public DigitsOfPiSceneController(){
        test = new TestDigitsOfPi();
        logger = new FileLogger("what.txt");
    }

    public void TestDigitsOfPi(ActionEvent event){
        Integer load = (int) (slider.getValue());
        test.run(load);
        System.out.println("Finished in " + test.getTime() + " s");
        logger.write(load, test.getTime(), "");

    }
    public void goBack(ActionEvent event) throws IOException {
        logger.close();
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
