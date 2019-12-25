package view;

import javafx.beans.NamedArg;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

public class Menu {

    @FXML
    private StackPane menuArea;
    @FXML
    private Button rollDice, stopButton;
    @FXML
    private ToggleButton musicButton;
    @FXML
    private ImageView dice1, dice2;

    public Menu() {}

    public Button getRollDice() {
        return rollDice;
    }

    public Button getStopButton() {
        return stopButton;
    }

    public ToggleButton getMusicButton() {
        return musicButton;
    }

    public StackPane getMenuArea() {
        return menuArea;
    }

    public ImageView getDice(int id){
        if (id == 0){
            return dice1;
        }
        return dice2;
    }

    //function to move the menuArea by x and y amount
    public void translate(@NamedArg("x pixels") double x, @NamedArg("y pixels") double y){
        menuArea.setTranslateX(x);
        menuArea.setTranslateY(y);
    }

}
