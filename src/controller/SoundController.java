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
    public static SoundController instance;

    private boolean status;
    private MediaPlayer backgroundPlayer, releaseHorsePlayer, horseMovePlayer, rollDicePlayer,horseKickPlayer;


    private SoundController(){
        status = false;
        backgroundPlayer = getMediaPlayer("backgroundMusic");
        releaseHorsePlayer =  getMediaPlayer("doorOpen");
        horseMovePlayer = getMediaPlayer("horseMove");
        rollDicePlayer = getMediaPlayer("rollingDice");
        horseKickPlayer = getMediaPlayer("horseKick");
    }

    private MediaPlayer getMediaPlayer(String fileName){
        Media media = new Media(FileIO.getMusicFile(fileName));
        return new MediaPlayer(media);
    }

    public static SoundController getInstance(){
        if (instance == null)
            instance = new SoundController();
        return instance;
    }



    public void setVolume(double music, double effect){
        backgroundPlayer.setVolume(music);
        releaseHorsePlayer.setVolume(effect);
        horseMovePlayer.setVolume(effect);
        rollDicePlayer.setVolume(effect);
        horseMovePlayer.setVolume(effect);

    }

    public void playMusic(){
        backgroundPlayer.play();
    }
}