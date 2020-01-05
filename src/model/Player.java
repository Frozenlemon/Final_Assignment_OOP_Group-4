package model;

import javafx.scene.paint.Color;

public class Player {

    private Horse[] horses;
    private int colorCode;

    public Player(int colorCode){
        this.colorCode = colorCode;
        horses = new Horse[4];
    }

    //getter and setter here

    public Horse getHorse(int id){
        return horses[id];
    }
}
