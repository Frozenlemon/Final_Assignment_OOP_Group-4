package model;


import controller.ModelController;
import controller.ViewController;

import java.util.ArrayList;

public class Player {

    private Horse[] horses;
    private int colorCode;
    ArrayList<Integer> choices = new ArrayList<>();

    public Player(int colorCode) {
        this.colorCode = colorCode;
        horses = new Horse[]{
                new Horse(colorCode*4),
                new Horse((colorCode*4) + 1),
                new Horse((colorCode*4) + 2),
                new Horse((colorCode*4) + 3)};
    }

    public int getColorCode() {
        return colorCode;
    }

    public Horse getHorse(int id) {
        return horses[id];
    }

    public void autoMove() {
        Dice[] allDice = ModelController.getInstance().getAllDice();
        getChoices(allDice);

        printChoice();

        if (canMove()) {
            selectMove(allDice);
        }
    }

    private boolean canMove() {
        for (int choice : choices) {
            if (choice != 0)
                return true;
        }
        return false;
    }


    public void getChoices(Dice[] allDice) {
        for (int i = 0; i < allDice.length; i++) {
            if (!allDice[i].isUsed()) {
                for (int j = 0; j < horses.length; j++) {
                    choices.add(getHorse(j).checkMove(allDice[i].getValue()));
                }
            }
        }
    }

    public void selectMove(Dice[] allDice) {
        int bestChoice = -1;
        int choice_index = -1;
        int diceIndex;
        for (int i = 0; i < choices.size(); i++) {
            if (bestChoice < choices.get(i)) {
                    bestChoice = choices.get(i);
                    choice_index = i;
            }
            else if (bestChoice == choices.get(i)){
                if (choice_index != -1){
                    if (horses[choice_index % 4].getPathIndex() < horses[i % 4].getPathIndex())
                    bestChoice = choices.get(i);
                }
                else{
                    bestChoice = choices.get(i);
                }
                choice_index = i;
            }
        }

        System.out.println("BEST CHOICE: " + bestChoice);
        int horseIndex = choice_index % 4;
        if (choices.size() == 8)
            diceIndex = choice_index / 4;
        else {
            diceIndex = -1;
            for (int i = 0; i < allDice.length; i++){
                if (!allDice[i].isUsed())
                    diceIndex = i;
            }
        }
        if (bestChoice != 7)
            horses[horseIndex].move(bestChoice, allDice[diceIndex].getValue());
        else
            horses[horseIndex].upgradeHorse(allDice[diceIndex].getValue());
        choices = new ArrayList<>();
        allDice[diceIndex].setUsed(true);
    }

    public void printChoice(){
        String result = "Posible choice: ";
        for (int choice: choices)
            result += choice + " ";
        System.out.println(result);
    }
}