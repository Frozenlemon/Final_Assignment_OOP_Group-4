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
import java.util.Scanner;

public class ViewController {

    private static ViewController instance;
    private Stage primaryStage;
    private BackGroundPane backGroundPane;
    private int inAnimation;

    private boolean isEndGame;

    private ViewController(){
        primaryStage = new Stage();
        primaryStage.setTitle("PLACE HOLDER NAME");
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
        ModelController.getInstance().initVariable(humans);
        //backGroundPane.startGame();
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
        //SoundController.getInstance().playMusic();
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
        int lang;
        Scanner s = new Scanner(System.in);
        lang = s.nextInt();
        ResourceBundle messages;

        if (choice.equals("vn")) {
            Locale.setDefault(new Locale("vi", "VN"));
        }
        else{
            Locale.setDefault(new Locale("en", "US"));
        }
        ResourceBundle bundle = ResourceBundle.getBundle("messege");
        String rollDice = bundle.getString("rollDice");
        String stopButton = bundle.getString("stopButton");
        String settingButton = bundle.getString("settingButton");
        backGroundPane.getMenu().setMenuSwitchLanguage(rollDice, stopButton, settingButton);
        String backButton = bundle.getString("backButton");
        String continueButton = bundle.getString("continueButton");
        String choosePlayer = bundle.getString("choosePlayer");
        backGroundPane.getSettingName().setSettingNameSwitchLanguage(backButton,continueButton,choosePlayer);
        String btnButton = bundle.getString("btnButton");
        String btnHome =  bundle.getString("btnHome");
        String enCheckBox = bundle.getString("enCheckBox");
        String vnCheckBox = bundle.getString("vnCheckBox");
        String chooseLanguageLabel = bundle.getString("chooseLanguageLabel");
        String volumeControlLabel = bundle.getString("volumeControlLabel");
        String msVolume = bundle.getString("msVolume");
        String efVolume = bundle.getString("efVolume");
        String bgmVolume = bundle.getString("bgmVolume");
        backGroundPane.getSetting().setSettingSwitchLanguage(btnButton,btnHome,enCheckBox,vnCheckBox,chooseLanguageLabel,volumeControlLabel,msVolume,efVolume,bgmVolume);
        String startGameButton = bundle.getString("startGameButton");
        String exitGameButton = bundle.getString("exitGameButton");
        backGroundPane.getStartMenu().setStartMenuSwitchLanguage(startGameButton,exitGameButton);
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
