package ua.com.alevel.services;


import lombok.Getter;
import ua.com.alevel.controller.Controller;
import ua.com.alevel.exception.ClassException;

import java.util.Scanner;

@Getter
public class AddAndSubtractDate {
    static long COUNT_DAYS_IN_LEAP_YEAR = 366;
    static long COUNT_DAYS_IN_YEAR = 365;
    static long COUNT_MS_IN_DAY = 86400000L;
    static long COUNT_MS_IN_HOUR = 3600000L;
    static long COUNT_MS_IN_MINUTE = 60000L;
    static long COUNT_MS_IN_SECOND = 1000L;
    //-------------------------------------
    long days;
    long months;
    long years;
    long second;
    long minute;
    long millisecond;
    long hours = 0;

    public Controller origin;

    public AddAndSubtractDate(String inputData) {
        String[] input = inputData.split("[/ :]");
        days = Integer.parseInt(input[0]);
        months = Integer.parseInt(input[1]);
        years = Integer.parseInt(input[2]);

        minute = Integer.parseInt(input[3]);
        second = Integer.parseInt(input[4]);
        millisecond = Integer.parseInt(input[5]);
    }

    @Override
    public String toString() {
        return days + "/" + months + "/" + years + " " + minute + ":" + second + "." + millisecond;
    }


    public void addData(AddAndSubtractDate addAndSubtractDate, String time) {

        long minuteForAdd;
        long secondForAdd;
        long millisecondForAdd;
        long dayForAdd = 0;

       try {
           String[] input = time.split("[:]");
           minuteForAdd = Integer.parseInt(input[0]);
           secondForAdd = Integer.parseInt(input[1]);
           millisecondForAdd = Integer.parseInt(input[2]);

           long millisecond = addAndSubtractDate.millisecond + millisecondForAdd;
           while (millisecond > 999) {
               millisecond = millisecond - 1000;
               secondForAdd++;
           }
           long seconds = addAndSubtractDate.second + secondForAdd;
           while (seconds > 59) {
               seconds = seconds - 60;
               minuteForAdd++;
           }
           long minutes = addAndSubtractDate.minute + minuteForAdd;
           while (minutes > 59) {
               minutes = minutes - 60;
               hours++;
           }

           while (hours > 24) {
               hours = hours - 24;
               dayForAdd++;
           }
           AddDays date = new AddDays((int) addAndSubtractDate.months, (int) addAndSubtractDate.days, (int) addAndSubtractDate.years);
           date.addDaysMethods((int) dayForAdd);

           int[] fields = date.getAddDays();
           addAndSubtractDate.months = fields[0];
           addAndSubtractDate.years = fields[2];
           addAndSubtractDate.days = fields[1];
           addAndSubtractDate.second = seconds;
           addAndSubtractDate.minute = minutes;
           addAndSubtractDate.millisecond = millisecond;
           addAndSubtractDate.output();
       }catch (Exception e){
           System.out.println("Check if the input is correct");
       }
    }

    public void replaceHoursToMinute(long hours) {
        System.out.println("Minute = " + hours * 60);
    }

    public void addYears(AddAndSubtractDate addAndSubtractDate, long years) {
        addAndSubtractDate.years = addAndSubtractDate.years + years;
        addAndSubtractDate.output();
    }

    public void addCenturies(AddAndSubtractDate addAndSubtractDate, long centuries) {
        centuries = centuries * 100;
        addAndSubtractDate.years = addAndSubtractDate.years + centuries;
        addAndSubtractDate.output();
    }

    //-------------------------------------------------------------------------------


    public void subtractMilliseconds(AddAndSubtractDate addAndSubtractDate, long milliseconds) throws ClassException {
        if (addAndSubtractDate.millisecond - milliseconds >= 0) {
            addAndSubtractDate.millisecond -= milliseconds;
            addAndSubtractDate.output();
        } else {
            throw new ClassException("Can't contain negative milliseconds");
        }
    }

    public void subtractSeconds(AddAndSubtractDate addAndSubtractDate, long seconds) throws ClassException {
        if (addAndSubtractDate.second - seconds >= 0) {
            addAndSubtractDate.second = addAndSubtractDate.second - seconds;
            addAndSubtractDate.output();
        } else {
            throw new ClassException("Can't contain negative seconds");
        }
    }

    public void subtractMinutes(AddAndSubtractDate addAndSubtractDate, long minutes) throws ClassException {
        if (addAndSubtractDate.minute - minutes >= 0) {
            addAndSubtractDate.minute = addAndSubtractDate.minute - minutes;
            addAndSubtractDate.output();
        } else {
            throw new ClassException("Can't contain negative minutes");
        }
    }

    public void subtractDays(AddAndSubtractDate addAndSubtractDate, long day) throws ClassException {
        try {
            if (addAndSubtractDate.days - day >= 0) {
                addAndSubtractDate.days = addAndSubtractDate.days - day;
                addAndSubtractDate.output();
            } else {
                throw new ClassException("Can't contain negative days. Try again");
            }
        } catch (ClassException e) {
            System.out.println(e.getMessage());
            System.out.println();
            Controller controller = new Controller();
            controller.run();
        }

    }

    public void subtractYears(AddAndSubtractDate addAndSubtractDate, long year) throws ClassException {
        if (addAndSubtractDate.years - year >= 0) {
            addAndSubtractDate.years = addAndSubtractDate.years - year;
            addAndSubtractDate.output();
        } else {
            throw new ClassException("Can't contain negative years");
        }

    }

    public void subtractCenturies(AddAndSubtractDate addAndSubtractDate, long centuries) throws ClassException {
        centuries = centuries * 100;
        subtractYears(addAndSubtractDate, centuries);
    }


    public void output() {
        String[] monthsNames = {"January", "February", "March", "April", "May", "June", "July",
                "August", "September", "October", "November", "December"};

        System.out.println("Possible date output formats:");
        System.out.println(
                "1) dd/mm/yy - 01/12/21\n" +
                        "2) m/d/yyyy - 3/4/2021\n" +
                        "3) mmm-d-yy - March 4 21\n" +
                        "4) dd-mmm-yyyy 00:00 - 09 April 1789 45:23\n");
        System.out.print("Enter desired date in one of the formats above: ");
        Scanner scanner = new Scanner(System.in);
        int choose = scanner.nextInt();
        System.out.println();

        switch (choose) {
            case 1:
                System.out.println(days + "/" + months + "/" + years % 100);
                break;
            case 2:
                System.out.println(months + "/" + days + "/" + years);
                break;
            case 3:
                System.out.println(monthsNames[(int) months - 1] + " " + days +
                        " " + years % 100);
                break;
            case 4:
                if (days < 10) {
                    System.out.print("0");
                }
                System.out.print(days + " ");
                System.out.print(monthsNames[(int) months - 1] + " " + years + " ");
                if (hours < 10) {
                    System.out.print("0");
                }
                System.out.print(hours + ":");
                if (minute < 10) {
                    System.out.print("0");
                }
                System.out.println(minute);
                break;
        }
        System.out.println();
        System.out.println("-----------------------");
        System.out.println("Return to menu: + \nExit: -");
        String menu = scanner.next();
        if ("+".equals(menu)) {
            try {
                origin.run();
            } catch (ClassException e) {
                e.printStackTrace();
            }
        } else System.exit(0);
        scanner.close();
    }

}
