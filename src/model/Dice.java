package model;

import java.util.Random;

public class Dice {
    private int id;
    private int value;

    public int Dice(){
        roll();
        return value;
    }

    public int getId(){ return id; }
    public int getValue(){
        return value;
    }

    public void roll(){
        Random rand = new Random();
        value = rand.nextInt(6) + 1;
    }
}
