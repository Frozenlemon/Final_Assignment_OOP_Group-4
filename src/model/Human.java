package model;

public class Human extends Player {

    private String name;
    private int accumulativeScore;
    private int staticScore;

    public Human(int colorCode, String name) {
        super(colorCode);
        this.name = name;
        this.accumulativeScore = 0;
    }

    public void addToAccumulativeScore(int score){
        accumulativeScore += score;
    }

    public int getScore(){
        staticScore = 0;
        for (int i = 0; i < 4; i++){
            if (getHorse(i).getHomeOnPath() > 0)
                staticScore += getHorse(i).getHomeOnPath();
        }
        return accumulativeScore + staticScore;
    }
}
