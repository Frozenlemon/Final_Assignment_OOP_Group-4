package view;

import controller.ViewController;
import javafx.animation.RotateTransition;
import javafx.animation.TranslateTransition;
import javafx.beans.NamedArg;
import javafx.fxml.FXML;
import javafx.scene.effect.Effect;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Circle;
import javafx.util.Duration;
import model.Horse;

public class GamePane {
    private static final int NO_OF_HORSES = 16;
    private static final int NO_OF_BASE = 4;
    private static final int NO_OF_PATHS = 48;

    @FXML
    private StackPane gamePane, imagePane, circleBase;
    @FXML
    private StackPane blueHorseCage, yellowHorseCage, greenHorseCage, redHorseCage;
    @FXML
    private Circle i0, i1, i2, i3, i4, i5, i6, i7, i8, i9, i10, i11, i12, i13, i14, i15;

    private ImageView[] horses;
    private Circle[] paths;

    public GamePane(){
        horses = new ImageView[NO_OF_HORSES];
        paths = new Circle[NO_OF_PATHS];
    }

    public void init(){
        for (int i = 0; i < NO_OF_HORSES; i++){
            horses[i] = (ImageView) imagePane.getChildren().get(i);
        }
        for (int i = 0; i < NO_OF_PATHS; i++){
            paths[i] = (Circle) circleBase.getChildren().get(i);
        }
        setStartingHorse();
        translate(-100,0);
    }

    public StackPane getGamePane(){
        return gamePane;
    }

    public void translate(@NamedArg("Translate the gamePane by x-horizontal and y vertical") double x, double y){
        gamePane.setTranslateX(x);
        gamePane.setTranslateY(y);
    }

    public void moveHorse(int horseIndex, int pathIndex, int moveCount, TranslateTransition queueTransition){
        moveCount--;
        TranslateTransition transition = new TranslateTransition(Duration.seconds(1), horses[horseIndex]);

        transition.setToX(paths[pathIndex].getTranslateX() + 10);
        transition.setToY(paths[pathIndex].getTranslateY());

        pathIndex--;
        transition.setOnFinished(e -> {
            if (queueTransition != null) {
                ViewController.getInstance().startAnimation();
                queueTransition.play();
            }
            ViewController.getInstance().finishAnimation();
        });

        if (moveCount > 0)
            moveHorse(horseIndex, pathIndex, moveCount, transition);
        else {
            ViewController.getInstance().startAnimation();
            transition.play();
        }
    }

    public void kickHorse(int horseIndex){
        TranslateTransition translateTransition = new TranslateTransition(Duration.seconds(1), horses[horseIndex]);
        double[] coordinate = getInitialCoordinate(horseIndex);
        translateTransition.setToX(coordinate[0]);
        translateTransition.setToY(coordinate[1]);
        translateTransition.setOnFinished(e -> ViewController.getInstance().finishAnimation());

        RotateTransition rotateTransition = new RotateTransition(Duration.seconds(1), horses[horseIndex]);
        translateTransition.play();
        rotateTransition.play();
    }

    public void change_Horse_Highlight(@NamedArg("Horse Index") int horseIndex){
        if (horses[horseIndex].getEffect() == null)
            horses[horseIndex].setEffect(new javafx.scene.effect.Bloom());
        else
            horses[horseIndex].setEffect(null);
    }

    private void setStartingHorse(){
        for (int i = 0; i < NO_OF_HORSES; i++){
            double[] coordinate = getInitialCoordinate(i);
            horses[i].setTranslateX(coordinate[0]);
            horses[i].setTranslateY(coordinate[1]);
        }
    }


    private double[] getInitialCoordinate(int index){
        double[] coordinate = new double[2];
        switch (index){
            case 0:
                coordinate[0] = i0.getCenterX();
                coordinate[1] = i0.getCenterY();
                break;
            case 1:
                coordinate[0] = i1.getTranslateX();
                coordinate[1] = i1.getTranslateY();
                break;
            case 2:
                coordinate[0] = i2.getTranslateX();
                coordinate[1] = i2.getTranslateY();
                break;
            case 3:
                coordinate[0] = i3.getTranslateX();
                coordinate[1] = i3.getTranslateY();
                break;
            case 4:
                coordinate[0] = i4.getTranslateX();
                coordinate[1] = i4.getTranslateY();
                break;
            case 5:
                coordinate[0] = i5.getTranslateX();
                coordinate[1] = i5.getTranslateY();
                break;
            case 6:
                coordinate[0] = i6.getTranslateX();
                coordinate[1] = i6.getTranslateY();
                break;
            case 7:
                coordinate[0] = i7.getTranslateX();
                coordinate[1] = i7.getTranslateY();
                break;
            case 8:
                coordinate[0] = i8.getTranslateX();
                coordinate[1] = i8.getTranslateY();
                break;
            case 9:
                coordinate[0] = i9.getTranslateX();
                coordinate[1] = i9.getTranslateY();
                break;
            case 10:
                coordinate[0] = i10.getTranslateX();
                coordinate[1] = i10.getTranslateY();
                break;
            case 11:
                coordinate[0] = i11.getTranslateX();
                coordinate[1] = i11.getTranslateY();
                break;
            case 12:
                coordinate[0] = i12.getTranslateX();
                coordinate[1] = i12.getTranslateY();
                break;
            case 13:
                coordinate[0] = i13.getTranslateX();
                coordinate[1] = i13.getTranslateY();
                break;
            case 14:
                coordinate[0] = i14.getTranslateX();
                coordinate[1] = i14.getTranslateY();
                break;
            case 15:
                coordinate[0] = i15.getTranslateX();
                coordinate[1] = i15.getTranslateY();
                break;
        }
        return coordinate;
    }

    private int getHorseIndex(ImageView horse){
        for (int i = 0; i < NO_OF_HORSES; i++){
            if(horse.getId().equals(horses[i].getId()))
                return i;
        }
        return -1;
    }

    @FXML
    private void clickOnHorse(MouseEvent evt){
        ImageView horse = (ImageView) evt.getSource();
        int horseIndex = getHorseIndex(horse);
        ViewController.getInstance().clickOnHorse(horseIndex);
    }
}