package ua.com.alevel.view;

import ua.com.alevel.controller.TaskSelectController;

public class Connection {
    public void start (){
        TaskSelectController controller = new TaskSelectController();
        controller.menu();
    }

}
