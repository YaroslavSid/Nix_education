package ua.com.alevel.services;


import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ConvertAndFindBetween {


    private static final long startYear = 1970L;

    private static final long MILLIS_IN_SECOND = 1000L;
    private static final long MILLIS_IN_MINUTE = 60_000L;
    private static final long COUNT_MS_IN_HOUR = 3600000L;
    private static final long MILLIS_IN_DAY = 86_400L;
    private static final long MILLISECONDS_IN_YEAR = 31_536_000_000L;
    private static final long MILLISECONDS_IN_LEAP_YEAR = 31_622_400_000L;

    Map<Long, Long> monthToMillis = new HashMap<>();

    {
        monthToMillis.put(1L, 2_678_400_000L);
        // skip february, it is immature
        monthToMillis.put(3L, 2_678_400_000L);
        monthToMillis.put(4L, 2_592_000_000L);
        monthToMillis.put(5L, 2_678_400_000L);
        monthToMillis.put(6L, 2_592_000_000L);
        monthToMillis.put(7L, 2_678_400_000L);
        monthToMillis.put(8L, 2_678_400_000L);
        monthToMillis.put(9L, 2_592_000_000L);
        monthToMillis.put(10L, 2_678_400_000L);
        monthToMillis.put(11L, 2_592_000_000L);
        monthToMillis.put(12L, 2_678_400_000L);
    }

    public long millis;

    // format dd/mm/yyyy mm:ss:ms
    public ConvertAndFindBetween(String date) {
        int years;
        int months;
        int days;
        int minutes;
        int seconds;
        int millis;

        String[] splitDate = date.split("[/ :]");
        days = Integer.parseInt(splitDate[0]);
        months = Integer.parseInt(splitDate[1]);
        years = Integer.parseInt(splitDate[2]);

        minutes = Integer.parseInt(splitDate[3]);
        seconds = Integer.parseInt(splitDate[4]);
        millis = Integer.parseInt(splitDate[5]);

        this.millis = getDateTimeInMillis(days, months, years, minutes, seconds, millis);
    }

    public long getMillis() {
        return millis;
    }

    public void differenceBetweenDate(ConvertAndFindBetween startDate, ConvertAndFindBetween finishDate) {
        long firstDateInMs = startDate.millis;
        long secondDateInMs = finishDate.millis;
        long differenceInMs = secondDateInMs - firstDateInMs;
        if (differenceInMs < 0) {
            differenceInMs = -(differenceInMs);
        }
        long years = (long) Math.floor(differenceInMs / (1000L * 60L * 60L * 24L * 30L * 12L));
        int months = (int) Math.floor(differenceInMs / (1000L * 60L * 60L * 24L * 30L));
        int days = (int) Math.floor((differenceInMs / MILLIS_IN_DAY) / 1000);
        int hours = (int) Math.floor((differenceInMs / COUNT_MS_IN_HOUR));
        int minutes = (int) Math.floor((differenceInMs / MILLIS_IN_MINUTE));
        long seconds = (long) Math.floor((differenceInMs / MILLIS_IN_SECOND));


        print(years, months, days, hours, minutes, seconds);
    }

    private void print(long years, long months, long days, long hours, long minutes, long seconds) {
        boolean print = true;
        while (print) {
            System.out.println("Choose type output\n" +
                    "1)years\n" +
                    "2)months\n" +
                    "3)days\n" +
                    "4)hours\n" +
                    "5)minutes\n" +
                    "6)seconds\n" +
                    "0)Exit");
            Scanner scanner = new Scanner(System.in);
            int variant = scanner.nextInt();
            switch (variant) {
                case 0:
                    print = false;
                    break;
                case 1:
                    System.out.println("years = " + years);
                    break;
                case 2:
                    System.out.println("months = " + (months));
                    break;
                case 3:
                    System.out.println("days = " + days);
                    break;
                case 4:
                    System.out.println("hours = " + hours);

                    break;
                case 5:
                    System.out.println("minutes = " + minutes);
                    break;
                case 6:
                    System.out.println("second = " + seconds);
                    break;
                default:
                    System.out.println("Try again");
            }
        }
    }


    private long getDateTimeInMillis(long days, long months, long years, long minutes, long seconds, long millis) {
        long totalMillisInYears = calcMillisInYears(years);
        long totalMillisInMonths = calcMillisInMonths(months, years);
        long totalMillisInDays = calcMillisInDays(days);
        long totalMillisInMinutes = calcMillisInMinutes(minutes);
        long totalMillisInSeconds = calcMillisInSeconds(seconds);

        return totalMillisInYears + totalMillisInMonths + totalMillisInDays + totalMillisInMinutes
                + totalMillisInSeconds + millis;
    }

    private long calcMillisInYears(long years) {
        long leapYearsNumber = countLeapYearsNumber(years - startYear);
        long usualYearsNumber = years - startYear - leapYearsNumber;

        long millisInLeapYears = leapYearsNumber * MILLISECONDS_IN_LEAP_YEAR;
        long millisInUsualYears = usualYearsNumber * MILLISECONDS_IN_YEAR;

        return millisInLeapYears + millisInUsualYears;
    }

    private long calcMillisInMonths(long months, long currentYear) {
        long totalMillisInAllMonths = 0L;

        for (long i = 1; i < months; i++) {
            if (i == 2) {
                if (isLeapYear(currentYear)) {
                    totalMillisInAllMonths += 2_505_600_000L;
                } else {
                    totalMillisInAllMonths += 2_419_200_000L;
                }
            } else {
                totalMillisInAllMonths += monthToMillis.get(i);
            }
        }

        return totalMillisInAllMonths;
    }

    private long calcMillisInDays(long days) {
        return (days - 1) * MILLIS_IN_DAY;
    }

    private long calcMillisInMinutes(long minutes) {
        return minutes * MILLIS_IN_MINUTE;
    }

    private long calcMillisInSeconds(long seconds) {
        return seconds * MILLIS_IN_SECOND;
    }

    private long countLeapYearsNumber(long years) {
        long leapYearsCount = 0;
        for (long i = 1; i <= years; i++) {
            if (isLeapYear(i)) {
                leapYearsCount++;
            }
        }
        return leapYearsCount;
    }

    private boolean isLeapYear(long year) {
        if ((year % 4) == 0) {
            if ((year % 100) == 0) {
                return (year % 400) == 0;
            } else {
                return true;
            }
        }
        return false;
    }

}
