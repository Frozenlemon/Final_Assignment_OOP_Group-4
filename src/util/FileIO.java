package util;

import javafx.beans.NamedArg;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

public class FileIO {
    private static final String PATH_TO_FXML = System.getProperty("user.dir") + File.separator + "src" + File.separator + "fxml" + File.separator;
    private static final String PATH_TO_IMAGE = PATH_TO_FXML + File.separator + "Images" + File.separator;

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

    public static String getDiceImage(int value){
        return new File(PATH_TO_IMAGE + "diceImage" + File.separator + value + ".png").toString();
    }
}
