/*
  RMIT University Vietnam
  Course: INTE2512 Object-Oriented Programming
  Semester: 2019C
  Assessment: Final Project
  Created date: 23/03/2019
  By: Le Minh s3757324
      Ho Duy Hoang s3672214
      Pham Thanh Dat s3678437
      Ta Quoc Thang s3713564
      Le Nguyen Thien Phu s3639855
  Last modified: 15/01/2020
  By: Le Minh s3757324
      Ho Duy Hoang s3672214
      Pham Thanh Dat s3678437
      Ta Quoc Thang s3713564
      Le Nguyen Thien Phu s3639855
  Acknowledgement:
[1] "Java Internationalization - javatpoint", www.javatpoint.com, 2020. [Online]. Available: https://www.javatpoint.com/internationalization-in-java. [Accessed: 14- Jan- 2020]
[2]"Internationalizing the Sample Program (The Java™ Tutorials > Internationalization > Introduction)", Docs.oracle.com, 2020. [Online]. Available: https://docs.oracle.com/javase/tutorial/i18n/intro/steps.html?fbclid=IwAR00AiifBkc8cmbF43D0YEUM6ZYayCq_ldGYnVxZBzE0xlL_-2n-5ElxSeE. [Accessed: 14- Jan- 2020]
[3]Tutorials.jenkov.com, 2020. [Online]. Available: http://tutorials.jenkov.com/java-internationalization/resourcebundle.html?fbclid=IwAR2FqduxMXmdSdnltj4q7BIrOCbadtPCiSEWwg2da_mvY71n-rPZHuQEG58. [Accessed: 14- Jan- 2020]
[4]"Code Conventions for the Java Programming Language: 9. Naming Conventions", Oracle.com, 2020. [Online]. Available: https://www.oracle.com/technetwork/java/codeconventions-135099.html?fbclid=IwAR2ZlGNIfZqOB3fUjnW0A5dKgyPZFeaq8XKSgGbbfc6qBrw3dnmC-skDNdo. [Accessed: 14- Jan- 2020]
[5]"JavaFX Playing Audio - javatpoint", www.javatpoint.com, 2020. [Online]. Available: https://www.javatpoint.com/javafx-playing-audio?fbclid=IwAR3WFDkfH5LvjWqk6OCoY55aLShLcb4VWCM7IFAyA-JkZBAYRlSpz0U-hFc. [Accessed: 14- Jan- 2020]
[6]"CSS Examples", W3schools.com, 2020. [Online]. Available: https://www.w3schools.com/css/css_examples.asp. [Accessed: 14- Jan- 2020]
*/
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
        kickedHorse.currentAreaCode = -1;
        kickedHorse.locationOnPath = -1;
        releasedHorses.remove(kickedHorse);
    }

    public int getId(){
        return this.id;
    }

    public int getColorCode(){
        return Converter.getColorCodeFromId(id)[0];
    }

    public int getPathIndex(){
        return (currentAreaCode * 12) + locationOnPath;
    }

    public int getMoveCount(){
        return this.moveCount;
    }

    public int getHomeOnPath(){
        return homeOnPath;
    }

    public void move(int moveStatus, int moveCount){
        System.out.println("Status: " + moveStatus);
        if (moveStatus == 2 || moveStatus == 4)
            moveCount = 1;
        this.moveCount = moveCount;
        //Move or move and kick horse
        if (moveStatus != 0)
        {
            //set location of all move
            if (moveStatus != 2 && moveStatus != 4) {
                int[] location = get_HorseNewLocation(moveCount);
                setHorseLocation(location[0], location[1]);
            }
            else
                setExitHorseLocation();     //set location of all exit

            //call the function updateHorses in ModelController
            if (moveStatus == 5 || moveStatus == 6)
                System.out.println("Hprse ID: " + this.id + "\nAREA CODE: " + currentAreaCode + "\nPATH: " + locationOnPath);
            if (moveStatus == 3 || moveStatus == 4 || moveStatus == 6){
                Horse colliedHorse = checkCollision(this);
                setKickedHorse(colliedHorse);
                ModelController.getInstance().updateHorses(this, colliedHorse);
            }
            else
                ModelController.getInstance().updateHorses(this, null);
        }
    }

    private void setHorseLocation(int currentAreaCode, int locationOnPath){
        this.currentAreaCode = currentAreaCode;
        this.locationOnPath = locationOnPath;
    }
    /*
    If Horse reach home and kick return 6,
    if horse can reach to home return 5,
    if horse can exit and kick return 4,
    if horse can exit return 3,
    if horse can move and kick return 2,
    if horse can move return 1,
    if horse cant do anything return 0
     */
    public int checkMove(int moveCount){
        if (currentAreaCode == getColorCode() && (locationOnPath == 0 || locationOnPath == -1)) {
            if (canUpgrade(moveCount))
                return 7;
            else
                return 0;
        }
        int value = canRelease(moveCount, currentAreaCode, locationOnPath); //check if the horse can be release
        if (value != -1)
            return value; //can return 0, 3 or 4
        else{
            int moveStatus = canMove(moveCount - 1, moveOneStep(currentAreaCode, locationOnPath));
            return isLastPoint(moveStatus, moveCount); //convert from regular move/ kick to got to last point if possible
        }
    }

    public boolean canUpgrade(int moveCount){
        if (homeOnPath == 6)
            return false;
        if (currentAreaCode == getColorCode() && locationOnPath == 0){
            if (moveCount > homeOnPath){
                for (int i = homeOnPath + 1; i < moveCount; i++){
                    if (!checkHomeBlock(i))
                        return false;
                }
                return true;
            }
        }
        else if (currentAreaCode == getColorCode() && locationOnPath == -1){
            if (moveCount == homeOnPath + 1){
                if (!checkHomeBlock(moveCount))
                    return false;
                return true;
            }
        }
        return false;
    }


    public void upgradeHorse(int moveCount){
        if (this.locationOnPath == 0)
            this.locationOnPath = -1;
        homeOnPath = moveCount;
        ModelController.getInstance().upgradeHorse(this);
    }

    public boolean checkHomeBlock(int home){
        for (Horse horse: releasedHorses){
            if (horse.getColorCode() == this.getColorCode()){
                if (home == horse.getHomeOnPath())
                    return false;
            }
        }
        return true;
    }

    public int checkCollision(int colorCode, int checkLocationMove, int checkAreaCodeMove){
        for (Horse checkHorse: releasedHorses) {
            if (checkLocationMove == checkHorse.getLocationOnPath() && checkAreaCodeMove == checkHorse.getCurrentAreaCode()){
                if (colorCode != checkHorse.getColorCode())
                    return 2; //collision with other player horse
                else
                    return 1; //collision with player horse
            }
        }
        return 0; //no collision
    }

    public Horse checkCollision(Horse horse){
        for (Horse rHorse: releasedHorses){
            if (horse.getColorCode() != rHorse.getColorCode()) {
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

    private int[] get_HorseNewLocation(int moveCount){
        int pathIndex = locationOnPath;
        int areaCode = currentAreaCode;

        pathIndex += moveCount;
        if (pathIndex > 11){
            pathIndex -= 12;
            areaCode += 1;
        }
        if (areaCode > 3)
            areaCode = 0;
        return new int[]{areaCode, pathIndex};
    }

    private int canRelease(int moveCount, int... location){
        if (location[0] == -1 && location[1] == -1){
            if (moveCount == 1 || moveCount == 6){
                int isCollied = checkCollision(getColorCode(),1, Converter.getColorCodeFromId(this.id)[0]);
                if (isCollied == 2)
                    return 4;// release and kick
                else if(isCollied == 0)
                    return 2; //release
                else
                    return 0; //cannot move
            }
            else
                return 0; //cant move
        }
        return -1; //already release
    }

    private int canMove(int remainMove, int... location){
        int isCollied = checkCollision(getColorCode(), location[1], location[0]);
        if (location[0] == getColorCode() && location[1] == 0 && remainMove > 0)
            return 0;   //get to last point but still have move left => cannot move
        if (isCollied != 0){
            if (remainMove > 0)
                return 0; // collied but still have move => cannot move
            else {
                if (isCollied == 2)
                    return 3; //collied with other's horse and have 0 move left => move kick
                else
                    return 0; //collied with own horse => cannot move
            }
        }
        else {
            if (remainMove > 0)
                return canMove(remainMove - 1, moveOneStep(location)); //not collied and still have move => check next step
            else
                return 1; // not collied and no move left => can move
        }
    }

    private int isLastPoint(int moveStatus, int moveCount){
        int[] newLocation = get_HorseNewLocation(moveCount);
        boolean isLastPoint = false;
        if (newLocation[1] == 0 && newLocation[0] == getColorCode())
            isLastPoint = true;

        if (moveStatus == 3){
            if (isLastPoint)
                return 6;
        }
        else if (moveStatus == 1){
            if (isLastPoint)
                return 5;
        }
        return moveStatus;
    }

    private int[] moveOneStep(int...location){
        int areaCode = location[0];
        int path = location[1] + 1;
        if (path > 11){
            path = 0;
            areaCode++;
        }
        if (areaCode > 3)
            areaCode = 0;
        return new int[]{areaCode, path};
    }
}