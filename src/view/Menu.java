package view;

import controller.ViewController;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.NamedArg;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import javafx.scene.effect.Effect;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;
import util.FileIO;

import java.util.Locale;
import java.util.Random;
import java.util.ResourceBundle;

import util.Language;

public class Menu {

    @FXML
    private StackPane menuArea;
    @FXML
    private Button rollDice, stopButton, settingButton;
    @FXML
    private ToggleButton musicButton;
    @FXML
    private ImageView dice1, dice2;

    public Menu() {
    }

    public Button getRollDice() {
        return rollDice;
    }

    public Button getStopButton() {
        return stopButton;
    }

    public Button getSettingButton() {return settingButton; }

    public ToggleButton getMusicButton() {
        return musicButton;
    }

    public StackPane getMenuArea() {
        return menuArea;
    }

    public void setMenuSwitchLanguage(String... inputs){
        rollDice.setText(inputs[0]);
        stopButton.setText(inputs[1]);
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
        stopButton.setText(textValue[1]);
    }

    public void rollAnimation(int value1, int value2){
        Timeline animation = new Timeline();
        KeyFrame kf = new KeyFrame(Duration.millis(200), e-> {
            Random rand = new Random();
            dice1.setImage(new Image("file:" + FileIO.getDiceImage(rand.nextInt(6))));
            dice2.setImage(new Image("file:" + FileIO.getDiceImage(rand.nextInt(6))));
        });
        animation.setCycleCount(10);
        animation.setOnFinished(e -> {
            dice1.setImage(new Image("file:" + FileIO.getDiceImage(value1)));
            dice2.setImage(new Image("file:" + FileIO.getDiceImage(value2)));
            ViewController.getInstance().finishAnimation();
        });
        animation.getKeyFrames().add(kf);
        ViewController.getInstance().addAnimation();
        animation.playFromStart();
    }

    //function to move the menuArea by x and y amount
    public void translate(@NamedArg("x pixels") double x, @NamedArg("y pixels") double y) {
        menuArea.setTranslateX(x);
        menuArea.setTranslateY(y);
    }

    public void highlightButton_On(@NamedArg("Button type: 0: rollDice, 1: musicButton, 2: stopButton") int type) {
        Effect shadow = new javafx.scene.effect.DropShadow();
        if (type ==0  || type ==2)
            rollDice.setEffect(shadow);
        else if (type==1)
            musicButton.setEffect(shadow);
        else
            stopButton.setEffect(shadow);

        System.out.println("highlight on");
    }

    public void highlightButton_Off(@NamedArg("Button type: 0: rollDice, 1: musicButton, 2: stopButton") int type) {
        if (type ==0  || type ==2)
            rollDice.setEffect(null);
        else if (type==1)
            musicButton.setEffect(null);
        else
            stopButton.setEffect(null);


        System.out.println("highlight off");
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
