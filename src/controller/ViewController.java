package controller;

import javafx.beans.NamedArg;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import model.Horse;
import model.Human;
import util.FileIO;
import view.BackGroundPane;

public class ViewController {

    private static ViewController instance;
    private Stage primaryStage;
    private BackGroundPane backGroundPane;
    private int inAnimation;

    private ViewController(){
        primaryStage = new Stage();
        primaryStage.setTitle("PLACE HOLDER NAME");
        backGroundPane = new BackGroundPane();
        primaryStage.setScene(new Scene(backGroundPane, 1010, 800));
        inAnimation = 0;
    }

    public static ViewController getInstance(){
        if (instance == null)
            instance = new ViewController();
        return instance;
    }

    public void startGame(Human... humans){
        ModelController.getInstance().initVariable(humans);
        //backGroundPane.startGame();
    }

    public int getInAnimation(){
        return this.inAnimation;
    }

    public void horseMoveAndKick(@NamedArg("Horse to move") Horse moveHorse, @NamedArg("Horse to kick") Horse kickedHorse){
        int kickedHorseIndex = horseIdConverter(kickedHorse.getId());
        horseMove(moveHorse);
        while (inAnimation !=0){};
        backGroundPane.getGamePane().kickHorse(kickedHorseIndex);
    }

    public void horseMove(@NamedArg("Horse to move") Horse horse){
        int horseIndex = horseIdConverter(horse.getId());
        backGroundPane.getGamePane().moveHorse(horseIndex, horse.getPathIndex(), horse.getMoveCount(), null);
    }

    public void clickOnDice(int diceId){
        ModelController.getInstance().moveHorse(diceId);
    }

    public void clickRollDice(){
        ModelController.getInstance().rollDice();
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
        primaryStage.show();
    }

    public void updateDice(int value_0, int value_1){
        backGroundPane.getMenu().getDice(0).setImage(new Image("file:" + FileIO.getDiceImage(value_0)));
        backGroundPane.getMenu().getDice(1).setImage(new Image("file:" + FileIO.getDiceImage(value_1)));
    }

    public void clickOnHorse(int horseIndex){
        Boolean success = ModelController.getInstance().selectHorse(horseIndex);
        if (success)
            backGroundPane.getGamePane().change_Horse_Highlight(horseIndex);
    }

    public void finishAnimation(){
        this.inAnimation--;
    }

    public void startAnimation(){
        this.inAnimation++;
    }

    private int horseIdConverter(int id){
        int areaCode = id / 10;
        int index = id % 10;
        return (areaCode * 4) + index;
    }
    
    public void showSetting() {
        backGroundPane.initSetting();
    }
}
