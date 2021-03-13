package ua.com.alevel;

import java.util.ArrayList;
import java.util.Scanner;

public class LessonTime {
    public static void main(String[] args) {
        System.out.println("/Task to find out the end of the  lesson/");
        System.out.println("(Maximum lessons per day = 10) Enter lesson number: ");
        Scanner scanner = new Scanner(System.in);
        String lesson = scanner.next();

        ArrayList<String> time = new ArrayList<>();
        time.add("9:45");
        time.add("10:35");
        time.add("11:35");
        time.add("12:25");
        time.add("13:25");
        time.add("14:15");
        time.add("15:15");
        time.add("16:05");
        time.add("17:05");
        time.add("17:55");
        try {
            System.out.println("This lesson ends at = " + time.get(Integer.parseInt(lesson) - 1 ) );
        } catch (IndexOutOfBoundsException index) {
            System.out.println("Lessons are over");
        }
    }
}
