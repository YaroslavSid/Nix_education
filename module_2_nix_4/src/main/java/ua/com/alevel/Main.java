package ua.com.alevel;


import ua.com.alevel.task1.Date;
import ua.com.alevel.task2.Name;
import ua.com.alevel.task3.WorkWithFiles;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Main {
    public static void main(String[] args) {
        Date dateList = new Date();
        List<String> date = new ArrayList<>();
        date.add("2020/04/01");
        date.add("02.04.2020");
        date.add("03/04/2020");
        date.add("04::04::2020");
        date.add("04-05-2020");
        date = dateList.transformDate(date);
        for (String line : date) {
            System.out.println("data = " + line);
        }
        System.out.println("---------------------------------------------------");


        Name nameList = new Name();
        List<String> name = new ArrayList<>();
        name.add("Ali");
        name.add("Yaroslav");
        name.add("Yaroslav");
        name.add("Edd");
        name.add("Egor");
        name.add("Egor");


        Optional<String> firstUniqueName = nameList.uniqueName(name);
        if (firstUniqueName.isPresent()) {
            System.out.println("firstUniqueName = " + firstUniqueName.get());
        } else {
            System.out.println("Not found ");
        }

        System.out.println("-------------------------");
        WorkWithFiles workWithFiles = new WorkWithFiles();
        try {
            workWithFiles.run();
            System.out.println("Done");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
