package model;

import javafx.scene.paint.Color;

public class Player {

    private Horse[] horses;
    private int colorCode;

    public Player(int colorCode){
        this.colorCode = colorCode;
        horses = new Horse[4];
    }

    public boolean isHuman(){
        return false;
    }

    //getter and setter here
    public int getColorCode(){
        return colorCode;
    }

    public Horse getHorse(int id){
        return horses[id];
    }

    public void autoMove(){

    }
}
