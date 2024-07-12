/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.game;

/**
 *
 * @author manuraj
 */
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Rules {

    public static void showRules(JFrame parentFrame) {
        // Create the rules frame
        JFrame rulesFrame = new JFrame("Sudoku Rules");
        rulesFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        rulesFrame.setSize(1024, 576);
        rulesFrame.setLocationRelativeTo(null);

        // Create a panel to hold the image and the back button
        JPanel mainPanel = new JPanel(new BorderLayout());

        // Load an image icon (replace with the actual image path)
        ImageIcon rulesImageIcon = new ImageIcon("C:\\Users\\manur\\OneDrive\\Desktop\\Game\\rules.jpeg");
        Image rulesImage = rulesImageIcon.getImage();
        Image scaledRulesImage = rulesImage.getScaledInstance(rulesFrame.getWidth(), rulesFrame.getHeight() - 100, Image.SCALE_SMOOTH);
        rulesImageIcon = new ImageIcon(scaledRulesImage);
        JLabel rulesImageLabel = new JLabel(rulesImageIcon);

        // Add the image label to the panel
        mainPanel.add(rulesImageLabel, BorderLayout.CENTER);

        // Create the back button
        JButton backButton = new JButton("Back");
        backButton.setPreferredSize(new Dimension(100, 50));
        backButton.setFont(new Font("Arial", Font.BOLD, 16));
        backButton.setBackground(new Color(118, 139, 56));
        backButton.setForeground(Color.WHITE);
        backButton.setFocusPainted(false);
        backButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        backButton.setOpaque(true);
        backButton.setBorderPainted(true);

        // Add action listener to the back button to return to the parent frame
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                rulesFrame.dispose(); // Close the rules frame
                parentFrame.setVisible(true); // Show the parent frame
            }
        });

        // Create a panel for the back button and add it to the main panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(backButton);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        // Add the main panel to the rules frame
        rulesFrame.add(mainPanel);

        // Hide the parent frame and show the rules frame
        parentFrame.setVisible(false);
        rulesFrame.setVisible(true);
    }
}