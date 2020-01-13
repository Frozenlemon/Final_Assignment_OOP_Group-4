package controller;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;
import util.FileIO;

import java.io.File;


public class SoundController {

    public static SoundController instance;

    private boolean status;
    private MediaPlayer backgroundPlayer, releaseHorsePlayer, horseMovePlayer, rollDicePlayer,horseKickPlayer;


    private SoundController(){
        status = false;
        backgroundPlayer = getMediaPlayer("backgroundMusic");

        releaseHorsePlayer =  getMediaPlayer("doorOpen");
        releaseHorsePlayer.setStartTime(Duration.seconds(0));
        releaseHorsePlayer.setStopTime(Duration.seconds(1));

        horseMovePlayer = getMediaPlayer("horseMove");
        horseMovePlayer.setStartTime(Duration.seconds(0));
        horseMovePlayer.setStopTime(Duration.seconds(1));

        rollDicePlayer = getMediaPlayer("rollingDice");
        rollDicePlayer.setStartTime(Duration.seconds(0));
        rollDicePlayer.setStopTime(Duration.seconds(1));

        horseKickPlayer = getMediaPlayer("horseKick");
        horseKickPlayer.setStartTime(Duration.seconds(0));
        horseKickPlayer.setStopTime(Duration.seconds(1));
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
        horseKickPlayer.setVolume(effect);
    }
    public void playMusic(){
        backgroundPlayer.play();
    }
}