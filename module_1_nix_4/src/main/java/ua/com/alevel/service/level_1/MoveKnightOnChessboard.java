package ua.com.alevel.service.level_1;

import ua.com.alevel.controller.TaskSelectController;

import java.util.Scanner;

public class MoveKnightOnChessboard {
    /*
    For check

    public static void main(String[] args) {
        MoveKnightOnChessboard chessboard = new MoveKnightOnChessboard();
        chessboard.stepKnight();
    }
     */

    public void stepKnight() {
        board();
        Scanner inputUser = new Scanner(System.in);
        System.out.println("Knight's move in the format \\ b1-c3 \\ or \\ x \\ to exit");
        String currPos;
        String nextPos;
        String move = inputUser.next();
        currPos = move.split("-")[0].toLowerCase();
        nextPos = move.split("-")[1].toLowerCase();
        if (isRightMove(currPos.charAt(0), Character.digit(currPos.charAt(1), 10),
                nextPos.charAt(0), Character.digit(nextPos.charAt(1), 10)))
            System.out.println("The move is correct");
        else System.out.println("So the horse does not walk!");
        System.out.println("-----------------------");
        System.out.println("Return to menu: + \nExit: -");
        String menu = inputUser.next();
        if ("+".equals(menu)){
            TaskSelectController task = new TaskSelectController();
            task.menu();
        }else System.exit(0);
        inputUser.close();
    }

    private boolean isRightPosition(char letter, int num) {
        return (letter >= 'a' && letter <= 'h') && (num >= 1 && num <= 8);
    }

    private boolean isRightMove(char letCurrPos, int numCurrPos, char letNewPos, int numNewPos) {
        return (isRightPosition(letCurrPos, numCurrPos) ||
                isRightPosition(letNewPos, numNewPos)) &&
                Math.abs(letCurrPos - letNewPos) + Math.abs(numCurrPos - numNewPos) == 3;
    }

    private void board() {
        StringBuilder brdStr = new StringBuilder();
        brdStr.append("  a b c d e f g h\n");
        for (int i = 1; i <= 8; i++) {
            brdStr.append(i);
            for (int c = 0; c < 8; c++) {
                brdStr.append(" .");
            }
            brdStr.append(" ").append(i).append("\n");
        }
        brdStr.append("  a b c d e f g h\n");
        System.out.println(brdStr);
    }
}