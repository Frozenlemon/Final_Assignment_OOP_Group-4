package view;

import javafx.beans.NamedArg;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import util.FileIO;

import java.io.IOException;

public class BackGroundPane extends StackPane {
    private Menu menu;
    private GamePane gamePane;
    private Setting setting;
    private StartMenu startMenu;
    private SettingName settingName;

    public BackGroundPane(){
        super();
        super.setPrefSize(1010,800);
        super.setAlignment(Pos.TOP_LEFT);
        initBackGroundPane();
        super.getChildren().addAll(gamePane.getGamePane(), menu.getMenuArea());
    }

    public Menu getMenu(){
        return this.menu;
    }
    public GamePane getGamePane(){
        return this.gamePane;
    }

//    public void startGame(){
//        setting.setVisible(false);
//        startMenu.setVisible(false);
//        settingName.reset();
//        gamePane.start();
//    }

    private void initBackGroundPane(){
        gamePane = new GamePane();
        menu = new Menu();
        setting = new Setting();
        startMenu = new StartMenu();
        settingName = new SettingName();
        initWindow();
    }

    private void initWindow(){
        initMenu();
        initGameArea();
//        initSetting();
//        initStartMenu();
//        initSettingName();
    }

    private void initMenu(){
        initFXMLLoader(menu, "Menu");
        menu.translate(810,0);
    }

    private void initGameArea(){
        initFXMLLoader(gamePane, "GamePane");
        gamePane.init();
    }

    private void initSetting(){
        initFXMLLoader(setting, "setting");
        setting.init();
    }

    private void initStartMenu(){
        initFXMLLoader(startMenu, "StartMenu");
    }

    private void initSettingName(){
        initFXMLLoader(settingName, "SettingName");
    }

    private void initFXMLLoader(Object controller, String fileName){
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(FileIO.getFXML_URL(fileName));
        loader.setController(controller);
        loadFXML(loader);
    }

    private void loadFXML(FXMLLoader loader){
        try {
            loader.load();
        }catch (IOException e){
            System.out.println(e);
            e.printStackTrace();
        }catch (Exception e){
            System.out.println(e);
            e.printStackTrace();
        }
    }
}
