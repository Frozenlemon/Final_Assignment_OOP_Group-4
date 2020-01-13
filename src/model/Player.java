package model;

import controller.ModelController;
import model.Horse;
import javafx.scene.paint.Color;

import java.util.ArrayList;

public class Player {

    private Horse[] horses;
    private int colorCode;
    private Dice[] dices;
    ArrayList<Integer> choices = new ArrayList<>();

    public Player(int colorCode){
        this.colorCode = colorCode;
        horses = new Horse[]{new Horse(colorCode*4+0), new Horse(colorCode*4+1), new Horse(colorCode*4+2), new Horse(colorCode*4+3)};
        dices = new Dice[]{new Dice()};
    }

    public boolean isHuman(){
        if (this instanceof Player)
            return true;
        return false;
    }

    public int getColorCode(){
        return colorCode;
    }

    public Horse getHorse(int id){
        return horses[id];
    }

    public void autoMove() {
        ModelController.getInstance().rollDice();
        Dice[] allDice = ModelController.getInstance().getAllDice();
        while (ModelController.getInstance().getAnimationCount() != 0) {}
        while (checkDice(allDice)){
            getChoice(allDice);
            selectMove(allDice);

        }
        ModelController.getInstance().nextPlayer();
    }

    public void getChoice(Dice[] allDice){
        for (int i = 0; i < dices.length; i++) {
            if (!dices[i].isUsed()) {
                for (int j = 0; j < horses.length; j++){
                    choices.add(getHorse(j).checkMove(allDice[i].getValue()));
                }
            }
        }
    }

    public void selectMove(Dice[] allDice){
        int bestChoice = -1;
        int choice_index = -1;
        for (int i = 0; i < choices.size(); i++){
            if (bestChoice < choices.get(i)) {
                bestChoice = choices.get(i);
                choice_index = i;
            }
        }
        switch (choice_index % 4){
            case 0:
                horses[0].move(bestChoice, allDice[choice_index/4].getValue());
                choices = new ArrayList<>();
                break;
        }
    }


    private boolean checkDice(Dice[] dices){
        for (Dice dice: dices)
            while (!dice.isUsed())
                return true;
        return false;
    }
}
