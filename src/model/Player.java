package model;


import controller.ModelController;

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

    public boolean isHuman() {
        return false;
    }

    public int getColorCode() {
        return colorCode;
    }

    public Horse getHorse(int id) {
        return horses[id];
    }

    public void autoMove() {
        ModelController.getInstance().rollDice();
        Dice[] allDice = ModelController.getInstance().getAllDice();
        while (ModelController.getInstance().getAnimationCount() != 0) {}
        while (checkDice(allDice)) {
            getChoices(allDice);
            selectMove(allDice);
            if (canMove()) {
                selectMove(allDice);
                while (ModelController.getInstance().getAnimationCount() != 0) {}

            }
            else {
                allDice[0].setUsed(true);
                allDice[1].setUsed(true);
            }
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
        for (int i = 0; i < choices.size(); i++) {
            if (bestChoice < choices.get(i)) {
                bestChoice = choices.get(i);
                choice_index = i;
            }
        }
        switch (choice_index % 4) {
            case 0:
                horses[0].move(bestChoice, allDice[choice_index / 4].getValue());
                break;
            case 1:
                horses[1].move(bestChoice, allDice[choice_index / 4].getValue());
                break;
            case 2:
                horses[2].move(bestChoice, allDice[choice_index / 4].getValue());
                break;
            case 3:
                horses[3].move(bestChoice, allDice[choice_index / 4].getValue());
                break;
        }
        choices = new ArrayList<>();
        allDice[choice_index].setUsed(true);
    }

    private boolean checkDice(Dice[] dices) {
        for (Dice dice : dices)
            while (!dice.isUsed())
                return true;
        return false;
    }
}