package view;

import controller.ViewController;
import javafx.animation.RotateTransition;
import javafx.animation.TranslateTransition;
import javafx.beans.NamedArg;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

public class GamePane {
    private static final int NO_OF_HORSES = 16;
    private static final int NO_OF_BASE = 4;
    private static final int NO_OF_PATHS_IN_BASE = 12;

    @FXML
    private StackPane GamePane, redBase, yellowBase, greenBase, blueBase, imagePane;
    @FXML
    private StackPane blueHorseCage, yellowHorseCage, greenHorseCage, redHorseCage;
    @FXML
    private Circle i0, i1, i2, i3, i4, i5, i6, i7, i8, i9, i10, i11, i12, i13, i14, i15;

    private ImageView[] horses;
    private Circle[] paths;

    public GamePane(){
    }

    public void init(){
        horses = new ImageView[NO_OF_HORSES];
        for (int i = 0; i < NO_OF_HORSES; i++){
            horses[i] = (ImageView) imagePane.getChildren().get(i);
        }
        for (int i = 0; i < NO_OF_BASE; i++){
            StackPane base = (StackPane) GamePane.getChildren().get(i);
            for (int j = 0; j < NO_OF_PATHS_IN_BASE; j++){
                int index = (i * 12) + j;
                paths[index] = (Circle) base.getChildren().get(j);
            }
        }
        translate(-100,0);
    }

    public StackPane getGamePane(){
        return GamePane;
    }

    public void translate(@NamedArg("Translate the gamePane by x-horizontal and y vertical") double x, double y){
        GamePane.setTranslateX(x);
        GamePane.setTranslateY(y);
    }

    public void moveHorse(int horseIndex, int pathIndex, int moveCount, TranslateTransition queueTransition){
        moveCount--;
        TranslateTransition transition = new TranslateTransition(Duration.seconds(1), horses[horseIndex]);

        transition.setToX(paths[pathIndex].getTranslateX() + 10);
        transition.setToY(paths[pathIndex].getTranslateY());

        pathIndex--;
        transition.setOnFinished(e -> {
            if (queueTransition != null)
                queueTransition.play();
        });

        if (moveCount > 0)
            moveHorse(horseIndex, pathIndex, moveCount, transition);
        else
            transition.play();
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

    private double[] getInitialCoordinate(int index){
        double[] coordinate = new double[2];
        switch (index){
            case 0:
                coordinate[0] = i0.getCenterX();
                coordinate[1] = i0.getCenterY();
                break;
            case 1:
                coordinate[0] = i1.getCenterX();
                coordinate[1] = i1.getCenterY();
                break;
            case 2:
                coordinate[0] = i2.getCenterX();
                coordinate[1] = i2.getCenterY();
                break;
            case 3:
                coordinate[0] = i3.getCenterX();
                coordinate[1] = i3.getCenterY();
                break;
            case 4:
                coordinate[0] = i4.getCenterX();
                coordinate[1] = i4.getCenterY();
                break;
            case 5:
                coordinate[0] = i5.getCenterX();
                coordinate[1] = i5.getCenterY();
                break;
            case 6:
                coordinate[0] = i6.getCenterX();
                coordinate[1] = i6.getCenterY();
                break;
            case 7:
                coordinate[0] = i7.getCenterX();
                coordinate[1] = i7.getCenterY();
                break;
            case 8:
                coordinate[0] = i8.getCenterX();
                coordinate[1] = i8.getCenterY();
                break;
            case 9:
                coordinate[0] = i9.getCenterX();
                coordinate[1] = i9.getCenterY();
                break;
            case 10:
                coordinate[0] = i10.getCenterX();
                coordinate[1] = i10.getCenterY();
                break;
            case 11:
                coordinate[0] = i11.getCenterX();
                coordinate[1] = i11.getCenterY();
                break;
            case 12:
                coordinate[0] = i12.getCenterX();
                coordinate[1] = i12.getCenterY();
                break;
            case 13:
                coordinate[0] = i13.getCenterX();
                coordinate[1] = i13.getCenterY();
                break;
            case 14:
                coordinate[0] = i14.getCenterX();
                coordinate[1] = i14.getCenterY();
                break;
            case 15:
                coordinate[0] = i15.getCenterX();
                coordinate[1] = i15.getCenterY();
                break;
        }
        return coordinate;
    }

}