package model;

public class Horse {
    private int id, currentAreaCode, locationOnPath, moveCount;
    private final String picturePath;

    public Horse(String picturePath){
        /*
        *  Constructor
        */
        //must have this line
        this.picturePath = picturePath;
    }

    //getter and setter here

    public void move(){
        /*
        * Change the currentAreaCode and locationOnPath base on the moveCount. The currentAreaCode increase from 0 to 3.
        * After 3 the currentAreaCode loop back to 0. Every areaCode has 12 locationOnPath indexing from 0 to 11.
        * The moveCount will reduce as the horse move along the locationOnPath until moveCount is 0.
        */
    }

    public String checkMove(){
        return "";
    }
}
