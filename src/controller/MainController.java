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
        primaryStage.setScene(new Scene(backGroundPane, 1010, 800));
    }

    public static MainController getInstance(){
        if (instance == null)
            instance = new MainController();
        return instance;
    }

    public void highlight(String id, int type){
        switch (type){
            case 0:
                ViewController.getInstance().highlight_On(id, backGroundPane);
                break;
            case 1:
                ViewController.getInstance().highlight_Off(id, backGroundPane);
        }
    }

    public void update(){
        primaryStage.show();
    }
}
