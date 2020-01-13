package model;

public class Human extends Player {

    private String name;
    private int score;

    public Human(int colorCode, String name) {
        super(colorCode);
        this.name = name;
        this.score = 0;
    }

    @Override
    public boolean isHuman(){
        return true;
    }

    public void changeScore(int value){
        this.score += value;
    }

    public int getScore(){
        return this.score;
    }
}
