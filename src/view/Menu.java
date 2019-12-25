package view;

import controller.MainController;
import javafx.beans.NamedArg;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
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

    public void highlightButton_On(@NamedArg("Button type: 0: rollDice, 1: musicButton, 2: stopButton") int type){
        /*
        * function to turn on highlight for a button. Button selected by the type provided in argument
        */
        System.out.println("highlight on");
    }

    public void highlightButton_Off(@NamedArg("Button type: 0: rollDice, 1: musicButton, 2: stopButton") int type){
        /*
         * function to turn off highlight for a button. Button selected by the type provided in argument
         */
        System.out.println("highlight off");
    }

    @FXML
    private void mouseHover_On(MouseEvent event){
        if (event.getSource() instanceof Button){
            Button source = (Button) event.getSource();
            MainController.getInstance().highlight_On(source.getId());
        }
    }

    @FXML
    private void mouseHover_Off(MouseEvent event){
        if (event.getSource() instanceof Button){
            Button source = (Button) event.getSource();
            MainController.getInstance().highlight_Off(source.getId());
        }
    }

}
