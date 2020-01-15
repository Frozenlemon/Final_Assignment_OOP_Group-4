package view;

import controller.SoundController;
import controller.ViewController;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import java.util.Locale;



public class Setting {

    @FXML
    private StackPane settingPane;

    @FXML
    private Button btnBack, btnHome;

    @FXML
    private Slider effectSlider, masterSlider, bgmSlider;

    @FXML
    private RadioButton enCheckBox, vnCheckBox;

    @FXML
    private Label chooseLanguageLabel, volumeControlLabel, msVolume, efVolume, bgmVolume;

    private Locale localeCurrent;
    private SoundController backgroundPlayer;

    public Setting() {
    }

    public StackPane getSettingPane(){
        return settingPane;
    }

    public void init(){
        enCheckBox.setOnMouseClicked(e-> clickOnCheckBox(enCheckBox, vnCheckBox));
        vnCheckBox.setOnMouseClicked(e -> clickOnCheckBox(vnCheckBox, enCheckBox));
    }

    /*public void init() {
        Language.setLanguageText(vBoxMain.getChildrenUnmodifiable());
        setSelectedEvent();
    }

    public void setVisible(boolean status) {
        settingPane.setVisible(status);
    }

    public void setSelectedEvent() {
        group.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            public void changed(ObservableValue<? extends Toggle> ov, Toggle old_toggle,
                    Toggle new_toggle) {

                if (group.getSelectedToggle() != null) {
                    RadioButton chk = (RadioButton) group.getSelectedToggle();
                    switch (chk.getId()) {
                        case "rdoEnglish":
                            switchLanguage(Locale.ENGLISH, 0);
                            break;
                        case "rdoVietnam":
                            switchLanguage(Locale.forLanguageTag("vi-VN"), 1);
                            Language.setLanguageText(vBoxMain.getChildrenUnmodifiable());
                            break;
                        default:
                            break;
                    }
                }
            }
        });
    }*/

    @FXML
    private void clickOnSlider(MouseEvent evt){
        SoundController.getInstance().setVolume(masterSlider.getValue(), bgmSlider.getValue(), effectSlider.getValue());
    }

    @FXML
    private void clickHome(){
        ViewController.getInstance().clickHome();
    }

    @FXML
    private void clickBack(){
        ViewController.getInstance().turnOffSetting();
    }


    public void clickOnCheckBox(RadioButton checkBoxA, RadioButton checkBoxB) {
        checkBoxA.setSelected(true);
        checkBoxB.setSelected(false);
        ViewController.getInstance().switchLanguage(checkBoxA.getId());
    }

//    private void setVolume(){
//        masterSlider.setValue(100);
//        masterSlider.valueProperty().addListener(new InvalidationListener() {
//            @Override
//            public void invalidated(Observable observable) {
//                backgroundPlayer.setVolume(masterSlider.getValue() / 100);
//            }
//        });
//    }



    /*private void cancelSetting() {
        Language.setLocale(localeCurrent);
        Language.setNumSwitches(numSwitchesCurrent);
        numSwitches = numSwitchesCurrent;
    }*/


    public void setSettingSwitchLanguage(String...inputs){
        btnBack.setText(inputs[0]);
        btnHome.setText(inputs[1]);
        enCheckBox.setText(inputs[2]);
        vnCheckBox.setText(inputs[3]);
        chooseLanguageLabel.setText(inputs[4]);
        volumeControlLabel.setText(inputs[5]);
        msVolume.setText(inputs[6]);
        efVolume.setText(inputs[7]);
        bgmVolume.setText(inputs[8]);
    }
}
