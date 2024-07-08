import java.util.Scanner;

public class Sudoku_game {
    private static final int SIZE = 9; // size of the Sudoku grid
    private static final int UNASSIGNED = 0; // empty cells in the grid

    public static void main(String[] args) {
        int[][] grid = new int[SIZE][SIZE];

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the Sudoku puzzle (9x9) row by row, use 0 for empty cells:");

        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                grid[i][j] = scanner.nextInt();
            }
        }

        if (solveSudoku(grid)) {
            System.out.println("Solved Sudoku:");
            printGrid(grid);
        } else {
            System.out.println("This Sudoku puzzle cannot be solved.");
        }

        scanner.close();
    }

    private static boolean solveSudoku(int[][] grid) {
        int[] rowCol = findUnassignedLocation(grid);
        if (rowCol == null) {
            return true; // no unassigned location, puzzle solved
        }

        int row = rowCol[0];
        int col = rowCol[1];

        for (int num = 1; num <= SIZE; num++) {
            if (isSafe(grid, row, col, num)) {
                grid[row][col] = num;

                if (solveSudoku(grid)) {
                    return true;
                }

                grid[row][col] = UNASSIGNED; // backtrack
            }
        }

        return false; // triggers backtracking
    }

    private static int[] findUnassignedLocation(int[][] grid) {
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                if (grid[row][col] == UNASSIGNED) {
                    return new int[] { row, col };
                }
            }
        }
        return null; // no unassigned locations found
    }

    private static boolean isSafe(int[][] grid, int row, int col, int num) {
        return !usedInRow(grid, row, num) &&
                !usedInCol(grid, col, num) &&
                !usedInBox(grid, row - row % 3, col - col % 3, num);
    }

    private static boolean usedInRow(int[][] grid, int row, int num) {
        for (int col = 0; col < SIZE; col++) {
            if (grid[row][col] == num) {
                return true;
            }
        }
        return false;
    }

    private static boolean usedInCol(int[][] grid, int col, int num) {
        for (int row = 0; row < SIZE; row++) {
            if (grid[row][col] == num) {
                return true;
            }
        }
        return false;
    }

    private static boolean usedInBox(int[][] grid, int boxStartRow, int boxStartCol, int num) {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                if (grid[row + boxStartRow][col + boxStartCol] == num) {
                    return true;
                }
            }
        }
        return false;
    }

    private static void printGrid(int[][] grid) {
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                System.out.print(grid[row][col] + " ");
            }
            System.out.println();
        }
    }
}
