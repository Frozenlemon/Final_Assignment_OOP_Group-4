package util;

public class Converter {

    public static int[] getColorCodeFromId(int horseIndex){
        int[] idCode = new int[2];
        idCode[0] = horseIndex / 4;
        return idCode;
    }
}
