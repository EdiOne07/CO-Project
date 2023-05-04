package Frontend;
import Backend.Elements;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import Backend.CSVWriter;
import java.io.IOException;
import java.util.List;

public class DatabaseSceneController{
    private Stage stage;
    private Scene scene;
    private Parent layout;
    @FXML
    private TableView<Elements> tableView;
    private TableColumn<Elements,String> computer_Name;
    private TableColumn<Elements,String> cpu;
    private TableColumn<Elements,String> hdd;
    private TableColumn<Elements,Integer> score;
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

    public void CSV(ActionEvent event){
        computer_Name.setCellValueFactory(new PropertyValueFactory<>("Computer Name"));
        cpu.setCellValueFactory(new PropertyValueFactory<>("CPU"));
        hdd.setCellValueFactory(new PropertyValueFactory<>("HDD"));
        score.setCellValueFactory(new PropertyValueFactory<>("Score"));

        CSVWriter a=new CSVWriter();
        a.CreateCSV();
        List<Elements> info=a.readCSV("D:\\Project\\test.csv");
        tableView.getItems().addAll(info);
    }
}
