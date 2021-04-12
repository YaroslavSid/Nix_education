package ua.com.alevel.controller;

import ua.com.alevel.exception.ClassException;
import ua.com.alevel.services.ConvertAndFindBetween;
import ua.com.alevel.services.DataComparator;
import ua.com.alevel.services.WorkWithDate;

import java.util.*;

public class Controller {
    public void run() throws ClassException {
        System.err.println("Attention: Application work with data which input up to minutes.\n" +
                "For add hours use <<replaceHoursToMinute>> and add minutes(Example: 5 hours = 300 minutes)\n" +
                "If data  without years or have only two numbers so used 1900 years");
        System.out.println("-------------------DataClass---------------------");
        System.out.println("Enter data:\n" +
                "(Example:16/02/2017 12:12:33)");
        String input = checkData();

        //  System.out.println(input);  Duplicate data to understand what data you entered. Uncomment if necessary!!!

        WorkWithDate workWithDate = new WorkWithDate(input);
        workWithDate.origin = this;

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
                ConvertAndFindBetween firstData = new ConvertAndFindBetween(input);
                ConvertAndFindBetween secondData = new ConvertAndFindBetween(data2);
                firstData.differenceBetweenDate(firstData, secondData);
                break;
            case 2:
                addToData(workWithDate);
                break;
            case 3:
                subtractFromData(workWithDate);
                break;
            case 4:
                compareDats(workWithDate);
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
            long milliseconds = Long.parseLong(result[5]);

            if (day > 31 || months > 12 || minute > 60 || seconds > 60 || milliseconds > 9999) {
                throw new ClassException("");
            }
            if (year <= 99) {
                year = year + 1900;
            }
            results = (day + "/" + months + "/" + year + " " + minute + ":" + seconds + ":" + milliseconds);
        } catch (Exception e) {
            System.out.println("Incorrect input. Try again");
            results = checkData();
        }
        return results;
    }

    private void addToData(WorkWithDate workWithDate) {
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
                workWithDate.addData(workWithDate, time);
                break;
            case "2":
                System.out.println("Please add years");
                int year = addTime.nextInt();
                workWithDate.addYears(workWithDate, year);
                break;
            case "3":
                System.out.println("Please add centuries");
                int centuries = addTime.nextInt();
                workWithDate.addCenturies(workWithDate, centuries);
                break;
            case "4":
                System.out.println("Enter hours which need convert");
                int hours = addTime.nextInt();
                workWithDate.replaceHoursToMinute(hours);
                addToData(workWithDate);
            default:
                System.out.println("Try again");
        }
        addTime.close();
    }


    private void subtractFromData(WorkWithDate workWithDate) throws ClassException {
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
                workWithDate.subtractMilliseconds(workWithDate, millisecond);
                break;
            case 2:
                System.out.println("Please enter second");
                int second = addTime.nextInt();
                workWithDate.subtractSeconds(workWithDate, second);
                break;
            case 3:
                System.out.println("Please enter minutes");
                int minutes = addTime.nextInt();
                workWithDate.subtractMinutes(workWithDate, minutes);
                break;
            case 4:
                System.out.println("Please enter days");
                int days = addTime.nextInt();
                workWithDate.subtractDays(workWithDate, days);
                break;
            case 5:
                System.out.println("Please enter years");
                int years = addTime.nextInt();
                workWithDate.subtractYears(workWithDate, years);
                break;
            case 6:
                System.out.println("Please enter centuries ");
                int centuries = addTime.nextInt();
                workWithDate.subtractCenturies(workWithDate, centuries);
                break;
            case 7:
                System.out.println("Enter hours which need convert");
                int hours = addTime.nextInt();
                workWithDate.replaceHoursToMinute(hours);
                subtractFromData(workWithDate);
                break;
        }
        addTime.close();
    }

    private void compareDats(WorkWithDate work) {
        boolean variant = true;
        System.out.println("Enter count  for compare <take into account the date that was before>:\n" +
                "(Example: 3)");
        Scanner enter = new Scanner(System.in);
        int count = enter.nextInt();
        System.out.println("Please add new data");
        List<WorkWithDate> dates = new ArrayList<>();
        dates.add(work);
        for (int i = 0; i < count; i++) {
            String string = checkData();
            WorkWithDate workWithDate = new WorkWithDate(string);
            dates.add(workWithDate);
        }

        DataComparator asc = new DataComparator();
        dates.sort(asc);
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