package view;

import javafx.beans.NamedArg;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;

public class Menu {

    @FXML
    private StackPane menuArea;

    private StackPane diceArea;
    private StackPane menu;
    private Button rollDice, quit;

    public Menu(@NamedArg("FXML fileName") String fileName){
        /*
         * Constructor to load the correct fxml file base on provided fileName. The loader will load the outer component of the fxml
         * into the menuArea
         */
    }

    //Add getter and setter here

    private void initDiceArea(){
        /*
         * Function to assign get the diceArea(FXML) component in menuArea into diceArea
         */
    }

    private void initMenu(){
        /*
         * Function to assign get the diceArea(FXML) component in menuArea into diceArea
         */
    }

    private void initButton(){
        /*
         * Function to assign get the 2 buttons rollDice and quit(FXML) component in menu into diceArea
         */
    }
}
