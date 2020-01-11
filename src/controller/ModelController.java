package controller;

import model.Dice;
import model.Horse;
import model.Human;
import model.Player;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

public class ModelController {
    private static final int NO_OF_PLAYER = 4;
    private final List<PropertyChangeListener> LISTENERS = new ArrayList<>();
    private static ModelController instance;

    private Player[] players;
    private int playerTurn;
    private Dice[] dices;
    private Horse focusedHorse;

    private ModelController(Human... humans){
        initVariable(humans);
        initListener();
        nextPlayer();
    }

    public static ModelController getInstance(){
        if (instance == null)
            instance = new ModelController();
        return instance;
    }

    private void initVariable(Human... humans){
        players = new Player[NO_OF_PLAYER];
        for (Human human : humans){
            players[human.getColorCode()] = human;
        }
        for (int i = 0; i < NO_OF_PLAYER; i++){
            if (players[i] == null)
                players[i] = new Player(i);
        }
        playerTurn = -1;
        dices = new Dice[2];
        focusedHorse = null;
    }


    public void updateHorses(Horse horse, Horse kickedHorse){
        if (kickedHorse != null){
            ViewController.getInstance().horseMoveAndKick(horse, kickedHorse);
        }
        else{
            ViewController.getInstance().horseMove(horse);
        }
    }

    public void selectHorse(int horseIndex){
        int[] idCode = horseIndexToIdConverter(horseIndex);
        Player player = players[playerTurn];
        if (idCode[0] == player.getColorCode()){
            focusedHorse = player.getHorse(idCode[1]);
        }
    }

    public void moveHorse(int diceId){
        if (focusedHorse != null){
            int moveCount =  dices[diceId].getValue();
            int moveStatus = focusedHorse.checkMove(moveCount);
            focusedHorse.move(focusedHorse.checkMove(moveStatus), moveCount);
        }
    }

    public void rollDice(){
        for (int i = 0; i < dices.length; i++){
            dices[i].roll();
        }
        ViewController.getInstance().updateDice(dices[0].getValue(), dices[1].getValue());
    }

    private int[] horseIndexToIdConverter(int horseIndex){
        int[] idCode = new int[2];
        idCode[0] = horseIndex / 4;
        idCode[1] = horseIndex % 4;
        return idCode;
    }

    private void nextPlayer(){
        int oldValue = playerTurn;
        playerTurn++;
        if (playerTurn > 3)
            playerTurn = 0;
        setPropertiesChange("playerTurn", oldValue, playerTurn);
    }

    private void addPropertiesChangeListener(PropertyChangeListener listener){
        LISTENERS.add(listener);
    }

    private void setPropertiesChange(String property, Object oldValue, Object newValue){
        for (PropertyChangeListener listener : LISTENERS){
            listener.propertyChange(new PropertyChangeEvent(this, property, oldValue, newValue));
        }
    }

    private void initListener(){
        this.addPropertiesChangeListener(evt -> {
            int playerIndex = (int) evt.getNewValue();
            Player player = players[playerIndex];
            if (!player.isHuman())
                player.autoMove();
        });
    }
}
