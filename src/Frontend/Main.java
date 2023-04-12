package Frontend;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application{
    Button HDDbutton, CPUbutton, backButton,backButton2;
    Stage window;
    Scene startingScene, CPUscene, HDDscene;
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        //here will be the main javafx code
        window = primaryStage;
        primaryStage.setTitle("The Mighty Capybara's Benchmark");
        Image icon = new Image("Frontend/Images/brown.png");
        window.getIcons().add(icon);

        backButton = new Button();
        backButton.setText("Click to go back to menu!");
        backButton.setOnAction( e-> {
            window.setScene(startingScene);
        });

        backButton2 = new Button();
        backButton2.setText("Click to go back to menu!");
        backButton2.setOnAction( e-> {
            window.setScene(startingScene);
        });

        CPUbutton = new Button();
        CPUbutton.setText("Click to test CPU!");
        CPUbutton.setOnAction( e-> {
            window.setScene(CPUscene);
        });
        //creating layout for CPU test
        Label label2 = new Label("wow such cool CPU");
        VBox CPUTestLayout = new VBox();
        CPUTestLayout.getChildren().addAll(label2, backButton);
        CPUscene = new Scene(CPUTestLayout,300,200);


        HDDbutton = new Button();
        HDDbutton.setText("Click to test HDD!");
        HDDbutton.setOnAction( e-> {
            window.setScene(HDDscene);
        });
        //creating layout for HDD test
        Label label = new Label("wow such cool HDD");
        VBox HDDTestLayout = new VBox();
        HDDTestLayout.getChildren().addAll(label, backButton2);
        HDDscene = new Scene(HDDTestLayout,300,200);
        //creating the layout of the  starting scene
        VBox layout = new VBox();
        layout.getChildren().addAll(HDDbutton,CPUbutton);

        startingScene = new Scene(layout, 300, 200);
        window.setScene(startingScene);
        window.show();

    }

}