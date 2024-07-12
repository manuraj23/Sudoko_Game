package com.mycompany.game;

import java.awt.*;
import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class SudokuGrid {

    private static JTextField selectedCell;
    private static JTextField[][] grid = new JTextField[9][9];
    private static final int[][] puzzle = {
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

    public static void createAndShowSudokuGrid() {
        // Create the main frame
        JFrame mainFrame = new JFrame("Sudoku Game");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(1024, 576); // Set the size to 1280x720
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setLayout(new BorderLayout());

        // Create a panel with a GridLayout 9x9 for the Sudoku grid
        JPanel gridPanel = new JPanel();
        gridPanel.setLayout(new GridLayout(9, 9));
        gridPanel.setPreferredSize(new Dimension(100, 100));
        gridPanel.setBorder(BorderFactory.createEmptyBorder(40, 20, 40, 20)); // Add border

        // Create 81 JTextFields and add them to the panel
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                grid[i][j] = new JTextField(1);
                grid[i][j].setHorizontalAlignment(JTextField.CENTER);
                grid[i][j].setFont(new Font("Arial", Font.BOLD, 24));
                if (puzzle[i][j] != 0) {
                    grid[i][j].setText(String.valueOf(puzzle[i][j]));
                    grid[i][j].setEditable(false);
                }
                grid[i][j].addFocusListener(new java.awt.event.FocusAdapter() {
                    public void focusGained(java.awt.event.FocusEvent evt) {
                        selectedCell = (JTextField) evt.getSource();
                    }
                });

                // Add KeyListener to handle arrow key navigation
                grid[i][j].addKeyListener(new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {
                        // Do nothing
                    }

                    @Override
                    public void keyPressed(KeyEvent e) {
                        int row = -1, col = -1;
                        for (int r = 0; r < 9; r++) {
                            for (int c = 0; c < 9; c++) {
                                if (grid[r][c] == selectedCell) {
                                    row = r;
                                    col = c;
                                    break;
                                }
                            }
                            if (row != -1) break;
                        }

                        if (row != -1 && col != -1) {
                            switch (e.getKeyCode()) {
                                case KeyEvent.VK_UP:
                                    if (row > 0) grid[row - 1][col].requestFocus();
                                    break;
                                case KeyEvent.VK_DOWN:
                                    if (row < 8) grid[row + 1][col].requestFocus();
                                    break;
                                case KeyEvent.VK_LEFT:
                                    if (col > 0) grid[row][col - 1].requestFocus();
                                    break;
                                case KeyEvent.VK_RIGHT:
                                    if (col < 8) grid[row][col + 1].requestFocus();
                                    break;
                            }
                        }
                    }

                    @Override
                    public void keyReleased(KeyEvent e) {
                        // Do nothing
                    }
                });

                // Set the border for cells
                if (i % 3 == 0 && j % 3 == 0) {
                    grid[i][j].setBorder(BorderFactory.createMatteBorder(2, 2, 1, 1, Color.BLACK));
                } else if (i % 3 == 0) {
                    grid[i][j].setBorder(BorderFactory.createMatteBorder(2, 0, 1, 1, Color.BLACK));
                } else if (j % 3 == 0) {
                    grid[i][j].setBorder(BorderFactory.createMatteBorder(0, 2, 1, 1, Color.BLACK));
                } else {
                    grid[i][j].setBorder(BorderFactory.createMatteBorder(0, 0, 1, 1, Color.LIGHT_GRAY));
                }

                gridPanel.add(grid[i][j]);
            }
        }

        // Create a panel for the buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10)); // Centered with some spacing

        // Create buttons
        JButton hintButton = createStyledButton("Hint");
        JButton newGameButton2 = createStyledButton("New Game");
//        JButton mainMenuButton = createStyledButton("Main Menu");
        JButton goBackButton = createStyledButton("Go Back");
        JButton validateButton = createStyledButton("Validate");

        // Add action listener for the Go Back button
        goBackButton.addActionListener(e -> {
            mainFrame.dispose(); // Close the current frame
            Game.main(null); // Open the main menu
        });

        // Add action listener for the Validate button
        validateButton.addActionListener(e -> {
            if (isSudokuSolved()) {
                int result = JOptionPane.showOptionDialog(mainFrame, "Congratulations! You won!\nDo you want to play again?", 
                    "Game Over", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null, 
                    new Object[]{"Play Again", "Main Menu"}, "Play Again");
                if (result == JOptionPane.YES_OPTION) {
                    // Reset the game or start a new game
                    mainFrame.dispose();
                    createAndShowSudokuGrid(); // Restart the game
                } else {
                    mainFrame.dispose(); // Return to main menu
                    Game.main(null);
                }
            } else {
                JOptionPane.showMessageDialog(mainFrame, "The puzzle is not solved yet. Keep trying!", 
                    "Not Solved", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        // Add the buttons to the panel
        buttonPanel.add(hintButton);
        buttonPanel.add(newGameButton2);
//        buttonPanel.add(mainMenuButton);
        buttonPanel.add(goBackButton);  // Add the Go Back button
        buttonPanel.add(validateButton);  // Add the Validate button

        // Create a panel for the numpad
        JPanel numpadPanel = new JPanel();
        numpadPanel.setLayout(new GridLayout(4, 3, 5, 5));  // Added 5-pixel gaps
        numpadPanel.setPreferredSize(new Dimension(400, 400));
        numpadPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Create numpad buttons
        for (int i = 1; i <= 12; i++) {
            if (i == 10 || i == 12) {
                // Create an empty label to fill the space at positions 10 and 12
                numpadPanel.add(new JLabel());
                continue;
            }
            if (i == 11) {
                // Create and add the Erase button at position 11
                JButton eraseButton = createStyledButton("Erase");
                eraseButton.setFont(new Font("Arial", Font.BOLD, 24));
                eraseButton.addActionListener(e -> {
                    if (selectedCell != null) {
                        selectedCell.setText("");
                    }
                });
                numpadPanel.add(eraseButton);
                continue;
            }
            // Create and add number buttons
            JButton numpadButton = createStyledButton(String.valueOf(i));
            numpadButton.setFont(new Font("Arial", Font.BOLD, 24));
            numpadButton.addActionListener(e -> {
                if (selectedCell != null) {
                    selectedCell.setText(numpadButton.getText());
                }
            });
            numpadPanel.add(numpadButton);
        }

        // Custom JPanel for background image
        JPanel backgroundPanel = new BackgroundPanel();
        backgroundPanel.setLayout(new BorderLayout());
        backgroundPanel.add(gridPanel, BorderLayout.CENTER);
        backgroundPanel.add(buttonPanel, BorderLayout.SOUTH);
        backgroundPanel.add(numpadPanel, BorderLayout.EAST);

        mainFrame.add(backgroundPanel);
        mainFrame.setVisible(true);
    }

    private static JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setPreferredSize(new Dimension(150, 50));
        button.setFont(new Font("Arial", Font.BOLD, 16));
        button.setBackground(new Color(118, 139, 56));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2)); // Set a 2px black border
        button.setOpaque(true);
        button.setBorderPainted(true); // Ensure the border is painted
        return button;
    }

    // Custom JPanel to include the background image
    private static class BackgroundPanel extends JPanel {
        private Image backgroundImage;

        public BackgroundPanel() {
            // Load the background image
            backgroundImage = new ImageIcon("/home/manu-raj/Sudoko_Game/Suduko-Project/sudoko_homepage.jpg").getImage();
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            // Draw the background image
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
    }

    // Method to check if the Sudoku puzzle is solved correctly
    private static boolean isSudokuSolved() {
        int[][] solution = {
            {5, 3, 4, 6, 7, 8, 9, 1, 2},
            {6, 7, 2, 1, 9, 5, 3, 4, 8},
            {1, 9, 8, 3, 4, 2, 5, 6, 7},
            {8, 5, 9, 7, 6, 1, 4, 2, 3},
            {4, 2, 6, 8, 5, 3, 7, 9, 1},
            {7, 1, 3, 9, 2, 4, 8, 5, 6},
            {9, 6, 1, 5, 3, 7, 2, 8, 4},
            {2, 8, 7, 4, 1, 9, 6, 3, 5},
            {3, 4, 5, 2, 8, 6, 1, 7, 9}
        };

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                String text = grid[i][j].getText();
                if (text.isEmpty() || Integer.parseInt(text) != solution[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }
}
