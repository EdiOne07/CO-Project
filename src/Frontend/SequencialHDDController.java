package Frontend;

import Frontend.TestBenchmark.TestHDDReadSeq;
import Frontend.TestBenchmark.TestHDDWriteSeq;
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
import java.text.DecimalFormat;
import java.text.NumberFormat;

public class SequencialHDDController {
    @FXML
    private ImageView bubble;
    @FXML
    private Label scoreLabel;
    private TestHDDReadSeq test1;
    private TestHDDWriteSeq test2;
    private Stage stage;
    private Scene scene;
    private Parent layout;
    @FXML
    private Slider write_file_slider;
    @FXML
    private Slider write_buff_slider;
    @FXML
    private Slider read_nofile_slider;
    @FXML
    private Slider read_szblock_slider;
    @FXML
    private Slider read_noblock_slider;

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
    public SequencialHDDController() {
        test1 = new TestHDDReadSeq();
        test2 = new TestHDDWriteSeq();
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
                int num_files = (int) (read_nofile_slider.getValue());
                int num_blocks = (int) (read_noblock_slider.getValue());
                int block_size = (int) (read_szblock_slider.getValue());
                long fileSize = (long) (write_file_slider.getValue())*1024*1024;
                int bufferSize = (int) (write_buff_slider.getValue());
                test1.initialize(num_files, block_size, num_blocks);
                test1.run();
                test2.run(fileSize, bufferSize);
                return null;
            }
        };

        task.setOnSucceeded(e -> {
            bubble.setImage(transparentImage);
            test1.clean();
            int score = 100*(test1.getScore() + test2.getScore()) / 2;
            scoreLabel.setText(score+ " points");

        });

        new Thread(task).start();
    }
}
