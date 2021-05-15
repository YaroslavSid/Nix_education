package ua.com.alevel.task3;

public class Graph {

    private static final int MAX_VALUE = 200_000;

    public String task(int start, int end, int[][] values) {

        int pastKeyValue;
        int[][] weights = new int[values.length][values.length];
        for (int i = 0; i <= end; i++) {
            for (int j = 0; j <= end; j++) {
                weights[i][j] = -1;
                if (i >= start && j == start) {
                    weights[i][j] = 0;
                }
                if (i == start && j > start) {
                    weights[i][j] = MAX_VALUE;
                }
            }
        }

        int countI = start + 1;
        int countJ = start + 1;
        pastKeyValue = weights[start][start];

        do {
            int min = 0;
            int minI = 0;
            int minJ = 0;

            for (int j = countJ; j <= end; j++) {
                if (weights[countI][j] <= 0) {
                    if (values[countI - 1][j] == 0) {
                        weights[countI][j] = MAX_VALUE;
                    } else {
                        weights[countI][j] = values[countI - 1][j] + pastKeyValue;
                    }

                    if (j == countJ || min > weights[countI][j]) {
                        min = weights[countI][j];
                        minI = countI;
                        minJ = j;
                    }
                }
            }

            pastKeyValue = min;
            for (int a = minI + 1; a <= end; a++) {
                weights[a][minJ] = min;
            }

            countI++;
            countJ++;

        } while (shouldContinue(start, end, weights));

        return String.valueOf(weights[end][end]);
    }

    private boolean shouldContinue(int start, int end, int[][] weight) {
        for (int i = start; i <= end; i++) {
            for (int j = start; j < end + 1; j++) {
                if (weight[i][j] < 0) {
                    return true;
                }
            }
        }
        return false;
    }

}


