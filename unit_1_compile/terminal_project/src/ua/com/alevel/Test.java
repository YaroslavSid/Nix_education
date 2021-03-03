package ua.com.alevel;

import ua.com.alevel.main.PartOne;
import ua.com.alevel.main.PartTwo;

public class Test {
    public static void main(String[] args) {
        PartOne partOne = new PartOne();
        PartTwo partTwo = new PartTwo();
        System.out.println(partOne.world() +
                partTwo.terminal());
    }
}
