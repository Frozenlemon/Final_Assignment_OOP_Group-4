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
