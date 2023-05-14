package Frontend;

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
    public void someHDDtest(ActionEvent event){
        IBenchmark bench = new HDDWriteBenchmark();
        IBenchmark bench1 = new HDDReadingBenchmark();
        bench1.initialize();
        bench.initialize();
        bench.run("fs", true);
        bench1.run();
        System.out.println("File read score on primary partition: " + bench1.getResult());
        bench.clean();
        bench1.clean();
    }
}
