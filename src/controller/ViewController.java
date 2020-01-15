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
package controller;

import javafx.animation.TranslateTransition;
import javafx.beans.NamedArg;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Horse;
import view.BackGroundPane;

import java.util.Locale;
import java.util.ResourceBundle;

public class ViewController {

    private static ViewController instance;
    private Stage primaryStage;
    private BackGroundPane backGroundPane;
    private int inAnimation;

    private boolean isEndGame;
    private boolean isPause;

    private ViewController(){
        primaryStage = new Stage();
        primaryStage.setTitle("Game");
        backGroundPane = new BackGroundPane();
        backGroundPane.addPane(backGroundPane.getStartMenu().getStartMenu());
        primaryStage.setScene(new Scene(backGroundPane, 1010, 800));
        inAnimation = 0;
        isEndGame = true;
    }

    public static ViewController getInstance(){
        if (instance == null)
            instance = new ViewController();
        return instance;
    }

    public int getInAnimation(){
        return this.inAnimation;
    }

    public void startGame(String... humans){
        reset();
        ModelController.getInstance().initVariable(humans);
        backGroundPane.getGamePane().initLabel(humans);
        backGroundPane.addPane(backGroundPane.getGamePane().getGamePane(), backGroundPane.getMenu().getMenuArea());
    }

    private void reset(){
        isEndGame = false;
        isPause = false;
        backGroundPane.getGamePane().reset();
        backGroundPane.getMenu().reset();
        ModelController.getInstance().reset();
    }

    public void clickStart(){
        backGroundPane.addPane(backGroundPane.getSettingName().getPlayerSelectPane());
    }

    public void clickSetting(){
        backGroundPane.addPane(backGroundPane.getSetting().getSettingPane());
    }

    public void clickExit(){
        System.exit(0);
    }

    public void clickHome(){
        isEndGame = true;
        backGroundPane.addPane(backGroundPane.getStartMenu().getStartMenu());
    }

    public void turnOffSetting(){
        if (isEndGame)
            backGroundPane.addPane(backGroundPane.getStartMenu().getStartMenu());
        else
            backGroundPane.addPane(backGroundPane.getGamePane().getGamePane(), backGroundPane.getMenu().getMenuArea());
    }

    public void horseMoveAndKick(@NamedArg("Horse to move") Horse moveHorse, @NamedArg("Horse to kick") Horse kickedHorse){
        int kickedHorseIndex = kickedHorse.getId();
        int moveHorseIndex = moveHorse.getId();
        TranslateTransition transition = backGroundPane.getGamePane().kickHorseAnimation(kickedHorseIndex);
        backGroundPane.getGamePane().moveHorse(moveHorseIndex, moveHorse.getPathIndex(), moveHorse.getMoveCount(), transition);
    }

    public void horseMove(@NamedArg("Horse to move") Horse horse, boolean isRelease){
        int horseIndex = horse.getId();
        if (isRelease)
            SoundController.getInstance().playHorseRelease();
        backGroundPane.getGamePane().moveHorse(horseIndex, horse.getPathIndex(), horse.getMoveCount(), null);
    }

    public void horseMoveToHome(int horseIndex, int home){
        backGroundPane.getGamePane().moveHorseToHome(horseIndex, home);
    }

    public void clickOnDice(int diceId){
        if (!isPause && !isEndGame && ModelController.getInstance().isPlayer()) {
            System.out.println(inAnimation);
            if (inAnimation == 0)
                ModelController.getInstance().requestFilter("moveHorse", diceId);
        }
    }

    public void clickRollDice(){
        if (!isPause && !isEndGame && ModelController.getInstance().isPlayer()) {
            System.out.println(inAnimation);
            if (inAnimation == 0)
                ModelController.getInstance().requestFilter("rollDice", -1);
        }
    }

    public void setHorse_Highlight(int horseIndex){
        backGroundPane.getGamePane().change_Horse_Highlight(horseIndex);
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
        else if (id.equals("setting"))
            return 1;
        else
            return 2;
    }

    public void update(){
        SoundController.getInstance().playMusic();
        primaryStage.show();
    }

    public void updateDice(int value_0, int value_1){
        backGroundPane.getMenu().rollAnimation(value_0, value_1);
    }

    public void clickOnHorse(int horseIndex){
        if (!isEndGame && !isPause) {
            if (ModelController.getInstance().isPlayer()) {
                Boolean success = ModelController.getInstance().selectHorse(horseIndex);
                if (success)
                    backGroundPane.getGamePane().change_Horse_Highlight(horseIndex);
            }
        }
    }

    public void switchLanguage(String choice){
        Locale vnLocale = new Locale("vi","VN");
        Locale.setDefault(vnLocale);

        if (choice.equals("vn")) {
            Locale.setDefault(new Locale("vi", "VN"));
        }
        else{
            Locale.setDefault(new Locale("en", "US"));
        }
        setMenuText();
        setSettingNameText();
        setSettingText();
        setStartMenuText();
    }

    private void setMenuText(){
        ResourceBundle bundle = ResourceBundle.getBundle("messages");
        String rollDice = bundle.getString("rollDice");
        String stopButton = bundle.getString("stopButton");
        String settingButton = bundle.getString("settingButton");
        backGroundPane.getMenu().setMenuSwitchLanguage(rollDice, stopButton, settingButton);
    }

    private void setSettingNameText(){
        ResourceBundle bundle = ResourceBundle.getBundle("messages");
        String backButton = bundle.getString("backButton");
        String continueButton = bundle.getString("continueButton");
        String choosePlayer = bundle.getString("choosePlayer");
        backGroundPane.getSettingName().setSettingNameSwitchLanguage(backButton,continueButton,choosePlayer);
    }

    private void setSettingText(){
        ResourceBundle bundle = ResourceBundle.getBundle("messages");
        String btnButton = bundle.getString("btnButton");
        String btnHome =  bundle.getString("btnHome");
        String enCheckBox = bundle.getString("enCheckBox");
        String vnCheckBox = bundle.getString("vnCheckBox");
        String chooseLanguageLabel = bundle.getString("chooseLanguageLabel");
        String volumeControlLabel = bundle.getString("volumeControlLabel");
        String msVolume = bundle.getString("msVolume");
        String efVolume = bundle.getString("efVolume");
        String bgmVolume = bundle.getString("bgmVolume");
        backGroundPane.getSetting().setSettingSwitchLanguage(btnButton, btnHome, enCheckBox, vnCheckBox, chooseLanguageLabel, volumeControlLabel, msVolume, efVolume, bgmVolume);
    }

    private void setStartMenuText(){
        ResourceBundle bundle = ResourceBundle.getBundle("messages");
        String startGameButton = bundle.getString("startGameButton");
        String exitGameButton = bundle.getString("exitGameButton");
        String settingButton = bundle.getString("settingButton");
        backGroundPane.getStartMenu().setStartMenuSwitchLanguage(startGameButton, settingButton,exitGameButton);
    }

    public void addAnimation(){
        this.inAnimation++;
    }
    
    public void showSetting() {
        backGroundPane.addPane(backGroundPane.getSetting().getSettingPane());
    }

    public void finishAnimation(){
        inAnimation--;
        if (!isPause) {
            //buffer();
            ModelController.getInstance().endTurn();
        }
    }

    public void setIsEndGame(boolean status){
        this.isEndGame = status;
    }

    public boolean isEndGame(){
        return isEndGame;
    }

    public void pause(){
        isPause = !isPause;
        if (!isPause)
            ModelController.getInstance().endTurn();
    }

    public void showPlayerTurn(int player){
        System.out.println(player);
        backGroundPane.getGamePane().setPlayerTurn(player);
    }

    public void upDateScore(int player, int score){
        backGroundPane.getGamePane().setScore(player, score);
    }

    private void buffer(){
        long t= System.currentTimeMillis();
        long end = t + 500;
        while(System.currentTimeMillis() < end){}
    }

}
