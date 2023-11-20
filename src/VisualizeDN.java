import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

public class VisualizeDN extends JPanel{

    private JPanel centralContainer;
    private JButton submitButton;
    private JButton backButton;
    private JTextField startDate;
    private JTextField endDate;

    public VisualizeDN(CardLayout cardLayout, JPanel cards) {
        setLayout(new BorderLayout());
        setBackground(Color.LIGHT_GRAY);

        centralContainer = new JPanel();
        centralContainer.setLayout(new BoxLayout(centralContainer, BoxLayout.Y_AXIS));
        centralContainer.setBackground(Color.LIGHT_GRAY);
        add(centralContainer, BorderLayout.NORTH);

        JLabel headerLabel = new JLabel("Daily Nutrient Intake Visualization");
        headerLabel.setFont(new Font("Arial", Font.BOLD, 16));

        JPanel headerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        headerPanel.setBackground(Color.LIGHT_GRAY);
        headerPanel.add(headerLabel);
        centralContainer.add(headerPanel);

        JPanel textFieldContainer = new JPanel();
        textFieldContainer.setLayout(new FlowLayout(FlowLayout.CENTER));
        textFieldContainer.setBackground(Color.LIGHT_GRAY);

        // Create text fields
        startDate = new JTextField(20);
        JPanel startPanel = createFieldPanel("Start Date (YYYY-MM-DD):", startDate);
        centralContainer.add(startPanel);

        endDate = new JTextField(20);
        JPanel endPanel = createFieldPanel("End Date (YYYY-MM-DD):", endDate);
        centralContainer.add(endPanel);

        // Add text fields to the container
        textFieldContainer.add(startPanel);
        textFieldContainer.add(endPanel);

        // Add the container to the central container
        centralContainer.add(textFieldContainer);

        submitButton = new JButton("Submit");
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.setBackground(Color.LIGHT_GRAY);
        buttonPanel.add(submitButton);

        backButton = new JButton("Back");
        buttonPanel.add(backButton);

        add(buttonPanel, BorderLayout.SOUTH);

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int i = 1;
                try {

                    LocalDate startingDate = LocalDate.parse(startDate.getText());
                    Launcher.setStartDate(startingDate);   

                    LocalDate endingDate = LocalDate.parse(endDate.getText());
                    Launcher.setEndDate(endingDate);
        
                } catch (DateTimeParseException ex) {
                    JOptionPane.showMessageDialog(VisualizeDN.this, 
                        "Invalid date format. Please enter the date in YYYY-MM-DD format.", 
                        "Date Format Error", JOptionPane.ERROR_MESSAGE);
                        i = 2;
                }

                if (i == 1) {
                    JOptionPane.showMessageDialog(null, "TODO: Display Visualization", "Success", JOptionPane.INFORMATION_MESSAGE);
                    // cardLayout.show(cards, "MainMenu");
                    resetForm();
                    }
            }
        });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cards, "MainMenu");
            }    
        });

    }

    public void resetForm() {
        startDate.setText(""); // Clear the date fields
        endDate.setText(""); 
    
        // Refresh the layout
        revalidate();
        repaint();
    }

    private JPanel createFieldPanel(String labelText, JComponent component) {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
    
        // Set constraints for the label
        gbc.gridx = 0; // Column
        gbc.gridy = 0; // Row
        gbc.gridwidth = 1; // Number of columns spanned by the component
        gbc.anchor = GridBagConstraints.CENTER; // Center alignment
        panel.add(new JLabel(labelText), gbc);
    
        // Set constraints for the input component
        gbc.gridy = 1; // Move to the next row
        gbc.insets = new Insets(5, 0, 5, 0); // Top padding
        panel.add(component, gbc);
    
        panel.setBackground(Color.LIGHT_GRAY);
        return panel;
    }
    
}
