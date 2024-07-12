/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.game;

/**
 *
 * @author manuraj
 */
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 *
 * @author manu-raj
 */


public class Game {

    public static void main(String[] args) {
        // Create the main frame
        JFrame frame = new JFrame("Sudoku");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Set the frame size to 1280x720 and center it
        frame.setSize(1024, 576);
        frame.setLocationRelativeTo(null);

        // Create a panel to hold the image with GridBagLayout
        JPanel imagePanel = new JPanel(new GridBagLayout());
        JLabel imageLabel = new JLabel();

        // Load an image icon (replace with the actual image path)
        ImageIcon imageIcon = new ImageIcon("C:\\Users\\manur\\OneDrive\\Desktop\\Game\\homepage.jpeg");

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
        Timer timer = new Timer(1500, actionEvent -> {
            frame.dispose(); // Close the initial frame

            // Create the second frame
            JFrame secondFrame = new JFrame("Sudoku");
            secondFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            secondFrame.setSize(1024, 576);
            secondFrame.setLocationRelativeTo(null);

            // Create a panel for the border and layout
            JPanel mainPanel = new JPanel(new BorderLayout());
            Color borderColor = new Color(118, 139, 56);

            // Set the border with different widths for each side
            mainPanel.setBorder(BorderFactory.createMatteBorder(30, 10, 30, 10, borderColor));

            // Create a left panel for the image
            JPanel leftPanel = new JPanel(new BorderLayout());

            // Load the left image
            ImageIcon leftImageIcon = new ImageIcon("/home/manu-raj/Sudoku_Game/Sudoku-Project/sudoku_homepage.jpg");
            Image leftImage = leftImageIcon.getImage();
            Image scaledLeftImage = leftImage.getScaledInstance(800, 600, Image.SCALE_SMOOTH); // Adjust the size as needed
            leftImageIcon = new ImageIcon(scaledLeftImage);
            JLabel leftImageLabel = new JLabel(leftImageIcon);

            // Add the image label to the left panel
            leftPanel.add(leftImageLabel, BorderLayout.CENTER);

            // Create a right panel for the buttons
            JPanel rightPanel = new JPanel(new GridBagLayout());

            // Create a GridBagConstraints object for positioning components
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.insets = new Insets(10, 10, 10, 10); // Add some padding around buttons
            gbc.anchor = GridBagConstraints.CENTER;
            gbc.gridx = 0;
            gbc.gridy = 0;

            // Create buttons
            JButton createGameButton = createStyledButton("Visualise");
            JButton newGameButton = createStyledButton("Play");
            JButton rulesButton = createStyledButton("Rules");

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
                SudokuGrid.createAndShowSudokuGrid(); // Open the Sudoku grid
            });

            // Add action listener to the Rules button to show the rules
            rulesButton.addActionListener(event -> {
                Rules.showRules(secondFrame); // Show the rules frame
            });

//             Add action listener to the Visualise button to show the visualiser
            createGameButton.addActionListener(event -> {
               Visual.Visualise();
            });

            // Show the second frame
            secondFrame.setVisible(true);
        });

        // Start the timer
        timer.setRepeats(false); // Only execute once
        timer.start();
    }

    private static JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setPreferredSize(new Dimension(200, 50));
        button.setFont(new Font("Arial", Font.BOLD, 16));
        button.setBackground(new Color(118, 139, 56));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2)); // Set a 2px black border
        button.setOpaque(true);
        button.setBorderPainted(true); // Ensure the border is painted
        return button;
    }
}
