package Frontend;

import Backend.CSVWriter;
import Backend.GetInfo;
import com.sun.javafx.collections.ObservableListWrapper;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.util.*;

import static java.util.Map.entry;

public class GraphSceneController implements Initializable{
    private Stage stage;
    private Scene scene;
    private Parent layout;
    @FXML
    private NumberAxis yAxis;
    @FXML
    private CategoryAxis xAxis;
    @FXML
    private BarChart<String, Number> XYChart;
    private HashMap<String, Integer> infoHash;
    private CSVWriter csvWriter = new CSVWriter();

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

        infoHash = csvWriter.readCSVToHashMap("Test2.csv");
        System.out.println(infoHash);
        xAxis.setCategories(FXCollections.<String>observableArrayList(Arrays.asList(
               "Read/Write Memory Random", "Read/Write Memory" , "Gauss-Legendre","Monte Carlo", "Matrix Multiplication")));
        xAxis.setLabel("Tests");
        yAxis.setLabel("Score");
        XYChart.setTitle("Comparison between tests");

        XYChart.Series series1 = new XYChart.Series<>();
        series1.setName("AMD Ryzen 7 4800H");
        series1.getData().add(new XYChart.Data<>("Gauss-Legendre", 129.0));
        series1.getData().add(new XYChart.Data<>("Matrix Multiplication", 60.0));
        series1.getData().add(new XYChart.Data<>("Monte Carlo", 434.0));
        series1.getData().add(new XYChart.Data<>("Read/Write Memory", 450.0));
        series1.getData().add(new XYChart.Data<>("Read/Write Memory Random", 535.0));

        XYChart.Series<String, Number> series2 = new XYChart.Series<>();
        series2.setName("Intel Core i5-8257U");
        series2.getData().add(new XYChart.Data<>("Gauss-Legendre", 125.0));
        series2.getData().add(new XYChart.Data<>("Matrix Multiplication", 12.0));
        series2.getData().add(new XYChart.Data<>("Monte Carlo", 291.0));
        series2.getData().add(new XYChart.Data<>("Read/Write Memory", 400.0));
        series2.getData().add(new XYChart.Data<>("Read/Write Memory Random", 409.0));

        XYChart.Series<String, Number> series3 = new XYChart.Series<>();
        series3.setName("AMD Ryzen 5 5600H");
        series3.getData().add(new XYChart.Data<>("Gauss-Legendre", 142.0));
        series3.getData().add(new XYChart.Data<>("Matrix Multiplication", 52.0));
        series3.getData().add(new XYChart.Data<>("Monte Carlo", 482.0));
        series3.getData().add(new XYChart.Data<>("Read/Write Memory", 500.0));
        series3.getData().add(new XYChart.Data<>("Read/Write Memory Random", 674.0));

        XYChart.Series<String, Number> series4 = new XYChart.Series<>();
        series4.setName("AMD Ryzen 7 5800H");
        series4.getData().add(new XYChart.Data<>("Gauss-Legendre", 137.0));
        series4.getData().add(new XYChart.Data<>("Matrix Multiplication", 192.0));
        series4.getData().add(new XYChart.Data<>("Monte Carlo", 469.0));
        series4.getData().add(new XYChart.Data<>("Read/Write Memory", 300.0));
        series4.getData().add(new XYChart.Data<>("Read/Write Memory Random", 674.0));

        XYChart.Series<String, Number> series5 = new XYChart.Series<>();
        series5.setName("AMD Ryzen 5 5500");
        series5.getData().add(new XYChart.Data<>("Gauss-Legendre", 165.0));
        series5.getData().add(new XYChart.Data<>("Matrix Multiplication", 185.0));
        series5.getData().add(new XYChart.Data<>("Monte Carlo", 207.0));
        series5.getData().add(new XYChart.Data<>("Read/Write Memory", 350.0));
        series5.getData().add(new XYChart.Data<>("Read/Write Memory Random", 151.0));

        XYChart.Series<String, Number> user = new XYChart.Series<>();
        user.setName("YOUR RESULTS");
        user.getData().add(new XYChart.Data<>("Gauss-Legendre",infoHash.get("Gauss-Legendre")));
        user.getData().add(new XYChart.Data<>("Matrix Multiplication", infoHash.get("Matrix Multiplication")));
        user.getData().add(new XYChart.Data<>("Monte Carlo", infoHash.get("Monte Carlo")));
        user.getData().add(new XYChart.Data<>("Read/Write Memory", infoHash.get("Read/Write Memory")));
        user.getData().add(new XYChart.Data<>("Read/Write Memory Random", infoHash.get("Read/Write Memory Random")));

        XYChart.getData().addAll(series1, series2, series3, series5, series4, user);
    }
}
