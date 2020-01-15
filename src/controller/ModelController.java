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
        players = new Player[NO_OF_PLAYER];
        playerTurn = -1;
        dices = new Dice[]{new Dice(), new Dice()};
        focusedHorse = null;
        hasRolled = false;
    }

    public static ModelController getInstance(){
        if (instance == null)
            instance = new ModelController();
        return instance;
    }

    public void reset(){
        playerTurn = -1;
        players = new Player[NO_OF_PLAYER];
        dices = new Dice[]{new Dice(), new Dice()};
        focusedHorse = null;
        hasRolled = false;
    }

    public void initVariable(String[] humans){
        for (int i = 0; i < NO_OF_PLAYER; i++){
            if (humans[i] != null)
                players[i] = new Human(i, humans[i]);
            else
                players[i] = new Player(i);
        }
        nextPlayer();
    }

    public void requestFilter(String request, int input){
        switch (request){
            case "rollDice":
                rollDice();
                break;
            case "moveHorse":
                moveHorse(input);
                break;
            default:
                break;
        }
    }

    public void updateHorses(Horse horse, Horse kickedHorse){
        if (kickedHorse != null){
            updateScore(playerTurn, 2);
            updateScore(kickedHorse.getColorCode(), -2);
            ViewController.getInstance().horseMoveAndKick(horse, kickedHorse);
        }
        else{
            if (horse.getCurrentAreaCode() == horse.getColorCode() && horse.getLocationOnPath() == 1)
                ViewController.getInstance().horseMove(horse, true);
            else
                ViewController.getInstance().horseMove(horse, false);
        }
    }

    public void upgradeHorse(Horse horse){
        updateScore(playerTurn, 0);
        ViewController.getInstance().horseMoveToHome(horse.getId(), horse.getHomeOnPath());
    }


    public boolean selectHorse(int horseIndex){
        int[] idCode = Converter.getColorCodeFromId(horseIndex);
        deselectHorse();
        Player player = players[playerTurn];
        if (idCode[0] == player.getColorCode()) {
            focusedHorse = player.getHorse(idCode[1]);
            return true;
        }
        return false;
    }

    private void deselectHorse(){
        if (focusedHorse != null)
            ViewController.getInstance().setHorse_Highlight(focusedHorse.getId());
        focusedHorse = null;
    }

    public void moveHorse(int diceId){
        if (focusedHorse != null) {
            int moveCount = dices[diceId].getValue();
            if (focusedHorse.canUpgrade(moveCount)) {
                focusedHorse.upgradeHorse(moveCount);
            }

            else if (!dices[diceId].isUsed()){
                int status = focusedHorse.checkMove(moveCount);
                if (status != 0) {
                    focusedHorse.move(status, moveCount);
                    dices[diceId].setUsed(true);
                }
            }
        }
    }

    public Dice[] getAllDice(){
        return dices;
    }

    public void updateScore(int playerIndex, int value){
        if (players[playerIndex] instanceof Human){
            Human player = (Human) players[playerIndex];
            player.addToAccumulativeScore(value);
            ViewController.getInstance().upDateScore(playerIndex, player.getScore());
        }
    }

    public void rollDice(){
        if (!hasRolled) {
            for (int i = 0; i < dices.length; i++) {
                dices[i].roll();
            }
            ViewController.getInstance().updateDice(dices[0].getValue(), dices[1].getValue());
            setDiceStatus(false);
            hasRolled = true;
        }
    }

    private boolean endGame(){
        for (int i = 0; i < 4; i++){
            if (players[playerTurn].getHorse(i).getHomeOnPath() < 0)
                return false;
        }
        return true;
    }

    public void endTurn(){
        if (endGame()) {
            ViewController.getInstance().setIsEndGame(true);
        }
        else if (!canMove())
            nextPlayer();
        else{
            if (!isPlayer()){
                if (dices[0].isUsed() && dices[1].isUsed() && !hasRolled)
                    rollDice();
                else
                    players[playerTurn].autoMove();
            }
        }
    }

    private boolean canMove(){
        for (int i = 0; i < 2; i++){
            Dice dice = dices[i];
            if (!dice.isUsed()){
                for (int j = 0; j < 4; j++){
                    if (players[playerTurn].getHorse(j).checkMove(dice.getValue()) != 0 || players[playerTurn].getHorse(j).canUpgrade(dice.getValue()))
                        return true;
                }
            }
        }
        if (dices[0].getValue() == dices[1].getValue()){
            if (dices[0].isUsed() && dices[1].isUsed()){
                hasRolled = false;
                return true;
            }
        }
        return false;
    }


    public void nextPlayer(){
        playerTurn++;
        hasRolled = false;
        setDiceStatus(true);
        deselectHorse();
        if (playerTurn > 3)
            playerTurn = 0;
        ViewController.getInstance().showPlayerTurn(playerTurn);
        if (!isPlayer())
            rollDice();
    }

    private void setDiceStatus(boolean status){
        for (int i =0; i < dices.length; i++)
            dices[i].setUsed(status);
    }


    public boolean isPlayer(){
        if (players[playerTurn] instanceof Human)
            return true;
        return false;
    }
}
