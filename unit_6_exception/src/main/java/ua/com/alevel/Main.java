package ua.com.alevel;

import ua.com.alevel.controller.Controller;
import ua.com.alevel.exception.ClassException;

public class Main {
    public static void main(String[] args) {
        Controller controller = new Controller();
        try {
            controller.run();
        } catch (ClassException e) {
            e.printStackTrace();
        }
    }
}
