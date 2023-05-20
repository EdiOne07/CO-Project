package Frontend;
import Backend.Elements;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import Backend.CSVWriter;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class DatabaseSceneController implements Initializable {

    private Stage stage;
    private Scene scene;
    private Parent layout;
    @FXML
    private TableView<Elements> table;
    @FXML
    private TableColumn<Elements,String> computer_Name;
    @FXML
    private TableColumn<Elements,String> cpu;
    @FXML
    private TableColumn<Elements,String> hdd;
    @FXML
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

    public DatabaseSceneController(){

       /* CSVWriter a=new CSVWriter();
        a.CreateCSV();
        List<Elements> info=a.readCSV("D:\\Project\\test.csv");
        computer_Name.setCellValueFactory(new PropertyValueFactory<>("Computer Name"));
        cpu.setCellValueFactory(new PropertyValueFactory<>("CPU"));
        hdd.setCellValueFactory(new PropertyValueFactory<>("HDD"));
        score.setCellValueFactory(new PropertyValueFactory<>("Score"));
        tableView.getItems().addAll(info);*/
        /*final ObservableList<Elements> data= FXCollections.observableArrayList(
            new Elements("Dorian","Popa","inoata",234)
        );
        computer_Name.setCellValueFactory(new PropertyValueFactory<>("Computer Name"));
        cpu.setCellValueFactory(new PropertyValueFactory<>("CPU"));
        hdd.setCellValueFactory(new PropertyValueFactory<>("HDD"));
        score.setCellValueFactory(new PropertyValueFactory<>("Score"));
        table.setItems(data);*/
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        computer_Name.setCellValueFactory(new PropertyValueFactory<Elements,String>("comp_name"));
        cpu.setCellValueFactory(new PropertyValueFactory<Elements,String>("cpu_name"));
        hdd.setCellValueFactory(new PropertyValueFactory<Elements,String>("hdd_name"));
        score.setCellValueFactory(new PropertyValueFactory<Elements,Integer>("score"));
        ObservableList<Elements> data= FXCollections.observableArrayList(
                new Elements("Dorian","Popa","inoata",234),
                new Elements("Alt dorian", "tot Popa", "are hdd", 10000)
        );
        table.setItems(data);
    }
}
