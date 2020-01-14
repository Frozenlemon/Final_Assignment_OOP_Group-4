package view;

import controller.SoundController;
import controller.ViewController;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.StackPane;
import java.awt.event.MouseEvent;
import java.util.Locale;



public class Setting {

    @FXML
    private StackPane settingPane;


    @FXML
    private Button btnBack, btnHome;

    @FXML
    private Slider effectSlider, masterSlider, bgmSlider;

    @FXML
    private CheckBox enCheckBox, vnCheckBox;

    @FXML
    private Label chooseLanguageLabel, volumeControlLabel, msVolume, efVolume, bgmVolume;

    private Locale localeCurrent;
    private SoundController backgroundPlayer;

    //int numSwitches, numSwitchesCurrent = 0; //0: English, 1 VietNamese

    public Setting() {
    }

    public StackPane getSettingPane(){
        return settingPane;
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

    @FXML
    public void clickOnCheckBox(MouseEvent evt) {
        CheckBox button = (CheckBox) evt.getSource();
        if (button.getId().equals("en")){
            if (!button.isSelected()) {
                button.setSelected(true);
                ViewController.getInstance().switchLanguage("en");
                if (vnCheckBox.isSelected())
                    vnCheckBox.setSelected(false);
            }
        }
        if (button.getId().equals("vn"))
            if(!button.isSelected()){
                button.setSelected(true);
                ViewController.getInstance().switchLanguage("vn");
                if (enCheckBox.isSelected())
                    enCheckBox.setSelected(false);
            }
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

    public void setDisplay(boolean status){
        settingPane.setVisible(status);
    }

    public void setSettingSwitchLanguage(String...inputs){
        btnBack.setText(inputs[0]);
        btnHome.setText(inputs[1]);
        enCheckBox.setText(inputs[3]);
        vnCheckBox.setText(inputs[4]);
        chooseLanguageLabel.setText(inputs[5]);
        volumeControlLabel.setText(inputs[6]);
        msVolume.setText(inputs[7]);
        efVolume.setText(inputs[8]);
        bgmVolume.setText(inputs[9]);
    }
}
