package ua.com.alevel;

import ua.com.alevel.main.AntOne;
import ua.com.alevel.main.AntTwo;

public class Test {
    public static void main(String[] args) {
        AntOne antOne = new AntOne();
        AntTwo antTwo =new AntTwo();
        System.out.println(antOne.world() + antTwo.ant());
    }
}
