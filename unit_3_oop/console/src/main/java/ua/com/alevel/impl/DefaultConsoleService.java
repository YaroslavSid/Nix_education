package ua.com.alevel.impl;

import ua.com.alevel.ConsoleService;
import ua.com.alevel.impl.helpforimpl.Implementation;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DefaultConsoleService implements ConsoleService {
    String first;
    String second;
    String condition;

    @Override
    public Implementation check() {

        System.out.println("Enter expression (for example 2+2): ");
        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();

        Pattern pattern = Pattern.compile("(\\d)+");
        Matcher matcher = pattern.matcher(line);

        if (matcher.find()) {
            first = matcher.group();
        }
        if (matcher.find()) {
            second = matcher.group();
        }

        Pattern pattern1 = Pattern.compile("(?=\\s)*([+\\-*/])(?=\\s)*");
        Matcher matcherCondition = pattern1.matcher(line);
        if (matcherCondition.find()) {
            condition = matcherCondition.group();
        } else {
            System.err.println("Operator not found");
        }
        return new Implementation(first, condition, second);
    }
}
