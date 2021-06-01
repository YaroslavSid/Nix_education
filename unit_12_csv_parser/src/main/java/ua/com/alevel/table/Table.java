package ua.com.alevel.table;

import java.util.Arrays;
import java.util.List;

public class Table {
    private final List<String> headlines;

    private final int rows;

    private final String[][] csvMatrix;


    public Table(String[][] cells) {
        this.headlines = Arrays.asList(cells[0]);
        this.csvMatrix = cells;
        this.rows = csvMatrix.length;
    }

    public String getCell(int indexRow, String headline) {
        return csvMatrix[indexRow + 1][headlines.indexOf(headline)];
    }

    public int sizeOfRows() {
        return rows;
    }
}
