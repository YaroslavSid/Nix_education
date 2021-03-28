package ua.com.alevel.service.level_1;

import ua.com.alevel.controller.TaskSelectController;

import java.util.Scanner;

public class AreaOfTriangle {
    /*
    For check

    public static void main(String[] args) {
        AreaOfTriangle area = new AreaOfTriangle();
        area.triangle();
    }

     */
    public void triangle() {
        System.out.println("Calculate the area of a triangle over three sides");
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter side A : ");
        double a = scanner.nextDouble();
        System.out.println("Enter side B : ");
        double b = scanner.nextDouble();
        System.out.println("Enter side C : ");
        double c = scanner.nextDouble();
        System.out.printf("Area of a triangle = %.2f", geroneSquare(a, b, c));
        System.out.println();
        System.out.println("-----------------------");
        System.out.println("Return to menu: + \nExit: -");
        String menu = scanner.next();
        if ("+".equals(menu)){
            TaskSelectController task = new TaskSelectController();
            task.menu();
        }else System.exit(0);
        scanner.close();
    }

    private double geroneSquare(double sideA, double sideB, double sideC) {
        double p = (sideA + sideB + sideC) / 2;
        return Math.sqrt(p * (p - sideA) * (p - sideB) * (p - sideC));
    }
}
