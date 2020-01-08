package view;

import com.sun.scenario.effect.DropShadow;
import controller.ModelController;
import controller.ModelController;
import javafx.application.Platform;
import javafx.beans.NamedArg;
import javafx.fxml.FXML;
import javafx.scene.control.ToggleButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;

import javax.swing.text.html.ImageView;
import java.awt.*;

public class StartMenu {

    @FXML
    private StackPane startMenuSceen;
    @FXML
    private Button startGameButton;
    @FXML
    private Button exitGameButton;
    @FXML
    private ToggleButton musicButton;
    @FXML
    private ImageView backGround;

    public StartMenu() {}

    public StackPane getStartMenuSceen(){
        return startMenuSceen;
    }
    public Button getStartGameButton() {
        return startGameButton; }

    public Button getExitGameButton() {
        return exitGameButton;
    }

    public ToggleButton getMusicButton(){
        return musicButton;
    }

    public ImageView getBackGround(){
        return backGround;
    }

    public void highlightButton_On(@NamedArg("Button type: 0: startGameMenu, 1: exitGameMenu, 2: musicButton") int type) {
        /*
         * function to turn on highlight for a button. Button selected by the type provided in argument
         */
        if (type ==0 || type==1 || type ==2){
            DropShadow shadow = new DropShadow();
            exitGameButton.setEffect(new javafx.scene.effect.DropShadow());

            System.out.println("highlight on");
        }}

    public void highlightButton_Off(@NamedArg("Button type: 0: rollDice, 1: musicButton, 2: stopButton") int type) {
        /*
         * function to turn off highlight for a button. Button selected by the type provided in argument
         */
        if (type ==0 || type==1 || type ==2){
            getExitGameButton().setEffect(null);


            System.out.println("highlight off");
        }
    }

    @FXML
    private void mouseHover_On(MouseEvent event){
        if (event.getSource() instanceof javafx.scene.control.Button){
            javafx.scene.control.Button source = (javafx.scene.control.Button) event.getSource();
            ModelController.getInstance().highlight_On(source.getId());
        }
    }

    @FXML
    private void mouseHover_Off(MouseEvent event){
        if (event.getSource() instanceof javafx.scene.control.Button){
            javafx.scene.control.Button source = (javafx.scene.control.Button) event.getSource();
            ModelController.getInstance().highlight_Off(source.getId());
        }
    }

    @FXML
    private void exitPlatform(){
        Platform.exit();
        System.exit(0);
    }

}
