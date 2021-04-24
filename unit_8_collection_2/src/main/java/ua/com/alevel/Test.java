package ua.com.alevel;


import ua.com.alevel.lib.MathSet;

import static java.lang.System.out;

public class Test {
    public static void main(String[] args) {
        out.println("Representation library by basic methods (based on <Integer>):");
        MathSet<Integer> mathSet = new MathSet<>();
        out.println("1)create list and add: 3 -4 5 5 6,1");
        mathSet.add(3);
        mathSet.add(-4);
        mathSet.add(5);
        mathSet.add(5);
        mathSet.add(6, 1);
        System.out.println("MathSet = " + mathSet);
        out.println("2) get average element and middle");
        Number average = mathSet.getAverage();
        Number middle = mathSet.getMedian();
        out.println("average = " + average + " middle = " + middle);
        out.println("3) create new MathSet, add to first through constructor and desc sort");
        MathSet<Integer> mathSetNew = new MathSet<>(mathSet);
        mathSetNew.add(965656565);
        out.println("new array = " + mathSetNew);
        out.print("sort desc = ");
        mathSetNew.sortDesc();
        out.println();
        out.println("4) get max and min value");
        out.println("min = " + mathSetNew.getMin() + " max = " + mathSetNew.getMax());
        out.println("5) sort asc and squash array from 1 to 3");
        out.print("sort asc = ");
        mathSetNew.sortAsc();
        out.println();
        out.println("squash = " + mathSetNew.squash(1, 3));


    }
}
