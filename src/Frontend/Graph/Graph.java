package Frontend.Graph;
import com.sun.javafx.geom.AreaOp;
import javafx.scene.chart.Chart;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.TableColumn;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.SQLOutput;

public class Graph {

    String line="";
    String buffer="";
    BufferedReader sc;
    String splitBy=";";
    String[] computer_data;
    public void scan () throws Exception {
        //parsing a CSV file into Scanner class constructor
        sc = new BufferedReader(new FileReader("D:\\Faculty\\CO-Project\\Test2.csv"));
        while ((line=sc.readLine())!= null)
        {
            computer_data =line.split(splitBy);
            System.out.println("Computer: " + computer_data[0] +", CPU model: " + computer_data[1] + ", Score: "+ computer_data[2]);  //find and returns the next complete token from this scanner
        }
        sc.close();  //closes the scanner
    }

    @Override public void start(Stage stage) {
        stage.setTitle("Test Chart Sample");
        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();
        final BarChart<String, Number> bc = new BarChart<String, Number>(xAxis, yAxis);
        bc.setTitle("Computer Score Test");
        xAxis.setLabel("Score");
        yAxis.setLabel("Computer");
        Graph n1=new Graph();
        XYChart.Series series1 = new XYChart.Series();
        series1.setName("Computer1");
        while ((buffer=n1.scan())!=null) {
            series1.getData().add(new XYChart.Data(computer_data[1], computer_data[2]));
            series1.getData().add(new XYChart.Data(computer_data[1], computer_data[2]));
            series1.getData().add(new XYChart.Data(computer_data[1], computer_data[2]));
            series1.getData().add(new XYChart.Data(computer_data[1], computer_data[2]));
            series1.getData().add(new XYChart.Data(computer_data[1], computer_data[2]));
        }
    }

}
