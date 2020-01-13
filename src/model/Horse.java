package model;

import controller.ModelController;
import util.Converter;

import java.util.ArrayList;

public class Horse {
    private static ArrayList<Horse> releasedHorses = new ArrayList<>();
    private int id, currentAreaCode, locationOnPath, moveCount, home;

    public Horse(int id){
        this.id = id;
        currentAreaCode = -1;
        locationOnPath = -1;
        home = -1;
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
        kickedHorse.home = -1;
    }

    public int getId(){
        return this.id;
    }

    public int getHome(){
        return home;
    }

    public int getLocationOnPath() {
        return locationOnPath;
    }

    public int getCurrentAreaCode() {
        return currentAreaCode;
    }

    public int getPathIndex(){
        return currentAreaCode * 12 + locationOnPath;
    }

    public int getMoveCount(){
        return this.moveCount;
    }

    public int getColor(int id){
        return Converter.getColorCodeFromId(id)[0];
    }

    public void move(int moveStatus, int moveCount){
        if (moveStatus == 3 || moveStatus == 4)
            moveCount = 1;
        this.moveCount = moveCount;
        //Move or move and kick horse
        if (moveStatus == 1 || moveStatus == 2 || moveStatus == 3 || moveStatus == 4)
        {
            //set location of move and move kick
            if (moveStatus == 1 || moveStatus == 2)
                setNewHorseLocation();
            else
                //set location of exit exit kick
                setExitHorseLocation();
            //call the function updateHorses in ModelController
            if (moveStatus == 2 || moveStatus == 4){
                Horse colliedHorse = checkCollision(this);
                ModelController.getInstance().updateHorses(this, colliedHorse);
                setKickedHorse(colliedHorse);
            }
            else
                ModelController.getInstance().updateHorses(this, null);
        }
        //Move to home
        if (currentAreaCode == getColor(id) && locationOnPath == 0)
            home = 0;
    }

    //If horse can reach to home return 5, if horse can exit and kick return 4, if horse can exit return 3, if horse can move and kick return 2, if horse can move return 1, if horse cant do anything return 0
    public int checkMove(int moveCount){
        int checkLocationOnPath = locationOnPath;
        int checkCurrentAreaCode = currentAreaCode;
        int checkHome = home;
        int checkMoveCount = moveCount;
        if (checkHome >= 0)
        {
            if (canMoveHome(checkMoveCount))
                return 5;
            else
                return 0;
        }
        else {
            if (checkMoveCount == 1 || checkMoveCount == 6)
                if (checkLocationOnPath == -1 && checkCurrentAreaCode == -1)
                    return checkRelease(checkLocationOnPath, checkLocationOnPath);

            while (checkMoveCount > 0) {
                checkLocationOnPath++;
                if (checkLocationOnPath > 11) {
                    checkLocationOnPath = 0;
                    checkCurrentAreaCode++;
                    if (checkCurrentAreaCode > 3)
                        checkCurrentAreaCode = 0;
                    if (checkMoveCount > 1 && checkCurrentAreaCode == getColor(id))
                        return 0;
                }
                if (checkCollision(checkLocationOnPath, checkCurrentAreaCode)) {
                    if (checkMoveCount == 1)
                        return 2;
                    else
                        return 0;
                }
                checkMoveCount--;
            }
            return 1;
        }
    }

    private int checkRelease(int...check){
        check[0] = 1;
        check[1] = getColor(id);
        if (checkCollision(check[0], check[1]))
            return 4;
        else
            return 3;
    }

    private boolean canMoveHome(int checkMoveCount){
        int checkHomePlace = 7;
        for (Horse checkHorse: releasedHorses)
            if (id != checkHorse.getId() && getColor(id) == getColor(checkHorse.getId()))
                if (checkHomePlace > checkHorse.getHome() && home < checkHorse.getHome())
                    checkHomePlace = checkHorse.getHome();
        return checkHomePlace > checkMoveCount;
    }

    private boolean checkCollision(int checkLocationMove,int checkAreaCodeMove){
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