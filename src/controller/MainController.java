package controller;

public class MainController {

    public MainController(){
        //SoundController.getInstance();
        ModelController.getInstance();
        ViewController.getInstance().update();

    }


}
