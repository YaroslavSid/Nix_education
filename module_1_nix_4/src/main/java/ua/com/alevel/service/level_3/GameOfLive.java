package ua.com.alevel.service.level_3;

import ua.com.alevel.controller.TaskSelectController;

import java.util.Scanner;

public class GameOfLive {

    /*
    For check

    public static void main(String[] args) {
         GameOfLive game = new GameOfLive();
         game.generation();
     }
     */
    public void generation() {
        int M = 10, N = 10;

        int[][] grid = {{0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 1, 1, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 1, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 1, 1, 1, 0},
                {0, 0, 0, 1, 1, 0, 0, 0, 0, 0},
                {0, 0, 1, 1, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 1, 0, 0, 0, 0},
                {0, 0, 0, 0, 1, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}
        };

        System.out.println("Natural Generation");
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                if (grid[i][j] == 0)
                    System.out.print(".");
                else
                    System.out.print("*");
            }
            System.out.println();
        }
        System.out.println();
        nextGeneration(grid, M, N);

        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println();
            System.out.println("-----------------------");
            System.out.println("Return to menu: + \nExit: -");
            String menu = scanner.next();
            if ("+".equals(menu)) {
                TaskSelectController task = new TaskSelectController();
                task.menu();
            } else System.exit(0);
        }
    }

    private void nextGeneration(int[][] grid, int M, int N) {
        int[][] future = new int[M][N];

        for (int l = 1; l < M - 1; l++) {
            for (int m = 1; m < N - 1; m++) {
                int aliveNeighbours = 0;
                for (int i = -1; i <= 1; i++)
                    for (int j = -1; j <= 1; j++)
                        aliveNeighbours += grid[l + i][m + j];
                        aliveNeighbours -= grid[l][m];

                        if ((grid[l][m] == 1) && (aliveNeighbours < 2)) {
                            future[l][m] = 0;

                        } else if ((grid[l][m] == 1) && (aliveNeighbours > 3)) {
                            future[l][m] = 0;

                        } else if ((grid[l][m] == 0) && (aliveNeighbours == 3)) {
                            future[l][m] = 1;

                        } else
                            future[l][m] = grid[l][m];
                    }
        }

        System.out.println("Next Generation");
        for (int k = 0; k < M; k++) {
            for (int j = 0; j < N; j++) {
                if (future[k][j] == 0)
                    System.out.print(".");
                else
                    System.out.print("*");
            }
            System.out.println();
        }
    }
}
