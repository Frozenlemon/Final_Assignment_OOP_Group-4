package model;

import controller.ModelController;
import model.Horse;
import javafx.scene.paint.Color;

public class Player {

    private Horse[] horses;
    private int colorCode;
    private Dice[] dices;
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

    //getter and setter here
    public int getColorCode(){
        return colorCode;
    }

    public Horse getHorse(int id){
        return horses[id];
    }

    public void autoMove() {
        ModelController.getInstance().rollDice();
        int[] dice_values = ModelController.getInstance().getAllDiceValue();
        //while (ModelController.getInstance().getAnimationCount() != 0) {
        //}
        for (int i = 0; i < dices.length; i++) {
            int init_possible = -1;
            int init_horse = -1;
            for (int j = 0; j < horses.length; j++) {
                if (init_possible < getHorse(j).checkMove(dice_values[i])){
                    init_horse = j;
                    init_possible = getHorse(j).checkMove(dice_values[i]);
                }
            }
            getHorse(init_horse).move(init_possible, dice_values[i]);
            //while (ModelController.getInstance().getAnimationCount() != 0);
            }
        ModelController.getInstance().nextPlayer();
    }

    private boolean checkDice(Dice[] dices){
        for (Dice dice: dices)
            while (!dice.isUsed())
                return true;
        return false;
    }
}
