package view;

import controller.ViewController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;

public class StartMenu {

    @FXML
    private StackPane startMenuScene;
    @FXML
    private Button startGameButton, settingButton, exitGameButton;

    public StartMenu(){}

    /*public StartMenu() {
        Language.setLanguageText(startMenuSceen.getChildrenUnmodifiable());
    }*/

//    public StackPane getStartMenuScene(){
//        return startMenuScene;
//    }
//    public Button getStartGameButton() {
//        return startGameButton; }
//
//    public Button getExitGameButton() {
//        return exitGameButton;
//    }

//    public void highlightButton_On(@NamedArg("Button type: 0: startGameMenu, 1: exitGameMenu, 2: musicButton") int type) {
//        /*
//         * function to turn on highlight for a button. Button selected by the type provided in argument
//         */
//        if (type ==0 || type==1 || type ==2){
//            DropShadow shadow = new DropShadow();
////            exitGameButton.setEffect(new javafx.scene.effect.DropShadow());
//
//
//        }}
//
//    public void highlightButton_Off(@NamedArg("Button type: 0: rollDice, 1: musicButton, 2: stopButton") int type) {
//        /*
//         * function to turn off highlight for a button. Button selected by the type provided in argument
//         */
//        if (type ==0 || type==1 || type ==2){
//            getExitGameButton().setEffect(null);
//        }
//    }
//
//    @FXML
//    private void mouseHover_On(MouseEvent event){
//        if (event.getSource() instanceof javafx.scene.control.Button){
//            javafx.scene.control.Button source = (javafx.scene.control.Button) event.getSource();
////            ModelController.getInstance().highlight_On(source.getId());
//        }
//    }
//
//    @FXML
//    private void mouseHover_Off(MouseEvent event){
//        if (event.getSource() instanceof javafx.scene.control.Button){
//            javafx.scene.control.Button source = (javafx.scene.control.Button) event.getSource();
////            ModelController.getInstance().highlight_Off(source.getId());
//        }
//    }

    public StackPane getStartMenu(){
        return this.startMenuScene;
    }

    @FXML
    private void clickExit(){
        ViewController.getInstance().clickExit();
    }

    @FXML
    private void clickStartGame(){
        ViewController.getInstance().clickStart();
    }

    @FXML void clickSetting(){
        ViewController.getInstance().clickSetting();
    }

    public void setDisplay(boolean status){
        startMenuScene.setVisible(status);
    }


    public void setStartMenuSwitchLanguage(String...inputs){
        startGameButton.setText(inputs[0]);
        settingButton.setText(inputs[1]);
        exitGameButton.setText(inputs[2]);
    }

}
