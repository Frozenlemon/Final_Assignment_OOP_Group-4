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

    public static String getImageFile(String fileName){
        return PATH_TO_IMAGE + fileName + ".png";
    }
}
