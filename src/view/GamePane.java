package view;

import javafx.beans.NamedArg;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Circle;

public class GamePane {
    @FXML
    private StackPane GamePane, redBase, yellowBase, greenBase, blueInitial, yellowInitial, greenInitial, redInitial;
    @FXML
    private HorseCageArea blueHorseCage, yellowHorseCage, greenHorseCage, redHorseCage;
    private ImageView[] horses;

    public GamePane(){
        horses = new ImageView[16];
    }

    public StackPane getGamePane(){
        return GamePane;
    }

    public void translate(@NamedArg("Translate the gamePane by x-horizontal and y verticle") double x, double y){
        GamePane.setTranslateX(x);
        GamePane.setTranslateY(y);
    }

    public void moveHorse(int index, int toAreaCode, int toLocationOnPath){
        
    }
}