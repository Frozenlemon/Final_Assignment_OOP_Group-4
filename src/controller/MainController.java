package controller;

public class MainController {

    public MainController(){
        ModelController.getInstance();
        ViewController.getInstance().update();
    }
}
