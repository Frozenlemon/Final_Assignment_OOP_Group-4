package controller;

import model.Horse;
import model.Player;

import java.util.ArrayList;

public class ModelController {
    private static final int NO_OF_PLAYER = 4;
    private static ModelController instance;
    private Player[] players = new Player[NO_OF_PLAYER];
    private ArrayList<Horse> releasedHorses;

    private ModelController(){
        for (int i = 0; i < NO_OF_PLAYER; i++){
            if (players[i] == null)
                players[i] = new Player(i);
        }
        releasedHorses = new ArrayList<>();
    }

    public static ModelController getInstance(){
        if (instance == null)
            instance = new ModelController();
        return instance;
    }

    public Horse checkCollision(Horse horse){
        for (Horse rHorse: releasedHorses){
            if (horse.getId() != rHorse.getId()){
                if (rHose.getCurrentAreaCode() == horse.getCurrentAreaCode() && rHose.getLocationOnPath() == horse.getLocationOnPath())
                    return rHorse;
            }
        }
        return null;
    }

    public void updateHorses(Horse horse){
        if (horse.getMoveStatus().equal("kick")){
            ViewController.getInstance().horseMoveAndKick(horse, checkCollision(horse));
        }
        else{
            ViewController.getInstance().horseMove(horse);
        }
    }
}
