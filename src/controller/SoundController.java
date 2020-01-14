package controller;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;
import util.FileIO;




public class SoundController {

    public static SoundController instance;

    private boolean status;
    private MediaPlayer backgroundPlayer, releaseHorsePlayer, horseMovePlayer, rollDicePlayer,horseKickPlayer;


    private SoundController(){
        status = false;
        backgroundPlayer = getMediaPlayer("backgroundMusic");

        releaseHorsePlayer =  getMediaPlayer("doorOpen");
        releaseHorsePlayer.setStartTime(Duration.millis(0));
        releaseHorsePlayer.setStopTime(Duration.millis(1));

        horseMovePlayer = getMediaPlayer("horseMove");
        horseMovePlayer.setStartTime(Duration.millis(0));
        horseMovePlayer.setStopTime(Duration.millis(1));

        rollDicePlayer = getMediaPlayer("rollingDice");
        rollDicePlayer.setStartTime(Duration.millis(0));
        rollDicePlayer.setStopTime(Duration.millis(1));

        horseKickPlayer = getMediaPlayer("horseKick");
        horseKickPlayer.setStartTime(Duration.millis(0));
        horseKickPlayer.setStopTime(Duration.millis(1));
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
    public void setVolume(double master, double music, double effect){
        backgroundPlayer.setVolume(master/100 * music);
        releaseHorsePlayer.setVolume(master / 100 * effect);
        horseMovePlayer.setVolume(master / 100 * effect);
        rollDicePlayer.setVolume(master / 100 * effect);
        horseMovePlayer.setVolume(master / 100 * effect);
        horseKickPlayer.setVolume(master / 100 * effect);
    }
    public void setMusic(double master, double music){
        backgroundPlayer.setVolume(master/100 * music);
    }
    public void setEffect(double master, double effect){
        releaseHorsePlayer.setVolume(master / 100 * effect);
        horseMovePlayer.setVolume(master / 100 * effect);
        rollDicePlayer.setVolume(master / 100 * effect);
        horseMovePlayer.setVolume(master / 100 * effect);
        horseKickPlayer.setVolume(master / 100 * effect);
    }


    public void playMusic(){
        backgroundPlayer.play();
    }
}