package Frontend;

import Frontend.TestBenchmark.TestMatrixMultiplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import Frontend.TestBenchmark.TestDigitsOfPi;
import javafx.stage.Stage;

import java.io.IOException;

public class MatrixMultiplicationController {
    private  TestMatrixMultiplication test;
    private Stage stage;
    private Scene scene;
    private Parent layout;
    @FXML
    private Slider slider1;
    @FXML
    private Slider slider11;
    @FXML
    private Slider slider111;
    @FXML
    private Slider slider112;
    @FXML
    private Label scoreLabel;
    public MatrixMultiplicationController(){
        test = new TestMatrixMultiplication();

    }

    public void TestMatrixMultiplication(ActionEvent event){
        Integer rowA = (int) (slider1.getValue());
        Integer colA = (int) (slider11.getValue());
        Integer rowB = (int) (slider111.getValue());
        Integer colB = (int) (slider112.getValue());
        test.run(rowA,colA,rowB,colB);
        scoreLabel.setText(test.getScore()+" "+"points");
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
