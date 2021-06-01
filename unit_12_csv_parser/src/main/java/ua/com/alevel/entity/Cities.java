package ua.com.alevel.entity;

import ua.com.alevel.Keys;

public class Cities {
    @Keys("index")
    private int id;
    @Keys("name_city")
    private String name;
    @Keys("population")
    private String population;



    @Override
    public String toString() {
        return "Cities{" +
                "index=" + id +
                ", name_city='" + name + '\'' +
                ", population_city='" + population + '\'' +
                '}';
    }

}
