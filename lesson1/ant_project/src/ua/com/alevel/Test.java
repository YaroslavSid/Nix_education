package ua.com.alevel;

import ua.com.alevel.main.AntOne;
import ua.com.alevel.main.AntTwo;

public class Test {
    public static void main(String[] args) {
        AntTwo antTwo = new AntTwo();
        AntOne antOne = new AntOne();
        System.out.println(antTwo.ant() + antOne.ant());
    }
}
