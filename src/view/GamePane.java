package view;

import controller.ViewController;
import javafx.animation.TranslateTransition;
import javafx.beans.NamedArg;
import javafx.fxml.FXML;
import javafx.scene.effect.Bloom;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

public class GamePane {
    private static final int NO_OF_HORSES = 16;
    private static final int NO_OF_PATHS = 48;
    private static final int ANIMATION_DURATION = 100;

    @FXML
    private StackPane gamePane, imagePane, circleBase, cages;
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
        translatePane(gamePane,-100, 0);
    }

    public StackPane getGamePane(){
        return gamePane;
    }

    public void translatePane(@NamedArg("Translate the gamePane by x-horizontal and y vertical") StackPane pane, double x, double y){
        pane.setTranslateX(x);
        pane.setTranslateY(y);
    }
    public void moveHorse(int horseIndex, int pathIndex, int moveCount, TranslateTransition queueTransition){
        moveCount--;
        TranslateTransition transition = new TranslateTransition(Duration.millis(ANIMATION_DURATION), horses[horseIndex]);

        transition.setToX(paths[pathIndex].getTranslateX() + 110);
        transition.setToY(paths[pathIndex].getTranslateY() - 10);

        pathIndex--;
        if(pathIndex == -1)
            pathIndex = 47;

        if (queueTransition == null)
            transition.setOnFinished(e -> ViewController.getInstance().finishAnimation());
        else
            transition.setOnFinished(e -> queueTransition.play());

        if (moveCount > 0)
            moveHorse(horseIndex, pathIndex, moveCount, transition);
        else {
            ViewController.getInstance().addAnimation();
            transition.play();
        }
    }

    public void moveHorseToHome(int horseIndex, int moveCount, TranslateTransition queueTransition){
        moveCount--;
        TranslateTransition transition = new TranslateTransition(Duration.millis(ANIMATION_DURATION), horses[horseIndex]);
        int id = ((horseIndex/4) * 6) + moveCount;
        double[] coordinate = new double[2];

        coordinate[0] =  cages.getChildren().get(id).getTranslateX();
        coordinate[1] =  cages.getChildren().get(id).getTranslateY();
        transition.setToX(coordinate[0] + 100);
        transition.setToY(coordinate[1]);

        if (queueTransition != null)
            transition.setOnFinished(e -> queueTransition.play());
        else
            transition.setOnFinished(e -> ViewController.getInstance().finishAnimation());

        if (moveCount > 0)
            moveHorseToHome(horseIndex, moveCount, transition);
        else {
            ViewController.getInstance().addAnimation();
            transition.play();
        }
    }

    public TranslateTransition kickHorseAnimation(int horseIndex){
        TranslateTransition translateTransition = new TranslateTransition(Duration.millis(ANIMATION_DURATION), horses[horseIndex]);
        double[] coordinate = getInitialCoordinate(horseIndex);
        translateTransition.setToX(coordinate[0]);
        translateTransition.setToY(coordinate[1]);
        translateTransition.setOnFinished(e -> ViewController.getInstance().finishAnimation());
        return translateTransition;
    }

    public void change_Horse_Highlight(@NamedArg("Horse Index") int horseIndex){
        if (horses[horseIndex].getEffect() == null) {
            Bloom effect = new javafx.scene.effect.Bloom();
            effect.setThreshold(0.1);
            horses[horseIndex].setEffect(effect);

        }
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
                coordinate[0] = i0.getTranslateX();
                coordinate[1] = i0.getTranslateY();
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
        coordinate[0] += 100;
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