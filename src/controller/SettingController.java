package controller;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import util.Language;

import java.net.URL;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 *
 * @author Phu Le
 */
public class SettingController implements Initializable {

    @FXML
    private Label lbChooseLanguage;

    @FXML
    private ToggleGroup group;

    @FXML
    private Pane paneMain;

    @FXML
    private VBox vboxMain;
    
    private Locale localeCurrent;

    int numSwitches, numSwitchesCurrent = 0; //0: English, 1 VietNamese

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setLanguageText(vboxMain.getChildrenUnmodifiable());
        setSelectedEvent();
    }

    private void setLanguageText(List<Node> listNode) {
        for (Node node : listNode) {
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
                setLanguageText(((Parent)node).getChildrenUnmodifiable());
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
                            setLanguageText(vboxMain.getChildrenUnmodifiable());
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
        numSwitches = numSwitchesCurrent;
    }
}
