/*
  RMIT University Vietnam
  Course: INTE2512 Object-Oriented Programming
  Semester: 2019C
  Assessment: Final Project
  Created date: 23/03/2019
  By: Le Minh s3757324
      Ho Duy Hoang s3672214
      Pham Thanh Dat s3678437
      Ta Quoc Thang s3713564
      Le Nguyen Thien Phu s3639855
  Last modified: 15/01/2020
  By: Le Minh s3757324
      Ho Duy Hoang s3672214
      Pham Thanh Dat s3678437
      Ta Quoc Thang s3713564
      Le Nguyen Thien Phu s3639855
  Acknowledgement:
[1] "Java Internationalization - javatpoint", www.javatpoint.com, 2020. [Online]. Available: https://www.javatpoint.com/internationalization-in-java. [Accessed: 14- Jan- 2020]
[2]"Internationalizing the Sample Program (The Java™ Tutorials > Internationalization > Introduction)", Docs.oracle.com, 2020. [Online]. Available: https://docs.oracle.com/javase/tutorial/i18n/intro/steps.html?fbclid=IwAR00AiifBkc8cmbF43D0YEUM6ZYayCq_ldGYnVxZBzE0xlL_-2n-5ElxSeE. [Accessed: 14- Jan- 2020]
[3]Tutorials.jenkov.com, 2020. [Online]. Available: http://tutorials.jenkov.com/java-internationalization/resourcebundle.html?fbclid=IwAR2FqduxMXmdSdnltj4q7BIrOCbadtPCiSEWwg2da_mvY71n-rPZHuQEG58. [Accessed: 14- Jan- 2020]
[4]"Code Conventions for the Java Programming Language: 9. Naming Conventions", Oracle.com, 2020. [Online]. Available: https://www.oracle.com/technetwork/java/codeconventions-135099.html?fbclid=IwAR2ZlGNIfZqOB3fUjnW0A5dKgyPZFeaq8XKSgGbbfc6qBrw3dnmC-skDNdo. [Accessed: 14- Jan- 2020]
[5]"JavaFX Playing Audio - javatpoint", www.javatpoint.com, 2020. [Online]. Available: https://www.javatpoint.com/javafx-playing-audio?fbclid=IwAR3WFDkfH5LvjWqk6OCoY55aLShLcb4VWCM7IFAyA-JkZBAYRlSpz0U-hFc. [Accessed: 14- Jan- 2020]
[6]"CSS Examples", W3schools.com, 2020. [Online]. Available: https://www.w3schools.com/css/css_examples.asp. [Accessed: 14- Jan- 2020]
*/
package view;


import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
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
