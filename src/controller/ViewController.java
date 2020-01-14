package controller;

import javafx.animation.TranslateTransition;
import javafx.beans.NamedArg;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Horse;
import model.Human;
import view.BackGroundPane;

import java.util.Locale;
import java.util.ResourceBundle;

public class ViewController {

    private static ViewController instance;
    private Stage primaryStage;
    private BackGroundPane backGroundPane;
    private int inAnimation;

    private boolean isEndGame;

    private ViewController(){
        primaryStage = new Stage();
        primaryStage.setTitle("Game");
        backGroundPane = new BackGroundPane();
        primaryStage.setScene(new Scene(backGroundPane, 1010, 800));
        inAnimation = 0;
        isEndGame = false;
    }

    public static ViewController getInstance(){
        if (instance == null)
            instance = new ViewController();
        return instance;
    }

    public int getInAnimation(){
        return this.inAnimation;
    }

    public void startGame(Human... humans){
        ModelController.getInstance().reset();
        ModelController.getInstance().initVariable(humans);
        backGroundPane.getGamePane().reset();
        backGroundPane.getMenu().reset();
        backGroundPane.getSettingName().setDisplay(false);
        backGroundPane.setDisplay(true);
    }

    public void clickStart(){
        backGroundPane.getStartMenu().setDisplay(false);
        backGroundPane.getSettingName().setDisplay(true);
    }

    public void clickSetting(){
        backGroundPane.getSetting().setDisplay(true);
    }

    public void clickExit(){
        System.exit(0);
    }

    public void clickHome(){
        isEndGame = true;
        backGroundPane.getGamePane().setDisplay(false);
        backGroundPane.getStartMenu().setDisplay(true);
    }

    public void turnOffSetting(){
        backGroundPane.getSetting().setDisplay(false);
    }

    public void horseMoveAndKick(@NamedArg("Horse to move") Horse moveHorse, @NamedArg("Horse to kick") Horse kickedHorse){
        int kickedHorseIndex = kickedHorse.getId();
        int moveHorseIndex = moveHorse.getId();
        TranslateTransition transition = backGroundPane.getGamePane().kickHorseAnimation(kickedHorseIndex);
        backGroundPane.getGamePane().moveHorse(moveHorseIndex, moveHorse.getPathIndex(), moveHorse.getMoveCount(), transition);
    }

    public void horseMove(@NamedArg("Horse to move") Horse horse){
        int horseIndex = horse.getId();
        backGroundPane.getGamePane().moveHorse(horseIndex, horse.getPathIndex(), horse.getMoveCount(), null);
    }

    public void horseMoveToHome(int horseIndex, int home){
        backGroundPane.getGamePane().moveHorseToHome(horseIndex, home, null);
    }

    public void clickOnDice(int diceId){
        if (!isEndGame && ModelController.getInstance().isPlayer()) {
            System.out.println(inAnimation);
            if (inAnimation == 0)
                ModelController.getInstance().requestFilter("moveHorse", diceId);
        }
    }

    public void clickRollDice(){
        if (!isEndGame && ModelController.getInstance().isPlayer()) {
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
        else if (id.equals("music"))
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
        if (!isEndGame) {
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
        ResourceBundle bundle = ResourceBundle.getBundle("messege");
        String rollDice = bundle.getString("rollDice");
        String stopButton = bundle.getString("stopButton");
        String settingButton = bundle.getString("settingButton");
        backGroundPane.getMenu().setMenuSwitchLanguage(rollDice, stopButton, settingButton);
    }

    private void setSettingNameText(){
        ResourceBundle bundle = ResourceBundle.getBundle("messege");
        String backButton = bundle.getString("backButton");
        String continueButton = bundle.getString("continueButton");
        String choosePlayer = bundle.getString("choosePlayer");
        backGroundPane.getSettingName().setSettingNameSwitchLanguage(backButton,continueButton,choosePlayer);
    }

    private void setSettingText(){
        ResourceBundle bundle = ResourceBundle.getBundle("messege");
        String btnButton = bundle.getString("btnButton");
        String btnHome =  bundle.getString("btnHome");
        String enCheckBox = bundle.getString("enCheckBox");
        String vnCheckBox = bundle.getString("vnCheckBox");
        String chooseLanguageLabel = bundle.getString("chooseLanguageLabel");
        String volumeControlLabel = bundle.getString("volumeControlLabel");
        String msVolume = bundle.getString("msVolume");
        String efVolume = bundle.getString("efVolume");
        String bgmVolume = bundle.getString("bgmVolume");
        backGroundPane.getSetting().setSettingSwitchLanguage(btnButton, btnHome, enCheckBox,vnCheckBox,chooseLanguageLabel,volumeControlLabel,msVolume,efVolume,bgmVolume);
    }

    private void setStartMenuText(){
        ResourceBundle bundle = ResourceBundle.getBundle("messege");
        String startGameButton = bundle.getString("startGameButton");
        String exitGameButton = bundle.getString("exitGameButton");
        String settingButton = bundle.getString("settingButton");
        backGroundPane.getStartMenu().setStartMenuSwitchLanguage(startGameButton, settingButton,exitGameButton);
    }

    public void addAnimation(){
        this.inAnimation++;
    }
    
    public void showSetting() {
        backGroundPane.initSetting();
    }

    public void finishAnimation(){
        inAnimation--;
        ModelController.getInstance().endTurn();
    }

    public void setIsEndGame(boolean status){
        this.isEndGame = status;
    }

    public boolean isEndGame(){
        return isEndGame;
    }
}
