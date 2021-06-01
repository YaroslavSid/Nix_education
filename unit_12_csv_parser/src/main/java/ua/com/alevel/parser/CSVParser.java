package ua.com.alevel.parser;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Objects;

public class CSVParser {
    public String[][] getTable(){
        String[] row;
        LinkedList<String[]> rows = new LinkedList<>();
        try (InputStream input = getClass().getClassLoader().getResourceAsStream("cities.csv")) {
            try (CSVReader reader = new CSVReader(new InputStreamReader(Objects.requireNonNull(input)))) {
                while ((row = reader.readNext()) != null) {
                    rows.addLast(row);
                }
            }
        } catch (IOException | CsvValidationException e) {
            System.out.println("Not found");
        }
        return rows.toArray(new String[rows.size()][]);
    }
}
