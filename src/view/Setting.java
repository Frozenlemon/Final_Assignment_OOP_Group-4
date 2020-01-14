package view;

import controller.SoundController;
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

import java.net.URL;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import util.Language;

/**
 *
 * @author Phu Le
 */
public class Setting {

    @FXML
    private ToggleGroup group;

    @FXML
    private StackPane settingPane;

    @FXML
    private VBox vBoxMain;

    private Locale localeCurrent;

    int numSwitches, numSwitchesCurrent = 0; //0: English, 1 VietNamese

    public Setting() {
    }

    public void init() {
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
    }

    private void switchLanguage(Locale locale, int language) {
        localeCurrent = Language.getLocale();
        numSwitchesCurrent = numSwitches;
        numSwitches = language;
        Language.setLocale(locale);
    }

    @FXML
    private void saveSetting() {
    }

    @FXML
    private void cancelSetting() {
        Language.setLocale(localeCurrent);
        Language.setNumSwitches(numSwitchesCurrent);
        numSwitches = numSwitchesCurrent;
    }
}
