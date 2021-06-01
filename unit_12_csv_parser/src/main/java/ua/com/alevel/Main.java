package ua.com.alevel;

import ua.com.alevel.entity.Cities;
import ua.com.alevel.mapper.PropertyBind;
import ua.com.alevel.parser.CSVParser;
import ua.com.alevel.table.Table;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        Table table = new Table(CSVParser.getTable());
        List<Cities> citiesList = new PropertyBind().initializeProperties(Cities.class, table);
        for (Cities cities : citiesList) {
            System.out.println(cities);
        }
    }
}