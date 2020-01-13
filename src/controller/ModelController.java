package controller;

import model.Dice;
import model.Horse;
import model.Human;
import model.Player;
import util.Converter;

public class ModelController {
    private static final int NO_OF_PLAYER = 4;
    private static ModelController instance;

    private Player[] players;
    private int playerTurn;
    private Dice[] dices;
    private Horse focusedHorse;
    private boolean hasRolled;

    private ModelController(){
        players = new Player[]{new Human(0, "test"), null, null, null};
        playerTurn = 0;
        dices = new Dice[]{new Dice(), new Dice()};
        focusedHorse = null;
        hasRolled = false;
    }

    public static ModelController getInstance(){
        if (instance == null)
            instance = new ModelController();
        return instance;
    }

    public void initVariable(Human... humans){
        for (Human human : humans){
            players[human.getColorCode()] = human;
        }
        for (int i = 0; i < NO_OF_PLAYER; i++){
            if (players[i] == null)
                players[i] = new Player(i);
        }
    }


    public void updateHorses(Horse horse, Horse kickedHorse){
        if (kickedHorse != null){
            ViewController.getInstance().horseMoveAndKick(horse, kickedHorse);
        }
        else{
            ViewController.getInstance().horseMove(horse);
        }
    }

    public int getAnimationCount(){
        return ViewController.getInstance().getInAnimation();
    }

    public boolean selectHorse(int horseIndex){
        int[] idCode = Converter.getColorCodeFromId(horseIndex);
        Player player = players[playerTurn];
        if (idCode[0] == player.getColorCode()){
            focusedHorse = player.getHorse(idCode[1]);
            return true;
        }
        return false;
    }

    public void deselectHorse(){
        if (focusedHorse != null)
            ViewController.getInstance().setHorse_Highlight(focusedHorse.getId());
        focusedHorse = null;
    }

    public void moveHorse(int diceId){
        if (focusedHorse != null){
            int moveCount =  dices[diceId].getValue();
            int status = focusedHorse.checkMove(moveCount);
            if (status != 0)
                focusedHorse.move( status, moveCount);
        }
    }

    public Dice[] getAllDice(){
        return dices;
    }

    public void rollDice(){
        if (!hasRolled) {
            for (int i = 0; i < dices.length; i++) {
                dices[i].roll();
            }
            ViewController.getInstance().updateDice(dices[0].getValue(), dices[1].getValue());
            hasRolled = true;
            if (!canMove())
                nextPlayer();
        }
    }

    private boolean canMove(){
        for (int i = 0; i < 2; i++){
            Dice dice = dices[i];
            if (!dice.isUsed()){
                for (int j = 0; j < 4; j++){
                    if (players[playerTurn].getHorse(j).checkMove(dice.getValue()) != 0)
                        return true;
                }
            }
        }
        return false;
    }

    public void nextPlayer(){
        resetDices();
        playerTurn++;
        deselectHorse();
        if (playerTurn > 3)
            playerTurn = 0;
        if (!players[playerTurn].isHuman()){}
            //players[playerTurn].autoMove();
    }

    private void resetDices(){
        for (Dice dice : dices)
            dice.setUsed(false);
        hasRolled = false;
    }
}
