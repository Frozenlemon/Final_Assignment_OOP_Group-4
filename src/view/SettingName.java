package view;

import controller.ViewController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import model.Human;

public class SettingName{

    @FXML
    private AnchorPane playerSelectPane;

    @FXML
    private TextField txtYellowName, txtRedName, txtBlueName, txtGreenName;

    @FXML
    private Button backButton, continueButton;

    @FXML
    private Label choosePlayer;

    public SettingName(){
    }

    public AnchorPane getPlayerSelectPane(){
        return playerSelectPane;
    }

    public void setDisplay(boolean status){
        playerSelectPane.setVisible(status);
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

    public void setSettingNameSwitchLanguage(String...inputs){
        backButton.setText(inputs[0]);
        continueButton.setText(inputs[1]);
        choosePlayer.setText(inputs[2]);
    }
}
