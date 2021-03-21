package ua.com.alevel.service;

import ua.com.alevel.Reverse;

import java.util.Scanner;

public class DufServiceReverse implements ServiceReverse {

    @Override
    public void choose(String line) {
        System.out.println("Choose methods implementation");
        System.out.println("1 - normal reverse");
        System.out.println("2 - reverse on the specified substring in the string");
        System.out.println("3 - the user can specify from which index to start and  which to end");
        Scanner scanner = new Scanner(System.in);
        String choose = scanner.next();
        switch (choose) {
            case "1":
                System.out.println("Your revers: " + Reverse.reverse(line));
                break;
            case "2":
                System.out.println("Enter your substring: ");
                Scanner sc = new Scanner(System.in);
                String subs = sc.nextLine();
                System.out.println("Your reverse: " + Reverse.reverse(line, subs));
                sc.close();
                break;
            case "3":
                System.out.print("Enter first index: ");
                int firstIndex = scanner.nextInt();
                System.out.print("Enter second index: ");
                int secondIndex = scanner.nextInt();
                System.out.println("Your reverse: " + Reverse.reverse(line, firstIndex, secondIndex));
                break;
            default:
                System.out.println("There are not such operation");
        }
        scanner.close();
    }
}
