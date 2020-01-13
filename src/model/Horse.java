package model;

import controller.ModelController;
import util.Converter;

import java.util.ArrayList;

public class Horse {
    private static ArrayList<Horse> releasedHorses = new ArrayList<>();
    private int id, currentAreaCode, locationOnPath, moveCount, homeOnPath;

    public Horse(int id){
        this.id = id;
        currentAreaCode = -1;
        locationOnPath = -1;
        homeOnPath = -1;
        moveCount = 0;
    }

    public void setExitHorseLocation(){
        this.currentAreaCode = Converter.getColorCodeFromId(id)[0];
        this.locationOnPath = 1;
        releasedHorses.add(this);
    }

    private void setKickedHorse(Horse kickedHorse){
        releasedHorses.remove(kickedHorse);
        kickedHorse.currentAreaCode = -1;
        kickedHorse.locationOnPath = -1;
        kickedHorse.homeOnPath = -1;
    }

    public int getId(){
        return this.id;
    }

    public int getPathIndex(){
        return currentAreaCode * 100 + locationOnPath;
    }

    public int getMoveCount(){
        return this.moveCount;
    }

    public void move(int moveStatus, int moveCount){
        this.moveCount = moveCount;
        //Move or move and kick horse
        if (moveStatus == 1 || moveStatus == 2 || moveStatus == 3 || moveCount == 4)
        {
            //set location of move and move kick
            if (moveStatus == 1 || moveStatus == 2)
                setNewHorseLocation();
            else
                //set location of exit exit kick
                if (moveStatus == 3 || moveStatus == 4)
                    setExitHorseLocation();
            //call the function updateHorses in ModelController
            if (moveStatus == 2 || moveStatus == 4){
                Horse colliedHorse = checkCollision(this);
                ModelController.getInstance().updateHorses(this, colliedHorse);
                setKickedHorse(colliedHorse);
            }
            else
                if (moveStatus == 1 || moveStatus == 3){
                ModelController.getInstance().updateHorses(this, null);
            }
        }
    }

    //If horse can reach to home return 5, if horse can exit and kick return 4, if horse can exit return 3, if horse can move and kick return 2, if horse can move return 1, if horse cant do anything return 0
    public int checkMove(int moveCount){
        int checkLocationOnPath = locationOnPath;
        int checkCurrentAreaCode = currentAreaCode;
        int checkHomeOnPath = homeOnPath;
        int checkMoveCount = moveCount;
        //if (checkHomeOnPath == 0)

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