package ua.com.alevel.controller;

import ua.com.alevel.exception.ClassException;
import ua.com.alevel.services.ConvertAndFindDifferentBetweenThem;
import ua.com.alevel.services.DataComparator;
import ua.com.alevel.services.AddAndSubtractDate;

import java.util.*;

public class Controller {
    public void run() throws ClassException {
        System.err.println("Attention: Application work with data which input up to minutes.\n" +
                "For add hours use <<replaceHoursToMinute>> and add minutes(Example: 5 hours = 300 minutes)\n" +
                "If data  without years or have only two numbers so used 1900 years\n" +
                "The program works with the format: days/months/years minutes:seconds:milliseconds");
        System.out.println("-------------------DataClass---------------------");
        System.out.println("Enter data:\n" +
                "(Example:16/02/2017 12:12:33 , 1/May/20 , /May/2020 12:12)");
        String input = checkData();

        //  System.out.println(input);  Duplicate data to understand what data you entered. Uncomment if necessary!!!

        AddAndSubtractDate addAndSubtractDate = new AddAndSubtractDate(input);
        addAndSubtractDate.origin = this;

        System.out.println();
        System.out.println("What you want to doing with data?\n" +
                "1) Different between other data\n" +
                "2) Add to data\n" +
                "3) Subtract from data\n" +
                "4) Compare data");
        Scanner scanner = new Scanner(System.in);
        int choose = scanner.nextInt();
        switch (choose) {
            case 1:
                System.out.println("Write the second date for difference");
                String data2 = checkData();
                ConvertAndFindDifferentBetweenThem firstData = new ConvertAndFindDifferentBetweenThem(input);
                ConvertAndFindDifferentBetweenThem secondData = new ConvertAndFindDifferentBetweenThem(data2);
                firstData.differenceBetweenDate(firstData, secondData);
                break;
            case 2:
                addToData(addAndSubtractDate);
                break;
            case 3:
                subtractFromData(addAndSubtractDate);
                break;
            case 4:
                compareDats(addAndSubtractDate);
                break;
            default:
                System.out.println("Choose any variant");
                Controller controller = new Controller();
                controller.run();
        }
        scanner.close();
    }

    private String checkData() {
        String results;
        try {
            Scanner sc = new Scanner(System.in);
            String s = sc.nextLine();
            String[] sar = s.split("[/:\\s]");
            String[] result = new String[6];
            String nameMonth;

            for (int i = 0; i < sar.length; i++) {
                if (sar[i].equals("")) {
                    sar[i] = "1";
                }
                result[i] = sar[i];
            }



            for (int i = 0; i < result.length; i++) {
                if (result[i] == null) {
                    result[i] = "0";
                }
            }
            if (result[0].equals("0")) {
                result[0] = "1";
            }
            if (result[1].equals("0")) {
                result[1] = "1";
            }

            nameMonth = result[1];
            if (nameMonth.equals("January")) {
                result[1] = "1";
            }
            if (nameMonth.equals("February")) {
                result[1] = "2";
            }
            if (nameMonth.equals("March")) {
                result[1] = "3";
            }
            if (nameMonth.equals("April")) {
                result[1] = "4";
            }
            if (nameMonth.equals("May")) {
                result[1] = "5";
            }
            if (nameMonth.equals("June")) {
                result[1] = "6";
            }
            if (nameMonth.equals("July")) {
                result[1] = "7";
            }
            if (nameMonth.equals("August")) {
                result[1] = "8";
            }
            if (nameMonth.equals("September")) {
                result[1] = "9";
            }
            if (nameMonth.equals("October")) {
                result[1] = "10";
            }
            if (nameMonth.equals("November")) {
                result[1] = "11";
            }
            if (nameMonth.equals("December")) {
                result[1] = "12";
            }

            for (String r : result) {
                long e = Long.parseLong(r);
                if (e < 0) {
                    throw new ClassException("");
                }
            }
            long day = Long.parseLong(result[0]);
            long months = Long.parseLong(result[1]);
            long year = Long.parseLong(result[2]);
            long minute = Long.parseLong(result[3]);
            long seconds = Long.parseLong(result[4]);
            long miliseconds = Long.parseLong(result[5]);

            if (day > 31 || months > 12 || minute > 60 || seconds > 60 || miliseconds > 9999) {
                throw new ClassException("");
            }
            if (year <= 99) {
                year = year + 1900;
            }
            results = (day + "/" + months + "/" + year + " " + minute + ":" + seconds + ":" + miliseconds);
        } catch (Exception e) {
            System.out.println("Incorrect input");
            results = checkData();
        }
        return results;
    }

    private void addToData(AddAndSubtractDate addAndSubtractDate) {
        System.out.println("Choose what you want to add\n" +
                "1)Add time\n" +
                "2)Add years\n" +
                "3)Add centuries\n" +
                "4)If you decide to add hour please convert them to minute");
        Scanner addTime = new Scanner(System.in);
        String choose = addTime.nextLine();
        switch (choose) {
            case "1":
                System.out.println("Please add time:\n(Example: 54:10:10)");
                String time = addTime.nextLine();
                addAndSubtractDate.addData(addAndSubtractDate, time);
                break;
            case "2":
                System.out.println("Please add years");
                int year = addTime.nextInt();
                addAndSubtractDate.addYears(addAndSubtractDate, year);
                break;
            case "3":
                System.out.println("Please add centuries");
                int centuries = addTime.nextInt();
                addAndSubtractDate.addCenturies(addAndSubtractDate, centuries);
                break;
            case "4":
                System.out.println("Enter hours which need convert");
                int hours = addTime.nextInt();
                addAndSubtractDate.replaceHoursToMinute(hours);
                addToData(addAndSubtractDate);
            default:
                System.out.println("Try again");
        }
        addTime.close();
    }


    private void subtractFromData(AddAndSubtractDate addAndSubtractDate) throws ClassException {
        System.out.println("Choose what you want to subtract\n" +
                "1)Subtract milliseconds\n" +
                "2)Subtract seconds\n" +
                "3)Subtract minutes\n" +
                "4)Subtract days\n" +
                "5)Subtract years\n" +
                "6)Subtract centuries\n" +
                "7)If you decide to add hour please convert them to minute");
        Scanner addTime = new Scanner(System.in);
        int choose = addTime.nextInt();
        switch (choose) {
            case 1:
                System.out.println("Please enter millisecond");
                int millisecond = addTime.nextInt();
                addAndSubtractDate.subtractMilliseconds(addAndSubtractDate, millisecond);
                break;
            case 2:
                System.out.println("Please enter second");
                int second = addTime.nextInt();
                addAndSubtractDate.subtractSeconds(addAndSubtractDate, second);
                break;
            case 3:
                System.out.println("Please enter minutes");
                int minutes = addTime.nextInt();
                addAndSubtractDate.subtractMinutes(addAndSubtractDate, minutes);
                break;
            case 4:
                System.out.println("Please enter days");
                int days = addTime.nextInt();
                addAndSubtractDate.subtractDays(addAndSubtractDate, days);
                break;
            case 5:
                System.out.println("Please enter years");
                int years = addTime.nextInt();
                addAndSubtractDate.subtractYears(addAndSubtractDate, years);
                break;
            case 6:
                System.out.println("Please enter centuries ");
                int centuries = addTime.nextInt();
                addAndSubtractDate.subtractCenturies(addAndSubtractDate, centuries);
                break;
            case 7:
                System.out.println("Enter hours which need convert");
                int hours = addTime.nextInt();
                addAndSubtractDate.replaceHoursToMinute(hours);
                subtractFromData(addAndSubtractDate);
                break;
        }
        addTime.close();
    }

    private void compareDats(AddAndSubtractDate work) {
        boolean variant = true;
        System.out.println("Enter count  for compare <take into account the date that was before>:\n" +
                "(Example: 3)");
        Scanner enter = new Scanner(System.in);
        int count = enter.nextInt();
        System.out.println("Please add new data");
        List<AddAndSubtractDate> dates = new ArrayList<>();
        dates.add(work);
        for (int i = 0; i < count; i++) {
            String string = checkData();
            AddAndSubtractDate addAndSubtractDate = new AddAndSubtractDate(string);
            dates.add(addAndSubtractDate);
        }

        DataComparator asc = new DataComparator();
        while (variant) {
            System.out.println("Choose type output:\n" +
                    "1)ascending\n" +
                    "2)descending\n" +
                    "0)Exit");
            Scanner scanner = new Scanner(System.in);
            int choose = scanner.nextInt();
            switch (choose) {
                case 0:
                    variant = false;
                    break;
                case 1:
                    dates.sort(asc);
                    System.out.println("Sorted dates asc:\n" + dates + "\n");
                    break;
                case 2:
                    dates.sort(asc.reversed());
                    System.out.println("Sorted dates desc:\n" + dates + "\n");
                    break;
            }
        }
        System.out.println();
        System.out.println("-----------------------");
        System.out.println("Return to menu: + \nExit: -");
        Scanner scanner = new Scanner(System.in);
        String menu = scanner.next();
        if ("+".equals(menu)) {
            try {
                run();
            } catch (ClassException e) {
                e.printStackTrace();
            }
        } else System.exit(0);
        scanner.close();
    }

}
