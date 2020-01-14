package util;

import javafx.beans.NamedArg;

import java.io.File;
import java.net.MalformedURLException;;
import java.net.URL;

public class FileIO {
    private static final String PATH_TO_FXML = System.getProperty("user.dir") + File.separator + "src" + File.separator + "fxml" + File.separator;
    private static final String PATH_TO_IMAGE = PATH_TO_FXML + File.separator + "Images" + File.separator;
    private static final String PATH_TO_AUDIO = PATH_TO_FXML + File.separator + "Sound" + File.separator;

    public static URL getFXML_URL(@NamedArg("FXML fileName") String fileName){
        File file = new File(PATH_TO_FXML + fileName + ".fxml");
        URL url = null;
        try{
            url = file.toURI().toURL();
        }catch (MalformedURLException e) {
            System.out.printf("URL Malformed");
        }
        return url;
    }

    public static String getMusicFile(@NamedArg("Audio filename") String fileName){
        File file = new File(PATH_TO_AUDIO + fileName + ".mp3");
        try{
            return file.toURI().toString();
        }catch (Exception e){
            System.out.printf("URI Malformed");
        }
        return null;
    }

    public static String getDiceImage(int value){
        return PATH_TO_IMAGE + "diceImages" + File.separator + value + ".png";
    }
}
