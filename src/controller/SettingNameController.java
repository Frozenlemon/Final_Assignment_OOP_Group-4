package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import model.Human;

import javax.swing.text.html.ImageView;
import java.net.URL;
import java.util.ResourceBundle;

public class SettingNameController implements Initializable {

    @FXML
    private ImageView imgYellow, imgRed, imgBlue, imgGreen;

    @FXML
    private TextField txtYellowName, txtRedName, txtBlueName, txtGreenName;

    @FXML
    public void continueGame() {
        Human humanYellow = null;
        if(txtYellowName.getText().isEmpty()) {
            humanYellow = new Human(1, txtYellowName.getText());
        }

        Human humanRed = null;
        if(txtRedName.getText().isEmpty()) {
            humanRed = new Human(3, txtRedName.getText());
        }

        Human humanBlue = null;
        if(txtBlueName.getText().isEmpty()) {
            humanBlue = new Human(0, txtBlueName.getText());
        }

        Human humanGreen = null;
        if(txtGreenName.getText().isEmpty()) {
            humanGreen = new Human(2, txtGreenName.getText());
        }

        ModelController.getInstance(humanYellow,humanYellow, humanBlue, humanGreen);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
