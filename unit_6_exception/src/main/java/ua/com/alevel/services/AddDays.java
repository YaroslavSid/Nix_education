package ua.com.alevel.services;

import java.security.InvalidParameterException;

public class AddDays {
    private static final boolean DEBUG = false;

    private static final int BASE_MONTH = 1;
    private static final int BASE_YEAR = 0;

    private int days;


    public AddDays(int month, int day, int year) {
        setAddDays(month, day, year);
    }

    public void setAddDays(int month, int day, int year) {
        if ((month >= 1) && (month <= 12)) {
        } else {
            String s = "Month not between 1 and 12";
            throw new InvalidParameterException(s);
        }

        if (year >= BASE_YEAR) {
            int temp = calculateMonthDays(month, year);
            if ((day >= 1) && (day <= temp)) {
            } else {
                String s = "Day not between 1 and " + temp;
                throw new InvalidParameterException(s);
            }

            int days = calculateYearDays(year);
            if (DEBUG) {
                System.out.println(days);
                System.out.println(temp);
            }

            days += temp;
            this.days = days + day;
            if (DEBUG) {
                System.out.println(day);
                System.out.println(this.days);
            }
        } else {
            String s = "Year before " + BASE_YEAR;
            throw new InvalidParameterException(s);
        }
    }

    public int[] getAddDays() {
        int days = this.days;
        if (DEBUG)
            System.out.println(days);

        int year = BASE_YEAR;
        int decrement = daysInYear(year);
        while (days > decrement) {
            days -= decrement;
            decrement = daysInYear(++year);
        }
        if (DEBUG)
            System.out.println(days);

        int month = BASE_MONTH;
        decrement = daysInMonth(month, year);
        while (days > decrement) {
            days -= decrement;
            decrement = daysInMonth(++month, year);
        }
        if (DEBUG)
            System.out.println(days);

        int day = days;

        int[] result = new int[3];
        result[0] = month;
        result[1] = day;
        result[2] = year;

        return result;
    }

    public void addDaysMethods(int increment) {
        this.days += increment;
    }

    private int calculateMonthDays(int month, int year) {
        int days = 0;

        for (int i = BASE_MONTH; i < month; i++) {
            days += daysInMonth(i, year);
        }

        return days;
    }

    private int calculateYearDays(int year) {
        int days = 0;

        for (int i = BASE_YEAR; i < year; i++) {
            days += daysInYear(i);
        }

        return days;
    }

    private int daysInMonth(int month, int year) {
        int[] days = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

        if (month == 2) {
            if (daysInYear(year) > 365) {
                return days[month - 1] + 1;
            }
        }

        return days[month - 1];
    }

    private int daysInYear(int year) {
        int days = 365;

        if ((year % 4) == 0) {
            if ((year % 100) == 0) {
                if ((year % 400) == 0) {
                    days++;
                }
            } else {
                days++;
            }
        }
        return days;
    }
}
