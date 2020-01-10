package controller;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;
import util.FileIO;

import java.io.File;


public class SoundController {

    public static final int STATUS_PLAY = 1;
    public static final int STATUS_STOP = 2;
    public static final String SOUND_ON = "Sound on";
    public static final String SOUND_OFF = "Sound off";
    public static SoundController intstance;

    private boolean status;
    private MediaPlayer backgroundPlayer, releaseHorse, horseMove, rollDice;


    private SoundController(){
        status = false;

        String path = new File("src\\fxml\\Sound\\doorOpen.mp3").getAbsolutePath();
        Media media = new Media(FileIO.getMusicFile("insert file name here"));

        backgroundPlayer = new MediaPlayer(media);
    }

    public String changeStatus(){
        if(status){
            // Status on
            stopSound();
        } else {
            playSound();
        }

        status = !status;
        if (status){
            // Status off
            return SOUND_OFF;
        } else {
            return SOUND_ON;
        }
    }

    public void playSound(){
        backgroundPlayer.play();
    }

    public void stopSound(){
        backgroundPlayer.stop();
    }

    public void restartSound(){
        backgroundPlayer.seek(new Duration(0));
        backgroundPlayer.play();
    }
}