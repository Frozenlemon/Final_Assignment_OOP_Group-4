package controller;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import view.BackGroundPane;

public class MainController {

    private static MainController instance;
    private Stage primaryStage;
    private BackGroundPane backGroundPane;

    private MainController(){
        primaryStage = new Stage();
        primaryStage.setTitle("PLACE HOLDER NAME");
        backGroundPane = new BackGroundPane();
        primaryStage.setScene(new Scene(backGroundPane));
    }

    public static MainController getInstance(){
        if (instance == null)
            instance = new MainController();
        return instance;
    }

    public void highlight_On(String id){
        int type = filterButtonType(id);
        backGroundPane.getMenu().highlightButton_On(type);
    }

    public void highlight_Off(String id){
        int type = filterButtonType(id);
        backGroundPane.getMenu().highlightButton_Off(type);
    }

    public void update(){
        primaryStage.show();
    }

    private int filterButtonType(String id){
        if (id.equals("rollDice"))
            return 0;
        else if (id.equals("music"))
            return 1;
        else
            return 2;
    }
}
