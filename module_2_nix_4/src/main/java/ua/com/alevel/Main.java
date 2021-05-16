package ua.com.alevel;


import ua.com.alevel.task1.Date;
import ua.com.alevel.task2.Name;
import ua.com.alevel.task3.WorkWithFiles;

import java.awt.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Main {

    public static final String OUTPUT_TXT = "module_2_nix_4/src/main/resources/output.txt";

    public static void main(String[] args) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(OUTPUT_TXT))) {
            Date dateList = new Date();
            List<String> date = new ArrayList<>();
            date.add("2020/04/01");
            date.add("02.04.2020");
            date.add("03/04/2020");
            date.add("04::04::2020");
            date.add("04-05-2020");
            writer.append("Task1").append(System.lineSeparator());
            for (String condition : date) {
                writer.append(condition).append(System.lineSeparator());
            }
            date = dateList.transformDate(date);
            writer.append(System.lineSeparator());
            writer.append("answer: ").append(System.lineSeparator());
            for (String line : date) {
                writer.append(line).append(System.lineSeparator());
            }
            writer.append(System.lineSeparator());
            writer.append("----------------------------").append(System.lineSeparator());

//---------------------------------------------------------------------------------

            Name nameList = new Name();
            List<String> name = new ArrayList<>();
            name.add("Yaroslav");
            name.add("Ali");
            name.add("Yaroslav");
            name.add("Edd");
            name.add("Egor");
            name.add("Egor");
            writer.append("Task2").append(System.lineSeparator());
            for (String condition : name) {
                writer.append(condition).append(System.lineSeparator());
            }

            Optional<String> firstUniqueName = nameList.uniqueName(name);
            if (firstUniqueName.isPresent()) {
                writer.append(System.lineSeparator());
                writer.append("answer: ").append(System.lineSeparator());
                writer.append(firstUniqueName.get());
            } else {
                System.out.println("Not found ");
            }
            writer.append(System.lineSeparator());
            writer.append("----------------------------").append(System.lineSeparator());
        }

//-----------------------------------------------------------------------------


        WorkWithFiles workWithFiles = new WorkWithFiles();
        try {
            workWithFiles.run();
            System.out.println("Done");
        } catch (IOException e) {
            e.printStackTrace();
        }

        File file = new File(OUTPUT_TXT);
        Desktop desktop = Desktop.getDesktop();
        desktop.open(file);

    }
}
