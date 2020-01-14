package view;

import controller.SoundController;
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

import java.net.URL;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import javafx.scene.media.MediaPlayer;
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

    @FXML
    private Slider masterSlider;

    @FXML
    private Slider effectSlider;

    @FXML
    private Slider bgmSlider;

    private Locale localeCurrent;
    private SoundController backgroundPlayer;

    int numSwitches, numSwitchesCurrent = 0; //0: English, 1 VietNamese

    public Setting() {
    }

    public void init() {
        setLanguageText(vBoxMain.getChildrenUnmodifiable());
        setSelectedEvent();
    }

    public void setVisible(boolean status) {
        settingPane.setVisible(status);
    }

    private void setLanguageText(List<Node> listNode) {
        for (Node node : listNode) {
            try {
                if (node instanceof Label) {
                    Label label = (Label) node;
                    label.textProperty().bind(Language.createStringBinding(label.getId(), numSwitches));
                    continue;
                }
                if (node instanceof Button) {
                    Button button = (Button) node;
                    button.textProperty().bind(Language.createStringBinding(button.getId(), numSwitches));
                    continue;
                }

                if (node instanceof Parent) {
                    setLanguageText(((Parent) node).getChildrenUnmodifiable());
                }
            } catch (Exception ex) {
            }
        }
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
                            setLanguageText(vBoxMain.getChildrenUnmodifiable());
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

    private void setVolumn(){
        masterSlider.setValue(100);
        masterSlider.valueProperty().addListener(new InvalidationListener() {
            @Override
            public void invalidated(Observable observable) {
                backgroundPlayer.setVolume(masterSlider.getValue() / 100);
            }
        });
    }



    @FXML
    private void saveSetting(URL location, ResourceBundle resourceBundle) {

    }

    @FXML
    private void cancelSetting() {
        Language.setLocale(localeCurrent);
        numSwitches = numSwitchesCurrent;
    }
}
