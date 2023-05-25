package Frontend;

import Backend.CSVWriter;
import Frontend.TestBenchmark.TestRandomHDD;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.HashMap;

public class RandomHDDController {
    @FXML
    private ImageView bubble;
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
    private Long fileSize;
    private Integer bufferSize;
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
        String imagePath = "Frontend/Images/bubble.png";
        String transparentPath = "Frontend/Images/transparent.png";

        Image transparentImage = new Image(transparentPath);
        Image image = new Image(imagePath);

        bubble.setImage(image);

        Task<Void> task = new Task<>() {
            @Override
            protected Void call() throws Exception {
                fileSize = (long) (fileSize_slider.getValue())*1024*1024;
                bufferSize = (int) (bufferSize_slider.getValue())*1024;
                test.initialize(fileSize);
                test.run(bufferSize);
                return null;
            }
        };

        task.setOnSucceeded(e -> {
            bubble.setImage(transparentImage);
            test.clean();
            String score = test.getResult();
            scoreLabel.setText(score + " " + "points");
            if(fileSize == 256*1024*1024 && bufferSize == 1*1024) {
                CSVWriter csvWriter = new CSVWriter();
                HashMap<String, Integer> infoHash = new HashMap<>();
                infoHash.put("Read/Write Memory Random", Integer.valueOf(test.getResult()));
                csvWriter.writeHashMapToCSV(infoHash, "Test2.csv");
            }
        });

        new Thread(task).start();
    }
}
