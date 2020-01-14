package view;

import controller.SoundController;
import controller.ViewController;
import util.Language;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.net.URL;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import javafx.scene.media.MediaPlayer;
import util.Language;


public class Setting {

    @FXML
    private ToggleGroup group;

    @FXML
    private StackPane settingPane;

    @FXML
    private VBox vBoxMain;

    @FXML
    private Button saveButton;
    private Slider masterSlider;

    @FXML
    private Slider effectSlider;

    @FXML
    private Slider bgmSlider;

    @FXML
    private CheckBox enCheckBox;
    @FXML
    private CheckBox vnCheckBox;

    private Locale localeCurrent;
    private SoundController backgroundPlayer;

    int numSwitches, numSwitchesCurrent = 0; //0: English, 1 VietNamese

    public Setting() {
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
    public void clickOnCheckBox(MouseEvent evt) {
        CheckBox button = (CheckBox) evt.getSource();
        if (button.getId().equals("en"))
            if (!button.isSelected()) {
                button.setSelected(true);
                ViewController.getInstance().switchLanguage("en");
                if (vnCheckBox.isSelected())
                    vnCheckBox.setSelected(false);
            }
        if (button.getId().equals("vn"))
            if(!button.isSelected()){
                button.setSelected(true);
                ViewController.getInstance().switchLanguage("vn");
                if (enCheckBox.isSelected())
                    enCheckBox.setSelected(false);
    }

    private void setVolume(){
        masterSlider.setValue(100);
        masterSlider.valueProperty().addListener(new InvalidationListener() {
            @Override
            public void invalidated(Observable observable) {
                backgroundPlayer.setVolume(masterSlider.getValue() / 100);
            }
        });
    }

    }

    /*private void cancelSetting() {
        Language.setLocale(localeCurrent);
        Language.setNumSwitches(numSwitchesCurrent);
        numSwitches = numSwitchesCurrent;
    }*/

}
