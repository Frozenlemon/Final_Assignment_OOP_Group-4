package controller;

import javafx.animation.Timeline;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;
import util.FileIO;




public class SoundController {

    public static SoundController instance;
    public static final int GENERAL_DURATION = 500;
    public static final int SPECIAL_DURATION = 1000;

    private boolean status;
    private MediaPlayer backgroundPlayer, releaseHorsePlayer, horseMovePlayer, rollDicePlayer,horseKickPlayer;


    private SoundController(){
        status = false;
        backgroundPlayer = getMediaPlayer("backgroundMusic");
        backgroundPlayer.setCycleCount(Timeline.INDEFINITE);

        releaseHorsePlayer =  getMediaPlayer("doorOpen");
        releaseHorsePlayer.setStartTime(Duration.millis(0));
        releaseHorsePlayer.setStopTime(Duration.millis(GENERAL_DURATION));

        horseMovePlayer = getMediaPlayer("horseMove");
        horseMovePlayer.setStartTime(Duration.millis(0));
        horseMovePlayer.setStopTime(Duration.millis(GENERAL_DURATION));

        rollDicePlayer = getMediaPlayer("rollingDice");
        rollDicePlayer.setStartTime(Duration.millis(0));
        rollDicePlayer.setStopTime(Duration.millis(SPECIAL_DURATION));

        horseKickPlayer = getMediaPlayer("horseKick");
        horseKickPlayer.setStartTime(Duration.millis(0));
        horseKickPlayer.setStopTime(Duration.millis(GENERAL_DURATION));
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
        setMusic(master, music);
        setEffect(master, effect);
    }

    public void setMusic(double master, double music){
        backgroundPlayer.setVolume(master/100 * music / 100);
    }

    public void setEffect(double master, double effect){
        releaseHorsePlayer.setVolume(master / 100 * effect / 100);
        rollDicePlayer.setVolume(master / 100 * effect / 100);
        horseMovePlayer.setVolume(master / 100 * effect / 100);
        horseKickPlayer.setVolume(master / 100 * effect / 100);
    }


    public void playMusic(){
        backgroundPlayer.play();
    }

    public void playHorseMove(){
        horseMovePlayer.stop();
        resetPlayer(horseMovePlayer, GENERAL_DURATION);
        horseMovePlayer.play();
    }

    public void playHorseKick(){
        horseKickPlayer.stop();
        resetPlayer(horseKickPlayer, GENERAL_DURATION);
        horseKickPlayer.play();
    }

    public void playHorseRelease(){
        releaseHorsePlayer.stop();
        resetPlayer(releaseHorsePlayer, GENERAL_DURATION);
        releaseHorsePlayer.play();
    }

    public void playRollDice(){
        rollDicePlayer.stop();
        resetPlayer(rollDicePlayer, SPECIAL_DURATION);
        rollDicePlayer.play();
    }

    private void resetPlayer(MediaPlayer player, int duration){
        player.seek(Duration.ZERO);
    }
}