import controller.MainController;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage){
        MainController controller = MainController.getInstance();
        controller.update();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
