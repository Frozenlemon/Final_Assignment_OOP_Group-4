package view;

import controller.ModelController;
import controller.ViewController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import model.Human;

import javax.swing.text.html.ImageView;
import java.net.URL;
import java.util.ResourceBundle;
import util.Language;

public class SettingName{

    @FXML
    private StackPane playerSelectPane;

    @FXML
    private ImageView imgYellow, imgRed, imgBlue, imgGreen;

    @FXML
    private TextField txtYellowName, txtRedName, txtBlueName, txtGreenName;

    public SettingName(){
        Language.setLanguageText(playerSelectPane.getChildrenUnmodifiable());
    }

    @FXML
    public void continueGame() {
        Human humanYellow = null;
        if(!txtYellowName.getText().isEmpty()) {
            humanYellow = new Human(1, txtYellowName.getText());
        }

        Human humanRed = null;
        if(!txtRedName.getText().isEmpty()) {
            humanRed = new Human(3, txtRedName.getText());
        }

        Human humanBlue = null;
        if(!txtBlueName.getText().isEmpty()) {
            humanBlue = new Human(0, txtBlueName.getText());
        }

        Human humanGreen = null;
        if(!txtGreenName.getText().isEmpty()) {
            humanGreen = new Human(2, txtGreenName.getText());
        }

        ViewController.getInstance().startGame(humanBlue, humanYellow, humanGreen, humanRed);
    }

}
