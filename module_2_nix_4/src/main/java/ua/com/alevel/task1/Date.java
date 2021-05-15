package ua.com.alevel.task1;

import java.util.ArrayList;
import java.util.List;

public class Date {
    public List<String> transformDate(List<String> date) {
        List<String> changeDate = new ArrayList<>();

        for (String takeDate : date) {

            String[] dateAfterFirstCheck = takeDate.split("/");

            if (dateAfterFirstCheck[0].length() > 5) {

                String[] dateAfterSecondCheck = dateAfterFirstCheck[0].split("-");
                if (dateAfterSecondCheck[0].length() == 2) {
                    String line = dateAfterSecondCheck[2] + dateAfterSecondCheck[0] + dateAfterSecondCheck[1];
                    changeDate.add(line);
                }
            } else {

                if (dateAfterFirstCheck[0].length() == 3 || dateAfterFirstCheck[0].length() == 4) {
                    String line = dateAfterFirstCheck[0] + dateAfterFirstCheck[1] + dateAfterFirstCheck[2];
                    changeDate.add(line);
                }
                if (dateAfterFirstCheck[0].length() == 2) {
                    String line = dateAfterFirstCheck[2] + dateAfterFirstCheck[1] + dateAfterFirstCheck[0];
                    changeDate.add(line);
                }
            }
        }
        return changeDate;
    }
}
