package view;

import javafx.beans.NamedArg;
import javafx.fxml.FXML;
import javafx.scene.layout.StackPane;

public class GamePane {
    @FXML
    private StackPane GamePane, redBase, yellowBase, greenBase, blueInitial, yellowInitial, greenInitial, redInitial;
    private HorseCageArea blueHorseCage, yellowHorseCage, greenHorseCage, redHorseCage;

    public GamePane(){}

    public StackPane getGamePane(){
        return GamePane;
    }

    public void translate(@NamedArg("Translate the gamePane by x-horizontal and y verticle") double x, double y){
        GamePane.setTranslateX(x);
        GamePane.setTranslateY(y);
    }
}