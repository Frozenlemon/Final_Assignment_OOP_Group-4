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
import controller.ViewController;

import java.util.ArrayList;

public class Player {

    private Horse[] horses;
    private int colorCode;
    ArrayList<Integer> choices = new ArrayList<>();

    public Player(int colorCode) {
        this.colorCode = colorCode;
        horses = new Horse[]{
                new Horse(colorCode*4),
                new Horse((colorCode*4) + 1),
                new Horse((colorCode*4) + 2),
                new Horse((colorCode*4) + 3)};
    }

    public int getColorCode() {
        return colorCode;
    }

    public Horse getHorse(int id) {
        return horses[id];
    }

    public void autoMove() {
        Dice[] allDice = ModelController.getInstance().getAllDice();
        getChoices(allDice);

        printChoice();

        if (canMove()) {
            selectMove(allDice);
        }
    }

    private boolean canMove() {
        for (int choice : choices) {
            if (choice != 0)
                return true;
        }
        return false;
    }


    public void getChoices(Dice[] allDice) {
        for (int i = 0; i < allDice.length; i++) {
            if (!allDice[i].isUsed()) {
                for (int j = 0; j < horses.length; j++) {
                    choices.add(getHorse(j).checkMove(allDice[i].getValue()));
                }
            }
        }
    }

    public void selectMove(Dice[] allDice) {
        int bestChoice = -1;
        int choice_index = -1;
        int diceIndex;
        for (int i = 0; i < choices.size(); i++) {
            if (bestChoice < choices.get(i)) {
                    bestChoice = choices.get(i);
                    choice_index = i;
            }
            else if (bestChoice == choices.get(i)){
                if (horses[choice_index % 4].getPathIndex() < horses[i % 4].getPathIndex()) {
                    bestChoice = choices.get(i);
                    choice_index = i;
                }
            }
        }

        System.out.println("BEST CHOICE: " + bestChoice);
        int horseIndex = choice_index % 4;
        if (choices.size() == 8)
            diceIndex = choice_index / 4;
        else {
            diceIndex = -1;
            for (int i = 0; i < allDice.length; i++){
                if (!allDice[i].isUsed())
                    diceIndex = i;
            }
        }
        if (bestChoice != 7)
            horses[horseIndex].move(bestChoice, allDice[diceIndex].getValue());
        else
            horses[horseIndex].upgradeHorse(allDice[diceIndex].getValue());
        choices = new ArrayList<>();
        allDice[diceIndex].setUsed(true);
    }

    public void printChoice(){
        String result = "Posible choice: ";
        for (int choice: choices)
            result += choice + " ";
        System.out.println(result);
    }
}