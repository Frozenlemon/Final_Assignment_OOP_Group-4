package controller;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class MainController {

    private static MainController instance;
    private Stage primaryStage;

    private MainController(){
        primaryStage = new Stage();
        primaryStage.setTitle("PLACE HOLDER NAME");
        primaryStage.setFullScreen(true);

        double width = primaryStage.getWidth();
        double height = primaryStage.getHeight();
        primaryStage.setScene(new Scene(new Pane(), width, height));
    }

    public static MainController getInstance(){
        if (instance == null)
            instance = new MainController();
        return instance;
    }

    public void update(){
        primaryStage.show();
    }
}
