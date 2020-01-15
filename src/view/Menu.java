package view;

import controller.SoundController;
import controller.ViewController;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.NamedArg;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.effect.Effect;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;
import util.FileIO;

import java.util.Random;

public class Menu {

    private static final int ANIMATION_CYCLE = 5;

    @FXML
    private StackPane menuArea;
    @FXML
    private Button rollDice, pauseButton, settingButton;
    @FXML
    private ImageView dice1, dice2;

    public Menu() {
    }

    public Button getRollDice() {
        return rollDice;
    }

    public Button getStopButton() {
        return pauseButton;
    }

    public Button getSettingButton() {return settingButton; }


    public StackPane getMenuArea() {
        return menuArea;
    }

    public void setMenuSwitchLanguage(String... inputs){
        rollDice.setText(inputs[0]);
        pauseButton.setText(inputs[1]);
        settingButton.setText(inputs[2]);
    }

    public ImageView getDice(int id) {
        if (id == 0) {
            return dice1;
        }
        return dice2;
    }

    public void setText(String... textValue){
        rollDice.setText(textValue[0]);
        pauseButton.setText(textValue[1]);
    }

    public void reset(){
        dice1.setImage(null);
        dice2.setImage(null);
    }

    public void rollAnimation(int value1, int value2){
        Timeline animation = new Timeline();
        KeyFrame kf = new KeyFrame(Duration.millis(200), e-> {
            Random rand = new Random();
            dice1.setImage(new Image("file:" + FileIO.getDiceImage(rand.nextInt(6))));
            dice2.setImage(new Image("file:" + FileIO.getDiceImage(rand.nextInt(6))));
        });
        animation.setCycleCount(ANIMATION_CYCLE);
        animation.setOnFinished(e -> {
            dice1.setImage(new Image("file:" + FileIO.getDiceImage(value1)));
            dice2.setImage(new Image("file:" + FileIO.getDiceImage(value2)));
            ViewController.getInstance().finishAnimation();
        });
        animation.getKeyFrames().add(kf);
        ViewController.getInstance().addAnimation();
        animation.playFromStart();
        SoundController.getInstance().playRollDice();
    }

    //function to move the menuArea by x and y amount
    public void translate(@NamedArg("x pixels") double x, @NamedArg("y pixels") double y) {
        menuArea.setTranslateX(x);
        menuArea.setTranslateY(y);
    }

    public void highlightButton_On(@NamedArg("Button type: 0: rollDice, 1: settingButton, 2: pauseButton") int type) {
        Effect shadow = new javafx.scene.effect.DropShadow();
        if (type ==0  || type ==2)
            rollDice.setEffect(shadow);
        else if (type==1)
            settingButton.setEffect(shadow);
        else
            pauseButton.setEffect(shadow);
    }

    public void highlightButton_Off(@NamedArg("Button type: 0: rollDice, 1: settingButton, 2: pauseButton") int type) {
        if (type ==0  || type ==2)
            rollDice.setEffect(null);
        else if (type==1)
            settingButton.setEffect(null);
        else
            pauseButton.setEffect(null);
    }

    @FXML
    public void clickOnPause(MouseEvent evt){
        ViewController.getInstance().pause();
    }

    @FXML
    public void clickRollDice(MouseEvent event){
        ViewController.getInstance().clickRollDice();
    }

    @FXML
    private void mouseHover_On(MouseEvent event){
        if (event.getSource() instanceof Button){
            Button source = (Button) event.getSource();
            ViewController.getInstance().highlight_On(source.getId());
        }
    }

    @FXML
    private void mouseHover_Off(MouseEvent event){
        if (event.getSource() instanceof Button){
            Button source = (Button) event.getSource();
            ViewController.getInstance().highlight_Off(source.getId());
        }
    }

    @FXML
    private void clickOnDice(MouseEvent evt){
        ImageView button = (ImageView) evt.getSource();
        int diceId;
        if (button.getId().equals("b0"))
            diceId = 0;
        else
            diceId = 1;
        ViewController.getInstance().clickOnDice(diceId);
    }

    
    @FXML
    private void showSetting() {
        ViewController.getInstance().showSetting();
    }

}
