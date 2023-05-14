package Frontend;

import javafx.event.ActionEvent;
import javafx.scene.control.TextField;
import Frontend.TestBenchmark.TestDigitsOfPi;

public class DigitsOfPiSceneController {
    public TextField LoadTextField;

    public void TestDigitsOfPi(ActionEvent event){
        Integer load = Integer.valueOf(LoadTextField.getText());
        TestDigitsOfPi test = new TestDigitsOfPi(load);
        System.out.println("Finished in" + test.getTime() + " s");
    }
}
