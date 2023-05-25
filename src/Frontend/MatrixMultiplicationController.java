package Frontend;

import Backend.CSVWriter;
import Frontend.TestBenchmark.TestMatrixMultiplication;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import Frontend.TestBenchmark.TestDigitsOfPi;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.HashMap;

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
    @FXML
    private ImageView bubble;

    public MatrixMultiplicationController(){
        test = new TestMatrixMultiplication();

    }

    public void TestMatrixMultiplication(ActionEvent event){
        String imagePath = "Frontend/Images/bubble.png";
        String transparentPath = "Frontend/Images/transparent.png";

        Image transparentImage = new Image(transparentPath);
        Image image = new Image(imagePath);
        Integer rowA = (int) (slider1.getValue());
        Integer colA = (int) (slider11.getValue());
        Integer rowB = (int) (slider11.getValue());
        Integer colB = (int) (slider112.getValue());
        bubble.setImage(image);

        Task<Void> task = new Task<>() {
            @Override
            protected Void call() throws Exception {

                test.run(rowA,colA,rowB,colB);
                return null;
            }
        };

        task.setOnSucceeded(e -> {
            bubble.setImage(transparentImage);
            scoreLabel.setText(test.getScore()+" "+"points");
            if(rowA == 2000 && colA == 2000 && colB == 2000)  {

                CSVWriter csvWriter = new CSVWriter();
                HashMap<String, Integer> infoHash = new HashMap<>();
                infoHash.put("Matrix Multiplication", test.getScore());
                csvWriter.writeHashMapToCSV(infoHash, "Test2.csv");
            }
        });

        new Thread(task).start();


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
