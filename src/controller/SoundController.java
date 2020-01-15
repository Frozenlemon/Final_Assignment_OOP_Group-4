/*
  RMIT University Vietnam
  Course: INTE2512 Object-Oriented Programming
  Semester: 2019C
  Assessment: Final Project
  Created date: 23/03/2019
  By: Le Minh s3757324
      Ho Duy Hoang s3672214
      Pham Thanh Dat s3678437
      Ta Quoc Thang s3713564
      Le Nguyen Thien Phu s3639855
  Last modified: 15/01/2020
  By: Le Minh s3757324
      Ho Duy Hoang s3672214
      Pham Thanh Dat s3678437
      Ta Quoc Thang s3713564
      Le Nguyen Thien Phu s3639855
  Acknowledgement:
[1] "Java Internationalization - javatpoint", www.javatpoint.com, 2020. [Online]. Available: https://www.javatpoint.com/internationalization-in-java. [Accessed: 14- Jan- 2020]
[2]"Internationalizing the Sample Program (The Java™ Tutorials > Internationalization > Introduction)", Docs.oracle.com, 2020. [Online]. Available: https://docs.oracle.com/javase/tutorial/i18n/intro/steps.html?fbclid=IwAR00AiifBkc8cmbF43D0YEUM6ZYayCq_ldGYnVxZBzE0xlL_-2n-5ElxSeE. [Accessed: 14- Jan- 2020]
[3]Tutorials.jenkov.com, 2020. [Online]. Available: http://tutorials.jenkov.com/java-internationalization/resourcebundle.html?fbclid=IwAR2FqduxMXmdSdnltj4q7BIrOCbadtPCiSEWwg2da_mvY71n-rPZHuQEG58. [Accessed: 14- Jan- 2020]
[4]"Code Conventions for the Java Programming Language: 9. Naming Conventions", Oracle.com, 2020. [Online]. Available: https://www.oracle.com/technetwork/java/codeconventions-135099.html?fbclid=IwAR2ZlGNIfZqOB3fUjnW0A5dKgyPZFeaq8XKSgGbbfc6qBrw3dnmC-skDNdo. [Accessed: 14- Jan- 2020]
[5]"JavaFX Playing Audio - javatpoint", www.javatpoint.com, 2020. [Online]. Available: https://www.javatpoint.com/javafx-playing-audio?fbclid=IwAR3WFDkfH5LvjWqk6OCoY55aLShLcb4VWCM7IFAyA-JkZBAYRlSpz0U-hFc. [Accessed: 14- Jan- 2020]
[6]"CSS Examples", W3schools.com, 2020. [Online]. Available: https://www.w3schools.com/css/css_examples.asp. [Accessed: 14- Jan- 2020]
*/
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