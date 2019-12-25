package view;

import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.layout.StackPane;
import util.FileIO;

import java.io.IOException;

public class BackGroundPane extends StackPane {
    Menu menu;
    GamePane gamePane;

    public BackGroundPane(){
        super();
        super.setPrefSize(1000,800);
        super.setAlignment(Pos.TOP_LEFT);
        initBackGroundPane();
        super.getChildren().add(menu.getMenuArea());
    }

    public Menu getMenu(){
        return this.menu;
    }

    private void initBackGroundPane(){
        gamePane = new GamePane();
        menu = new Menu();
        initMenu();
        menu.translate(800,0);
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
