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
        initVariable();
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
            ViewController.getInstance().horseMoveAndKick(horse, kickedHorse);
        }
        else{
            ViewController.getInstance().horseMove(horse);
        }
    }

    public void upgradeHorse(Horse horse){
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

                System.out.println("CAN UPGRADE");
                focusedHorse.upgradeHorse(moveCount);
            }
            else if (!dices[diceId].isUsed()){
                System.out.println("CAN MOVE");
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
        if (endGame() || ViewController.getInstance().isEndGame()) {
            ViewController.getInstance().setIsEndGame(true);
        }
        else if (!canMove())
            nextPlayer();
        else{
            if (!isPlayer())
                players[playerTurn].autoMove();
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
        return false;
    }


    public void nextPlayer(){
        playerTurn++;
        hasRolled = false;
        setDiceStatus(true);
        deselectHorse();
        if (playerTurn > 3)
            playerTurn = 0;
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
