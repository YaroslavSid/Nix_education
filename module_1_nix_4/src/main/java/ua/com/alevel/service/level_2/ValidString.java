package ua.com.alevel.service.level_2;

import ua.com.alevel.controller.TaskSelectController;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Scanner;

public class ValidString {
    /*
    For check
 public static void main(String[] args) {
        ValidString validString = new ValidString();
        validString.enterString();
    }

     */
    public void enterString() {
        try (Scanner in = new Scanner(System.in)) {
            System.out.println("Check line for characters such as :'(' ')'  '{' '}'  '[' ']'");
            System.out.println("Please enter a sequence of brackets:");
            String s = in.nextLine();
            s = s.replaceAll("\\s+", "");
            if (valid(s)) {
                System.out.println(" Yes");
            } else {
                System.out.println(" No");
            }
            System.out.println("-----------------------");
            System.out.println("Return to menu: + \nExit: -");
            String menu = in.next();
            if ("+".equals(menu)) {
                TaskSelectController task = new TaskSelectController();
                task.menu();
            } else System.exit(0);
        }
    }

    private static boolean valid(String s) {
        Deque<Integer> sq = new LinkedList<>();
        for (char c : s.toCharArray()) {
            int i = "{[(}])".indexOf(c);
            if (i >= 0) {
                if (i >= 3) {
                    if ((sq.size() == 0) || (sq.poll() != i - 3)) {
                        return false;
                    }
                } else {
                    sq.push(i);
                }
            }
        }
        return sq.isEmpty();
    }
}




