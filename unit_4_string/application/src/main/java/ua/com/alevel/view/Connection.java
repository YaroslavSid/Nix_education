package ua.com.alevel.view;

import ua.com.alevel.controller.ControllerReverse;

public class Connection {
    public void run(){
        ControllerReverse controllerReverse = new ControllerReverse();
        controllerReverse.read();
    }
}
