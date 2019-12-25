package view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.StackPane;
import util.FileIO;

import java.io.IOException;

public class BackGroundPane extends StackPane {
    Menu menu;
    GamePane gamePane;

    public BackGroundPane(){
        super();
        super.setPrefSize(1000,800);
        gamePane = new GamePane();
        menu = new Menu();
        initMenu();
        super.getChildren().add(menu.getMenuArea());
    }

    private void initMenu(){
        FXMLLoader menuLoader = new FXMLLoader();
        menuLoader.setLocation(FileIO.getFXML_URL("Menu"));
        menuLoader.setController(menu);
        try {
            menuLoader.load();
        }catch (IOException e){
            System.out.println(e);
        }
    }
}
