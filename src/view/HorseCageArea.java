package view;

import javafx.beans.NamedArg;
import javafx.fxml.FXML;
import javafx.scene.layout.StackPane;

public class HorseCageArea {

    @FXML
    private StackPane horseCageArea;

    private StackPane[] cages;

    public HorseCageArea(@NamedArg("FXML filename") String fileName){
        /*
         * Constructor to load the correct fxml file base on provided fileName. The loader will load the outer component of the fxml
         * into the horseCageArea
         */
    }

    //Add getter and setter here

    public StackPane getCage(int index){
        /*
         * return an object from paths base on index
         */
        return null;
    }

    private void initCages(){
        /*
         * Function to get all the cages(FXML) component in baseArea and add them into the cages array
         */
    }
}
