package ua.com.alevel.impl;

import ua.com.alevel.ConsoleService;
import ua.com.alevel.impl.helpforimpl.Implementation;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DefaultConsoleService implements ConsoleService {
    int first;
    int second;
    String c;

    @Override
    public Implementation check() {

        System.out.println("Enter expression (for example 2+2): ");
        Scanner scanner = new Scanner(System.in);
        String x = scanner.nextLine();


        Pattern pattern = Pattern.compile("(\\d)+");
        Matcher p = pattern.matcher(x);

        if (p.find()) {
            String a = p.group();
            first = Integer.parseInt(a);
        }
        if (p.find()) {
            String b = p.group();
            second = Integer.parseInt(b);
        }

        Pattern pattern1 = Pattern.compile("(?=\\s)*([+\\-*/])(?=\\s)*");
        Matcher d = pattern1.matcher(x);
        if (d.find()) {
            c = d.group();
        } else {
            System.err.println("Operator not found");
        }
        return new Implementation(first, c, second);
    }
}
