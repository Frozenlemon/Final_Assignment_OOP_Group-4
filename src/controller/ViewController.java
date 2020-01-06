package controller;

import javafx.beans.NamedArg;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Horse;
import view.BackGroundPane;

public class ViewController {

    private static ViewController instance;
    private Stage primaryStage;
    private BackGroundPane backGroundPane;

    private ViewController(){
        primaryStage = new Stage();
        primaryStage.setTitle("PLACE HOLDER NAME");
        backGroundPane = new BackGroundPane();
        primaryStage.setScene(new Scene(backGroundPane, 1010, 800));
    }

    public static ViewController getInstance(){
        if (instance == null)
            instance = new ViewController();
        return instance;
    }

    public void horseMoveAndKick(@NamedArg("Horse to move") Horse moveHorse, @NamedArg("Horse to kick") Horse kickedHorse){

    }

    public void horseMove(@NamedArg("Horse to move") Horse horse){
        int pathIndex = horseIdConverter(horse.getId());
        backGroundPane.getGamePane()
    }

    public void highlight_On(String id){
        int type = filterButtonType(id);
        backGroundPane.getMenu().highlightButton_On(type);
    }

    public void highlight_Off(String id){
        int type = filterButtonType(id);
        backGroundPane.getMenu().highlightButton_Off(type);
    }

    private int filterButtonType(String id){
        if (id.equals("rollDice"))
            return 0;
        else if (id.equals("music"))
            return 1;
        else
            return 2;
    }

    public void update(){
        primaryStage.show();
    }

    private int horseIdConverter(int id){
        int areaCode = id / 100;
        int locationOnPath = id % 100;
        return (areaCode * 12) + locationOnPath;
    }
}
