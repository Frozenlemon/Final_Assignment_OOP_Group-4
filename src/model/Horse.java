package model;

import controller.ModelController;

import java.util.ArrayList;

public class Horse {
    private ArrayList<Horse> releasedHorses = new ArrayList<Horse>();
    private int id, currentAreaCode, locationOnPath, moveCount;

    //id format ** (color, number of horse)
    public void Horse(int id){
        this.id = id;
        currentAreaCode = -1;
        locationOnPath = -1;
        //releasedHorses = new ArrayList<>();
        moveCount = 0;
    }

    //getter and setter here{
    public void setter(){
        ArrayList<Integer> ID = new ArrayList<>();
        int identification;
        for (int i = 0; i < 4; i++){
            for (int j = 0; j < 4; j ++){
                identification = 0;
                identification = i * 10 +j;
                ID.add(identification);
                Horse(identification);
            }
        }

        for (int i = 0; i < ID.size(); i++) {
            Horse hor = new Horse();
            hor.Horse(ID.get(i));
            releasedHorses.add(hor);
        }
    }

    public int getId(){
        return this.id;
    }

    public int getPathIndex(){
        //return an int represent the index of the path in the view. The format of the index is a 3 digit integer where
        // the 1st index is the multiple of 12 and the currentAreaCode and the remain 2 digit is the locationOnPath
        return currentAreaCode * 100 + locationOnPath;
    }

    public int getMoveCount(){
        return this.moveCount;
    }

    public void move(int moveStatus, int moveCount){
        this.moveCount = moveCount;
        if (moveStatus == 1 || moveStatus == 2)
        {
            setNewHorseLocation();
            //call the function updateHorses in ModelController
            if (moveStatus == 2){
                Horse colliedHorse = checkCollision(this);
                ModelController.getInstance().updateHorses(this, colliedHorse);
            }
            else{
                ModelController.getInstance().updateHorses(this, null);
            }
        }
    }

    //If horse can exit and kick return 4, if horse can exit return 3, if horse can move and kick return 2, if horse can move return 1, if horse cant do anything return 0
    public int checkMove(int moveCount){
        int checkLocationOnPath = locationOnPath;
        int checkCurrentAreaCode = currentAreaCode;
        int checkMoveCount = moveCount;
        if (checkMoveCount == 1 || checkMoveCount == 6) {
            if (canBlockOrKick(checkLocationOnPath, checkCurrentAreaCode))
                return 4;
            else
                return 3;
        }
        else {
            while (checkMoveCount > 0)
            {
                checkLocationOnPath++;
                if (checkLocationOnPath > 11)
                {
                    checkLocationOnPath = 0;
                    checkCurrentAreaCode++;
                    if (checkCurrentAreaCode > 3)
                        checkCurrentAreaCode = 0;
                    if (canBlockOrKick(checkLocationOnPath, checkCurrentAreaCode))
                        return 0;
                }
                checkMoveCount--;
            }
        }
        if (canBlockOrKick(checkLocationOnPath, checkCurrentAreaCode))
            return 2;
        else
            return 1;
    }

    public boolean canBlockOrKick(int checkLocationMove,int checkAreaCodeMove){
        for (Horse checkHorse: releasedHorses) {
            if (checkLocationMove == checkHorse.getLocationOnPath() && checkAreaCodeMove == checkHorse.getCurrentAreaCode())
                return true;
        }
        return false;
    }

    public Horse checkCollision(Horse horse){
        for (Horse rHorse: releasedHorses){
            if (horse.getId() != rHorse.getId()){
                if (rHorse.getCurrentAreaCode() == horse.getCurrentAreaCode() && rHorse.getLocationOnPath() == horse.getLocationOnPath())
                    return rHorse;
            }
        }
        return null;
    }

    public int getLocationOnPath() {
        return locationOnPath;
    }

    public int getCurrentAreaCode() {
        return currentAreaCode;
    }

    private void setNewHorseLocation(){
        locationOnPath += moveCount;
        if (locationOnPath > 11){
            locationOnPath -= 12;
            currentAreaCode++;
            if (currentAreaCode > 3)
                currentAreaCode = 0;
        }
    }
}