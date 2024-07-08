package com.sudoko.suduko.project;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.MatteBorder;

public class SudukoProject {

    public static void main(String[] args) {
        // Create the main frame
        JFrame frame = new JFrame("Sudoku");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Set the frame size to 2080x720 and center it
        frame.setSize(1280, 720);
        frame.setLocationRelativeTo(null);

        // Create a panel to hold the image with GridBagLayout
        JPanel imagePanel = new JPanel(new GridBagLayout());
        JLabel imageLabel = new JLabel();

        // Load an image icon (replace with the actual image path)
        ImageIcon imageIcon = new ImageIcon("/home/manu-raj/Sudoko_Game/Suduko-Project/sudoko_homepage.jpg");

        // Scale the image to fit the frame size
        Image image = imageIcon.getImage();
        Image scaledImage = image.getScaledInstance(frame.getWidth(), frame.getHeight(), Image.SCALE_SMOOTH);
        imageIcon = new ImageIcon(scaledImage);
        imageLabel.setIcon(imageIcon);

        // Add the image label to the panel
        imagePanel.add(imageLabel);

        // Add the image panel to the frame and make it visible
        frame.add(imagePanel);
        frame.setVisible(true);

        // Set a timer to switch to the second frame after 3 seconds
        Timer timer = new Timer(1500, e -> {
            frame.dispose(); // Close the initial frame

            // Create the second frame
            JFrame secondFrame = new JFrame("Sudoku");
            secondFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            secondFrame.setSize(1280, 720);
            secondFrame.setLocationRelativeTo(null);

            // Create a panel for the border and layout
            JPanel mainPanel = new JPanel(new BorderLayout());
            Color borderColor = new Color(155, 155, 20);

// Set the border with different widths for each side
            mainPanel.setBorder(new MatteBorder(30, 10, 30, 10, borderColor));  // Green border of 20px

            
            // Create a left panel for the image
            JPanel leftPanel = new JPanel();
            leftPanel.setLayout(new BorderLayout());

            // Load the left image
            ImageIcon leftImageIcon = new ImageIcon("/home/manu-raj/Sudoko_Game/Suduko-Project/sudoko_homepage.jpg");
            Image leftImage = leftImageIcon.getImage();
            Image scaledLeftImage = leftImage.getScaledInstance(800, 720, Image.SCALE_SMOOTH); // Adjust the size as needed
            leftImageIcon = new ImageIcon(scaledLeftImage);
            JLabel leftImageLabel = new JLabel(leftImageIcon);

            // Add the image label to the left panel
            leftPanel.add(leftImageLabel, BorderLayout.CENTER);

            // Create a right panel for the buttons
            JPanel rightPanel = new JPanel();
            rightPanel.setLayout(new GridBagLayout());

            // Create a GridBagConstraints object for positioning components
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.insets = new Insets(10, 10, 10, 10); // Add some padding around buttons
            gbc.anchor = GridBagConstraints.CENTER;
            gbc.gridx = 0;
            gbc.gridy = 0;

            // Create buttons
            JButton newGameButton = new JButton("New Game");
            JButton createGameButton = new JButton("Create Your Own Game");
            JButton rulesButton = new JButton("Rules");

            // Customize button styles
            newGameButton.setPreferredSize(new Dimension(200, 50));
            createGameButton.setPreferredSize(new Dimension(200, 50));
            rulesButton.setPreferredSize(new Dimension(200, 50));

            newGameButton.setFont(new Font("Arial", Font.BOLD, 16));
            createGameButton.setFont(new Font("Arial", Font.BOLD, 16));
            rulesButton.setFont(new Font("Arial", Font.BOLD, 16));

            newGameButton.setBackground(new Color(100, 150, 250));
            createGameButton.setBackground(new Color(100, 150, 250));
            rulesButton.setBackground(new Color(100, 150, 250));

            newGameButton.setForeground(Color.WHITE);
            createGameButton.setForeground(Color.WHITE);
            rulesButton.setForeground(Color.WHITE);

            newGameButton.setFocusPainted(false);
            createGameButton.setFocusPainted(false);
            rulesButton.setFocusPainted(false);

            newGameButton.setBorder(BorderFactory.createLineBorder(new Color(80, 120, 200), 2));
            createGameButton.setBorder(BorderFactory.createLineBorder(new Color(80, 120, 200), 2));
            rulesButton.setBorder(BorderFactory.createLineBorder(new Color(80, 120, 200), 2));

            // Add buttons to the right panel in a column layout
            rightPanel.add(newGameButton, gbc);
            gbc.gridy++;
            rightPanel.add(createGameButton, gbc);
            gbc.gridy++;
            rightPanel.add(rulesButton, gbc);

            // Center the buttons in the right panel
            rightPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0)); // Remove default border

            // Add the left and right panels to the main panel
            mainPanel.add(leftPanel, BorderLayout.WEST);
            mainPanel.add(rightPanel, BorderLayout.CENTER);

            // Add the main panel to the second frame
            secondFrame.add(mainPanel);

            // Add action listener to the New Game button to show the grid
            newGameButton.addActionListener(event -> {
                secondFrame.dispose(); // Close the second frame

                // Create the grid frame
                JFrame gridFrame = new JFrame("Sudoku Grid");
                gridFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                gridFrame.setSize(1280, 720);
                gridFrame.setLocationRelativeTo(null);

                // Create a panel with a GridLayout 9x9
                JPanel gridPanel = new JPanel();
                gridPanel.setLayout(new GridLayout(9, 9));

                // Create 81 JTextFields and add them to the panel
                JTextField[][] grid = new JTextField[9][9];
                for (int i = 0; i < 9; i++) {
                    for (int j = 0; j < 9; j++) {
                        grid[i][j] = new JTextField(1);
                        grid[i][j].setHorizontalAlignment(JTextField.CENTER);
                        gridPanel.add(grid[i][j]);
                    }
                }

                // Add the grid panel to the frame
                gridFrame.add(gridPanel);
                gridFrame.setVisible(true);
            });

            // Show the second frame
            secondFrame.setVisible(true);
        });

        // Start the timer
        timer.setRepeats(false); // Only execute once
        timer.start();
    }
}
