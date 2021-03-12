package ua.com.alevel;

import java.util.Scanner;

public class Work {
    private boolean exit = true;
    private int sum = 0;
    private int answer;


    public void inputValidation() {
        String data;

        Scanner scanner = new Scanner(System.in);
        System.out.println("/Task that find all numbers and sums them up/");
        System.out.println("Enter data: ");

        while (exit) {
            data = scanner.nextLine();
            if (data.trim().isEmpty()) {
                System.err.println("The string is  empty! Try again ");
            } else {
                answer = findAndSum(data);
                exit = false;
            }
        }
        System.out.println("----------------------------");
        System.out.println("Answer = " + answer+ ";");
    }

    private int findAndSum(String data) {
        String dataReserve = data.trim();

        for (int i = 0; i < dataReserve.length(); i++) {
            char cha = dataReserve.charAt(i);
            if (cha >= '0' && cha <= '9') {
                int numeral = Character.getNumericValue(cha);
                sum = sum + numeral;
            }
        }
        return sum;
    }
}
