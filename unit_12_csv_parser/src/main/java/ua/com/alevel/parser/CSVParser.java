package ua.com.alevel.parser;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import lombok.var;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;

public class CSVParser {

    public static String[][] getTable(){
        String[] row;
        LinkedList<String[]> rows = new LinkedList<>();
        try (InputStream input = CSVParser.class.getResourceAsStream("/cities.csv");
             var reader = new CSVReader(new InputStreamReader(input))) {
            while ((row = reader.readNext()) != null) {
                rows.addLast(row);
            }
        } catch (IOException | CsvValidationException e) {
            System.out.println("Not found");
        }
        return rows.toArray(new String[rows.size()][]);
    }
}
