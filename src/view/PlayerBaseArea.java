package view;

import javafx.beans.NamedArg;
import javafx.fxml.FXML;
import javafx.scene.layout.StackPane;

public class PlayerBaseArea {

    @FXML
    private StackPane baseArea;

    private StackPane base;
    private StackPane[] paths;


    public PlayerBaseArea(@NamedArg("FXML filename") String fileName){
        /*
         * Constructor to load the correct fxml file base on provided fileName. The loader will load the outer component of the fxml
         * into the baseArea
         */
    }

    //Add getter and setter here

    public StackPane getPath(int index){
        /*
         * return an object from paths base on index
         */
        return null;
    }

    private void initBase(){
        /*
         * Function to get base in baseArea
         */
    }

    private void initPaths(){
        /*
         * Function to get all the paths(FXML) component in baseArea and add them into the paths array
         */
    }

}
