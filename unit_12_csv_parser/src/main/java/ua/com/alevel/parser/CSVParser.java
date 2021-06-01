package ua.com.alevel.parser;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CSVParser {
    public String[][] getTable(String path){
        String[] row;
        List<String[]> rows = new ArrayList<>();
        try (InputStream input = getClass().getClassLoader().getResourceAsStream(path)) {
            try (CSVReader reader = new CSVReader(new InputStreamReader(Objects.requireNonNull(input)))) {
                while ((row = reader.readNext()) != null) {
                    rows.add(row);
                }
            }
        } catch (IOException | CsvValidationException e) {
            System.out.println("Not found");
        }
        return rows.toArray(new String[rows.size()][]);
    }
}
