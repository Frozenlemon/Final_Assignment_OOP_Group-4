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
