package ua.com.alevel;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class OccurrenceOfCharacter {
    public static void main(String[] args) {
        String line;
        boolean exit = true;

        System.out.println("/Task that find all characters,sort them and determines the " +
                " number of  occurrences of each character/");
        System.out.println("Enter data: ");

        HashMap<String, Integer> hashMap = new HashMap<>();
        Scanner scanner = new Scanner(System.in);

        while (exit) {
            line = scanner.nextLine();
            if (line.trim().isEmpty()) {
                System.err.println("The line is  empty! Try again ");
            } else {
                Pattern pattern = Pattern.compile("[a-zA-Z]");
                Matcher matcher = pattern.matcher(line);

                while (matcher.find()) {
                    hashMap.merge(matcher.group(), 1, Integer::sum);
                }
                System.out.println("Answer: ");
                SortedSet<String> keys = new TreeSet<>(hashMap.keySet());
                for (String key : keys) {
                    Integer value = hashMap.get(key);
                    System.out.println(key + "=" + value);
                }
                exit = false;
            }
        }
    }
}
