package view;

import javafx.beans.NamedArg;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
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
    }

    public void addPane(Node... nodes){
        super.getChildren().setAll(nodes);
    }

    public Menu getMenu(){
        return this.menu;
    }
    public GamePane getGamePane(){
        return this.gamePane;
    }

    public Setting getSetting(){
        return this.setting;
    }

    public StartMenu getStartMenu(){
        return this.startMenu;
    }

    public SettingName getSettingName(){
        return this.settingName;
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
        initSetting();
        initStartMenu();
        initSettingName();
    }

    private void initMenu(){
        initFXMLLoader(menu, "Menu");
        menu.translate(810,0);
    }

    private void initGameArea(){
        initFXMLLoader(gamePane, "GamePane");
        gamePane.init();
    }

    public void initSetting(){
        initFXMLLoader(setting, "setting");
        System.out.println("Setting done");
        setting.init();
    }

    private void initStartMenu(){
        initFXMLLoader(startMenu, "StartMenu");
        System.out.println("StartMenu done");
    }

    private void initSettingName(){
        initFXMLLoader(settingName, "SettingName");
        System.out.println("SettingName done");
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
