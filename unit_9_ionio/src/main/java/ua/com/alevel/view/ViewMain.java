package ua.com.alevel.view;

import ua.com.alevel.controller.AuthorController;
import ua.com.alevel.controller.BookController;

import java.io.IOException;
import java.util.Scanner;

public class ViewMain {
    public static void start() {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Base for create:\n1) Authors\n2) Books");
            System.out.println("Select base:");
            String choose = scanner.next();
            switch (choose) {
                case "1":
                    AuthorController authorController = new AuthorController();
                    try {
                        authorController.run();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                case "2":
                    BookController bookController = new BookController();
                    try {
                        bookController.run();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
            }
        }
    }
}
