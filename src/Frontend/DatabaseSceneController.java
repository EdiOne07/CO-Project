package Frontend;
import Backend.Elements;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
    public TableView<Elements> table;
    @FXML
    public TableColumn<Elements,String> computer_Name;
    @FXML
    public TableColumn<Elements,String> cpu;
    @FXML
    public TableColumn<Elements,String> hdd;
    @FXML
    public TableColumn<Elements,Integer> Score;
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

    public DatabaseSceneController(ActionEvent event){

       /* CSVWriter a=new CSVWriter();
        a.CreateCSV();
        List<Elements> info=a.readCSV("D:\\Project\\test.csv");
        computer_Name.setCellValueFactory(new PropertyValueFactory<>("Computer Name"));
        cpu.setCellValueFactory(new PropertyValueFactory<>("CPU"));
        hdd.setCellValueFactory(new PropertyValueFactory<>("HDD"));
        score.setCellValueFactory(new PropertyValueFactory<>("Score"));
        tableView.getItems().addAll(info);*/
        TableColumn comp_name=new TableColumn("comp_name");
        TableColumn CPU=new TableColumn("CPU");
        TableColumn HDD=new TableColumn("HDD");
        TableColumn score=new TableColumn("Score");
        table.getColumns().addAll(comp_name,CPU,HDD,score);
        final ObservableList<Elements> data= FXCollections.observableArrayList(
            new Elements("Dorian","Popa","inoata",234)
        );
        comp_name.setCellValueFactory(new PropertyValueFactory<>("Computer Name"));
        CPU.setCellValueFactory(new PropertyValueFactory<>("CPU"));
        HDD.setCellValueFactory(new PropertyValueFactory<>("HDD"));
        score.setCellValueFactory(new PropertyValueFactory<>("Score"));
        table.setItems(data);
    }
}
