package model;

import java.util.Random;

public class Dice {
    private static int diceCounter = 0;
    private int id;
    private int value;
    private boolean isUsed;

    public Dice(){
        if(diceCounter < 2) {
            this.id = diceCounter;
            roll();
            diceCounter++;
        }
    }

    public int getId(){ return id; }
    public int getValue(){
        return value;
    }

    public void roll(){
        Random rand = new Random();
        value = rand.nextInt(6) + 1;
        isUsed = false;
    }

    public void setUsed(boolean status){
        this.isUsed = status;
    }

    public boolean isUsed(){
        return isUsed;
    }
}
