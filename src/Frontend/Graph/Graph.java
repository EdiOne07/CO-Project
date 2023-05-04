package Frontend.Graph;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Graph extends Application {

    public void start(Stage stage) {
        String csvFile = "src\\Frontend\\Graph\\Test3.csv";
        String line = "";
        String csvSplitBy = ";";

        // Create a new XYChart series for the data
        XYChart.Series<Number, String> series = new XYChart.Series<>();

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            while ((line = br.readLine()) != null) {
                // Split the CSV row into an array of values
                String[] data = line.split(csvSplitBy);

                // Add a new data point to the series for each row in the CSV
                series.getData().add(new XYChart.Data<>(Double.parseDouble(data[2]), data[1]));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Create the x and y axes
        NumberAxis xAxis = new NumberAxis();
        CategoryAxis yAxis = new CategoryAxis();

        // Create the bar chart
        BarChart<Number, String> barChart = new BarChart<>(xAxis, yAxis);
        barChart.setTitle("Computer Performance Scores");
        barChart.setLegendVisible(false);

        // Add the data series to the chart
        barChart.getData().add(series);

        // Create a new scene and add the chart to it
        Scene scene = new Scene(barChart, 800, 600);

        // Set the scene and show the stage
        stage.setScene(scene);
        stage.show();
    }
}