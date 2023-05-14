package Frontend;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Slider;
import Frontend.TestBenchmark.TestDigitsOfPi;

public class DigitsOfPiSceneController {
    private  TestDigitsOfPi test;
    @FXML
    private Slider slider;
    public DigitsOfPiSceneController(){
        test = new TestDigitsOfPi();

    }

    public void TestDigitsOfPi(ActionEvent event){
        Integer load = (int) (slider.getValue());
        test.run(load);
        System.out.println("Finished in " + test.getTime() + " s");
    }
}
