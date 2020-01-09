package model;

import controller.ModelController;

import java.util.ArrayList;

public class Horse {
    private ArrayList<Horse> releasedHorses;
    private int id, currentAreaCode, locationOnPath, moveCount;

    //id format ** (color, number of horse)
    public Horse(int id){
        this.id = id;
        currentAreaCode = -1;
        locationOnPath = -1;
        releasedHorses = new ArrayList<>();
        moveCount = 0;
    }

    //getter and setter here{

    public int getId(){
        return this.id;
    }

    public int getPathIndex(){
        //return an int represent the index of the path in the view. The format of the index is a 3 digit integer where
        // the 1st index is the multiple of 12 and the currentAreaCode and the remain 2 digit is the locationOnPath
        return -1;
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
        if (moveStatus == 0){

        }
    }

    //If horse can exit the starting area return 0, if horse can move return 1, if horse can kick return 2, if horse cant do anything return 3
    public int checkMove(int moveCount){
        int checkLocationOnPath = locationOnPath;
        int checkCurrentAreaCode = currentAreaCode;
        int checkMoveCount = moveCount;
        if (checkMoveCount == 1 || checkMoveCount == 6)
            return 0;
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
                    if (canBlockOrKick(checkLocationOnPath, checkCurrentAreaCode) == 1)
                        return 3;
                }
                checkMoveCount--;
            }
        }
        if (canBlockOrKick(checkLocationOnPath, checkCurrentAreaCode) == 1)
            return 2;
        else
            return 1;
    }

    public int canBlockOrKick(int checkLocationMove,int checkAreaCodeMove){
        for (Horse checkHorse: releasedHorses) {
            if (checkLocationMove == checkHorse.getLocationOnPath() && checkAreaCodeMove == checkHorse.getCurrentAreaCode())
                return 1;
        }
        return 0;
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