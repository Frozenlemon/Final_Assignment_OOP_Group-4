package view;

import javafx.beans.NamedArg;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import util.FileIO;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class Menu {

    @FXML
    private StackPane menuArea;
    private Button rollDice, quit, music;

    public Menu(@NamedArg("Menu.fxml") String fileName) throws MalformedURLException {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(FileIO.getFXML_URL(fileName));
            menuArea = loader.load();
        } catch (IOException e){
            System.out.println("Menu import error: " + e.getMessage());
        }

        initButton();
    }

    public Button getRollDice() {
        return rollDice;
    }

    public Button getQuit() {
        return quit;
    }

    public Button getMusic() {
        return music;
    }

    public StackPane getMenuArea() {
        return menuArea;
    }

    public void effect(String buttonName){

    }

    @FXML
    private void test(Event e){
        //call Controller
    }



    private void initButton(){
        music =  (Button) menuArea.getChildren().get(1);
        rollDice = (Button) menuArea.getChildren().get(2);
        quit = (Button) menuArea.getChildren().get(3);
    }
}
