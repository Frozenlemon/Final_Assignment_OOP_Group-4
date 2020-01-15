package view;

import controller.ViewController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;

public class SettingName{

    @FXML
    private StackPane playerSelectPane;

    @FXML
    private TextField txtYellowName, txtRedName, txtBlueName, txtGreenName;

    @FXML
    private Button backButton, continueButton;

    @FXML
    private Label choosePlayer;

    public SettingName(){
    }

    public StackPane getPlayerSelectPane(){
        return playerSelectPane;
    }

    @FXML
    public void continueGame() {
        String[] players = new String[4];
        if(!txtBlueName.getText().isEmpty()) {
            players[0] = txtBlueName.getText();
        }

        if(!txtYellowName.getText().isEmpty()) {
            players[1] = txtYellowName.getText();
        }

        if(!txtGreenName.getText().isEmpty()) {
            players[2] = txtGreenName.getText();
        }

        if(!txtRedName.getText().isEmpty()) {
            players[3] = txtRedName.getText();
        }

        ViewController.getInstance().startGame(players);
    }

    public void clickBack(){
        ViewController.getInstance().clickHome();
    }


    public void setSettingNameSwitchLanguage(String...inputs){
        backButton.setText(inputs[0]);
        continueButton.setText(inputs[1]);
        choosePlayer.setText(inputs[2]);
    }
}
