package ua.com.alevel.controller;

import ua.com.alevel.service.level_1.AreaOfTriangle;
import ua.com.alevel.service.level_1.MoveKnightOnChessboard;
import ua.com.alevel.service.level_1.UniqueElement;
import ua.com.alevel.service.level_2.ValidString;
import ua.com.alevel.service.level_3.GameOfLive;

import java.util.Scanner;

public class TaskSelectController {
    public void  menu () {
        System.out.println("What task do you want to check?\n"
        +"First level -> 1.1 - Unique Element\n"
                +"               1.2 - Move Knight on Chessboard\n" +
                "               1.3 - Area of Triangle");
        System.out.println("Second level -> 2 - Valid String\n" +
                "Third level  -> 3 - Game of Life");
        System.out.println("Choose variant:");
        Scanner scanner = new Scanner(System.in);
        String choice = scanner.next();

        switch (choice){
            case "1.1":
                UniqueElement element = new UniqueElement();
                element.enterForFindUnique();
                break;
            case "1.2":
                MoveKnightOnChessboard chessboard = new MoveKnightOnChessboard();
                chessboard.stepKnight();
                break;
            case "1.3":
                AreaOfTriangle area = new AreaOfTriangle();
                area.triangle();
                break;
            case "2":
                ValidString validString = new ValidString();
                validString.enterString();
                break;
            case "3":
                GameOfLive game = new GameOfLive();
                game.generation();
                break;
            default:
                System.out.println("Choose from the suggested");
        }
    }
}
