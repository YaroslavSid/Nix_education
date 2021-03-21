package ua.com.alevel.controller;

import ua.com.alevel.service.DufServiceReverse;

import java.util.Scanner;

public class ControllerReverse {

    public void read() {
        String introduce = "METHODS <<REVERSE>>";
        char[] arr = introduce.toCharArray();
        for (char c : arr) {
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.print(c);
        }

        System.out.println();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the string");
        String line = scanner.nextLine();
        DufServiceReverse dufServiceReverse = new DufServiceReverse();
        dufServiceReverse.choose(line);
        scanner.close();
    }
}
