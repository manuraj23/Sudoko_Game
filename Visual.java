package com.mycompany.game;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

public class Visual {
    final static int N = 9;  // Size of the Sudoku grid
    static JLabel[][] jLabel = new JLabel[N][N];
    static int[][] initialBoard = {
        {5, 3, 0, 0, 7, 0, 0, 0, 0},
        {6, 0, 0, 1, 9, 5, 0, 0, 0},
        {0, 9, 8, 0, 0, 0, 0, 6, 0},
        {8, 0, 0, 0, 6, 0, 0, 0, 3},
        {4, 0, 0, 8, 0, 3, 0, 0, 1},
        {7, 0, 0, 0, 2, 0, 0, 0, 6},
        {0, 6, 0, 0, 0, 0, 2, 8, 0},
        {0, 0, 0, 4, 1, 9, 0, 0, 5},
        {0, 0, 0, 0, 8, 0, 0, 7, 9}
    };
    static int[][] board = new int[N][N];
    static boolean stopSolving = false;

    static boolean isSafe(int row, int col, int num) {
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Check the row
        for (int x = 0; x < N; x++) {
            if (board[row][x] == num) {
                return false;
            }
        }

        // Check the column
        for (int x = 0; x < N; x++) {
            if (board[x][col] == num) {
                return false;
            }
        }

        // Check the 3x3 subgrid
        int startRow = row - row % 3, startCol = col - col % 3;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i + startRow][j + startCol] == num) {
                    return false;
                }
            }
        }

        return true;
    }

    static boolean findSolution(int row, int col) {
        if (stopSolving) return false;

        if (row == N - 1 && col == N) {
            return true;
        }

        if (col == N) {
            row++;
            col = 0;
        }

        if (board[row][col] != 0) {
            return findSolution(row, col + 1);
        }

        for (int num = 1; num <= N; num++) {
            if (isSafe(row, col, num)) {
                board[row][col] = num;
                jLabel[row][col].setText(String.valueOf(num));
                jLabel[row][col].setBackground(Color.GREEN);

                if (findSolution(row, col + 1)) {
                    return true;
                }

                board[row][col] = 0;
                jLabel[row][col].setText("0");
                jLabel[row][col].setBackground(Color.RED);
            }
        }

        return false;
    }

    static void solveSudoku() {
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < N; ++i) {
            for (int j = 0; j < N; ++j) {
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                jLabel[i][j].setText(board[i][j] == 0 ? "0" : String.valueOf(board[i][j]));
                jLabel[i][j].setBackground(Color.LIGHT_GRAY);
            }
        }

        if (!findSolution(0, 0)) {
            System.out.println("No Solution.\n");
        } else {
            printSolution();
        }
    }

    static void printSolution() {
        for (int i = 0; i < N; ++i) {
            for (int j = 0; j < N; ++j) {
                System.out.printf("%d ", board[i][j]);
            }
            System.out.printf("\n");
        }
    }

    static void loadBoard() {
        stopSolving = true;
        for (int i = 0; i < N; ++i) {
            for (int j = 0; j < N; ++j) {
                board[i][j] = initialBoard[i][j];
                jLabel[i][j].setText(board[i][j] == 0 ? "0" : String.valueOf(board[i][j]));
                if (board[i][j] != 0) {
                    jLabel[i][j].setBackground(Color.GRAY);  // Set filled cells to grey
                } else {
                    jLabel[i][j].setBackground(Color.WHITE);  // Set empty cells to white
                }
            }
        }
        stopSolving = false;
    }

    static void clearBoard() {
        stopSolving = true;
        for (int i = 0; i < N; ++i) {
            for (int j = 0; j < N; ++j) {
                board[i][j] = 0;
                jLabel[i][j].setText("0");
                jLabel[i][j].setBackground(Color.WHITE);
            }
        }
        stopSolving = false;
    }

    public static void Visualise() {
        JFrame jFrame = new JFrame("Sudoku Solver Visualizer.");
        jFrame.setLayout(new BorderLayout());
        jFrame.setSize(1024, 576);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel gridPanel = new JPanel(new GridLayout(N, N));
        for (int i = 0; i < N; ++i) {
            for (int j = 0; j < N; ++j) {
                jLabel[i][j] = new JLabel("0");
                jLabel[i][j].setHorizontalAlignment(SwingConstants.CENTER);
                jLabel[i][j].setSize(50, 50);
                jLabel[i][j].setOpaque(true);
                jLabel[i][j].setFont(new Font("Arial", Font.BOLD, 20));

                int top = (i % 3 == 0) ? 3 : 1;
                int left = (j % 3 == 0) ? 3 : 1;
                int bottom = ((i + 1) % 3 == 0) ? 3 : 1;
                int right = ((j + 1) % 3 == 0) ? 3 : 1;

                jLabel[i][j].setBorder(BorderFactory.createMatteBorder(top, left, bottom, right, Color.BLACK));

                gridPanel.add(jLabel[i][j]);
            }
        }

        JPanel controlPanel = new JPanel();
        JButton loadButton = createStyledButton("Load");
        JButton stopButton = createStyledButton("Stop");
        JButton playButton = createStyledButton("Play");
        JButton clearButton = createStyledButton("Clear");
        JButton backButton = createStyledButton("Back to Menu");

        loadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadBoard();
            }
        });

        stopButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                stopSolving = true;
            }
        });

        playButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                stopSolving = false;
                new Thread(() -> {
                    solveSudoku();
                }).start();
            }
        });

        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearBoard();
            }
        });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jFrame.dispose();  // Close the visualizer frame
                Game.main(new String[]{});  // Reopen the main menu
            }
        });

        controlPanel.add(loadButton);
        controlPanel.add(stopButton);
        controlPanel.add(playButton);
        controlPanel.add(clearButton);
        controlPanel.add(backButton);

        jFrame.add(gridPanel, BorderLayout.CENTER);
        jFrame.add(controlPanel, BorderLayout.SOUTH);

        jFrame.setLocationRelativeTo(null);  // Centers the frame on the screen
        jFrame.setVisible(true);

        loadBoard();
    }

    private static JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setPreferredSize(new Dimension(185, 50));
        button.setFont(new Font("Arial", Font.BOLD, 16));
        button.setBackground(new Color(118, 139, 56));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2)); // Set a 2px black border
        button.setOpaque(true);
        button.setBorderPainted(true); // Ensure the border is painted
        return button;
    }

    public static void main(String args[]) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                Visualise();
            }
        });
    }
}
