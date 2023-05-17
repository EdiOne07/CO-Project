package Frontend;

import Frontend.Benchmark.HDD.HDDRandomAccess;
import Frontend.Benchmark.HDD.HDDReadingBenchmark;
import Frontend.Benchmark.HDD.HDDWriteBenchmark;
import Frontend.Benchmark.IBenchmark;
import Frontend.Timing.ITimer;
import Frontend.Timing.Timer;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HDDSceneController {
    private Stage stage;
    private Scene scene;
    private Parent layout;
    public void goBack(ActionEvent event) throws IOException {
        try{
            Parent layout = FXMLLoader.load(getClass().getClassLoader().getResource("Frontend/Main.fxml"));
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
    public void sequencialHDDTest(ActionEvent event){
        try{
            Parent layout = FXMLLoader.load(getClass().getClassLoader().getResource("Frontend/SequencialHDDScene.fxml"));
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

    public void randomHDDTest(ActionEvent event) {
        try{
            Parent layout = FXMLLoader.load(getClass().getClassLoader().getResource("Frontend/RandomHDDScene.fxml"));
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

    public void someHDDTest(ActionEvent event) {
//        IBenchmark bench = new HDDWriteBenchmark();
        IBenchmark bench2 = new HDDRandomAccess();
        bench2.initialize(1024*1024*1024*2);
//        bench.initialize();
//        bench.run("fs", true, (long) 1024*1024*1024, 1024*4);
        bench2.run("w", "ft", 1024);
        System.out.println(bench2.getResult());
        bench2.run("w", "ft", 1024);
        System.out.println(bench2.getResult());
        bench2.run("w", "ft", 1024);
        System.out.println(bench2.getResult());
//        bench.clean();
        bench2.clean();
    }
}
