import javax.swing.*;
import javax.swing.border.MatteBorder;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

public class MainMenu extends JPanel {

    private JButton dietDataEntryButton;
    private JButton exerciseDataEntryButton;
    private JButton visualizeCEDataButton;
    private JButton visualizeDNDataButton;
    private JButton visualizeFLDataButton;
    private JButton visualizeCFGDataButton;
    private JButton backButton;

    public MainMenu(CardLayout cardLayout, JPanel cards) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(Color.LIGHT_GRAY);

        add(Box.createVerticalGlue());

        JLabel headerLabel = new JLabel("Welcome, User!");
        headerLabel.setFont(new Font("Arial", Font.BOLD, 20));
        headerLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        JPanel headerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        headerPanel.setBackground(Color.LIGHT_GRAY);
        
        headerPanel.add(headerLabel);
        add(headerPanel);

        JSeparator separator1 = new JSeparator(SwingConstants.HORIZONTAL);
        separator1.setPreferredSize(new Dimension(200, 20)); // Set your desired width and thickness here
        separator1.setMaximumSize(separator1.getPreferredSize());
        
        add(Box.createHorizontalGlue());
        add(separator1);
        add(Box.createHorizontalGlue());

        add(separator1);

        JLabel headerLabel2 = new JLabel("Log Diet & Exercise Data");
        headerLabel2.setFont(new Font("Arial", Font.PLAIN, 14));
        headerLabel2.setAlignmentX(Component.CENTER_ALIGNMENT);
        JPanel headerPanel2 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        headerPanel2.setBackground(Color.LIGHT_GRAY);
        headerPanel2.add(headerLabel2);
        add(headerPanel2);

        dietDataEntryButton = new JButton("Log My Diet Data");
        JPanel buttonPanel1 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel1.setBackground(Color.LIGHT_GRAY);
        buttonPanel1.add(dietDataEntryButton);
        add(buttonPanel1);

        exerciseDataEntryButton = new JButton("Log My Exercise Data");
        JPanel buttonPanel2 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel2.setBackground(Color.LIGHT_GRAY);
        buttonPanel2.add(exerciseDataEntryButton);
        add(buttonPanel2);

        JSeparator separator2 = new JSeparator(SwingConstants.HORIZONTAL);
        separator2.setPreferredSize(new Dimension(200, 20)); // Set your desired width and thickness here
        separator2.setMaximumSize(separator2.getPreferredSize());
        
        add(Box.createHorizontalGlue());
        add(separator2);
        add(Box.createHorizontalGlue());

        add(separator2);

        JLabel headerLabel3 = new JLabel("Visualize Data");
        headerLabel3.setFont(new Font("Arial", Font.PLAIN, 14));
        headerLabel3.setAlignmentX(Component.CENTER_ALIGNMENT);
        JPanel headerPanel3 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        headerPanel3.setBackground(Color.LIGHT_GRAY);
        headerPanel3.add(headerLabel3);
        add(headerPanel3);

        visualizeCEDataButton = new JButton("Visualize My Calorie Intake & Exercise");
        JPanel buttonPanel3 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel3.setBackground(Color.LIGHT_GRAY);
        buttonPanel3.add(visualizeCEDataButton);
        add(buttonPanel3);

        visualizeDNDataButton = new JButton("Visualize My Daily Nutrient Intake");
        JPanel buttonPanel4 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel4.setBackground(Color.LIGHT_GRAY);
        buttonPanel4.add(visualizeDNDataButton);
        add(buttonPanel4);

        visualizeFLDataButton = new JButton("Visualize My Fat Loss");
        JPanel buttonPanel5 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel5.setBackground(Color.LIGHT_GRAY);
        buttonPanel5.add(visualizeFLDataButton);
        add(buttonPanel5);

        visualizeCFGDataButton = new JButton("Visualize My Average Plate");
        JPanel buttonPanel6 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel6.setBackground(Color.LIGHT_GRAY);
        buttonPanel6.add(visualizeCFGDataButton);
        add(buttonPanel6);

        JSeparator separator3 = new JSeparator(SwingConstants.HORIZONTAL);
        separator3.setPreferredSize(new Dimension(200, 20)); // Set your desired width and thickness here
        separator3.setMaximumSize(separator3.getPreferredSize());
        
        add(Box.createHorizontalGlue());
        add(separator3);
        add(Box.createHorizontalGlue());

        add(separator3);

        
        backButton = new JButton("Back");
        JPanel buttonPanel7 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel7.setBackground(Color.LIGHT_GRAY);
        buttonPanel7.add(backButton);
        add(buttonPanel7);

        // ADD MORE BUTTONS!!!

        dietDataEntryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cards, "DietDataEntryPanel");
            }
        });

        exerciseDataEntryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cards, "ExerciseDataEntryPanel");
            }
        });

        visualizeCEDataButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            //     if ((Launcher.getDateOfMeal() != null) && (Launcher.getDateOfExercise() != null)) { 
            //         cardLayout.show(cards, "VisualizeCE");
            //     } else {
            //         JOptionPane.showMessageDialog(MainMenu.this, 
            //             "Please log your diet and exercise data before visualizing it", 
            //             "Diet & Exercise Data Error", JOptionPane.ERROR_MESSAGE);
            //     }
            // }

            cardLayout.show(cards, "VisualizeCE");

            }
        });

        visualizeDNDataButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            //     if ((Launcher.getDateOfMeal() != null) && (Launcher.getDateOfExercise() != null)) { 
            //         cardLayout.show(cards, "VisualizeDN");
            //     } else {
            //         JOptionPane.showMessageDialog(MainMenu.this, 
            //             "Please log your diet and exercise data before visualizing it", 
            //             "Diet & Exercise Data Error", JOptionPane.ERROR_MESSAGE);
            //     }
            // }

            cardLayout.show(cards, "VisualizeDN");
            }
        });

        visualizeFLDataButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            //     if ((Launcher.getDateOfMeal() != null) && (Launcher.getDateOfExercise() != null)) { 
            //         cardLayout.show(cards, "VisualizeFL");
            //     } else {
            //         JOptionPane.showMessageDialog(MainMenu.this, 
            //             "Please log your diet and exercise data before visualizing it", 
            //             "Diet & Exercise Data Error", JOptionPane.ERROR_MESSAGE);
            //     }
            // }

            cardLayout.show(cards, "VisualizeFL");
            }
        });

        visualizeCFGDataButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            //     if ((Launcher.getDateOfMeal() != null) && (Launcher.getDateOfExercise() != null)) { 
            //         cardLayout.show(cards, "VisualizeCFG");
            //     } else {
            //         JOptionPane.showMessageDialog(MainMenu.this, 
            //             "Please log your diet and exercise data before visualizing it", 
            //             "Diet & Exercise Data Error", JOptionPane.ERROR_MESSAGE);
            //     }
            // }

            cardLayout.show(cards, "VisualizeCFG");
            }
        });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cards, "MainFrameFitHealth");
            }    
        });
        
    }
    
}
