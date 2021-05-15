package ua.com.alevel.task3;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class WorkWithFiles {

    public static final String OUTPUT_TXT = "module_2_nix_4/src/main/resources/output.txt";
    public static final String INPUT_TXT = "module_2_nix_4/src/main/resources/input.txt";

    Graph graph = new Graph();

    public void run() throws IOException {
        List<String> cities = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(INPUT_TXT))) {

            int citiesNumber = Integer.parseInt(reader.readLine());
            int[][] citiesCrossMap = new int[citiesNumber][citiesNumber];


            int neighboursNumber;
            for (int i = 0; i < citiesNumber; i++) {
                cities.add(reader.readLine());
                neighboursNumber = Integer.parseInt(reader.readLine());
                for (int j = 0; j < neighboursNumber; j++) {
                    String[] numbers = reader.readLine().split(" ");

                    citiesCrossMap[i][Integer.parseInt(numbers[0]) - 1] = Integer.parseInt(numbers[1]);
                }
            }

            int searchNumber = Integer.parseInt(reader.readLine());
            StringBuilder directions = new StringBuilder();
            for (int i = 0; i < searchNumber; i++) {
                directions.append(reader.readLine()).append(";");
            }

            search(cities, citiesCrossMap, searchNumber, directions.toString());
        }
    }


    private void search(List<String> cities, int[][] values, int r, String directions) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(OUTPUT_TXT))) {

            StringTokenizer tokens = new StringTokenizer(directions, ";");
            for (int j = 0; j < r; j++) {
                String[] fromTo = tokens.nextToken().split(" ");

                String temp1 = fromTo[0];
                int start = 0;
                for (int i = 0; i < cities.size(); i++) {
                    if (cities.get(i).equals(temp1)) {
                        start = i;
                    }
                }

                String temp2 = fromTo[1];
                int end = 0;
                for (int i = 0; i < cities.size(); i++) {
                    if (cities.get(i).equals(temp2)) {
                        end = i;
                    }
                }

                writer.append(graph.task(start, end, values))
                        .append("\n");
            }
        }

    }

}
