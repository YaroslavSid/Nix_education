package ua.com.alevel.services;


import lombok.Getter;
import ua.com.alevel.controller.Controller;
import ua.com.alevel.exception.ClassException;

import java.util.Scanner;

@Getter
public class WorkWithDate implements Comparable<WorkWithDate> {
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

    public WorkWithDate(String inputData) {
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


    //-------------------------------------------------------------------------------------------------------------


    public void addData(WorkWithDate workWithDate, String time) {
        long minuteForAdd;
        long secondForAdd;
        long millisecondForAdd;
        long dayForAdd = 0;


        String[] input = time.split("[:]");
        minuteForAdd = Integer.parseInt(input[0]);
        secondForAdd = Integer.parseInt(input[1]);
        millisecondForAdd = Integer.parseInt(input[2]);

        long millisecond = workWithDate.millisecond + millisecondForAdd;
        while (millisecond > 999) {
            millisecond = millisecond - 1000;
            secondForAdd++;
        }
        long seconds = workWithDate.second + secondForAdd;
        while (seconds > 59) {
            seconds = seconds - 60;
            minuteForAdd++;
        }
        long minutes = workWithDate.minute + minuteForAdd;
        while (minutes > 59) {
            minutes = minutes - 60;
            hours++;
        }

        while (hours > 24) {
            hours = hours - 24;
            dayForAdd++;
        }
        AddDays date = new AddDays((int) workWithDate.months, (int) workWithDate.days, (int) workWithDate.years);
        date.addDaysMethods((int) dayForAdd);

        int[] fields = date.getAddDays();
        workWithDate.months = fields[0];
        workWithDate.years = fields[2];
        workWithDate.days = fields[1];
        workWithDate.second = second;
        workWithDate.minute = minute;
        workWithDate.millisecond = millisecond;
        workWithDate.output();
    }

    public void replaceHoursToMinute(long hours) {
        System.out.println("Minute = " + hours * 60);
    }

    public void addYears(WorkWithDate workWithDate, long years) {
        workWithDate.years = workWithDate.years + years;
        workWithDate.output();
    }

    public void addCenturies(WorkWithDate workWithDate, long centuries) {
        centuries = centuries * 100;
        workWithDate.years = workWithDate.years + centuries;
        workWithDate.output();
    }

    //-------------------------------------------------------------------------------


    public void subtractMilliseconds(WorkWithDate workWithDate, long milliseconds) throws ClassException {
        if (workWithDate.millisecond - milliseconds >= 0) {
            workWithDate.millisecond -= milliseconds;
            workWithDate.output();
        } else {
            throw new ClassException("Can't contain negative milliseconds");
        }
    }

    public void subtractSeconds(WorkWithDate workWithDate, long seconds) throws ClassException {
        if (workWithDate.second - seconds >= 0) {
            workWithDate.second = workWithDate.second - seconds;
            workWithDate.output();
        } else {
            throw new ClassException("Can't contain negative seconds");
        }
    }

    public void subtractMinutes(WorkWithDate workWithDate, long minutes) throws ClassException {
        if (workWithDate.minute - minutes >= 0) {
            workWithDate.minute = workWithDate.minute - minutes;
            workWithDate.output();
        } else {
            throw new ClassException("Can't contain negative minutes");
        }
    }

    public void subtractDays(WorkWithDate workWithDate, long day) throws ClassException {
        try {
            if (workWithDate.days - day >= 0) {
                workWithDate.days = workWithDate.days - day;
                workWithDate.output();
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

    public void subtractYears(WorkWithDate workWithDate, long year) throws ClassException {
        if (workWithDate.years - year >= 0) {
            workWithDate.years = workWithDate.years - year;
            workWithDate.output();
        } else {
            throw new ClassException("Can't contain negative years");
        }

    }

    public void subtractCenturies(WorkWithDate workWithDate, long centuries) throws ClassException {
        centuries = centuries * 100;
        subtractYears(workWithDate, centuries);
    }

    //----------------------------------------------------------------------------

    @Override
    public int compareTo(WorkWithDate o) {
        int res = (int) (this.years - o.years);
        if (res == 0) {
            res = (int) (this.months - o.months);
        }
        if (res == 0) {
            res = (int) (this.days - o.days);
        }
        if (res == 0) {
            res = (int) (this.minute - o.minute);
        }
        return res;
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
