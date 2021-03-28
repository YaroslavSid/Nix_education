package ua.com.alevel.service.level_1;

import ua.com.alevel.controller.TaskSelectController;

import java.util.Scanner;

public class UniqueElement {
    /*
    For check

    public static void main(String[] args) {
        UniqueElement element = new UniqueElement();
        element.enterForFindUnique();
    }
     */

    private static int[] array;
    private static int flag = 0;

    public void enterForFindUnique() {
        UniqueElement uniqueElement = new UniqueElement();
        int size;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the size of array(Example: 5):");
        size = scanner.nextInt();
        System.out.println("Enter the elements so that the array is completely filled: \n " +
                "(Example: size = 5 -> filling 1 3 3 4 1)");
        array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = scanner.nextInt();
        }
        uniqueElement.findUnique();
        System.out.println("-----------------------");
        System.out.println("Return to menu: + \nExit: -");
        String menu = scanner.next();
        if ("+".equals(menu)){
            TaskSelectController task = new TaskSelectController();
            task.menu();
        }else System.exit(0);
        scanner.close();

    }

    private void findUnique() {
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length; j++) {
                if (i != j) {
                    if (array[i] != array[j]) {
                        flag = 1;
                    } else {
                        flag = 0;
                        break;
                    }
                }
            }
            if (flag == 1) {
                System.out.println("The unique element = " + array[i]);
            }
        }
    }
}
