package Frontend;

import Frontend.Graph.Graph;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;

public class GraphSceneController {
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
       xAxis.setCategories(FXCollections.<String>observableArrayList(Arrays.asList(
                "Gauss-Legendre", "Matrix Multiplication", "Monte Carlo", "Read/Write Memory","Read/Write Memory Random")));
        xAxis.setLabel("Tests");
        yAxis.setLabel("Score");
        XYChart.setTitle("Comparison between tests");
        XYChart.Series series1 = new XYChart.Series<>();

        series1.setName("Fiat");
        series1.getData().add(new XYChart.Data<>("Gauss-Legendre", 12.0));
        series1.getData().add(new XYChart.Data<>("Matrix Multiplication", 343.0));
        series1.getData().add(new XYChart.Data<>("Monte Carlo", 52.0));
        series1.getData().add(new XYChart.Data<>("Read/Write Memory", 65.0));
        series1.getData().add(new XYChart.Data<>("Read/Write Memory Random", 65.0));

        XYChart.Series<String, Number> series2 = new XYChart.Series<>();
        series2.setName("Audi");
        series2.getData().add(new XYChart.Data<>("Gauss-Legendre", 55.0));
        series2.getData().add(new XYChart.Data<>("Matrix Multiplication", 6.0));
        series2.getData().add(new XYChart.Data<>("Monte Carlo", 130.0));
        series2.getData().add(new XYChart.Data<>("Read/Write Memory", 43.0));
        series2.getData().add(new XYChart.Data<>("Read/Write Memory Random", 65.0));

        XYChart.Series<String, Number> series3 = new XYChart.Series<>();
        series3.setName("Ford");
        series3.getData().add(new XYChart.Data<>("Gauss-Legendre", 41.0));
        series3.getData().add(new XYChart.Data<>("Matrix Multiplication", 223.0));
        series3.getData().add(new XYChart.Data<>("Monte Carlo", 365.0));
        series3.getData().add(new XYChart.Data<>("Read/Write Memory", 66.0));
        series3.getData().add(new XYChart.Data<>("Read/Write Memory Random", 65.0));

        XYChart.Series<String, Number> series4 = new XYChart.Series<>();
        series4.setName("Megan");
        series4.getData().add(new XYChart.Data<>("Gauss-Legendre", 41.0));
        series4.getData().add(new XYChart.Data<>("Matrix Multiplication", 223.0));
        series4.getData().add(new XYChart.Data<>("Monte Carlo", 65.0));
        series4.getData().add(new XYChart.Data<>("Read/Write Memory", 66.0));
        series4.getData().add(new XYChart.Data<>("Read/Write Memory Random", 65.0));

        XYChart.Series<String, Number> series5 = new XYChart.Series<>();
        series5.setName("Dacie");
        series5.getData().add(new XYChart.Data<>("Gauss-Legendre", 41.0));
        series5.getData().add(new XYChart.Data<>("Matrix Multiplication", 22.0));
        series5.getData().add(new XYChart.Data<>("Monte Carlo", 65.0));
        series5.getData().add(new XYChart.Data<>("Read/Write Memory", 656.0));
        series5.getData().add(new XYChart.Data<>("Read/Write Memory Random", 65.0));

        XYChart.getData().addAll(series1, series2, series3, series4, series5);
    }
}
