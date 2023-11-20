import javax.swing.*;
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

    public MainMenu(CardLayout cardLayout, JPanel cards) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(Color.LIGHT_GRAY);

        add(Box.createVerticalGlue());

        JLabel headerLabel = new JLabel("Welcome, User!");
        headerLabel.setFont(new Font("Arial", Font.BOLD, 16));
        headerLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        JPanel headerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        headerPanel.setBackground(Color.LIGHT_GRAY);
        headerPanel.add(headerLabel);
        add(headerPanel);

        JLabel headerLabel2 = new JLabel("What would you like to do?");
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
                cardLayout.show(cards, "VisualizeCE");
            }
        });

        visualizeDNDataButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cards, "VisualizeDN");
            }
        });
        
    }
    
}
