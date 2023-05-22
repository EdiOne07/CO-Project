package Frontend;

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
import javafx.stage.Stage;
import javafx.scene.image.ImageView;


import java.io.IOException;

public class DigitsOfPiSceneController {
    @FXML
    private ImageView bubble;
    @FXML
    private Label scoreLabel;
    private  TestDigitsOfPi test;
    private Stage stage;
    private Scene scene;
    private Parent layout;
    @FXML
    private Slider slider;
    public DigitsOfPiSceneController(){
        test = new TestDigitsOfPi();

    }

    /*public void TestDigitsOfPi(ActionEvent event){
        String imagePath = "Frontend/Images/bubble.png";
        String transparentPath = "Frontend/Images/transparent.png";
        Image transparentImage = new Image(transparentPath);
        Image image = new Image(imagePath);
        bubble.setImage(image);
        Integer load = (int) (slider.getValue());
        test.run(load);
        //System.out.println("Finished in " + test.getTime() + " s");
        bubble.setImage(transparentImage);

        scoreLabel.setText(test.getScore() + "");

    }*/
    public void TestDigitsOfPi(ActionEvent event) {
        String imagePath = "Frontend/Images/bubble.png";
        String transparentPath = "Frontend/Images/transparent.png";

        Image transparentImage = new Image(transparentPath);
        Image image = new Image(imagePath);

        bubble.setImage(image);

        Task<Void> task = new Task<>() {
            @Override
            protected Void call() throws Exception {
                Integer load = (int) slider.getValue();
                test.run(load);
                return null;
            }
        };

        task.setOnSucceeded(e -> {
            bubble.setImage(transparentImage);
            scoreLabel.setText(""+test.getScore());
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
